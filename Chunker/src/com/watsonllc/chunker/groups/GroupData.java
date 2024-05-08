package com.watsonllc.chunker.groups;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.bukkit.configuration.file.YamlConfiguration;

import com.watsonllc.chunker.Main;

public class GroupData {

	private static File groupsFile = new File(Main.instance.getDataFolder(), "groups.yml");
	private static YamlConfiguration groups = YamlConfiguration.loadConfiguration(groupsFile);
	
	public static void create() {
		if(groupsFile.exists())
			return;
		else
			save();
	}
	
	public static void save() {
		try {
			groups.save(groupsFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void set(String string, Object object) {
		groups.set(string, object);
	}
	
	public static Object get(String string) {
		return groups.get(string);
	}
	
	public static int getInt(String string) {
		return groups.getInt(string);
	}
	
	public static String getString(String string) {
		return groups.getString(string);
	}
	
	public static List<String> getStringList(String string){
		return groups.getStringList(string);
	}	
}