package cz.flyultra;

import org.apache.commons.io.FileUtils;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;

public class WorldRestorer {

    private String worldName;
    private File backupDirectory;
    private JavaPlugin plugin;

    public WorldRestorer(JavaPlugin plugin, String world) {
        this.worldName = world;
        this.plugin = plugin;
        backupDirectory = new File(plugin.getDataFolder(), "backups");
    }

    public void backupWorld() {
        World world = Bukkit.getWorld(worldName);
        if (world != null) {
            try {
                File worldFolder = world.getWorldFolder();
                File backupFolder = new File(backupDirectory, worldName);

                if (!backupFolder.exists()) {
                    backupFolder.mkdirs();
                } else {
                    restoreWorld();
                }
                FileUtils.copyDirectory(worldFolder, backupFolder);
                plugin.getLogger().info("World has been saved " + worldName + ".");
            } catch (IOException e) {
                plugin.getLogger().severe("Something went wrong with saving world. ("+worldName+")");
                e.printStackTrace();
            }
        }
    }

    public void restoreWorld() {
        File backupFolder = new File(backupDirectory, worldName);
        if (backupFolder.exists()) {
            World world = Bukkit.getWorld(worldName);
            if (world != null) {
                Bukkit.unloadWorld(world, false);
            }
            assert world != null;
            deleteWorld(world.getWorldFolder());
            try {
                FileUtils.copyDirectory(backupFolder, world.getWorldFolder());
                plugin.getLogger().info("World has been restored " + worldName + ".");
            } catch (IOException e) {
                plugin.getLogger().severe("Something went wrong " + worldName + " (backup)");
                e.printStackTrace();
            }
            WorldCreator worldCreator = new WorldCreator(worldName);
            Bukkit.createWorld(worldCreator);
        }
    }

    public void deleteWorld(File worldFolder) {
        if (worldFolder.exists()) {
            try {
                FileUtils.deleteDirectory(worldFolder);
            } catch (IOException e) {
                plugin.getLogger().severe("Something went wrong: " + worldFolder.getAbsolutePath());
                e.printStackTrace();
            }
        }
    }
}