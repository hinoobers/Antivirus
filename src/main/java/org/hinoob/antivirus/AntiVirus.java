package org.hinoob.antivirus;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.hinoob.antivirus.event.PlayerOpEvent;
import org.hinoob.antivirus.event.PluginLoadEvent;
import org.hinoob.antivirus.listener.PluginListener;
import org.hinoob.antivirus.util.NMSUtil;
import org.hinoob.antivirus.util.Pair;
import org.hinoob.antivirus.util.Settings;

import java.util.*;
import java.util.logging.Logger;

public class AntiVirus extends JavaPlugin {

    private final String pluginName = "Coreography";
    private final List<Plugin> pluginsToLoad = new ArrayList<>();
    private final List<Pair<UUID, Boolean>> previousOp = new ArrayList<>();

    @Override
    public void onLoad() {
        // cache & disable all previous plugins
        // for next plugins, we can use PluginEnableEvent
        disablePreviousPlugins();
    }

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(new PluginListener(), this);

        ModuleManager.getInstance().register();
        for(Plugin toLoad : this.pluginsToLoad){
            PluginLoadEvent event = new PluginLoadEvent(toLoad);
            ModuleManager.getInstance().get().forEach(m -> m.onPluginLoaded(event));

            if(!event.isCancelled()){
                Bukkit.getPluginManager().enablePlugin(toLoad);
            }
        }
        pluginsToLoad.clear();

        // post
        NMSUtil.load();
        Settings.load(this);
        getServer().getScheduler().runTaskTimer(this, () -> {
            for(Player player : Bukkit.getOnlinePlayers()){
                Optional<Pair<UUID, Boolean>> result = this.previousOp.stream().filter(p -> p.getX().equals(player.getUniqueId())).findAny();
                if(result.isPresent()){
                    if(player.isOp() != result.get().getY()){
                        PlayerOpEvent event = new PlayerOpEvent(player);
                        ModuleManager.getInstance().get().forEach(m -> m.onPlayerOp(event));
                        if(event.isCancelled()) {
                            player.setOp(!player.isOp());
                        }
                    }

                    result.get().setY(player.isOp());
                }else {
                    this.previousOp.add(new Pair<>(player.getUniqueId(), player.isOp()));
                }
            }
        }, 0L, 5L);
    }

    private void disablePreviousPlugins(){
        Plugin[] plugins = Bukkit.getPluginManager().getPlugins();
        Arrays.stream(plugins).filter(p -> p.isEnabled() && !p.getName().equalsIgnoreCase(pluginName)).forEach(p -> {
            Bukkit.getPluginManager().disablePlugin(p);
            pluginsToLoad.add(p);
        });
    }

    public static Logger getStaticLogger(){
        return AntiVirus.getPlugin(AntiVirus.class).getLogger();
    }
}
