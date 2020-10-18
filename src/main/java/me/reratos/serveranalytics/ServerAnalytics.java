package me.reratos.serveranalytics;

import me.reratos.serveranalytics.connections.Database;
import me.reratos.serveranalytics.connections.ServerDatabase;
import me.reratos.serveranalytics.dao.ServerDAO;
import me.reratos.serveranalytics.event.EntityEvents;
import me.reratos.serveranalytics.event.PlayerEvents;
import me.reratos.serveranalytics.model.ServerModel;
import me.reratos.serveranalytics.utils.ConfigConstants;
import org.bukkit.Bukkit;
import org.bukkit.Server;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.HandlerList;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.*;

public class ServerAnalytics extends JavaPlugin {

    public static FileConfiguration config;
    public static UUID serverUUID = null;

    private static Plugin plugin;
    private static final Server server = Bukkit.getServer();
    private static ServerDatabase serverDatabase;
    public static ServerModel serverModel;

    @Override
    public void onEnable() {
        plugin = this;

        try {
            initConfig();

            Database.initialize(serverDatabase);

            ServerDAO serverDAO = new ServerDAO();

            serverModel = serverDAO.findByUuid(serverUUID);

            if(serverModel == null) {
                serverModel = new ServerModel();

                serverModel.setName(Bukkit.getServer().getName());
                serverModel.setServerUUID(serverUUID);

                serverDAO.save(serverModel);
            }

        } catch (Exception e) {
            e.printStackTrace();
            onDisable();
            return;
        }

        initListeners();
    }

    @Override
    public void onDisable() {
        Database.closeConnection();

        HandlerList.unregisterAll(this);
        Bukkit.getScheduler().cancelTasks(this);

        Bukkit.getPluginManager().disablePlugin(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        return super.onCommand(sender, command, label, args);
    }

    private void initConfig() {
        saveDefaultConfig();
        config = getConfig().options().copyDefaults(true).configuration();
        saveConfig();

        // verifica se no arquivo já está configurado este servidor
        // caso contrario é atrelado um UUID aleatório a ele
        String uuid = config.getString(ConfigConstants.SERVER_UUID);

        if (uuid != null && uuid.isEmpty()) {
            serverUUID = UUID.randomUUID();
            config.set(ConfigConstants.SERVER_UUID, serverUUID.toString());
        } else {
            serverUUID = UUID.fromString(uuid);
        }

        // pega a configuração do arquivo para comunicação com o banco
        serverDatabase = new ServerDatabase();

        serverDatabase.setDialect(config.getString(ConfigConstants.DATABASE_DIALECT));
        serverDatabase.setUrl(config.getString(ConfigConstants.DATABASE_URL));
        serverDatabase.setPort(config.getInt(ConfigConstants.DATABASE_PORT));
        serverDatabase.setSchema(config.getString(ConfigConstants.DATABASE_SCHEMA));
        serverDatabase.setTimeZone(config.getString(ConfigConstants.DATABASE_TIME_ZONE));
        serverDatabase.setUsername(config.getString(ConfigConstants.DATABASE_USERNAME));
        serverDatabase.setPasswordBase64(config.getString(ConfigConstants.DATABASE_PASSWORD_BASE64));

        saveConfig();
    }

    private void initListeners() {
        server.getPluginManager().registerEvents(new PlayerEvents(), this);
        server.getPluginManager().registerEvents(new EntityEvents(), this);
    }

}
