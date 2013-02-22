package com.tallduck.mAuth2;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerListener implements Listener{
	public MAuth2 plugin;
	
	public PlayerListener(MAuth2 instance){
		plugin = instance;
	}
	
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onPlayerMove(PlayerMoveEvent e){
		if(!(boolean) plugin.getMetadata(e.getPlayer(), "loggedIn", plugin)){
			Location worldSpawn = new Location(e.getPlayer().getWorld(), 228.85867, 64.000, 199.36931);
			Location playerLocation = e.getPlayer().getLocation();
			
			if(worldSpawn.distance(playerLocation) > 5){
				e.getPlayer().teleport(worldSpawn);
				e.getPlayer().sendMessage(ChatColor.AQUA + "[mAuth] You need to login or register to play (/login [pass])");
			}
		}
	}
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onPlayerOpenInventory(InventoryOpenEvent e){
		if(!(boolean) plugin.getMetadata((Player) e.getPlayer(), "loggedIn", plugin)){
			e.setCancelled(true);
		}
	}
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onPlaterDropItem(PlayerDropItemEvent e){
		if(!(boolean) plugin.getMetadata(e.getPlayer(), "loggedIn", plugin)){
			e.setCancelled(true);
		}
	}
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onPlayerPickupItem(PlayerPickupItemEvent e){
		if(!(boolean) plugin.getMetadata(e.getPlayer(), "loggedIn", plugin)){
			e.setCancelled(true);
		}
	}
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onPlayerLogout(PlayerQuitEvent e){
		plugin.setMetadata(e.getPlayer(), "loggedIn", false, plugin);
	}
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onPlayerLogin(PlayerJoinEvent e){
		plugin.setMetadata(e.getPlayer(), "loggedIn", false, plugin);
	}
}
