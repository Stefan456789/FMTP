package at.FMTP.commands;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.bukkit.Difficulty;
import org.bukkit.GameRule;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import at.FMTP.Main;

public class CMDDifficulty implements CommandExecutor, TabCompleter {

    private Main main;

    public CMDDifficulty(Main main) {
        this.main = main;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String arg2, String[] args) {

        if (sender instanceof Player) {
            Difficulty diff = Difficulty.EASY;
            boolean mogr = false;
            String succm = null;
            List<World> worlds = sender.getServer().getWorlds();
            if (sender.isOp()) {
                if (args.length != 1) {
                    return false;
                }
                switch (args[0]) {

                    case "peaceful":
                    case "0":
                        diff = Difficulty.PEACEFUL;
                        mogr = true;
                        succm = "peaceful";
                        break;

                    case "easy":
                    case "1":
                        diff = Difficulty.EASY;
                        mogr = false;
                        succm = "easy";
                        break;
                    case "normal":
                    case "2":
                        diff = Difficulty.NORMAL;
                        succm = "normal";
                        mogr = false;
                        break;

                    case "hard":
                    case "3":
                        diff = Difficulty.HARD;
                        succm = "hard";
                        mogr = false;
                        break;


                    default:

                        sender.sendMessage("The difficulty " + args[0] + " does not exist!");

                        return true;
                }
                for (int x = 0; worlds.size() <= x; x++) {

                    worlds.get(x).setDifficulty(diff);

                }
                for (int x = 0; worlds.size() <= x; x++) {

                    worlds.get(x).setGameRule(GameRule.MOB_GRIEFING, mogr);

                }

                main.getServer().broadcastMessage("The difficulty has been set to " + succm + "!");

            } else {
                sender.sendMessage("You do not have the permissions for that command!");
                return true;
            }

        } else {
            sender.sendMessage("You need to be a player! Or make /minecraft:difficulty <peaceful/easy/normal/hard>");
        }

        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender arg0, Command arg1, String arg2, String[] args) {
        boolean needOp = main.getConfig().getBoolean("config.needOpForDifficulty");
        if (arg0.isOp() == needOp) {

            List<String> a1 = Arrays.asList("peaceful", "easy", "normal", "hard");
            List<String> a2 = new ArrayList<String>();

            if (args.length == 1) {
                for (String guess : a1) {
                    if (guess.toLowerCase().startsWith(args[0].toLowerCase()))
                        a2.add(guess);

                }

            }

            return a2;

        } else {
            List<String> nothing = new ArrayList<String>();
            return nothing;

        }

    }

}
