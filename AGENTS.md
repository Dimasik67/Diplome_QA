# AGENTS.md — FMH Android

## Проект

«Мобильный хоспис» — учебный дипломный Android-проект (Netology).
Один модуль `:app`, namespace и applicationId — `ru.edu.qamid`.

## Стек

- Kotlin 2.1.21, AGP 8.13.2, Gradle 8.13, JDK 21
- compileSdk/targetSdk 36, minSdk 23
- MVVM: ViewModel + LiveData, Navigation Component (Safe Args), ViewBinding
- Hilt (kapt), Room (kapt), Retrofit + Gson + OkHttp logging
- Material Components, RecyclerView, SwipeRefreshLayout, Coroutines

## Сборка и проверки

Gradle требует JDK 21. В терминале используйте JBR из Android Studio:

```bash
export JAVA_HOME="/Applications/Android Studio.app/Contents/jbr/Contents/Home"
```

- Сборка debug: `./gradlew :app:assembleDebug`
- Сборка release: `./gradlew :app:assembleRelease`
- Lint: `./gradlew :app:lintDebug`
- UI-тесты (нужен подключённый эмулятор или устройство):
  `./gradlew :app:connectedDebugAndroidTest`
- Один UI-тест:
  `./gradlew :app:connectedDebugAndroidTest -Pandroid.testInstrumentationRunnerArguments.class=ru.edu.qamid.fmhandroid.ui.test.AuthorizationTest`

После изменений в Kotlin/XML запускайте `:app:assembleDebug`. После правок
UI-тестов запускайте соответствующий `connectedDebugAndroidTest`.

## Структура

`app/src/main/java/ru/edu/qamid/`:

- `api/` — Retrofit-сервисы
- `dto/`, `entity/` — сетевые и Room-модели
- `dao/`, `db/` — Room
- `repository/` — репозитории
- `viewmodel/` — ViewModel
- `ui/` — Activity, фрагменты, навигация
- `adapter/`, `utils/`, `extensions/`, `auth/`, `exceptions/`, `enum/`

`app/src/androidTest/java/ru/edu/qamid/fmhandroid/ui/` — UI-тесты:
`pages/` (Page Object), `steps/`, `test/`, `data/`, `matchers/`.

## Правила кода

- Пакеты — в нижнем регистре без подчёркиваний.
- Классы — PascalCase, функции и свойства — camelCase.
- Приватное свойство с публичным API — с префиксом `_` (`_elementList`).
- Enum-константы — `SCREAMING_SNAKE_CASE`.
- XML: snake_case, имя заканчивается типом view
  (`line_divider_image_view`, `date_not_set`).
- Тексты интерфейса — на русском, в существующем стиле.
- Новые экраны и тесты оформляйте по текущим паттернам проекта
  (MVVM, ViewBinding, Page Object + Steps для UI-тестов).
- URL бэкенда берите из `BuildConfig.BASE_URL`, не хардкодьте.

## Ограничения

- Не менять `applicationId`, `versionCode`, `versionName` без явной просьбы.
- Не трогать `local.properties`, `.idea/`, `build/`, `.gradle/`, `.kotlin/`.
- Не обновлять версии Gradle/AGP/зависимостей без явной просьбы.
- Не исправлять несвязанные предупреждения (deprecated API) в рамках другой задачи.
- `app/gradle/wrapper` удалён — используйте `./gradlew` из корня проекта.
- Не коммитить и не пушить без явной просьбы.

Дополнительные правила именования — в `README.md`.
