package punisher.punisher;

import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import punisher.punisher.command.PunisherCommandExecutor;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.text.ParseException;

import java.util.Objects;
public final class Punisher extends JavaPlugin implements Listener {

    private static Punisher instance;

    public static Punisher getInstance() {
        return instance;
    }
    @Override
    public void onEnable() {
        saveDefaultConfig();
        Punisher.instance = this;
        Objects.requireNonNull(getCommand("punisher")).setExecutor(new PunisherCommandExecutor());
        getServer().getPluginManager().registerEvents(this, this);
    }
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        e.setJoinMessage(ChatColor.BLUE + "A player has logged in: " + e.getPlayer().getName());
        String uuid = e.getPlayer().getUniqueId().toString();
        if(!getConfig().contains(uuid)) {
            FileConfiguration config = Punisher.getInstance().getConfig();
            config.set(uuid + ".banned", false);
            Punisher.getInstance().saveConfig();
        }
        String banned = getConfig().getString(e.getPlayer().getUniqueId() + ".banned");
        if (Objects.equals(banned, "false")) return ;
        String reason = getConfig().getString(e.getPlayer().getUniqueId() + ".reason");
        String time = getConfig().getString(e.getPlayer().getUniqueId() + ".time");
        if (Objects.equals(time, "never")) {
            Objects.requireNonNull(e.getPlayer()).kickPlayer(ChatColor.DARK_RED + "» " + ChatColor.RED + "You have a banned \n" + ChatColor.DARK_RED + "» " + ChatColor.RED + "Reason: " + reason + "!\n" + ChatColor.DARK_RED + "» " + ChatColor.RED + "Expire: Not Expire");
            return;
        }
        Calendar cl = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd-HH");
        String str = sdf.format(cl.getTime());
        getLogger().info(time + str);
        SimpleDateFormat checkformat = new SimpleDateFormat("yyyy-MM-dd-HH");
        try {
            Date date1 = checkformat.parse(time);
            Date date2 = checkformat.parse(str);
            if (date1.before(date2)) {
                FileConfiguration config = Punisher.getInstance().getConfig();
                config.set(uuid + ".banned", false);
                return;
            }
        } catch (ParseException ex) {
            ex.printStackTrace();
        }
        Objects.requireNonNull(e.getPlayer()).kickPlayer(ChatColor.DARK_RED + "» " + ChatColor.RED + "You have a banned \n" + ChatColor.DARK_RED + "» " + ChatColor.RED + "Reason: " + reason + "!\n" + ChatColor.DARK_RED + "» " + ChatColor.RED + "Expire: " + time);

    }
    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent e) {
        e.setQuitMessage(ChatColor.BLUE + "A player has left the game: " + e.getPlayer().getName());
        String reason = getConfig().getString(e.getPlayer().getUniqueId() + ".reason");
        if (Objects.equals(reason, "False")) return ;
        e.setQuitMessage("");
    }

}