package me.reratos.serveranalytics.event;

import me.reratos.serveranalytics.dao.ChatEventDAO;
import me.reratos.serveranalytics.dao.PluginDAO;
import me.reratos.serveranalytics.dao.PluginEventDAO;
import me.reratos.serveranalytics.dao.ServerEventDAO;
import me.reratos.serveranalytics.model.*;
import org.apache.commons.lang.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.server.*;
import org.bukkit.plugin.Plugin;

public class ServerEvents implements Listener {

    private ServerEventModel insertServerEvent(ServerEvent serverEvent) {
        ServerEventDAO serverEventDAO = new ServerEventDAO();
        ServerEventModel serverEventModel = new ServerEventModel();
        serverEventModel.setEventType(serverEvent.getEventName());
        serverEventModel.setAsynchronous(serverEvent.isAsynchronous());

        return serverEventDAO.save(serverEventModel);
    }

    private PluginModel saveOrUpdatePlugin(Plugin plugin) {
        PluginDAO pdao = new PluginDAO();
        PluginModel pluginModel = pdao.findByName(plugin.getDescription().getName());

        if(pluginModel == null) {
            pluginModel = new PluginModel(plugin.getName());
        }

        pluginModel.setVersion(plugin.getDescription().getVersion());
        pluginModel.setMain(plugin.getDescription().getMain());
        pluginModel.setAuthors(StringUtils.join(plugin.getDescription().getAuthors(), ", "));
        pluginModel.setDescription(plugin.getDescription().getDescription());
        pluginModel.setApiVersion(plugin.getDescription().getAPIVersion());
        pluginModel.setContentConfig(plugin.getConfig().saveToString());

        return pdao.saveOrUpdate(pluginModel);
    }

    private void insertPluginEvent(PluginEvent pluginEvent, ServerEventModel serverEventModel) {
        PluginEventDAO pedao = new PluginEventDAO();
        PluginEventModel pem = new PluginEventModel();
        PluginModel pluginModel = saveOrUpdatePlugin(pluginEvent.getPlugin());

        pem.setPluginModel(pluginModel);
        pem.setServerEventModel(serverEventModel);
        pem.setEnabled(pluginEvent.getPlugin().isEnabled());

        pedao.save(pem);
    }

    private void onServerChat(ServerEventModel serverEventModel, String message, boolean isCommand) {
        ChatEventDAO ceDAO = new ChatEventDAO();
        ChatEventModel chatEventModel = new ChatEventModel();

        chatEventModel.setCommand(isCommand);
        chatEventModel.setMessage(message);
        chatEventModel.setServerEventModel(serverEventModel);

        ceDAO.save(chatEventModel);
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onPluginEnable(PluginEnableEvent pluginEnableEvent) {
        ServerEventModel serverEventModel = insertServerEvent(pluginEnableEvent);
        insertPluginEvent(pluginEnableEvent, serverEventModel);
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onPluginDisable(PluginDisableEvent pluginDisableEvent) {
        ServerEventModel serverEventModel = insertServerEvent(pluginDisableEvent);
        insertPluginEvent(pluginDisableEvent, serverEventModel);
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onServerLoad(ServerLoadEvent serverLoadEvent) {
        insertServerEvent(serverLoadEvent);
        ServerLoadEvent.LoadType loadType = serverLoadEvent.getType();

        for(Plugin p : Bukkit.getPluginManager().getPlugins()) {
            saveOrUpdatePlugin(p);
        }

        if(loadType == ServerLoadEvent.LoadType.STARTUP) {

        }
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onServerCommandEvent(ServerCommandEvent event) {
        ServerEventModel serverEventModel = insertServerEvent(event);

        onServerChat(serverEventModel, event.getCommand(), true);
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onBroadcastMessageEvent(BroadcastMessageEvent event) {
        ServerEventModel serverEventModel = insertServerEvent(event);

        onServerChat(serverEventModel, event.getMessage(), false);
    }
}
