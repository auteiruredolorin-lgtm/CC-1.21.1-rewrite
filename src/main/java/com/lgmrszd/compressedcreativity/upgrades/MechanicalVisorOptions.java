package com.lgmrszd.compressedcreativity.upgrades;

import me.desht.pneumaticcraft.api.client.pneumatic_helmet.IGuiScreen;
import me.desht.pneumaticcraft.api.client.pneumatic_helmet.IOptionPage;

// TODO: Restore full implementation once PNC API differences are resolved
public class MechanicalVisorOptions implements IOptionPage {

    public MechanicalVisorOptions(IGuiScreen screen, MechanicalVisorClientHandler handler) {
    }

    @Override
    public IGuiScreen getGuiScreen() {
        return null;
    }
}
