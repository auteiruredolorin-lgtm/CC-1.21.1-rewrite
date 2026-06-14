package com.lgmrszd.compressedcreativity;

import com.lgmrszd.compressedcreativity.index.*;
import com.lgmrszd.compressedcreativity.index.CCItems;
import com.lgmrszd.compressedcreativity.index.recipe.CCSequencedAssemblyRecipeGen;
import com.simibubi.create.foundation.data.CreateRegistrate;
import com.tterrag.registrate.providers.ProviderType;
import net.createmod.ponder.foundation.PonderIndex;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.data.event.GatherDataEvent;
import net.neoforged.neoforge.event.server.ServerAboutToStartEvent;
import net.neoforged.bus.api.EventPriority;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.*;
import net.neoforged.fml.loading.FMLEnvironment;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import net.neoforged.fml.ModContainer;
import com.lgmrszd.compressedcreativity.network.ModNetwork;

@Mod(CompressedCreativity.MOD_ID)
public class CompressedCreativity {

    public static final Logger LOGGER = LogManager.getLogger();
    public static final String MOD_ID = "compressedcreativity";

    public static final CreateRegistrate REGISTRATE = CreateRegistrate.create(CompressedCreativity.MOD_ID);

    public CompressedCreativity(ModContainer modContainer) {
        IEventBus eventBus = modContainer.getEventBus();
        REGISTRATE.registerEventListeners(eventBus);
        CCConfigHelper.init();
        CCConfigHelper.registerConfigListener(eventBus);
        eventBus.addListener(this::setup);
        eventBus.addListener(this::doClientStuff);
        eventBus.addListener(this::postInit);
        // DistExecutor removed in NeoForge — use FMLEnvironment.dist check instead
        if (FMLEnvironment.dist == Dist.CLIENT) {
            CCBlockPartials.init();
        }
        // Register network packets
        eventBus.addListener(ModNetwork::register);
        NeoForge.EVENT_BUS.addListener(this::serverStart);
        CCCreativeTabs.register(eventBus);
        CCItems.register(eventBus);
        CCBlocks.register();
        CCBlockEntities.register();
        CCUpgrades.init();
        eventBus.addListener(EventPriority.LOWEST, CompressedCreativity::gatherData);
    }

    private void setup(final FMLCommonSetupEvent event) {
        LOGGER.info("HELLO FROM PREINIT");
        CCCommonSetup.init(event);
    }

    private void doClientStuff(final FMLClientSetupEvent event) {
        CCClientSetup.init(event);
        PonderIndex.addPlugin(new CCPonderPlugin());
    }

    private void serverStart(final ServerAboutToStartEvent event) {
        // CCHeatBehaviour.registerHeatBehaviour();
    }

    public void postInit(FMLLoadCompleteEvent evt) {
    }

    public static void gatherData(GatherDataEvent event) {
        DataGenerator gen = event.getGenerator();
        PackOutput output = gen.getPackOutput();
        REGISTRATE.addDataGenerator(ProviderType.LANG, provider -> {
            PonderIndex.addPlugin(new CCPonderPlugin());
            PonderIndex.getLangAccess().provideLang(MOD_ID, provider::add);
        });
        CCLangExtender.ExtendLang(REGISTRATE);
        gen.addProvider(true, new CCSequencedAssemblyRecipeGen(output));
    }
}
