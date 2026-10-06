# Summit Mod - Agent Guide

## Project Overview
Summit is a modern, vanilla-friendly Fabric mod (reimagining of Apotheosis) for Minecraft 26.3.
- Mod ID: `summit`
- Package: `com.summit`
- Dependencies: Fabric API, ModMenu, YACL (YetAnotherConfigLib v3.9.6+26.3-fabric)
- Core philosophy: Everything customizable must be configurable in-game via YACL

## Build & Version Info
- Minecraft: 26.3
- Fabric Loader: 0.19.5
- Fabric Loom: 1.17-SNAPSHOT
- Fabric API: 0.161.0+26.3
- YACL: 3.9.6+26.3-fabric
- Java: 25 (source/target compatibility)

## Key Files
- Main mod entry: `src/main/java/com/summit/Summit.java`
- Config: `src/main/java/com/summit/config/SummitConfig.java`
- Client config screen: `src/client/java/com/summit/config/SummitConfigScreen.java`
- Mixins: `src/main/java/com/summit/mixin/`
- Items registry: `src/main/java/com/summit/registry/SummitItems.java`
- Fabric mod JSON: `src/main/resources/fabric.mod.json`
- Lang: `src/main/resources/assets/summit/lang/en_us.json`
- Config file: `config/summit.json` (Gson, server-authoritative)

## Module Plan
1. **Spawner Tweaks** (current focus) - Mixin on BaseSpawner, config-driven
2. **Enchanting Tweaks** - Capturing enchantment, loot table additions
3. **Difficulty Scaling** - Mobs scale with player power (30-40% stronger when player is 100% stronger)

## Code Conventions
- Use `Summit.id(path)` for identifiers
- Mixins use `@Inject` with `cancellable = true` where needed
- Config values accessed via `SummitConfig.get()`
- YACL options built in `SummitConfigScreen.create()`
- Server config is authoritative; client screen edits same config file

## Important Notes
- Config is saved on load to create documented defaults
- Spawner mixin applies config in `serverTick` HEAD, cancels if redstone control enabled and no signal
- LivingEntityMixin adds Capturing enchantment spawn-egg drop on death
- SugarCaneBlockMixin overrides `getBlocksToGrowUpTo` for configurable height
- Data generator entrypoint exists but is empty (for future data gen)
- Ref folder contains Minecraft 26.3 client source and fabric docs

## Current Session Notes - 2026-10-06

### Completed
- Renamed mod from Apotheos-ish to Summit (package, mod ID, resources, mixins)
- Pushed rename to GitHub (https://github.com/GlitchSlayed/Apotheos-ish)
- Updated README.md for GitHub
- Created spawner module skeleton in `src/main/java/com/summit/spawner/`
- Added SpawnerModuleSettings to SummitConfig
- Updated lang file with spawner keys
- Created entity blacklist tag at `data/c/tags/entity/blacklisted_from_spawners.json`

### Blockers / Next Steps
- `./gradlew build` compiles successfully (all compilation errors fixed)
- Remaining checklist:
  - Silk touch harvesting for spawners
  - Spawner persistence through world saves
  - Despawn grace period
  - Spawner item tooltips with stats
  - Advancements JSONs
  - Push changes to GitHub after completing remaining features
