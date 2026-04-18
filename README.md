# NSGL (No Steam Global List)

A small Java Mindustry mod that lets you opt in or out (default=out) of Mindustry's global Steam ban and Steam admin lists.

By default, this mod keeps both lists disabled and exposes toggles in the in-game settings so you can decide whether they should apply to your server.

## What It Does

Mindustry can fetch global Steam ID lists that are used to ban players automatically and more concerningly, grant admin privileges automatically/
This is done entirely without the player's consent.
This mod replaces those list checks with user-configurable versions controlled from the settings menu.

## In-Game Settings

After installing the mod, open `Settings -> No Steam Global List`

Available options:

- `bans`: enable or disable the global Steam banlist
- `admins`: enable or disable the global Steam admin-list
- `Hide NSGL No-Steam Warning`: suppress the warning shown when the mod is loaded on a non-Steam instance (useful for players who use both vanilla and Steam versions.)

Both `bans` and `admins` default to `off`.

## Steam-Only Behavior

This mod only affects Steam builds of Mindustry.

On non-Steam instances, Mindustry does not load the Steam global lists, so the mod stops early and optionally shows a warning message.

## Build

Requirements: Java 17

Build the jar with:

```bash
./gradlew jar
```

Output: `build/libs/no-steam-global-list.jar`

## Manually Install

1. Build the jar.
2. Import `build/libs/no-steam-global-list.jar` into Mindustry.
3. Restart the game for changes to take effect.
4. Open `Settings -> No Steam Global List` and choose which lists you want enabled.
