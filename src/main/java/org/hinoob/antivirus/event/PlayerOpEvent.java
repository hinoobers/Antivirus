package org.hinoob.antivirus.event;

import lombok.Getter;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class PlayerOpEvent extends CancellableEvent{

    @Getter
    private Player player;

    public PlayerOpEvent(Player player){
        this.player = player;
    }
}
