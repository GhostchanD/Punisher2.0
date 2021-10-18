package punisher.punisher.command;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.event.Listener;

import java.util.Arrays;


public final class PunisherCommandExecutor implements CommandExecutor, Listener {

    private static final SubCommand[] SUB_COIMMANDS = {
            new BanSubCommand(),
            new UnbanSubCommand(),
            new KickSubCommand(),
            new HelpSubCommand(),
            new BanlistSubCommand()
    };

    private void showHelp(CommandSender sender) {
        sender.sendMessage(ChatColor.RED + "Unknown command. Do \"/punisher help\" to show the command list");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length < 1) {
            showHelp(sender);
            return true;
        }

        // check for sub commands
        for (SubCommand subCommand : SUB_COIMMANDS) {
            if (!Arrays.asList(subCommand.getAliases()).contains(args[0])) continue;
            subCommand.exec(sender, args);
            return true;
        }

        // sub command not found
        showHelp(sender);
        return true;
    }
}
