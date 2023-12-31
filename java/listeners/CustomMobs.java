package listeners;

import misc.Plugin;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.List;
import java.util.Objects;
import java.util.Random;

public class CustomMobs implements Listener {
	static Random random = new Random();

	@EventHandler
	public void onEntitySpawn(EntitySpawnEvent e) {

		// MAXOR, STORM, GOLDOR, NECRON
		try {
			if(e.getEntity() instanceof Wither wither) {
				switch(random.nextInt(4)) {
					// MAXOR
					case 0 -> {
						wither.setCustomName(ChatColor.GOLD + String.valueOf(ChatColor.BOLD) + "﴾ " + ChatColor.RED + ChatColor.BOLD + "Maxor" + ChatColor.GOLD + ChatColor.BOLD + " ﴿");
						Objects.requireNonNull(wither.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED)).setBaseValue(2.0);
						Objects.requireNonNull(wither.getAttribute(Attribute.GENERIC_FLYING_SPEED)).setBaseValue(2.0);
						Bukkit.broadcastMessage(ChatColor.RED + String.valueOf(ChatColor.BOLD) + "MAXOR, the fastest Wither in the universe, has come to destroy you 0.01 seconds faster than all other Withers!");
					}

					// STORM
					case 1 -> {
						wither.setCustomName(ChatColor.GOLD + String.valueOf(ChatColor.BOLD) + "﴾ " + ChatColor.RED + ChatColor.BOLD + "Storm" + ChatColor.GOLD + ChatColor.BOLD + " ﴿");
						wither.getServer().dispatchCommand(Bukkit.getConsoleSender(), "weather thunder 1000000");
						List<Entity> entities = (List<Entity>) wither.getWorld().getNearbyEntities(wither.getLocation(), 64, 64, 64);
						for(Entity entity : entities) {
							if(entity instanceof LivingEntity entity1) {
								wither.getWorld().spawnEntity(entity1.getLocation(), EntityType.LIGHTNING);
								entity1.damage(10);
							}
						}
						Bukkit.broadcastMessage(ChatColor.RED + String.valueOf(ChatColor.BOLD) + "STORM, most explosive Wither in the universe, has come to smite you with his Lightning!");
					}

					// GOLDOR
					case 2 -> {
						wither.setCustomName(ChatColor.GOLD + String.valueOf(ChatColor.BOLD) + "﴾ " + ChatColor.RED + ChatColor.BOLD + "Goldor" + ChatColor.GOLD + ChatColor.BOLD + " ﴿");
						Objects.requireNonNull(wither.getAttribute(Attribute.GENERIC_ARMOR)).setBaseValue(12.5);
						Bukkit.broadcastMessage(ChatColor.RED + String.valueOf(ChatColor.BOLD) + "GOLDOR, the most defensive Wither in the universe, has come to stand in your way!");
					}

					// NECRON
					case 3 -> {
						wither.setCustomName(ChatColor.GOLD + String.valueOf(ChatColor.BOLD) + "﴾ " + ChatColor.RED + ChatColor.BOLD + "Necron" + ChatColor.GOLD + ChatColor.BOLD + " ﴿");
						Bukkit.broadcastMessage(ChatColor.RED + String.valueOf(ChatColor.BOLD) + "NECRON, the strongest Wither in the universe, has come to wipe you straight off the planet!");
					}
				}
				wither.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, -1, 255));
				wither.setTarget(Plugin.getNearestPlayer(wither));
				wither.setCustomNameVisible(true);

				// SUPERIOR DRAGON
			} else if(e.getEntity() instanceof EnderDragon dragon) {
				if(random.nextDouble() > 0.85) {
					dragon.setCustomName(ChatColor.GOLD + String.valueOf(ChatColor.BOLD) + "﴾ " + ChatColor.RED + ChatColor.BOLD + "Superior Dragon" + ChatColor.GOLD + ChatColor.BOLD + " ﴿");
					Objects.requireNonNull(dragon.getAttribute(Attribute.GENERIC_ARMOR)).setBaseValue(6.25);
					Objects.requireNonNull(dragon.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED)).setBaseValue(0.875);
					dragon.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, -1, 1));
					dragon.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, -1, 255));
					dragon.setTarget(Plugin.getNearestPlayer(dragon));
					dragon.setCustomNameVisible(true);
					Bukkit.broadcastMessage(ChatColor.RED + String.valueOf(ChatColor.BOLD) + "The SUPERIOR DRAGON has arrived to test your strength!");
				}
			}
		} catch(Exception exception) {
			// do nothing
		}
	}
}