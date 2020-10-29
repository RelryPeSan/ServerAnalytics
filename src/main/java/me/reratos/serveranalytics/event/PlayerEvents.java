package me.reratos.serveranalytics.event;

import me.reratos.serveranalytics.dao.PlayerChangedWorldEventDAO;
import me.reratos.serveranalytics.dao.ChatEventDAO;
import me.reratos.serveranalytics.dao.PlayerDAO;
import me.reratos.serveranalytics.dao.PlayerEventDAO;
import me.reratos.serveranalytics.model.*;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.*;

public class PlayerEvents implements Listener {

    private PlayerModel getPlayerModel(Player player) {
        PlayerDAO pdao = new PlayerDAO();
        PlayerModel pm = pdao.findByUuid(player.getUniqueId());

        if(pm == null) {
            pm = new PlayerModel(player);

            pm = pdao.save(pm);
        }

        return pm;
    }

    private PlayerEventModel insertPlayerEvent(PlayerEvent playerEvent) {
        PlayerModel pm = getPlayerModel(playerEvent.getPlayer());

        PlayerEventDAO pedao = new PlayerEventDAO();
        PlayerEventModel pem = new PlayerEventModel();
        pem.setPlayer(pm);
        pem.setEventType(playerEvent.getEventName());

        return pedao.save(pem);
    }

    private void onPlayerChat(PlayerEventModel playerEventModel, String message, boolean isCommand) {
        ChatEventDAO pceDAO = new ChatEventDAO();
        ChatEventModel chatEventModel = new ChatEventModel();

        chatEventModel.setCommand(isCommand);
        chatEventModel.setMessage(message);
        chatEventModel.setPlayerEventModel(playerEventModel);

        pceDAO.save(chatEventModel);
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onPlayerJoin(PlayerJoinEvent event) {
        insertPlayerEvent(event);
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onPlayerQuit(PlayerQuitEvent event) {
        insertPlayerEvent(event);
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onPlayerChangedWorldEvent(PlayerChangedWorldEvent event) {
        PlayerEventModel playerEventModel = insertPlayerEvent(event);
        PlayerChangedWorldEventDAO pcweDAO = new PlayerChangedWorldEventDAO();
        PlayerChangedWorldEventModel pcwem = new PlayerChangedWorldEventModel();

        WorldModel wTo = WorldEvents.getWorldModel(event.getPlayer().getWorld());
        WorldModel wFrom = WorldEvents.getWorldModel(event.getFrom());

        pcwem.setPlayerEventModel(playerEventModel);
        pcwem.setWorldModelFrom(wFrom);
        pcwem.setWorldModelTo(wTo);

        pcweDAO.save(pcwem);
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onAsyncPlayerChatEvent(AsyncPlayerChatEvent event) {
        PlayerEventModel playerEventModel = insertPlayerEvent(event);
        ChatEventModel chatEventModel = new ChatEventModel();

        onPlayerChat(playerEventModel, event.getMessage(), false);
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onPlayerCommandPreprocessEvent(PlayerCommandPreprocessEvent event) {
        PlayerEventModel playerEventModel = insertPlayerEvent(event);

        onPlayerChat(playerEventModel, event.getMessage(), true);
    }

}
