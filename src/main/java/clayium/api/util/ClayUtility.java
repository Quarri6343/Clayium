package clayium.api.util;

import clayium.api.ClayValues;

public class ClayUtility {

    public static String getCEWithUnit(long CE){
        if(CE > ClayValues.CE){
            return String.format("%.1f",(float) CE / 1000000);
        }
        else if(CE > ClayValues.milliCE){
            return String.format("%.1fm",(float) CE / 1000);
        }
        else{
            return String.format("%du", CE);
        }
    }

}
