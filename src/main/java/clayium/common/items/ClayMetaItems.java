package clayium.common.items;

import gregtech.api.items.metaitem.MetaItem;
import gregtech.api.items.toolitem.ToolMetaItem;
import gregtech.common.items.MetaTool;

public final class ClayMetaItems {

    private ClayMetaItems() {
    }

    public static MetaItem<?>.MetaValueItem CLAYTASK_CUTCIRCLE;
    public static MetaItem<?>.MetaValueItem CLAYTASK_CUTSQUARE;
    public static MetaItem<?>.MetaValueItem CLAYTASK_PAT;
    public static MetaItem<?>.MetaValueItem CLAYTASK_PRESS;
    public static MetaItem<?>.MetaValueItem CLAYTASK_ROLL;
    public static MetaItem<?>.MetaValueItem CLAYTASK_SLICE;

    public static ToolMetaItem<?>.MetaToolValueItem CLAY_SHOVEL;
    public static ToolMetaItem<?>.MetaToolValueItem CLAY_PICKAXE;
//
//    public static ArmorMetaItem<?>.ArmorMetaValueItem PISTON_BOOTS;

    public static void init() {
        ClayMetaItem1 metaItem1 = new ClayMetaItem1();
        metaItem1.setRegistryName("meta_item_1");
        ClayMetaTool tool = new ClayMetaTool();
        tool.setRegistryName("meta_tool");
//        GTEMetaArmor armor = new GTEMetaArmor();
//        armor.setRegistryName("gte_armor");
    }
}
