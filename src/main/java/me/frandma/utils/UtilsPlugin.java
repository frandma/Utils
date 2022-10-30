package me.frandma.utils;

import lombok.Getter;
import lombok.Setter;
import me.frandma.utils.commands.*;
import me.frandma.utils.listeners.*;
import me.frandma.utils.other.PAPI;
import me.frandma.utils.user.PlayersDB;
import me.frandma.utils.user.UserData;
import net.milkbowl.vault.chat.Chat;
import org.bukkit.Bukkit;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

@Getter
@Setter
public final class UtilsPlugin extends JavaPlugin {
    private static UtilsPlugin instance;
    private static Chat chat;
    private final UserData userData = new UserData();

    @Override
    public void onEnable() {
        instance = this;
        getConfig().options().copyDefaults(true);
        saveDefaultConfig();
        registerCommands();
        registerListeners();
        PlayersDB.setup();
        papi();
        setupChat();
    }

    private void registerCommands() {
        getCommand("ban").setExecutor(new BanCommand(this));
        getCommand("clear").setExecutor(new ClearCommand());
        getCommand("discord").setExecutor(new DiscordCommand(this));
        getCommand("gamemode").setExecutor(new GamemodeCommand());
        getCommand("give").setExecutor(new GiveCommand());
        getCommand("message").setExecutor(new MessageCommand());
        getCommand("mod").setExecutor(new ModCommand());
        getCommand("mutechat").setExecutor(new MuteChatCommand(this));
        getCommand("mute").setExecutor(new MuteCommand(this));
        getCommand("powertool").setExecutor(new PowerToolCommand());
        getCommand("staffchat").setExecutor(new StaffChatCommand());
        getCommand("sudo").setExecutor(new SudoCommand());
        getCommand("teleport").setExecutor(new TeleportCommand());
        getCommand("tphere").setExecutor(new TphereCommand());
        getCommand("vanish").setExecutor(new VanishCommand());
        getCommand("view").setExecutor(new ViewCommand());
    }

    private void registerListeners() {
        Bukkit.getPluginManager().registerEvents(new PlayerChatListener(this), this);
        Bukkit.getPluginManager().registerEvents(new InventoryClickListener(), this);
        Bukkit.getPluginManager().registerEvents(new InventoryCloseListener(), this);
        Bukkit.getPluginManager().registerEvents(new PlayerInteractListener(), this);
        Bukkit.getPluginManager().registerEvents(new PlayerJoinListener(this), this);
        Bukkit.getPluginManager().registerEvents(new PlayerLoginListener(this), this);
        Bukkit.getPluginManager().registerEvents(new PlayerPreLoginListener(this), this);
        Bukkit.getPluginManager().registerEvents(new PlayerQuitListener(this), this);
    }


    public static Chat getChat() {
        return chat;
    }
    public static UtilsPlugin getInstance() { return instance; }
    public UserData getUserData() {
        return this.userData;
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

}
