package org.hinoob.antivirus.util;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class NMSUtil {

    private static final String VERSION_NAME = Bukkit.getServer().getClass().getPackage().getName()
            .replace(".", ",").split(",")[3];
    private static final String OBC = "org.bukkit.craftbukkit." + VERSION_NAME + ".";
    private static final String NMS = "net.minecraft.server." + VERSION_NAME + ".";

    public static Class<?> CRAFT_PLAYER_CLASS;

    public static void load (){
        CRAFT_PLAYER_CLASS = getOBCClass("entity.CraftPlayer");
    }

    private static Class<?> getNMSClass(String name){
        try {
            return Class.forName(NMS + name);
        }catch(ClassNotFoundException ex){
            return null;
        }
    }

    private static Class<?> getOBCClass(String name){
        try {
            return Class.forName(OBC + name);
        }catch(ClassNotFoundException ex){
            return null;
        }
    }

    public static Object getCraftPlayer(Player player){
        try {
            return CRAFT_PLAYER_CLASS.cast(player);
        }catch(ClassCastException ex){
            return null;
        }
    }
}
