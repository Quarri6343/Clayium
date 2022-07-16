package clayium.common.items;

import clayium.api.items.metaitem.StandardClayMetaItem;

import static clayium.common.items.ClayMetaItems.*;

public class ClayMetaItem1 extends StandardClayMetaItem {

    public ClayMetaItem1() {
        super();
    }

    @Override
    public void registerSubItems() {

        //Tier 0
        CLAYTASK_CUTCIRCLE = addItem(0, "claytask.cutcircle").setTier(0);
        CLAYTASK_CUTSQUARE = addItem(1, "claytask.cutsquare").setTier(0);
        CLAYTASK_PAT = addItem(2, "claytask.pat").setTier(0);
        CLAYTASK_PRESS = addItem(3, "claytask.press").setTier(0);
        CLAYTASK_ROLL = addItem(4, "claytask.roll").setTier(0);
        CLAYTASK_SLICE = addItem(5, "claytask.slice").setTier(0);

        //Tier 1
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
        CLAY_DUST = addItem(25, "clay.dust").setTier(1);

        CLAY_ROLLING_PIN_RAW = addItem(26, "clay.rolling.pin.raw");
        CLAY_SLICER_RAW = addItem(27, "clay.slicer.raw");
        CLAY_SPATULA_RAW = addItem(28, "clay.spatula.raw");


        //Tier 2
        CLAY_CIRCUIT = addItem(29, "clay.circuit").setTier(2);
        CLAY_CIRCUIT_BOARD = addItem(30, "clay.circuit.board").setTier(2);
        DENSE_CLAY_PLATE = addItem(31, "dense.clay.plate").setTier(2);
        DENSE_CLAY_STICK = addItem(32, "dense.clay.stick").setTier(2);
        DENSE_CLAY_STICK_SHORT = addItem(33, "dense.clay.stick.short").setTier(2);
        DENSE_CLAY_RING = addItem(34, "dense.clay.ring").setTier(2);
        DENSE_CLAY_RING_SMALL = addItem(35, "dense.clay.ring.small").setTier(2);
        DENSE_CLAY_GEAR = addItem(36, "dense.clay.gear").setTier(2);
        DENSE_CLAY_BLADE = addItem(37, "dense.clay.blade").setTier(2);
        DENSE_CLAY_NEEDLE = addItem(38, "dense.clay.needle").setTier(2);
        DENSE_CLAY_DISC = addItem(39, "dense.clay.disc").setTier(2);
        DENSE_CLAY_DISC_SMALL = addItem(40, "dense.clay.disc.small").setTier(2);
        DENSE_CLAY_CYLINDER = addItem(41, "dense.clay.cylinder").setTier(2);
        DENSE_CLAY_PIPE = addItem(42, "dense.clay.pipe").setTier(2);
        DENSE_CLAY_PLATE_LARGE = addItem(43, "dense.clay.plate.large").setTier(2);
        DENSE_CLAY_GRINDING_HEAD = addItem(44, "dense.clay.grinding.head").setTier(2);
        DENSE_CLAY_BEARING = addItem(45, "dense.clay.bearing").setTier(2);
        DENSE_CLAY_SPINDLE = addItem(46, "dense.clay.spindle").setTier(2);
        DENSE_CLAY_CUTTING_HEAD = addItem(47, "dense.clay.cutting.head").setTier(2);
        DENSE_CLAY_WHEEL = addItem(48, "dense.clay.wheel").setTier(2);
        DENSE_CLAY_DUST = addItem(49, "dense.clay.dust").setTier(2);
        CLAY_SHARD_COMPRESSED = addItem(50, "clay.shard.compressed");
        CLAY_SHARD_INDUSTRIAL = addItem(51, "clay.shard.industrial");
        CLAY_SHARD_INDUSTRIAL_ADVANCED = addItem(52, "clay.shard.industrial.advanced");

        //Tier 3
        SIMPLE_CIRCUIT = addItem(53, "simple.circuit").setTier(3);
        CEE_BOARD = addItem(54, "cee.board").setTier(3);
        CEE_CIRCUIT = addItem(55, "cee.circuit").setTier(3);
        CLAY_ENERGY_EXCITOR = addItem(56, "clay.energy.excitor").setTier(3);
        INDUSTRIAL_CLAY_PLATE = addItem(57, "industrial.clay.plate").setTier(3);
        INDUSTRIAL_CLAY_PLATE_LARGE = addItem(58, "industrial.clay.plate.large").setTier(3);
        INDUSTRIAL_CLAY_DUST = addItem(59, "industrial.clay.dust").setTier(3);
        ENERGIZED_CLAY_DUST = addItem(60, "energized.clay.dust").setTier(3);

        //Tier 4
        BASIC_CIRCUIT = addItem(61, "basic.circuit").setTier(4);
        INDUSTRIAL_CLAY_PLATE_ADVANCED = addItem(62, "industrial.clay.plate.advanced").setTier(4);
        INDUSTRIAL_CLAY_PLATE_LARGE_ADVANCED = addItem(63, "industrial.clay.plate.large.advanced").setTier(4);
        INDUSTRIAL_CLAY_DUST_ADVANCED = addItem(64, "industrial.clay.dust.advanced").setTier(4);
    }
}
