package clayium.api.gui.widgets;

import clayium.api.recipes.ClayRecipeMap;
import clayium.integration.jei.ClayJeiPlugin;
import clayium.integration.jei.recipe.ClayRecipeMapCategory;
import gregtech.api.GTValues;
import gregtech.api.gui.resources.TextureArea;
import gregtech.api.gui.widgets.ProgressWidget;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.I18n;
import net.minecraftforge.fml.client.config.GuiUtils;
import net.minecraftforge.fml.common.Loader;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.DoubleSupplier;

public class ClayRecipeProgressWidget extends ProgressWidget {

    private final ClayRecipeMap<?> recipeMap;
    private final static int HOVER_TEXT_WIDTH = 200;

    public ClayRecipeProgressWidget(DoubleSupplier progressSupplier, int x, int y, int width, int height, ClayRecipeMap<?> recipeMap) {
        super(progressSupplier, x, y, width, height);
        this.recipeMap = recipeMap;
    }

    public ClayRecipeProgressWidget(DoubleSupplier progressSupplier, int x, int y, int width, int height, TextureArea fullImage, MoveType moveType, ClayRecipeMap<?> recipeMap) {
        super(progressSupplier, x, y, width, height, fullImage, moveType);
        this.recipeMap = recipeMap;
    }

    public ClayRecipeProgressWidget(int ticksPerCycle, int x, int y, int width, int height, TextureArea fullImage, MoveType moveType, ClayRecipeMap<?> recipeMap) {
        super(ticksPerCycle, x, y, width, height, fullImage, moveType);
        this.recipeMap = recipeMap;
    }

    @Override
    public boolean mouseClicked(int mouseX, int mouseY, int button) {
        if (!Loader.isModLoaded(GTValues.MODID_JEI))
            return false;
        if (isMouseOverElement(mouseX, mouseY) && ClayRecipeMapCategory.getCategoryMap().containsKey(recipeMap)) {
            // Since categories were even registered at all, we know JEI is active.
            List<String> categoryID = new ArrayList<>();
            categoryID.add(ClayRecipeMapCategory.getCategoryMap().get(recipeMap).getUid());
            ClayJeiPlugin.jeiRuntime.getRecipesGui().showCategories(categoryID);
            return true;
        }
        return false;
    }


    @Override
    public void drawInForeground(int mouseX, int mouseY) {
        super.drawInForeground(mouseX, mouseY);
        if (isMouseOverElement(mouseX, mouseY) && Loader.isModLoaded(GTValues.MODID_JEI)) {
            Minecraft mc = Minecraft.getMinecraft();
            GuiUtils.drawHoveringText(Collections.singletonList(I18n.format("gui.widget.recipeProgressWidget.default_tooltip")), mouseX, mouseY,
                    sizes.getScreenWidth(),
                    sizes.getScreenHeight(), HOVER_TEXT_WIDTH, mc.fontRenderer);
        }
    }

}
