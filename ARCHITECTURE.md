# NOVA TV Architecture

```text
Android TV
   |
   +-- Source UI
   |     +-- M3U/M3U8
   |     +-- Stalker/Ministra
   |
   +-- Normalized Content Model
   |     +-- Channel
   |     +-- Movie
   |     +-- Series/Episode
   |     +-- EPG Program
   |
   +-- Player (Media3)
   |
   +-- API
         +-- PostgreSQL
         +-- Watch progress
         +-- Source metadata
         +-- Optional account/sync
```

External playlist/portal credentials must be treated as secrets. Never log passwords, MACs or bearer tokens.
