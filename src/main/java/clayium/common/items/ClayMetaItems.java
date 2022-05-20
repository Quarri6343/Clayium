package clayium.common.items;

import gregtech.api.items.metaitem.MetaItem;

public final class ClayMetaItems {

    private ClayMetaItems() {
    }

    public static MetaItem<?>.MetaValueItem CLAYTASK_CUTCIRCLE;
    public static MetaItem<?>.MetaValueItem CLAYTASK_CUTSQUARE;
    public static MetaItem<?>.MetaValueItem CLAYTASK_PAT;
    public static MetaItem<?>.MetaValueItem CLAYTASK_PRESS;
    public static MetaItem<?>.MetaValueItem CLAYTASK_ROLL;
    public static MetaItem<?>.MetaValueItem CLAYTASK_SLICE;
//
//    public static ArmorMetaItem<?>.ArmorMetaValueItem PISTON_BOOTS;

    public static void init() {
        ClayMetaItem1 metaItem1 = new ClayMetaItem1();
        metaItem1.setRegistryName("meta_item_1");
//        GTEMetaArmor armor = new GTEMetaArmor();
//        armor.setRegistryName("gte_armor");
    }
}
