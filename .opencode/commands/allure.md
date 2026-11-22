---
description: Запустить UI-тесты и собрать Allure-отчёт
---

Запусти UI-тесты и собери Allure-отчёт.

1. Проверь устройство:

   "$HOME/Library/Android/sdk/platform-tools/adb" devices

2. Если указан класс или метод — запусти только его:

   ./scripts/allure.sh --class $ARGUMENTS

   Иначе запусти все UI-тесты:

   ./scripts/allure.sh --run

3. Если тесты падают — разбери `build/instrumentation.log` и отчёт
   `build/allure-report/index.html`, исправь причину и повтори запуск.
4. В конце покажи, сколько результатов собрано, путь к отчёту и статус тестов.
   Не открывай браузер без просьбы.
