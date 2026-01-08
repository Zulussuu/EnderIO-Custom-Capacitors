---
navigation:
  parent: index.md
  title: Config File
  icon: minecraft:writable_book
---

# Adding via Config

Config file location: `config/custom_capacitors.toml`

The config file already has examples. Remove the `#` at the start of lines to enable them.

## Example

```toml
[[capacitor]]
id = "mega_capacitor"
name = "Mega Capacitor"
level = 10
glow = true
color = "0xFF00FF"
```

## Parameters

**id**: Unique ID using lowercase and underscores

**name**: Display name shown in game

**level**: Power multiplier (1 = Basic, 2 = Double Layer, 3 = Octadic)

**glow**: Enchant glow effect (true or false)

**color**: Hex color code like 0xRRGGBB
