---
description: Исправить конкретную ошибку из Build Output или Logcat
---

Вот ошибка из Android Studio, Gradle или Logcat:

$ARGUMENTS

Найди первопричину, исправь минимальным изменением и затем пересобери проект:

export JAVA_HOME="/Applications/Android Studio.app/Contents/jbr/Contents/Home"
./gradlew :app:assembleDebug --console=plain

Если ошибка воспроизводится только в рантайме, объясни, как её проверить
(adb logcat, шаги в приложении), и что именно изменено.
