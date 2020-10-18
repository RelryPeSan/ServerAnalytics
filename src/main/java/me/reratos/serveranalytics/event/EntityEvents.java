package me.reratos.serveranalytics.event;

import me.reratos.serveranalytics.dao.EntityDeathEventDAO;
import me.reratos.serveranalytics.dao.PlayerDAO;
import me.reratos.serveranalytics.model.EntityDeathEventModel;
import me.reratos.serveranalytics.model.PlayerModel;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;

public class EntityEvents implements Listener {

    private PlayerModel getPlayerModel(Player player) {
        PlayerDAO pdao = new PlayerDAO();
        PlayerModel pm = pdao.findByUuid(player.getUniqueId());

        if(pm == null) {
            pm = new PlayerModel(player);

            pdao.save(pm);
        }

        return pm;
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onEntityDeath(EntityDeathEvent entityDeathEvent) {
        EntityDeathEventModel edem = null;
        LivingEntity entityDead = entityDeathEvent.getEntity();
        Player killer = entityDead.getKiller();
        PlayerModel pmKiller = null;

        // se a entidade não morreu por um player é ignorado
        if (!(entityDead instanceof Player) && killer == null) return;

        if(killer != null) {
            pmKiller = getPlayerModel(killer);

            if(entityDead instanceof Player) {
                PlayerModel pmDead = getPlayerModel((Player) entityDead);
                edem = new EntityDeathEventModel(pmDead, pmKiller);
            } else {
                edem = new EntityDeathEventModel(entityDead, pmKiller);
            }

        } else {
            PlayerModel pmDead = getPlayerModel((Player) entityDead);

            edem = new EntityDeathEventModel(pmDead);
        }

        edem.setDroppedExp(entityDeathEvent.getDroppedExp());

        try {
            edem.setLastDamageCause(entityDeathEvent.getEntity().getLastDamageCause().getCause().name());
        } catch (NullPointerException ignored) {}
        edem.setLastDamage(entityDeathEvent.getEntity().getLastDamage());

        EntityDeathEventDAO edeDAO = new EntityDeathEventDAO();

        edeDAO.save(edem);
    }

}
