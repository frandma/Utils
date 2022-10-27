package me.frandma.utils;

import lombok.Getter;
import lombok.Setter;
import me.frandma.utils.commands.*;
import me.frandma.utils.listeners.*;
import me.frandma.utils.sqlite.SQL;
import me.frandma.utils.user.UserData;
import net.milkbowl.vault.chat.Chat;
import org.bukkit.Bukkit;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

@Getter
@Setter
public final class Utils extends JavaPlugin {
    public static Utils instance;
    public static Chat chat;
    public static File file;
    private final UserData userData = new UserData();

    @Override
    public void onEnable() {
        instance = this;
        file = getFile();
        getConfig().options().copyDefaults();
        saveDefaultConfig();
        registerCommands();
        registerListeners();
        SQL.setup();
        papi();
        setupChat();
    }

    private void registerCommands() {
        getCommand("clear").setExecutor(new ClearCommand());
        getCommand("discord").setExecutor(new DiscordCommand());
        getCommand("gamemode").setExecutor(new GamemodeCommand());
        getCommand("give").setExecutor(new GiveCommand());
        getCommand("message").setExecutor(new MessageCommand());
        getCommand("mod").setExecutor(new ModCommand());
        getCommand("mute").setExecutor(new MuteCommand());
        getCommand("powertool").setExecutor(new PowerToolCommand());
        getCommand("staffchat").setExecutor(new StaffChatCommand());
        getCommand("sudo").setExecutor(new SudoCommand());
        getCommand("teleport").setExecutor(new TeleportCommand());
        getCommand("tphere").setExecutor(new TphereCommand());
        getCommand("vanish").setExecutor(new VanishCommand());
        getCommand("view").setExecutor(new ViewCommand());
    }

    private void registerListeners() {
        Bukkit.getPluginManager().registerEvents(new PlayerChatListener(), this);
        Bukkit.getPluginManager().registerEvents(new InventoryClickListener(), this);
        Bukkit.getPluginManager().registerEvents(new InventoryCloseListener(), this);
        Bukkit.getPluginManager().registerEvents(new PlayerInteractListener(), this);
        Bukkit.getPluginManager().registerEvents(new PlayerJoinListener(), this);
        Bukkit.getPluginManager().registerEvents(new PlayerLoginListener(), this);
        Bukkit.getPluginManager().registerEvents(new PlayerPreLoginListener(), this);
        Bukkit.getPluginManager().registerEvents(new PlayerQuitListener(), this);
    }

    private void papi() {
        if (Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null) {
            new PAPI(this).register();
        }
    }

    private boolean setupChat() {
        RegisteredServiceProvider<Chat> rsp = getServer().getServicesManager().getRegistration(Chat.class);
        chat = rsp.getProvider();
        return chat != null;
    }

    public static Chat getChat() {
        return chat;
    }

    public UserData getUserData() {
        return this.userData;
    }
}
