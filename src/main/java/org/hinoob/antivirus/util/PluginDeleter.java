package org.hinoob.antivirus.util;

import org.bukkit.plugin.Plugin;
import org.hinoob.antivirus.AntiVirus;

import java.io.File;

public class PluginDeleter {

    public enum Response {
        SUCCESS,
        NOT_FOUND,
    }

    public static void delete(Plugin plugin){
        Response response = deleteP(plugin);
        switch (response) {
            case SUCCESS:
                AntiVirus.getStaticLogger().info("'" + plugin.getName() + "' deleted!");
                break;
            case NOT_FOUND:
                AntiVirus.getStaticLogger().info("FAILED TO FIND THE PLUGIN FILE FOR PLUGIN '" + plugin.getName() + "'");
                break;
        }
    }

    private static Response deleteP(Plugin plugin){
        File pluginsFolder = new File("plugins");
        plugin.getDataFolder().delete();
        for(File file : pluginsFolder.listFiles()){
            if(file.isDirectory()) continue;

            if(file.getName().equals(plugin.getName()) || file.getName().contains(plugin.getName())){
                file.delete();
                return Response.SUCCESS;
            }
        }

        return Response.NOT_FOUND;
    }
}
