---
description: Запустить UI-тесты и исправить падения
---

Запусти UI-тесты на подключённом устройстве или эмуляторе и исправь падения.

1. Проверь, что устройство видно:

   adb devices

2. Запусти тесты:

   export JAVA_HOME="/Applications/Android Studio.app/Contents/jbr/Contents/Home"
   ./gradlew :app:connectedDebugAndroidTest --console=plain

   Если указан конкретный тест или класс, ограничь запуск:

   ./gradlew :app:connectedDebugAndroidTest \
     -Pandroid.testInstrumentationRunnerArguments.class=$ARGUMENTS

3. Найди отчёт о падении в app/build/reports/androidTests/connected/ и разбери стектрейс.
4. Исправляй причину: код приложения, Page Object, Steps или сам тест. Не подгоняй
   тест под баг и не отключай проверки без объяснения.
5. Повторяй запуск до зелёного результата. В конце перечисли изменённые файлы.

Фокус (если указан): $ARGUMENTS
