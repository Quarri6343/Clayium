package clayium.loaders.recipe;

public class ClayRecipeManager {

    public static void init() {
        ClayCraftingRecipeLoader.Init();
        ClaySmeltingRecipeLoader.Init();
        ClayMachineRecipeLoader.Init();
    }
}