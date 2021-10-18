package punisher.punisher.command;

import org.bukkit.command.CommandSender;

public interface SubCommand {
    void exec(CommandSender sender, String[] args);
    String[] getAliases();
}
