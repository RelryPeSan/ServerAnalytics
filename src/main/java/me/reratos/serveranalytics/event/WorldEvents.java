package me.reratos.serveranalytics.event;

import me.reratos.serveranalytics.dao.WorldDAO;
import me.reratos.serveranalytics.dao.WorldEventDAO;
import me.reratos.serveranalytics.model.WorldEventModel;
import me.reratos.serveranalytics.model.WorldModel;
import org.bukkit.World;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.world.*;

public class WorldEvents implements Listener {

    public static WorldModel getWorldModel(World world) {
        WorldDAO worldDAO = new WorldDAO();
        WorldModel worldModel = worldDAO.findByName(world.getName());

        return worldModel;
    }

    private WorldModel saveOrUpdateWorld(World world) {
        WorldDAO worldDAO = new WorldDAO();
        WorldModel worldModel = worldDAO.findByName(world.getName());

        if(worldModel == null){
            worldModel = new WorldModel(world.getName());
        }

        worldModel.setAutoSave(world.isAutoSave());
        worldModel.setDifficulty(world.getDifficulty().name());
        worldModel.setEnvironment(world.getEnvironment().name());
        worldModel.setHardcore(world.isHardcore());
        worldModel.setViewDistance(world.getViewDistance());

        return worldDAO.saveOrUpdate(worldModel);

//        return worldDAO.findByName(worldModel.getName());
    }

    private WorldEventModel worldEvent(WorldEvent worldEvent) {
        WorldEventDAO worldEventDAO = new WorldEventDAO();
        WorldEventModel worldEventModel = new WorldEventModel();
        WorldModel worldModel = saveOrUpdateWorld(worldEvent.getWorld());

        worldEventModel.setWorldModel(worldModel);
        worldEventModel.setEventType(worldEvent.getEventName());

        return worldEventDAO.save(worldEventModel);
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onWorldInitEvent(WorldInitEvent worldInitEvent) {

    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onWorldLoadEvent(WorldLoadEvent worldLoadEvent) {
        worldEvent(worldLoadEvent);
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onWorldUnloadEvent(WorldUnloadEvent worldUnloadEvent) {
        worldEvent(worldUnloadEvent);
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onWorldSaveEvent(WorldSaveEvent worldSaveEvent) {
//        worldEvent(worldSaveEvent);
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onPortalCreateEvent(PortalCreateEvent portalCreateEvent) {
        worldEvent(portalCreateEvent);
    }
}
