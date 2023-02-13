package org.hinoob.antivirus.event;

import lombok.Getter;
import org.bukkit.plugin.Plugin;

public class PluginLoadEvent extends CancellableEvent{

    @Getter private Plugin plugin;

    public PluginLoadEvent(Plugin plugin){
        this.plugin = plugin;
    }
}
