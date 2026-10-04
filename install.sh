#!/usr/bin/env bash
set -euo pipefail
ROOT="$(cd "$(dirname "$0")" && pwd)"
command -v docker >/dev/null || { echo "Docker is required: https://docs.docker.com/get-docker/"; exit 1; }
cd "$ROOT/backend"
if [ ! -f .env ]; then
  printf 'POSTGRES_PASSWORD=%s\n' "$(openssl rand -hex 24 2>/dev/null || echo 'change-me-now')" > .env
fi
docker compose --env-file .env up -d --build
echo
echo "NOVA backend installed."
echo "Health: http://localhost:8080/health"
echo "API:    http://localhost:8080/api/v1"
echo
echo "Android TV: open android-tv/ in Android Studio and Run on an Android TV/Google TV device or emulator."
