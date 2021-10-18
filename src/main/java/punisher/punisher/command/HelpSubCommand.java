package punisher.punisher.command;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

public class HelpSubCommand implements SubCommand {
    @Override
    public void exec(CommandSender sender, String[] args) {
        sender.sendMessage(ChatColor.RED + "[Help]");
        sender.sendMessage(ChatColor.RED + "/punisher ban playerName reason");
        sender.sendMessage(ChatColor.RED + "/punisher unban playerName");
        sender.sendMessage(ChatColor.RED + "/punisher kick playerName reason");
    }

    @Override
    public String[] getAliases() {
        return new String[]{"help"};
    }
}