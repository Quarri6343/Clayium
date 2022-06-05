package clayium.common.items;

import clayium.api.items.metaitem.ClayMetaItem;
import gregtech.api.items.toolitem.ToolMetaItem;

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
    public static ClayMetaItem<?>.ClayMetaValueItem CLAY_DUST;

    public static ClayMetaItem<?>.ClayMetaValueItem CLAY_ROLLING_PIN_RAW;
    public static ClayMetaItem<?>.ClayMetaValueItem CLAY_SLICER_RAW;
    public static ClayMetaItem<?>.ClayMetaValueItem CLAY_SPATULA_RAW;

    public static ClayMetaItem<?>.ClayMetaValueItem CLAY_CIRCUIT;
    public static ClayMetaItem<?>.ClayMetaValueItem CLAY_CIRCUIT_BOARD;
    public static ClayMetaItem<?>.ClayMetaValueItem DENSE_CLAY_PLATE;
    public static ClayMetaItem<?>.ClayMetaValueItem DENSE_CLAY_STICK;
    public static ClayMetaItem<?>.ClayMetaValueItem DENSE_CLAY_STICK_SHORT;
    public static ClayMetaItem<?>.ClayMetaValueItem DENSE_CLAY_RING;
    public static ClayMetaItem<?>.ClayMetaValueItem DENSE_CLAY_RING_SMALL;
    public static ClayMetaItem<?>.ClayMetaValueItem DENSE_CLAY_GEAR;
    public static ClayMetaItem<?>.ClayMetaValueItem DENSE_CLAY_BLADE;
    public static ClayMetaItem<?>.ClayMetaValueItem DENSE_CLAY_NEEDLE;
    public static ClayMetaItem<?>.ClayMetaValueItem DENSE_CLAY_DISC;
    public static ClayMetaItem<?>.ClayMetaValueItem DENSE_CLAY_DISC_SMALL;
    public static ClayMetaItem<?>.ClayMetaValueItem DENSE_CLAY_CYLINDER;
    public static ClayMetaItem<?>.ClayMetaValueItem DENSE_CLAY_PIPE;
    public static ClayMetaItem<?>.ClayMetaValueItem DENSE_CLAY_PLATE_LARGE;
    public static ClayMetaItem<?>.ClayMetaValueItem DENSE_CLAY_GRINDING_HEAD;
    public static ClayMetaItem<?>.ClayMetaValueItem DENSE_CLAY_BEARING;
    public static ClayMetaItem<?>.ClayMetaValueItem DENSE_CLAY_SPINDLE;
    public static ClayMetaItem<?>.ClayMetaValueItem DENSE_CLAY_CUTTING_HEAD;
    public static ClayMetaItem<?>.ClayMetaValueItem DENSE_CLAY_WHEEL;
    public static ClayMetaItem<?>.ClayMetaValueItem DENSE_CLAY_DUST;
    public static ClayMetaItem<?>.ClayMetaValueItem CLAY_SHARD_COMPRESSED;
    public static ClayMetaItem<?>.ClayMetaValueItem CLAY_SHARD_INDUSTRIAL;
    public static ClayMetaItem<?>.ClayMetaValueItem CLAY_SHARD_INDUSTRIAL_ADVANCED;

    public static ClayMetaItem<?>.ClayMetaValueItem SIMPLE_CIRCUIT;
    public static ClayMetaItem<?>.ClayMetaValueItem CEE_BOARD;
    public static ClayMetaItem<?>.ClayMetaValueItem CEE_CIRCUIT;
    public static ClayMetaItem<?>.ClayMetaValueItem CLAY_ENERGY_EXCITOR;
    public static ClayMetaItem<?>.ClayMetaValueItem INDUSTRIAL_CLAY_PLATE;
    public static ClayMetaItem<?>.ClayMetaValueItem INDUSTRIAL_CLAY_PLATE_LARGE;
    public static ClayMetaItem<?>.ClayMetaValueItem INDUSTRIAL_CLAY_DUST;
    public static ClayMetaItem<?>.ClayMetaValueItem ENERGIZED_CLAY_DUST;

    public static ToolMetaItem<?>.MetaToolValueItem CLAY_SHOVEL;
    public static ToolMetaItem<?>.MetaToolValueItem CLAY_PICKAXE;
    public static ToolMetaItem<?>.MetaToolValueItem CLAY_ROLLING_PIN;
    public static ToolMetaItem<?>.MetaToolValueItem CLAY_SLICER;
    public static ToolMetaItem<?>.MetaToolValueItem CLAY_SPATULA;
    public static ToolMetaItem<?>.MetaToolValueItem CLAY_WRENCH;
    public static ToolMetaItem<?>.MetaToolValueItem CLAY_PIPING_TOOL_IO;
    public static ToolMetaItem<?>.MetaToolValueItem CLAY_PIPING_TOOL_PIPING;
    public static ToolMetaItem<?>.MetaToolValueItem CLAY_PIPING_TOOL_MEMORY;
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
