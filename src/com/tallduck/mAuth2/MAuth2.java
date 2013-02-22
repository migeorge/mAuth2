package com.tallduck.mAuth2;

import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class MAuth2 extends JavaPlugin{
	String version = "0.0.2";
	private PlayerListener PlayerListener = new PlayerListener(this);
	
	@Override
	public void onEnable(){
		PluginManager pm = getServer().getPluginManager();
		pm.registerEvents(this.PlayerListener, this);
		
		getLogger().info("[" + version + "] Enabled");
	}
	@Override
	public void onDisable(){
		getLogger().info("[" + version + "] Disabled");
	}
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
		Player player = (Player)sender;
		if(cmd.getName().equalsIgnoreCase("hello")){
			player.sendMessage("[mAuth2] Hello there!");
		}
		if(cmd.getName().equalsIgnoreCase("login")){
			setMetadata(player, "loggedIn", true, this);
			player.sendMessage(ChatColor.AQUA + "[mAuth] You have successfully logged in!");
		}
		return true;
	}
	
	//CUSTOM METHODS	
	public void setMetadata(Player player, String key, Object value, Plugin plugin){
		  player.setMetadata(key,new FixedMetadataValue(plugin,value));
	}
	
	public Object getMetadata(Player player, String key, Plugin plugin){
	  List<MetadataValue> values = player.getMetadata(key);  
	  for(MetadataValue value : values){
	     if(value.getOwningPlugin().getDescription().getName().equals(plugin.getDescription().getName())){
	        return value.value();
	     }
	  }
	return values;
	}
}
