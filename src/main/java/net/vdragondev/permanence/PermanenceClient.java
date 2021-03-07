package net.vdragondev.permanence;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.screenhandler.v1.ScreenRegistry;
import net.minecraft.util.registry.Registry;
import net.vdragondev.permanence.kiln.KilnScreen;
import net.vdragondev.permanence.quern.QuernScreen;

public class PermanenceClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        ScreenRegistry.register(PermanenceMod.QUERN_SCREEN, QuernScreen::new);
        ScreenRegistry.register(PermanenceMod.KILN_SCREEN, KilnScreen::new);
    }
}
