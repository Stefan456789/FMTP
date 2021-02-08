package at.FMTP.commands;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import at.FMTP.Main;

public class CMDGamemode implements CommandExecutor, TabCompleter {
    private Main main;

    public CMDGamemode(Main main) {
        this.main = main;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String arg2, String[] args) {

        GameMode gm = GameMode.SURVIVAL;
        String succm = null;
        Player player = null;
        if (sender.isOp() == true) {


            if (args.length != 1) {
                if (args.length != 2) {
                    return false;

                }

            }


            if (args.length == 1) {
                args[0] = args[0].toLowerCase();
                if (sender instanceof Player) {

                    switch (args[0].toLowerCase()) {
                        case "0":
                            gm = GameMode.SURVIVAL;
                            succm = "survival";
                            break;

                        case "survival":
                            gm = GameMode.SURVIVAL;
                            succm = "survival";
                            break;

                        case "1":
                            gm = GameMode.CREATIVE;
                            succm = "creative";
                            break;

                        case "creative":
                            gm = GameMode.CREATIVE;
                            succm = "creative";
                            break;

                        case "2":
                            gm = GameMode.ADVENTURE;
                            succm = "adventure";
                            break;

                        case "adventure":
                            gm = GameMode.ADVENTURE;
                            succm = "adventure";
                            break;

                        case "3":
                            gm = GameMode.SPECTATOR;
                            succm = "spectator";
                            break;

                        case "spectator":
                            gm = GameMode.SPECTATOR;
                            succm = "spectator";
                            break;

                        default:


                            sender.sendMessage("The gamemode " + args[0] + " does not exist!");


                    }
                    player = (Player) sender;
                }
            }

            if (args.length == 2) {

                switch (args[0]) {
                    case "0":
                    case "survival":
                        gm = GameMode.SURVIVAL;
                        succm = "survival";
                        break;

                    case "1":
                    case "creative":
                        gm = GameMode.CREATIVE;
                        succm = "creative";
                        break;

                    case "2":
                    case "adventure":
                        gm = GameMode.ADVENTURE;
                        succm = "adventure";
                        break;

                    case "3":
                    case "spectator":
                        gm = GameMode.SPECTATOR;
                        succm = "spectator";
                        break;


                    default:

                        sender.sendMessage("The gamemode " + args[0] + " does not exist!");
                        return true;


                }

                player = main.getServer().getPlayer(args[1]);

                if (player == null) {
                    sender.sendMessage("This Player is not online!");
                    return true;


                }

            }


            player.setGameMode(gm);
            sender.sendMessage("The gamemode has been set to " + succm + "!");
            return true;

        } else sender.sendMessage("You do not have the permissions for that command!");
        return true;


    }


    @Override
    public List<String> onTabComplete(CommandSender arg0, Command arg1, String arg2, String[] args) {

        if (arg0.isOp() == true) {


            List<String> a1 = Arrays.asList("survival", "creative", "adventure", "spectator");
            List<String> a2 = new ArrayList<String>();


            if (args.length == 1) {

                for (String guess : a1) {
                    if (guess.toLowerCase().startsWith(args[0].toLowerCase())) a2.add(guess);
                }

            }


            if (args.length == 2) {

                for (Player guess : main.getServer().getOnlinePlayers()) {
                    if (guess.getDisplayName().toLowerCase().startsWith(args[1].toLowerCase()))
                        a2.add(guess.getDisplayName().toLowerCase());
                }

            }
            Collections.sort(a2);
            return a2;


        } else {
            List<String> nothing = new ArrayList<String>();
            return nothing;
        }
    }
}
