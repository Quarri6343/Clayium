package clayium.api.items.metaitem;

import gregtech.api.items.metaitem.MetaItem;
import gregtech.api.items.metaitem.stats.IItemBehaviour;
import gregtech.api.items.metaitem.stats.IItemComponent;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Arrays;
import java.util.List;

public class ClayMetaItem<T extends ClayMetaItem<?>.ClayMetaValueItem> extends MetaItem<T> {

    public ClayMetaItem(short metaItemOffset) {
        super(metaItemOffset);
    }

    @SuppressWarnings("unchecked")
    @Override
    protected T constructMetaValueItem(short metaValue, String unlocalizedName) {
        return (T) new ClayMetaItem.ClayMetaValueItem(metaValue, unlocalizedName);
    }

    @Override
    public void addInformation(@Nonnull ItemStack itemStack, @Nullable World worldIn, @Nonnull List<String> lines, @Nonnull ITooltipFlag tooltipFlag) {
        T item = getItem(itemStack);
        if (item == null) return;

        if (item.getTier() > -1) {
            lines.add(I18n.format("gui.Common.tier", item.getTier()));
        }

        String unlocalizedTooltip = "metaitem." + item.unlocalizedName + ".tooltip";
        if (I18n.hasKey(unlocalizedTooltip)) {
            lines.addAll(Arrays.asList(I18n.format(unlocalizedTooltip).split("/n")));
        }

        for (IItemBehaviour behaviour : getBehaviours(itemStack)) {
            behaviour.addInformation(itemStack, lines);
        }

        if (tooltipFlag.isAdvanced()) {
            lines.add("MetaItem Id: " + item.unlocalizedName);
        }
    }

    public class ClayMetaValueItem extends MetaValueItem {

        private int tier = -1;

        protected ClayMetaValueItem(int metaValue, String unlocalizedName) {
            super(metaValue, unlocalizedName);
        }

        public ClayMetaItem.ClayMetaValueItem setTier(int tier) {
            this.tier = tier;
            return this;
        }

        @Override
        public ClayMetaItem.ClayMetaValueItem addComponents(IItemComponent... stats) {
            super.addComponents(stats);
            return this;
        }

        @Override
        public ClayMetaItem.ClayMetaValueItem setModelAmount(int modelAmount) {
            return (ClayMetaItem.ClayMetaValueItem) super.setModelAmount(modelAmount);
        }

        @Override
        public ClayMetaItem.ClayMetaValueItem setRarity(EnumRarity rarity) {
            return (ClayMetaItem.ClayMetaValueItem) super.setRarity(rarity);
        }

        @Override
        public ClayMetaItem<T>.ClayMetaValueItem setMaxStackSize(int maxStackSize) {
            return (ClayMetaItem.ClayMetaValueItem) super.setMaxStackSize(maxStackSize);
        }

        public int getTier() {
            return tier;
        }
    }
}
