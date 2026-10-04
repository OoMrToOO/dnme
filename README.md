# NOVA TV

Netflix-style media discovery + TiviMate-style IPTV experience for Android TV / Google TV.

## Sources
- M3U / M3U8
- Stalker / Ministra Portal

## Features in MVP
- Home, Live TV, Movies, Series, Favorites
- M3U parsing and import
- Stalker handshake/profile/categories/channels/VOD/series/link endpoints
- XMLTV EPG import
- PostgreSQL persistence
- Watch progress and favorites API
- Media3 player
- Docker Compose backend

## Install backend
```bash
./install.sh
```

Backend health: `http://localhost:8080/health`

## Android TV
Open `android-tv/` in Android Studio. Use an Android TV / Google TV emulator or device.
Set the API base URL in the app's `BuildConfig`/configuration before production use.

## Important
The application is a player/client. It does not provide channels, subscriptions, or copyrighted streams. Use only playlists and portal credentials you are authorized to use.
