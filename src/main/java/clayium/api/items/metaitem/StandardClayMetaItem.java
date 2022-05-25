package clayium.api.items.metaitem;

public class StandardClayMetaItem extends ClayMetaItem<ClayMetaItem<?>.ClayMetaValueItem> {

    public StandardClayMetaItem() {
        super((short) 0);
    }

    public StandardClayMetaItem(short metaItemOffset) {
        super(metaItemOffset);
    }

    @Override
    protected ClayMetaValueItem constructMetaValueItem(short metaValue, String unlocalizedName) {
        return new ClayMetaValueItem(metaValue, unlocalizedName);
    }
}
