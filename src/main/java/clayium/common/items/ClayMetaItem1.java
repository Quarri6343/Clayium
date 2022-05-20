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
    }
}
