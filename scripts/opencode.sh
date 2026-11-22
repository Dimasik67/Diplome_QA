#!/usr/bin/env bash
# Запуск OpenCode в этом проекте с правильным JDK.
set -euo pipefail

export JAVA_HOME="${JAVA_HOME:-/Applications/Android Studio.app/Contents/jbr/Contents/Home}"

if ! "$JAVA_HOME/bin/java" -version >/dev/null 2>&1; then
  echo "JDK не найден: $JAVA_HOME" >&2
  echo "Укажите JAVA_HOME или установите Android Studio." >&2
  exit 1
fi

if command -v opencode >/dev/null 2>&1; then
  exec opencode "$@"
fi

exec "/Applications/OpenCode.app/Contents/Resources/opencode-cli" "$@"
