package de.fabilucius.commons.item.creation;

import de.fabilucius.commons.compatability.item.IndependentItemStack;
import org.bukkit.OfflinePlayer;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

public class SkullBuilder {

    private final ItemStack itemStack;
    private final SkullMeta skullMeta;

    public SkullBuilder() {
        this.itemStack = IndependentItemStack.getItemStack("PLAYER_HEAD");
        if (!(itemStack.getItemMeta() instanceof SkullMeta)) {
            throw new IllegalStateException("Cannot create a Skull ItemStack when the ItemStack's ItemMeta isn't instance of SkullMeta.");
        }
        this.skullMeta = (SkullMeta) this.getItemStack().getItemMeta();
    }

    public SkullBuilder setOwner(String name) {
        this.getSkullMeta().setOwner(name);
        return this;
    }

    public SkullBuilder setOwningPlayer(OfflinePlayer owningPlayer) {
        this.getSkullMeta().setOwningPlayer(owningPlayer);
        return this;
    }

    public ItemStackBuilder toItemStackBuilder() {
        return new ItemStackBuilder(this.build());
    }

    public ItemStack build() {
        this.getItemStack().setItemMeta(this.getSkullMeta());
        return this.getItemStack();
    }

    public ItemStack getItemStack() {
        return itemStack;
    }

    private SkullMeta getSkullMeta() {
        return skullMeta;
    }
}
