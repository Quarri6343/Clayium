package clayium.common.items;

import clayium.common.tools.ToolClayPickaxe;
import clayium.common.tools.ToolClayShovel;
import gregtech.api.items.toolitem.ToolMetaItem;

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
    }
}
