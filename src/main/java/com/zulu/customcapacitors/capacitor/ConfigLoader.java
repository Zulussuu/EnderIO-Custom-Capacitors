package com.zulu.customcapacitors.capacitor;

import com.electronwill.nightconfig.core.file.CommentedFileConfig;
import com.electronwill.nightconfig.core.Config;
import com.zulu.customcapacitors.CustomCapacitors;
import com.zulu.customcapacitors.item.EnergyCapacitorItem;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import java.nio.file.*;
import java.util.*;

public class ConfigLoader {
    private static final Path CFG = Paths.get("config/custom_capacitors.toml");
    private static final List<CapData> DATA = new ArrayList<>();
    private static final Map<String, RegistryObject<EnergyCapacitorItem>> ITEMS = new HashMap<>();

    public record CapData(String id, String name, float level, boolean glow, int color) {
    }

    public static void load() {
        try {
            if (!Files.exists(CFG)) {
                Files.writeString(CFG, """
                        # Custom Capacitors Config
                        # Remove # to enable, add # to disable
                        # EnderIO default capacitors: Basic=1, Double-Layer=2, Octadic=3
                        # level = can be any number like 3.5, 4.2, or 6.39, not just whole numbers!
                        # color = hex RGB (e.g. FF0000 = red, 00FF00 = green)
                        # To add more capacitors, copy one of the entries below and edit the values

                        [[capacitor]]
                        id = "tier4_capacitor"
                        name = "Tier 4 Capacitor"
                        level = 4
                        glow = false
                        color = "4488FF"

                        [[capacitor]]
                        id = "tier5_capacitor"
                        name = "Tier 5 Capacitor"
                        level = 5
                        glow = false
                        color = "FF8844"

                        [[capacitor]]
                        id = "tier6_capacitor"
                        name = "Tier 6 Capacitor"
                        level = 6
                        glow = true
                        color = "FF44FF"

                        # [[capacitor]]
                        # id = "mythril_capacitor"
                        # name = "Mythril Capacitor"
                        # level = 5
                        # glow = true
                        # color = "44FFFF"

                        # [[capacitor]]
                        # id = "half_tier_capacitor"
                        # name = "Half Tier Capacitor"
                        # level = 3.5
                        # glow = false
                        # color = "88FF88"

                        # [[capacitor]]
                        # id = "precision_capacitor"
                        # name = "Precision Capacitor"
                        # level = 7.25
                        # glow = true
                        # color = "FFAA00"
                        """);
            }

            CommentedFileConfig cfg = CommentedFileConfig.of(CFG.toFile());
            cfg.load();

            List<Config> caps = cfg.getOrElse("capacitor", Collections.emptyList());
            for (Config c : caps) {
                String id = c.get("id");
                String name = c.get("name");
                float level = ((Number) c.getOrElse("level", 1.0)).floatValue();
                boolean glow = c.getOrElse("glow", false);
                String colorStr = c.getOrElse("color", "FFFFFF");
                int color = Integer.parseInt(colorStr.replace("#", ""), 16);

                if (id != null && name != null) {
                    DATA.add(new CapData(id, name, level, glow, color));
                }
            }
            cfg.close();
            CustomCapacitors.LOGGER.info("[CustomCapacitors] Loaded {} capacitors from config", DATA.size());
        } catch (Exception e) {
            CustomCapacitors.LOGGER.error("[CustomCapacitors] Failed to load config", e);
        }
    }

    public static void register(DeferredRegister<Item> reg) {
        for (CapData d : DATA) {
            ITEMS.put(d.id, reg.register(d.id, () -> new EnergyCapacitorItem(
                    new Item.Properties().stacksTo(64), d.level, d.name, d.glow, d.color)));
        }
    }

    public static Collection<RegistryObject<EnergyCapacitorItem>> getItems() {
        return ITEMS.values();
    }

    public static List<CapData> getData() {
        return DATA;
    }
}