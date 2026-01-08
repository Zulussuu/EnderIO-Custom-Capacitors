---
navigation:
  parent: index.md
  title: KubeJS
  icon: minecraft:command_block
---

# Adding via KubeJS

Script location: `kubejs/startup_scripts/`

You can copy these examples from the CurseForge/Modrinth pages.

## Example

StartupEvents.registry("item", event => {

event.create("my_capacitor", "custom_capacitors:capacitor")
.displayName("My Capacitor")
.level(6)
.glowing(true)
.texture("minecraft:item/diamond")
.maxStackSize(16)
.rarity("epic")
.fireResistant()

})

## Methods

| Method | Description |
|--------|-------------|
| .displayName(text) | Display name |
| .level(number) | Power level |
| .glowing(true/false) | Glow effect |
| .texture(id) | Custom texture |
| .maxStackSize(number) | Stack size |
| .rarity(type) | Rarity |
| .fireResistant() | Fire immunity |
