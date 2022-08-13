package clayium.api.recipes.map;

import clayium.api.recipes.ClayRecipe;
import gregtech.api.recipes.Recipe;
import gregtech.api.recipes.map.AbstractMapIngredient;
import gregtech.api.recipes.map.Either;
import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;

import java.util.Map;
import java.util.stream.Stream;

public class ClayBranch {
    // Keys on this have *(should)* unique hashcodes.
    private Map<AbstractMapIngredient, Either<ClayRecipe, ClayBranch>> nodes;
    // Keys on this have collisions, and must be differentiated by equality.
    private Map<AbstractMapIngredient, Either<ClayRecipe, ClayBranch>> specialNodes;

    public Stream<ClayRecipe> getRecipes(boolean filterHidden) {
        Stream<ClayRecipe> stream = null;
        if (nodes != null) {
            stream = nodes.values().stream().flatMap(either -> either.map(Stream::of, right -> right.getRecipes(filterHidden)));
        }
        if (specialNodes != null) {
            if (stream == null) {
                stream = specialNodes.values().stream().flatMap(either -> either.map(Stream::of, right -> right.getRecipes(filterHidden)));
            } else {
                stream = Stream.concat(stream, specialNodes.values().stream().flatMap(either -> either.map(Stream::of, right -> right.getRecipes(filterHidden))));
            }
        }
        if (stream == null) {
            return Stream.empty();
        }
        if (filterHidden) {
            stream = stream.filter(t -> !t.isHidden());
        }
        return stream;
    }

    public boolean isEmptyBranch() {
        return (nodes == null || nodes.isEmpty()) && (specialNodes == null || specialNodes.isEmpty());
    }

    public Map<AbstractMapIngredient, Either<ClayRecipe, ClayBranch>> getNodes() {
        if (nodes == null) {
            nodes = new Object2ObjectOpenHashMap<>(2);
        }
        return nodes;
    }

    public Map<AbstractMapIngredient, Either<ClayRecipe, ClayBranch>> getSpecialNodes() {
        if (specialNodes == null) {
            specialNodes = new Object2ObjectOpenHashMap<>(2);
        }
        return specialNodes;
    }
}
