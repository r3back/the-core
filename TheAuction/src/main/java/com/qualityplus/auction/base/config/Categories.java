package com.qualityplus.auction.base.config;

import com.cryptomorin.xseries.XMaterial;
import com.google.common.collect.ImmutableMap;
import com.qualityplus.assistant.inventory.Item;
import com.qualityplus.assistant.inventory.background.Background;
import com.qualityplus.assistant.util.itemstack.ItemBuilder;
import com.qualityplus.auction.api.category.AuctionCategory;
import com.qualityplus.auction.api.gui.GUIDisplayItem;
import eu.okaeri.configs.OkaeriConfig;
import eu.okaeri.configs.annotation.Header;
import eu.okaeri.configs.annotation.NameModifier;
import eu.okaeri.configs.annotation.NameStrategy;
import eu.okaeri.configs.annotation.Names;
import eu.okaeri.platform.core.annotation.Configuration;

import java.util.*;

@Configuration(path = "categories.yml")
@Header("================================")
@Header("       Categories      ")
@Header("================================")
@Names(strategy = NameStrategy.HYPHEN_CASE, modifier = NameModifier.TO_LOWER_CASE)
public final class Categories extends OkaeriConfig {
    public List<AuctionCategory> categoryList = Arrays.asList(
            AuctionCategory.builder()
                    .id("weapons")
                    .displayName("&6Weapons")
                    .materials(getWeapons())
                    .displayInfo(GUIDisplayItem.builder()
                            .background(getBackGround(XMaterial.ORANGE_STAINED_GLASS_PANE))
                            .description(Arrays.asList("&7Examples:", "&8■ &7Swords", "&8■ &7Bows", "&8■ &7Axes", "&8■ &7Magic Weapons"))
                            .icon(XMaterial.GOLDEN_SWORD)
                            .slot(0)
                            .build())
                    .filters(new ArrayList<>())
                    .build(),
            AuctionCategory.builder()
                    .id("armor")
                    .displayName("&bArmor")
                    .materials(getArmors())
                    .displayInfo(GUIDisplayItem.builder()
                            .background(getBackGround(XMaterial.BLUE_STAINED_GLASS_PANE))
                            .description(Arrays.asList("&7Examples:", "&8■ &7Hats", "&8■ &7Chestplates", "&8■ &7Leggings", "&8■ &7Boots"))
                            .icon(XMaterial.DIAMOND_CHESTPLATE)
                            .slot(9)
                            .build())
                    .filters(new ArrayList<>())
                    .build(),
            AuctionCategory.builder()
                    .id("accessories")
                    .displayName("&2Accessories")
                    .materials(getAccessories())
                    .displayInfo(GUIDisplayItem.builder()
                            .background(getBackGround(XMaterial.GREEN_STAINED_GLASS_PANE))
                            .description(Arrays.asList("&7Examples:", "&8■ &7Swords", "&8■ &7Bows", "&8■ &7Axes", "&8■ &7Magic Weapons"))
                            .icon(XMaterial.PLAYER_HEAD)
                            .texture("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMzE1ZjAyZWFjNjAyNmI4ZTg3MTJjYTRkNzgxYjc5MWJiYmI3YjQ3NTVhYmRhMjdmNDYyMTg5YjkwZmVkNjZhMSJ9fX0=")
                            .slot(18)
                            .build())
                    .filters(new ArrayList<>())
                    .build(),
            AuctionCategory.builder()
                    .id("consumables")
                    .displayName("&cConsumables")
                    .materials(getConsumables())
                    .displayInfo(GUIDisplayItem.builder()
                            .background(getBackGround(XMaterial.RED_STAINED_GLASS_PANE))
                            .description(Arrays.asList("&7Examples:", "&8■ &7Potions", "&8■ &7Food", "&8■ &7Books"))
                            .icon(XMaterial.APPLE)
                            .slot(27)
                            .build())
                    .filters(new ArrayList<>())
                    .build(),
            AuctionCategory.builder()
                    .id("blocks")
                    .displayName("&eBlocks")
                    .materials(getBlocks())
                    .displayInfo(GUIDisplayItem.builder()
                            .background(getBackGround(XMaterial.BROWN_STAINED_GLASS_PANE))
                            .description(Arrays.asList("&7Examples:", "&8■ &7Dirt", "&8■ &7Stone", "&8■ &7Any blocks really"))
                            .icon(XMaterial.COBBLESTONE)
                            .slot(36)
                            .build())
                    .filters(new ArrayList<>())
                    .build(),
            AuctionCategory.builder()
                    .id("tools_and_misc")
                    .displayName("&dTools and Misc")
                    .materials(getTools())
                    .displayInfo(GUIDisplayItem.builder()
                            .background(getBackGround(XMaterial.MAGENTA_STAINED_GLASS_PANE))
                            .description(Arrays.asList("&7Examples:", "&8■ &7Tools", "&8■ &7Specials", "&8■ &7Magic", "&8■ &7Staff Items"))
                            .icon(XMaterial.STICK)
                            .slot(45)
                            .build())
                    .filters(new ArrayList<>())
                    .build());


