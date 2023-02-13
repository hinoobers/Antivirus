package org.hinoob.antivirus.module;

import org.hinoob.antivirus.event.PlayerOpEvent;
import org.hinoob.antivirus.event.PluginLoadEvent;

public abstract class Module {

    public abstract void onPluginLoaded(PluginLoadEvent event);
    public abstract void onPlayerOp(PlayerOpEvent event);
}
