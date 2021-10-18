package punisher.punisher.command;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import punisher.punisher.Punisher;



public class UnbanSubCommand implements SubCommand{
    @Override
    public void exec(CommandSender sender, String[] args) {
        if (args[0].equalsIgnoreCase("unban")) {
            Bukkit.getScheduler().runTaskAsynchronously(Punisher.getInstance(), () -> {
                OfflinePlayer target = Bukkit.getOfflinePlayer(args[1]);
                Bukkit.getScheduler().runTask(Punisher.getInstance(), () -> {
                    String uuid = target.getUniqueId().toString();
                    FileConfiguration config = Punisher.getInstance().getConfig();
                    config.set(uuid + ".banned", false);
                    Punisher.getInstance().saveConfig();
                    sender.sendMessage(ChatColor.RED + "Unbanned " + target.getName());
                    });
                });
            }
        }
    @Override
    public String[] getAliases() {
        return new String[]{"unban"};
    }
}
