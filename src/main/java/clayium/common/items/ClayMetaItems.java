package clayium.common.items;

import clayium.api.items.metaitem.ClayMetaItem;
import gregtech.api.items.metaitem.MetaItem;
import gregtech.api.items.toolitem.ToolMetaItem;
import gregtech.common.items.MetaTool;

public final class ClayMetaItems {

    private ClayMetaItems() {
    }

    public static ClayMetaItem<?>.ClayMetaValueItem CLAYTASK_CUTCIRCLE;
    public static ClayMetaItem<?>.ClayMetaValueItem CLAYTASK_CUTSQUARE;
    public static ClayMetaItem<?>.ClayMetaValueItem CLAYTASK_PAT;
    public static ClayMetaItem<?>.ClayMetaValueItem CLAYTASK_PRESS;
    public static ClayMetaItem<?>.ClayMetaValueItem CLAYTASK_ROLL;
    public static ClayMetaItem<?>.ClayMetaValueItem CLAYTASK_SLICE;

    public static ClayMetaItem<?>.ClayMetaValueItem CLAY_PLATE;
    public static ClayMetaItem<?>.ClayMetaValueItem CLAY_STICK;
    public static ClayMetaItem<?>.ClayMetaValueItem CLAY_STICK_SHORT;
    public static ClayMetaItem<?>.ClayMetaValueItem CLAY_RING;
    public static ClayMetaItem<?>.ClayMetaValueItem CLAY_RING_SMALL;
    public static ClayMetaItem<?>.ClayMetaValueItem CLAY_GEAR;
    public static ClayMetaItem<?>.ClayMetaValueItem CLAY_BLADE;
    public static ClayMetaItem<?>.ClayMetaValueItem CLAY_NEEDLE;
    public static ClayMetaItem<?>.ClayMetaValueItem CLAY_DISC;
    public static ClayMetaItem<?>.ClayMetaValueItem CLAY_DISC_SMALL;
    public static ClayMetaItem<?>.ClayMetaValueItem CLAY_CYLINDER;
    public static ClayMetaItem<?>.ClayMetaValueItem CLAY_PIPE;
    public static ClayMetaItem<?>.ClayMetaValueItem CLAY_BALL_LARGE;
    public static ClayMetaItem<?>.ClayMetaValueItem CLAY_PLATE_LARGE;
    public static ClayMetaItem<?>.ClayMetaValueItem CLAY_GRINDING_HEAD;
    public static ClayMetaItem<?>.ClayMetaValueItem CLAY_BEARING;
    public static ClayMetaItem<?>.ClayMetaValueItem CLAY_SPINDLE;
    public static ClayMetaItem<?>.ClayMetaValueItem CLAY_CUTTING_HEAD;
    public static ClayMetaItem<?>.ClayMetaValueItem CLAY_WHEEL;

    public static ClayMetaItem<?>.ClayMetaValueItem CLAY_ROLLING_PIN_RAW;
    public static ClayMetaItem<?>.ClayMetaValueItem CLAY_SLICER_RAW;
    public static ClayMetaItem<?>.ClayMetaValueItem CLAY_SPATULA_RAW;
    public static ClayMetaItem<?>.ClayMetaValueItem CLAY_ROLLING_PIN;
    public static ClayMetaItem<?>.ClayMetaValueItem CLAY_SLICER;
    public static ClayMetaItem<?>.ClayMetaValueItem CLAY_SPATULA;
    public static ClayMetaItem<?>.ClayMetaValueItem CLAY_WRENCH;
    public static ClayMetaItem<?>.ClayMetaValueItem CLAY_PIPING_TOOL_IO;
    public static ClayMetaItem<?>.ClayMetaValueItem CLAY_PIPING_TOOL_PIPING;
    public static ClayMetaItem<?>.ClayMetaValueItem CLAY_PIPING_TOOL_MEMORY;

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
