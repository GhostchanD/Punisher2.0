package punisher.punisher.command;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import punisher.punisher.Punisher;


import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Objects;

public class BanlistSubCommand implements SubCommand{
    @Override
    public void exec(CommandSender sender, String[] args) {
        sender.sendMessage(ChatColor.RED + "Coding now...");
    }
    public String[] getAliases() {
        return new String[]{"banlist"};
    }
}
