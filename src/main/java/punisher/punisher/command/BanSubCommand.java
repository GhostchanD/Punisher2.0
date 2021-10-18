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
import java.util.Date;
import java.util.Objects;


public class BanSubCommand implements SubCommand {

//    private Pattern HOURS = Pattern.compile("([0-9]*)h");
//   private Pattern MINUTES = Pattern.compile("([0-9]*)m");
//    private Pattern SECONDS = Pattern.compile("([0-9]*)s");

    //    private Date parse(String str) throws NumberFormatException {
//        Matcher matcher = HOURS.matcher(str);
//        int seconds = 0;
//        if (matcher.find()) {
//            seconds += Integer.parseInt(matcher.group(1)) * 60 * 60;
//        }
//        matcher = MINUTES.matcher(str);
//        if (matcher.find()) {
//            seconds += Integer.parseInt(matcher.group(1)) * 60;
//        }
//        matcher = SECONDS.matcher(str);
//        if (matcher.find()) {
//            seconds += Integer.parseInt(matcher.group(1));
//        }
//        return Date.from(ZonedDateTime.of(LocalDateTime.now().plus(seconds, ChronoUnit.SECONDS), ZoneId.systemDefault()).toInstant());
//    }
    public static int intparse(char str2) {
        String str = String.valueOf(str2);
        if (Objects.equals(str, "9")) {
            return 9;
        }
        if (Objects.equals(str, "8")) {
            return 8;
        }
        if (Objects.equals(str, "7")) {
            return 7;
        }
        if (Objects.equals(str, "6")) {
            return 6;
        }
        if (Objects.equals(str, "5")) {
            return 5;
        }
        if (Objects.equals(str, "4")) {
            return 4;
        }
        if (Objects.equals(str, "3")) {
            return 3;
        }
        if (Objects.equals(str, "2")) {
            return 2;
        }
        if (Objects.equals(str, "1")) {
            return 1;
        }
        return 0;
    }

    @Override
    public void exec(CommandSender sender, String[] args) {
        if (args[0].equalsIgnoreCase("ban")) {
            Bukkit.getScheduler().runTaskAsynchronously(Punisher.getInstance(), () -> {
                OfflinePlayer target = Bukkit.getOfflinePlayer(args[1]);
                Bukkit.getScheduler().runTask(Punisher.getInstance(), () -> {
                    boolean banned = Punisher.getInstance().getConfig().getBoolean(target.getUniqueId() + ".banned", false);
                    if (banned) {
                        sender.sendMessage(ChatColor.RED + "That player is already banned");
                        return;
                    }
                    if (target.isOnline()) {
                        target.getPlayer().kickPlayer(ChatColor.DARK_RED + "» " + ChatColor.RED + "You are banned by " + sender.getName() + " Reason: " + args[2] + "!");
                    }
                    // set/save config
                    Date date = new Date();
                    Calendar cl = Calendar.getInstance();
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd-HH-mm");
                    FileConfiguration config = Punisher.getInstance().getConfig();
                    String uuid = target.getUniqueId().toString();
                    String str = args[3];
                    int yn = str.indexOf("y");
                    int ynb = 1;
                    int yn4 = 0;
                    if (str.contains("y")) {
                        sender.sendMessage("okay loaded y");
                        int i = 0;
                        while (i < 1) {
                            int yn2 = yn - 1;
                            sender.sendMessage(String.valueOf(yn2));
                            if(yn2 == -1) {
                                cl.add(Calendar.YEAR, yn4);
                                break;
                            }
                            char yn3 = str.charAt(yn2);
                            if(Character.isDigit(yn3)) {
                                int result = intparse(yn3);
                                sender.sendMessage("get result"+ result);
                                yn4 += result * ynb;
                                yn = yn - 1;
                                ynb = ynb*10;
                                if(yn3 == 0) {
                                    cl.add(Calendar.YEAR, yn4);
                                    break;
                                }
                            } else {
                                cl.add(Calendar.YEAR, yn4);
                                break;
                            }
                        }
                    }
                    cl.add(Calendar.MONTH, 0);
                    cl.add(Calendar.DAY_OF_MONTH, 0);
                    cl.add(Calendar.HOUR, 0);
                    cl.add(Calendar.MINUTE, 0);
                    String str2 = sdf.format(cl.getTime());
                    config.set(uuid + ".banned", true);
                    config.set(uuid + ".reason", args[2]);
                    config.set(uuid + ".time", str2);
                    Punisher.getInstance().saveConfig();
                    // send messages
                    for (Player ops : Bukkit.getOnlinePlayers()) {
                        if (ops.isOp()) {
                            ops.sendMessage(ChatColor.GOLD + "[Log]");
                            ops.sendMessage(ChatColor.GOLD + "Method: " + args[0]);
                            ops.sendMessage(ChatColor.GOLD + "Player Name: " + args[1]);
                            ops.sendMessage(ChatColor.GOLD + "Reason: " + args[2]);
                            ops.sendMessage(ChatColor.GOLD + "Time: " + str2);
                        }
                    }
                });
            });
        }
    }
    @Override
    public String[] getAliases() {
        return new String[]{"ban"};
    }
}
