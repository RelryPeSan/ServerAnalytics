package me.reratos.serveranalytics.event;

import me.reratos.serveranalytics.dao.PlayerDAO;
import me.reratos.serveranalytics.dao.PlayerEventDAO;
import me.reratos.serveranalytics.model.PlayerEventModel;
import me.reratos.serveranalytics.model.PlayerModel;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerEvents implements Listener {

    private PlayerModel getPlayerModel(Player player) {
        PlayerDAO pdao = new PlayerDAO();
        PlayerModel pm = pdao.findByUuid(player.getUniqueId());

        if(pm == null) {
            pm = new PlayerModel(player);

            pdao.save(pm);
        }

        return pm;
    }

    private void insertEvent(Player player, String eventName) {
        PlayerModel pm = getPlayerModel(player);

        PlayerEventDAO pedao = new PlayerEventDAO();
        PlayerEventModel pem = new PlayerEventModel();
        pem.setPlayer(pm);
        pem.setEventType(eventName);

        pedao.save(pem);
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onPlayerJoin(PlayerJoinEvent playerJoinEvent) {
        insertEvent(playerJoinEvent.getPlayer(), playerJoinEvent.getEventName());
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onPlayerQuit(PlayerQuitEvent playerQuitEvent) {
        insertEvent(playerQuitEvent.getPlayer(), playerQuitEvent.getEventName());
    }

}
