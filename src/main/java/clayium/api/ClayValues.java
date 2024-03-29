package clayium.api;


/**
 * Made for static imports, this Class is just a Helper.
 */
public class ClayValues {

    /**
     * The short names for the tiers, used for registration primarily
     */
    public static final String[] TN = new String[]{"T0", "T1", "T2", "T3", "T4", "T5", "T6", "T7", "T8", "T9", "T10", "T11", "T12", "T13"};
    /**
     * Color values for the tiers
     */
    public static final int[] TC = new int[]{0xB4B4B4, 0xDCDCDC, 0xFF6400, 0xFFFF1E, 0x808080, 0xF0F0F5, 0xDCDCF5, 0xC8C8F5, 0xB4B4F5, 0xA0A0F5, 0x8C8CF5, 0x7878F5, 0x6464F5, 0x5050F5, 0x2828F5};
    /**
     * clay energy unit for recipes
     */
    public static final int microCE = 1;
    public static final int milliCE = 1000;
    public static final int CE = 1000000;

    /**
     * ModID strings, since they are quite common parameters
     */
    public static final String MODID = "clayium";
}
