package net.vdragondev.permanence;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.screenhandler.v1.ScreenRegistry;
import net.vdragondev.permanence.screens.QuernScreen;

public class PermanenceClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        ScreenRegistry.register(PermanenceMod.QUERN_SCREEN, QuernScreen::new);
    }
}
