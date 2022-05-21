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

    public static MetaItem<?>.MetaValueItem CLAY_PLATE;
    public static MetaItem<?>.MetaValueItem CLAY_STICK;
    public static MetaItem<?>.MetaValueItem CLAY_STICK_SHORT;
    public static MetaItem<?>.MetaValueItem CLAY_RING;
    public static MetaItem<?>.MetaValueItem CLAY_RING_SMALL;
    public static MetaItem<?>.MetaValueItem CLAY_GEAR;
    public static MetaItem<?>.MetaValueItem CLAY_BLADE;
    public static MetaItem<?>.MetaValueItem CLAY_NEEDLE;
    public static MetaItem<?>.MetaValueItem CLAY_DISC;
    public static MetaItem<?>.MetaValueItem CLAY_DISC_SMALL;
    public static MetaItem<?>.MetaValueItem CLAY_CYLINDER;
    public static MetaItem<?>.MetaValueItem CLAY_PIPE;
    public static MetaItem<?>.MetaValueItem CLAY_BALL_LARGE;
    public static MetaItem<?>.MetaValueItem CLAY_PLATE_LARGE;
    public static MetaItem<?>.MetaValueItem CLAY_GRINDING_HEAD;
    public static MetaItem<?>.MetaValueItem CLAY_BEARING;
    public static MetaItem<?>.MetaValueItem CLAY_SPINDLE;
    public static MetaItem<?>.MetaValueItem CLAY_CUTTING_HEAD;
    public static MetaItem<?>.MetaValueItem CLAY_WHEEL;

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
