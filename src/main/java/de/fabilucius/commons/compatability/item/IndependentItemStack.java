package de.fabilucius.commons.compatability.item;

import de.fabilucius.commons.compatability.protocol.ServerVersion;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;

public enum IndependentItemStack {

    /* all colored dyes */
    WHITE_DYE("INK_SACK", DyeColor.WHITE.getDyeData(), "WHITE_DYE", "BONE_MEAL"),
    ORANGE_DYE("INK_SACK", DyeColor.ORANGE.getDyeData(), "ORANGE_DYE"),
    MAGENTA_DYE("INK_SACK", DyeColor.MAGENTA.getDyeData(), "MAGENTA_DYE"),
    LIGHT_BLUE_DYE("INK_SACK", DyeColor.LIGHT_BLUE.getDyeData(), "LIGHT_BLUE_DYE"),
    YELLOW_DYE("INK_SACK", DyeColor.YELLOW.getDyeData(), "YELLOW_DYE", "DANDELION_YELLOW"),
    LIME_DYE("INK_SACK", DyeColor.LIME.getDyeData(), "LIME_DYE"),
    PINK_DYE("INK_SACK", DyeColor.PINK.getDyeData(), "PINK_DYE"),
    GRAY_DYE("INK_SACK", DyeColor.GRAY.getDyeData(), "GRAY_DYE"),
    LIGHT_GRAY_DYE("INK_SACK", DyeColor.LIGHT_GRAY.getDyeData(), "LIGHT_GRAY_DYE"),
    CYAN_DYE("INK_SACK", DyeColor.CYAN.getDyeData(), "CYAN_DYE"),
    PURPLE_DYE("INK_SACK", DyeColor.PURPLE.getDyeData(), "PURPLE_DYE"),
    BLUE_DYE("INK_SACK", DyeColor.BLUE.getDyeData(), "BLUE_DYE", "LAPIS_LAZULI"),
    BROWN_DYE("INK_SACK", DyeColor.BROWN.getDyeData(), "BROWN_DYE", "COCOA_BEANS"),
    GREEN_DYE("INK_SACK", DyeColor.GREEN.getDyeData(), "GREEN_DYE", "CACTUS_GREEN"),
    RED_DYE("INK_SACK", DyeColor.RED.getDyeData(), "RED_DYE", "ROSE_RED"),
    BLACK_DYE("INK_SACK", DyeColor.BLACK.getDyeData(), "BLACK_DYE", "INK_SAC"),

    PLAYER_HEAD("SKULL_ITEM", 3, "PLAYER_HEAD");

    IndependentItemStack(String oldMaterial, String newMaterial) {
        this.material = ServerVersion.isCurrentVersionHigherOrEqual(ServerVersion.v1_13) ? newMaterial : oldMaterial;
    }

    IndependentItemStack(String oldMaterial, Number oldData, String... newMaterial) {
        if (ServerVersion.isCurrentVersionHigherOrEqual(ServerVersion.v1_13)) {
            this.material = Arrays.stream(newMaterial).filter(possibleMaterial ->
                    Material.getMaterial(possibleMaterial) != null).findFirst().orElse("AIR");
        } else {
            this.material = oldMaterial;
            this.data = oldData.shortValue();
        }
    }

    private final String material;
    private Short data = Short.MIN_VALUE;

    public String getMaterial() {
        return material;
    }

    public Short getData() {
        return data;
    }

    public ItemStack toItemStack() {
        Material material = Material.getMaterial(this.getMaterial());
        if (material != null) {
            if (this.getData() == Short.MIN_VALUE) {
                return new ItemStack(material);
            } else {
                return new ItemStack(material, 1, data);
            }
        }
        return new ItemStack(Material.AIR);
    }

    public static ItemStack getItemStack(String name) {
        ItemStack itemStack = IndependentItemStack.valueOf(name).toItemStack();
        if (!itemStack.getType().equals(Material.AIR)) {
            return itemStack;
        }
        Material material = Material.getMaterial(name);
        return material == null ? new ItemStack(Material.AIR) : new ItemStack(material);
    }
}
