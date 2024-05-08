package com.watsonllc.chunker.groups;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Chunk;
import org.bukkit.World;
import org.bukkit.entity.Player;

public class GroupManager {

	private static final int defaultPower = 15;

	public static void createGroup(String name, Player owner) {
		String PATH = "groups." + name;

		List<String> members = new ArrayList<>();
		List<String> chunks = new ArrayList<>();

		GroupData.set(PATH + ".powner", defaultPower);
		GroupData.set(PATH + ".owner", owner.getName());
		GroupData.set(PATH + ".members", members);
		GroupData.set(PATH + ".chunks", chunks);
		GroupData.save();
	}

	private String groupName;

	public GroupManager(String groupName) {
		this.groupName = groupName;
	}

	public int getPower() {
		return (int) GroupData.get("groups." + groupName + ".power");
	}

	public String getOwner() {
		return (String) GroupData.get("groups." + groupName + ".owner");
	}

	public boolean isOwner(Player player) {
		if (getOwner().equalsIgnoreCase(player.getName()))
			return true;
		else
			return false;
	}

	public List<String> getMembers() {
		return GroupData.getStringList("groups." + groupName + ".members");
	}

	public String convertChunkToData(Chunk chunk) {
		String world = chunk.getWorld().toString();
		int x = chunk.getX();
		int z = chunk.getZ();

		String chunkData = world + ":" + x + "-" + z;
		
		return chunkData;
	}
	
	public Chunk convertChunkFromData(String string) {
		String[] parts = string.split(":");
		String worldName = parts[0];
		String[] coordinates = parts[1].split("-");
		int chunkX = Integer.parseInt(coordinates[0]);
		int chunkZ = Integer.parseInt(coordinates[1]);

		World world = Bukkit.getWorld(worldName);
		
		if(world == null)
			return null;
		
		return world.getChunkAt(chunkX, chunkZ);
	}
	
	public List<String> getChunks() {
		return GroupData.getStringList("groups." + groupName + ".chunks");
	}

	public boolean containsChunk(Chunk chunk) {
		List<String> chunkList = getChunks();

		int x = chunk.getX();
		int z = chunk.getZ();

		String chunkData = x + "-" + z;

		if (chunkList.contains(chunkData))
			return true;
		else
			return false;
	}

	public void addChunk(Chunk chunk) {
		List<String> chunkList = getChunks();

		int x = chunk.getX();
		int z = chunk.getZ();

		String newChunk = x + "-" + z;

		chunkList.add(newChunk);
	}

	public void removeChunk(Chunk chunk) {
		List<String> chunkList = getChunks();

		int x = chunk.getX();
		int z = chunk.getZ();

		String chunkData = x + "-" + z;

		if (!chunkList.contains(chunkData))
			return;

	}
}