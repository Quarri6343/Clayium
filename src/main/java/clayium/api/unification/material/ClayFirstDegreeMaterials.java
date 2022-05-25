package clayium.api.unification.material;

import gregtech.api.unification.material.properties.PropertyKey;
import gregtech.api.unification.material.properties.ToolProperty;

import static gregtech.api.unification.material.Materials.*;

public class ClayFirstDegreeMaterials {

    /**
     * 25001 -25100
     */
    public static void init() {

//        Galvalume = new Material.Builder(24001, "galvalume")
//                .dust().ingot().fluid()
//                .fluidTemp(1200)
//                .color(0x072743)
//                .iconSet(MaterialIconSet.METALLIC)
//                .flags(GENERATE_PLATE, GENERATE_ROD, GENERATE_FRAME)
//                .components(Steel, 4, Zinc, 1, Aluminium, 1)
//                .blastTemp(1700, GasTier.HIGHEST, 120, 120)
//                .build();

        ToolProperty clayToolProperty = new ToolProperty();
        clayToolProperty.setToolSpeed(6.0f);
        clayToolProperty.setToolDurability(128);
        clayToolProperty.setToolEnchantability(10);
        clayToolProperty.setShouldIgnoreCraftingTools(true);
        Clay.setProperty(PropertyKey.TOOL, clayToolProperty);
    }
}
