package org.hinoob.antivirus.listener;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.server.PluginEnableEvent;
import org.hinoob.antivirus.AntiVirus;
import org.hinoob.antivirus.ModuleManager;
import org.hinoob.antivirus.event.PluginLoadEvent;

public class PluginListener implements Listener {

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onEnable(PluginEnableEvent event){
        PluginLoadEvent e = new PluginLoadEvent(event.getPlugin());
        ModuleManager.getInstance().get().forEach(m -> m.onPluginLoaded(e));
        if(e.isCancelled()){
            Bukkit.getPluginManager().disablePlugin(event.getPlugin());
        }
    }
}
