#!/usr/bin/env bash
# Сборка UI-тестов, запуск на устройстве и генерация Allure-отчёта.
set -euo pipefail

PROJECT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")/.." && pwd)"
APP_APK="$PROJECT_DIR/app/build/outputs/apk/debug/app-debug.apk"
TEST_APK="$PROJECT_DIR/app/build/outputs/apk/androidTest/debug/app-debug-androidTest.apk"
RESULTS_DIR="$PROJECT_DIR/build/allure-results"
REPORT_DIR="$PROJECT_DIR/build/allure-report"
LOG_FILE="$PROJECT_DIR/build/instrumentation.log"
PACKAGE="${ALLURE_TEST_PACKAGE:-ru.edu.qamid}"
TEST_RUNNER="${ALLURE_TEST_RUNNER:-androidx.test.runner.AndroidJUnitRunner}"
ADB="${ADB:-$HOME/Library/Android/sdk/platform-tools/adb}"
ALLURE_VERSION="2.46.2"

usage() {
    cat <<EOF
Использование: scripts/allure.sh [опции]

Опции:
  --run              собрать APK, установить их и прогнать UI-тесты
  --class CLASS      прогнать только указанный класс (можно CLASS#метод), включает --run
  --open             открыть готовый отчёт в браузере
  -h, --help         показать эту справку

Без опций скрипт выгружает уже имеющиеся на устройстве результаты
и генерирует отчёт в build/allure-report.

Запуск идёт через adb shell am instrument, а не через connectedDebugAndroidTest:
Gradle-задача после прогона удаляет приложение вместе с results-директорией.
EOF
}

find_allure() {
    if [[ -n "${ALLURE_HOME:-}" && -x "$ALLURE_HOME/bin/allure" ]]; then
        echo "$ALLURE_HOME/bin/allure"
    elif command -v allure >/dev/null 2>&1; then
        command -v allure
    elif [[ -x "$HOME/.local/allure/allure-$ALLURE_VERSION/bin/allure" ]]; then
        echo "$HOME/.local/allure/allure-$ALLURE_VERSION/bin/allure"
    else
        echo "Allure CLI не найден. Установите Allure $ALLURE_VERSION или задайте ALLURE_HOME." >&2
        exit 1
    fi
}

run_tests=0
test_class=""
open_report=0
while [[ $# -gt 0 ]]; do
    case "$1" in
        --run)
            run_tests=1
            shift
            ;;
        --class)
            [[ $# -ge 2 ]] || { echo "Не указан класс тестов" >&2; exit 1; }
            test_class="$2"
            run_tests=1
            shift 2
            ;;
        --open)
            open_report=1
            shift
            ;;
        -h|--help)
            usage
            exit 0
            ;;
        *)
            echo "Неизвестный аргумент: $1" >&2
            usage >&2
            exit 1
            ;;
    esac
done

[[ -x "$ADB" ]] || { echo "adb не найден: $ADB" >&2; exit 1; }
"$ADB" get-state >/dev/null 2>&1 || {
    echo "Подключите Android-устройство или запустите эмулятор." >&2
    exit 1
}
allure="$(find_allure)"
mkdir -p "$PROJECT_DIR/build"

if [[ $run_tests -eq 1 ]]; then
    export JAVA_HOME="${JAVA_HOME:-/Applications/Android Studio.app/Contents/jbr/Contents/Home}"
    echo "Сборка APK..."
    (cd "$PROJECT_DIR" && ./gradlew :app:assembleDebug :app:assembleDebugAndroidTest --console=plain)

    echo "Установка APK на устройство..."
    "$ADB" install -r "$APP_APK" >/dev/null
    "$ADB" install -r "$TEST_APK" >/dev/null
    "$ADB" shell run-as "$PACKAGE" rm -rf files/allure-results || true

    instrument_args=(-w)
    if [[ -n "$test_class" ]]; then
        instrument_args+=(-e class "$test_class")
    fi

    echo "Запуск тестов..."
    set +e
    "$ADB" shell am instrument "${instrument_args[@]}" \
        "$PACKAGE.test/$TEST_RUNNER" 2>&1 | tee "$LOG_FILE"
    adb_status=${PIPESTATUS[0]}
    set -e

    if grep -qE 'FAILURES!!!|Process crashed|INSTRUMENTATION_CODE: -1' "$LOG_FILE"; then
        test_status=1
    else
        test_status=$adb_status
    fi
else
    test_status=0
fi

echo "Выгрузка результатов Allure с устройства..."
if ! "$ADB" shell run-as "$PACKAGE" ls files/allure-results >/dev/null 2>&1; then
    echo "Результаты Allure на устройстве не найдены: проверьте, что тесты действительно запустились." >&2
    exit 1
fi
rm -rf "$RESULTS_DIR" "$REPORT_DIR"
"$ADB" exec-out run-as "$PACKAGE" sh -c \
    "cd /data/data/$PACKAGE/files && tar cf - allure-results" | tar xf - -C "$PROJECT_DIR/build"

result_count="$(find "$RESULTS_DIR" -name '*-result.json' 2>/dev/null | wc -l | tr -d ' ')"
[[ "$result_count" -gt 0 ]] || {
    echo "Результаты Allure не найдены. Сначала запустите тесты: scripts/allure.sh --run" >&2
    exit 1
}
echo "Найдено результатов: $result_count"

echo "Генерация отчёта..."
"$allure" generate "$RESULTS_DIR" -o "$REPORT_DIR" --clean
echo "Отчёт готов: $REPORT_DIR/index.html"

if [[ $open_report -eq 1 ]]; then
    "$allure" open "$REPORT_DIR"
fi

exit "$test_status"
