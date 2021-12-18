package de.fabilucius.commons.item.creation;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

public class ItemStackBuilder {

    private final ItemStack itemStack;
    private final ItemMeta itemMeta;

    public ItemStackBuilder(Material material) {
        this.itemStack = new ItemStack(material);
        this.itemMeta = this.getItemStack().getItemMeta();
    }

    public ItemStackBuilder(ItemStack itemStack) {
        this.itemStack = itemStack;
        this.itemMeta = this.getItemStack().getItemMeta();
    }

    public ItemStackBuilder setDisplayName(String displayName) {
        this.getItemMeta().setDisplayName(displayName);
        return this;
    }

    public ItemStackBuilder setLore(List<String> lore) {
        this.getItemMeta().setLore(lore);
        return this;
    }

    public ItemStackBuilder setAmount(int amount) {
        this.getItemStack().setAmount(amount);
        return this;
    }

    public ItemStackBuilder setDurability(short durability) {
        this.getItemStack().setDurability(durability);
        return this;
    }

    public ItemStackBuilder setDamage(int damage) {
        if (!(this.getItemMeta() instanceof Damageable)) {
            throw new IllegalStateException("Cannot add damage to an ItemStack whose ItemMeta isn't instance of Damageable.");
        }
        Damageable damageableItemMeta = (Damageable) this.getItemMeta();
        damageableItemMeta.setDamage(damage);
        return this;
    }

    public ItemStackBuilder setUnbreakable() {
        this.getItemMeta().setUnbreakable(true);
        return this;
    }

    public ItemStackBuilder addEnchantment(Enchantment enchantment, int level) {
        this.getItemMeta().addEnchant(enchantment, level, true);
        return this;
    }

    public ItemStack build() {
        this.getItemStack().setItemMeta(this.getItemMeta());
        return this.getItemStack();
    }

    /**
     * Returns the <b>ItemStack</b> that is modified by this builder.
     * It's strongly recommended to make use of the {@link ItemStackBuilder#build()} method instead to make sure that every
     * necessary changes to the <b>ItemStack</b> are made.
     *
     * @return the ItemStack that gets modified by this builder
     */
    @Deprecated
    public ItemStack getItemStack() {
        return itemStack;
    }

    private ItemMeta getItemMeta() {
        return itemMeta;
    }
}
