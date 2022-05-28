package clayium.common.items;

import clayium.api.items.metaitem.StandardClayMetaItem;
import gregtech.api.items.metaitem.StandardMetaItem;
import gregtech.api.items.toolitem.WrenchItemStat;

import static clayium.common.items.ClayMetaItems.*;

public class ClayMetaItem1 extends StandardClayMetaItem {

    public ClayMetaItem1() {
        super();
    }

    @Override
    public void registerSubItems() {

        CLAYTASK_CUTCIRCLE = addItem(0, "claytask.cutcircle").setTier(0);
        CLAYTASK_CUTSQUARE = addItem(1, "claytask.cutsquare").setTier(0);
        CLAYTASK_PAT = addItem(2, "claytask.pat").setTier(0);
        CLAYTASK_PRESS = addItem(3, "claytask.press").setTier(0);
        CLAYTASK_ROLL = addItem(4, "claytask.roll").setTier(0);
        CLAYTASK_SLICE = addItem(5, "claytask.slice").setTier(0);

        CLAY_PLATE = addItem(6, "clay.plate").setTier(1);
        CLAY_STICK = addItem(7, "clay.stick").setTier(1);
        CLAY_STICK_SHORT = addItem(8, "clay.stick.short").setTier(1);
        CLAY_RING = addItem(9, "clay.ring").setTier(1);
        CLAY_RING_SMALL = addItem(10, "clay.ring.small").setTier(1);
        CLAY_GEAR = addItem(11, "clay.gear").setTier(1);
        CLAY_BLADE = addItem(12, "clay.blade").setTier(1);
        CLAY_NEEDLE = addItem(13, "clay.needle").setTier(1);
        CLAY_DISC = addItem(14, "clay.disc").setTier(1);
        CLAY_DISC_SMALL = addItem(15, "clay.disc.small").setTier(1);
        CLAY_CYLINDER = addItem(16, "clay.cylinder").setTier(1);
        CLAY_PIPE = addItem(17, "clay.pipe").setTier(1);
        CLAY_BALL_LARGE = addItem(18, "clay.ball.large").setTier(1);
        CLAY_PLATE_LARGE = addItem(19, "clay.plate.large").setTier(1);
        CLAY_GRINDING_HEAD = addItem(20, "clay.grinding.head").setTier(1);
        CLAY_BEARING = addItem(21, "clay.bearing").setTier(1);
        CLAY_SPINDLE = addItem(22, "clay.spindle").setTier(1);
        CLAY_CUTTING_HEAD = addItem(23, "clay.cutting.head").setTier(1);
        CLAY_WHEEL = addItem(24, "clay.wheel").setTier(1);

        CLAY_ROLLING_PIN_RAW = addItem(25, "clay.rolling.pin.raw");
        CLAY_SLICER_RAW = addItem(26, "clay.slicer.raw");
        CLAY_SPATULA_RAW = addItem(27, "clay.spatula.raw");
    }
}
