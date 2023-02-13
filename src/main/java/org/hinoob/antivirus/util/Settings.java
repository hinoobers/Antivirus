package org.hinoob.antivirus.util;

import org.hinoob.antivirus.AntiVirus;

public class Settings {

    public static boolean DELETE_DETECTED_PLUGINS = false;
    public static boolean NO_MORE_OPS = false;

    public static void load(AntiVirus virus){
        DELETE_DETECTED_PLUGINS = virus.getConfig().getBoolean("delete-detected-plugins");
        NO_MORE_OPS = virus.getConfig().getBoolean("no-more-ops");
    }
}
