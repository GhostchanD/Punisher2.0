package punisher.punisher.command;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.CommandSender;
import punisher.punisher.Punisher;

public class KickSubCommand implements SubCommand{

    @Override
    public void exec(CommandSender sender, String[] args) {
        if (args[0].equalsIgnoreCase("kick")) {
            Bukkit.getScheduler().runTaskAsynchronously(Punisher.getInstance(), () -> {
                OfflinePlayer target = Bukkit.getOfflinePlayer(args[1]);
                Bukkit.getScheduler().runTask(Punisher.getInstance(), () -> {
                    target.getPlayer().kickPlayer(ChatColor.DARK_RED + "» " + ChatColor.RED + "You are kicked by " + sender.getName() + " Reason: " + args[2] + "!");
                });
            });
        }
    }
    @Override
    public String[] getAliases() {
        return new String[]{"kick"};
    }
}
