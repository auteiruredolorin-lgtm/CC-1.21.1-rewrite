package com.lgmrszd.compressedcreativity.blocks.bracketed_pressure_tube;

import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.client.model.generators.ModelFile;

public class BracketedPressureTubeBlockStateGenerator {

    // TODO: restore DataGenContext / RegistrateBlockstateProvider overload when Registrate dep is added
    public static ModelFile tubeCore(BlockStateProvider prov) {
        return prov.models()
                .getExistingFile(prov.modLoc("block/pressure_tube_core"));
    }

    // blockState() method re-enable when BracketedPressureTubeBlock / Registrate are available
}
