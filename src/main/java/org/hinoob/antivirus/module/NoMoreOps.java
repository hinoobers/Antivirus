package org.hinoob.antivirus.module;

import org.hinoob.antivirus.AntiVirus;
import org.hinoob.antivirus.event.PlayerOpEvent;
import org.hinoob.antivirus.event.PluginLoadEvent;

public class NoMoreOps extends Module{

    @Override
    public void onPluginLoaded(PluginLoadEvent event) {

    }

    @Override
    public void onPlayerOp(PlayerOpEvent event) {
        if(event.getPlayer().isOp()){
            event.setCancelled(true);
        }
    }
}
