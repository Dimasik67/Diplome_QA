---
description: Проверить сборку и lint без правок кода
---

Проверь проект, ничего не исправляя без подтверждения:

1. Сборка:

   export JAVA_HOME="/Applications/Android Studio.app/Contents/jbr/Contents/Home"
   ./gradlew :app:assembleDebug --console=plain

2. Lint:

   ./gradlew :app:lintDebug --console=plain

3. Покажи список ошибок и важных предупреждений, сгруппируй по файлам и предложи
   план исправления. Отчёт lint: app/build/reports/lint-results-debug.html
