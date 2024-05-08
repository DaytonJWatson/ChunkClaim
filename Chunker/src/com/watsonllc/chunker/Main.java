package com.watsonllc.chunker;

import org.bukkit.plugin.java.JavaPlugin;

import com.watsonllc.chunker.groups.GroupData;

public class Main extends JavaPlugin {
	
	public static Main instance;
	
	@Override
	public void onEnable() {
		instance = this;
		
		GroupData.create();
	}
	
}
