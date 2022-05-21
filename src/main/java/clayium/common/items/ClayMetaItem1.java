package clayium.common.items;

import gregtech.api.items.metaitem.StandardMetaItem;
import static clayium.common.items.ClayMetaItems.*;

public class ClayMetaItem1 extends StandardMetaItem {

    public ClayMetaItem1() {
        super();
    }

    @Override
    public void registerSubItems() {

        CLAYTASK_CUTCIRCLE = addItem(0, "claytask.cutcircle");
        CLAYTASK_CUTSQUARE = addItem(1, "claytask.cutsquare");
        CLAYTASK_PAT = addItem(2, "claytask.pat");
        CLAYTASK_PRESS = addItem(3, "claytask.press");
        CLAYTASK_ROLL = addItem(4, "claytask.roll");
        CLAYTASK_SLICE = addItem(5, "claytask.slice");

        CLAY_PLATE = addItem(6, "clay.plate");
        CLAY_STICK = addItem(7, "clay.stick");
        CLAY_STICK_SHORT = addItem(8, "clay.stick.short");
        CLAY_RING = addItem(9, "clay.ring");
        CLAY_RING_SMALL = addItem(10, "clay.ring.small");
        CLAY_GEAR = addItem(11, "clay.gear");
        CLAY_BLADE = addItem(12, "clay.blade");
        CLAY_NEEDLE = addItem(13, "clay.needle");
        CLAY_DISC = addItem(14, "clay.disc");
        CLAY_DISC_SMALL = addItem(15, "clay.disc.small");
        CLAY_CYLINDER = addItem(16, "clay.cylinder");
        CLAY_PIPE = addItem(17, "clay.pipe");
        CLAY_BALL_LARGE = addItem(18, "clay.ball.large");
        CLAY_PLATE_LARGE = addItem(19, "clay.plate.large");
        CLAY_GRINDING_HEAD = addItem(20, "clay.grinding.head");
        CLAY_BEARING = addItem(21, "clay.bearing");
        CLAY_SPINDLE = addItem(22, "clay.spindle");
        CLAY_CUTTING_HEAD = addItem(23, "clay.cutting.head");
        CLAY_WHEEL = addItem(24, "clay.wheel");
    }
}
