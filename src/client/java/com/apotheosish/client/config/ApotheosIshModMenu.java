package com.apotheosish.client.config;

import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;

/** Makes the YACL screen available from Mod Menu when that optional mod is installed. */
public final class ApotheosIshModMenu implements ModMenuApi {
    @Override
    public ConfigScreenFactory<?> getModConfigScreenFactory() {
        return ApotheosIshConfigScreen::create;
    }
}
