package clayium.common.items;

import clayium.common.tools.ToolClayPickaxe;
import clayium.common.tools.ToolClayShovel;
import gregtech.api.items.toolitem.CutterItemStat;
import gregtech.api.items.toolitem.ScrewdriverItemStat;
import gregtech.api.items.toolitem.ToolMetaItem;
import gregtech.api.items.toolitem.WrenchItemStat;
import gregtech.api.sound.GTSounds;
import gregtech.common.tools.ToolScrewdriver;
import gregtech.common.tools.ToolWireCutter;
import gregtech.common.tools.ToolWrench;

import static clayium.common.items.ClayMetaItems.*;

public class ClayMetaTool  extends ToolMetaItem<ToolMetaItem<?>.MetaToolValueItem> {

    public ClayMetaTool() {
        super();
    }

    @Override
    public void registerSubItems() {
        CLAY_SHOVEL = (MetaToolValueItem) addItem(0, "tool.clay.shovel").setToolStats(new ToolClayShovel())
                .setFullRepairCost(1)
                .addOreDict("craftingToolClayShovel");
        CLAY_PICKAXE = (MetaToolValueItem) addItem(1, "tool.clay.pickaxe").setToolStats(new ToolClayPickaxe())
                .setFullRepairCost(3)
                .addOreDict("craftingToolClayPickaxe");
        CLAY_ROLLING_PIN = (MetaToolValueItem) addItem(2, "tool.clay.rolling.pin").setToolStats(new ToolScrewdriver())
                .setFullRepairCost(1)
                .addOreDict("craftingToolClayRollingPin")
                .addComponents(new ScrewdriverItemStat());
        CLAY_SLICER = (MetaToolValueItem) addItem(3, "tool.clay.slicer").setToolStats(new ToolWrench())
                .setFullRepairCost(1)
                .addOreDict("craftingToolClaySlicer")
                .addComponents(new WrenchItemStat());
        CLAY_SPATULA = (MetaToolValueItem) addItem(4, "tool.clay.spatula").setToolStats(new ToolWireCutter())
                .setFullRepairCost(1)
                .addOreDict("craftingToolClaySpatula")
                .addComponents(new CutterItemStat());
        //CLAY_WRENCH
        //CLAY_PIPING_TOOL_IO
        //CLAY_PIPING_TOOL_PIPING
        //CLAY_PIPING_TOOL_MEMORY
    }
}
