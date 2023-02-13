package org.hinoob.antivirus.module;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.hinoob.antivirus.AntiVirus;
import org.hinoob.antivirus.event.PlayerOpEvent;
import org.hinoob.antivirus.event.PluginLoadEvent;
import org.hinoob.antivirus.util.PluginDeleter;
import org.hinoob.antivirus.util.Settings;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SuspiciousPluginDescription extends Module {

    private final List<String> badAuthors = new ArrayList<>();
    private final List<String> badDescriptions = new ArrayList<>();

    public SuspiciousPluginDescription(){
        
    }

    @Override
    public void onPluginLoaded(PluginLoadEvent event) {
        String reason = "";
        for(String badDescription : badDescriptions){
            if(event.getPlugin().getDescription().getDescription().toLowerCase().contains(badDescription.toLowerCase())) {
                reason = "bad_description (" + badDescription + ")";
                break;
            }
        }
        if(reason.length() == 0){
            for(String badAuthor : badAuthors){
                if(event.getPlugin().getDescription().getAuthors().stream().anyMatch(s -> s.equalsIgnoreCase(badAuthor))){
                    reason = "bad_author (" + badAuthor + ")";
                    break;
                }
            }
        }

        if(reason.length() > 0){
            AntiVirus.getStaticLogger().info("Detected '" + event.getPlugin().getName() + "' as bad! (" + reason + ")");
            AntiVirus.getStaticLogger().info("Action taken: disabled" + (Settings.DELETE_DETECTED_PLUGINS ? " & deleted" : ""));
            event.setCancelled(true);

            if(Settings.DELETE_DETECTED_PLUGINS){
                PluginDeleter.delete(event.getPlugin());
            }
        }
    }

    @Override
    public void onPlayerOp(PlayerOpEvent event) {

    }
}