    public Optional<AuctionCategory> getById(String id){
        return categoryList.stream()
                .filter(category -> category.getId().equals(id))
                .findFirst();
    }

    private Background getBackGround(XMaterial material){
        return new Background(ImmutableMap.<Integer, Item>builder()
                .put(1, ItemBuilder.of(material, 1, " ", Collections.emptyList()).build())
                .put(2, ItemBuilder.of(material, 1, " ", Collections.emptyList()).build())
                .put(3, ItemBuilder.of(material, 1, " ", Collections.emptyList()).build())
                .put(4, ItemBuilder.of(material, 1, " ", Collections.emptyList()).build())
                .put(5, ItemBuilder.of(material, 1, " ", Collections.emptyList()).build())
                .put(6, ItemBuilder.of(material, 1, " ", Collections.emptyList()).build())
                .put(7, ItemBuilder.of(material, 1, " ", Collections.emptyList()).build())
                .put(8, ItemBuilder.of(material, 1, " ", Collections.emptyList()).build())
                .put(10, ItemBuilder.of(material, 1, " ", Collections.emptyList()).build())
                .put(19, ItemBuilder.of(material, 1, " ", Collections.emptyList()).build())
                .put(28, ItemBuilder.of(material, 1, " ", Collections.emptyList()).build())
                .put(37, ItemBuilder.of(material, 1, " ", Collections.emptyList()).build())
                .put(46, ItemBuilder.of(material, 1, " ", Collections.emptyList()).build())
                .put(47, ItemBuilder.of(material, 1, " ", Collections.emptyList()).build())
                .put(48, ItemBuilder.of(material, 1, " ", Collections.emptyList()).build())
                .put(49, ItemBuilder.of(material, 1, " ", Collections.emptyList()).build())
                .put(50, ItemBuilder.of(material, 1, " ", Collections.emptyList()).build())
                .put(51, ItemBuilder.of(material, 1, " ", Collections.emptyList()).build())
                .put(52, ItemBuilder.of(material, 1, " ", Collections.emptyList()).build())
                .put(53, ItemBuilder.of(material, 1, " ", Collections.emptyList()).build())

                .put(17, ItemBuilder.of(material, 1, " ", Collections.emptyList()).build())
                .put(26, ItemBuilder.of(material, 1, " ", Collections.emptyList()).build())
                .put(35, ItemBuilder.of(material, 1, " ", Collections.emptyList()).build())
                .put(44, ItemBuilder.of(material, 1, " ", Collections.emptyList()).build())


                .build());
    }

    private List<XMaterial> getConsumables(){
        List<XMaterial> allowed = new ArrayList<>();

        for(XMaterial material : XMaterial.VALUES){
            String parsed = material.toString().toLowerCase();
            if(parsed.contains("waxed") || parsed.contains("fishing_rod")) continue;

            if(parsed.contains("apple") || parsed.contains("bread") || parsed.contains("pumpkin") || parsed.contains("fish") || parsed.contains("pork") ||
                    parsed.contains("carrot") || parsed.contains("chicken") || parsed.contains("beef"))
                allowed.add(material);
        }
        return allowed;
    }

    private List<XMaterial> getAccessories(){
        return Collections.singletonList(XMaterial.PLAYER_HEAD);
    }

    private List<XMaterial> getTools(){
        List<XMaterial> allowed = new ArrayList<>();

        for(XMaterial material : XMaterial.VALUES){
            String parsed = material.toString().toLowerCase();
            if(parsed.contains("waxed")) continue;

            if(parsed.contains("axe") || parsed.contains("pickaxe") || parsed.contains("shovel") || parsed.contains("hoe") || parsed.contains("shears"))
                allowed.add(material);
        }
        return allowed;
    }

    private List<XMaterial> getWeapons(){
        List<XMaterial> allowed = new ArrayList<>();

        for(XMaterial material : XMaterial.VALUES){
            String parsed = material.toString().toLowerCase();
            if(parsed.contains("waxed")) continue;

            if(parsed.contains("sword"))
                allowed.add(material);
        }
        return allowed;
    }

    private List<XMaterial> getBlocks(){
        return Arrays.asList(XMaterial.OAK_LOG, XMaterial.JUNGLE_LOG, XMaterial.SPRUCE_LOG, XMaterial.DARK_OAK_LOG, XMaterial.BIRCH_LOG, XMaterial.ACACIA_LOG,
                XMaterial.DIAMOND_BLOCK, XMaterial.GOLD_BLOCK, XMaterial.IRON_BLOCK, XMaterial.COAL_BLOCK, XMaterial.LAPIS_BLOCK);
    }

    private List<XMaterial> getArmors(){
        List<XMaterial> allowed = new ArrayList<>();
        for(XMaterial material : XMaterial.VALUES){
            String parsed = material.toString().toLowerCase();

            if(parsed.contains("leggings") || parsed.contains("chestplate") || parsed.contains("boots") || parsed.contains("helmet"))
                allowed.add(material);
        }
        return allowed;
    }
}
