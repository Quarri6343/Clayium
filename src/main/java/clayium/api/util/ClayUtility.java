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

    public static long getCEDividedwithUnit(long CE){
        if(CE > ClayValues.CE){
            return Math.round((float) CE / 1000000);
        }
        else if(CE > ClayValues.milliCE){
            return Math.round((float) CE / 1000);
        }
        else{
            return CE;
        }
    }
}
