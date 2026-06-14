package com.lgmrszd.compressedcreativity.index.recipe;

import com.lgmrszd.compressedcreativity.content.Mesh;
import com.lgmrszd.compressedcreativity.index.CCItems;
import com.lgmrszd.compressedcreativity.index.CCMisc;
import com.simibubi.create.content.fluids.transfer.FillingRecipe;
import com.simibubi.create.content.processing.sequenced.SequencedAssemblyRecipeBuilder;
import com.simibubi.create.foundation.data.recipe.CreateRecipeProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.world.level.material.Fluids;

import java.util.concurrent.CompletableFuture;
import java.util.function.UnaryOperator;

// TODO: Fully port to Create 1.21.1 datagen API (GeneratedRecipe removed, all field gone)
public class CCSequencedAssemblyRecipeGen extends CreateRecipeProvider {

    public CCSequencedAssemblyRecipeGen(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries);
    }

    @Override
    public String getName() {
        return "Compressed Creativity Sequenced Assembly Recipes";
    }
}
