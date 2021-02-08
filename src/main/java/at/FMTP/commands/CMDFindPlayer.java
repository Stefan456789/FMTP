package at.FMTP.commands;

import at.FMTP.Main;
import com.mojang.nbt.CompoundTag;
import com.mojang.nbt.IntTag;
import com.mojang.nbt.ListTag;
import com.mojang.nbt.NbtIo;
import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CMDFindPlayer implements CommandExecutor, TabCompleter {
    private Main main;

    public CMDFindPlayer(Main main) {
        this.main = main;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        try {
            if (sender.isOp()) {
                if (args.length != 1) return false;
                @SuppressWarnings("deprecation") OfflinePlayer player = sender.getServer().getOfflinePlayer(args[0]);
                if (player.isOnline()) {
                    Location playerloc = player.getPlayer().getLocation();
                    sender.sendMessage(args[0] + "is at " + playerloc.getX() + " " + playerloc.getY() + " " + playerloc.getZ() + " and in the world " + playerloc.getWorld() + ".");
                } else {

                    File playdataFile = new File(sender.getServer().getWorldContainer().getAbsoluteFile() + main.getPlayerdataPath() + player.getUniqueId() + ".dat");
                    FileInputStream inputStream = new FileInputStream(playdataFile);
                    CompoundTag playerdata = NbtIo.readCompressed(inputStream);
                    ListTag<IntTag> pos = (ListTag<IntTag>) playerdata.getList("Pos");
                    String dim = playerdata.getString("Dimension");

                    sender.sendMessage(String.format(args[0] + "is at" + pos.get(0) + " " + pos.get(1) + " " + pos.get(2) + " and in the dimension " + dim + "."));
                    return true;
                }
            } else sender.sendMessage("You do not have the permissions for that command!");
        } catch (
                FileNotFoundException e) {
            e.printStackTrace();
        } catch (
                IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String s, String[] args) {
        if (sender.isOp()) {
            OfflinePlayer[] of = sender.getServer().getOfflinePlayers();

            List<String> offlineNames = new ArrayList<String>();
            List<String> suggestion = new ArrayList<String>();

            if (args.length == 1) {
                for (int x = 0; x < of.length; x++) {
                    offlineNames.add(of[x].getName());
                }
                for (String guess : offlineNames) {
                    if (guess.toLowerCase().startsWith(args[0].toLowerCase()))
                        suggestion.add(guess);
                }

            }

            return suggestion;

        } else {
            List<String> nothing = new ArrayList<String>();
            return nothing;

        }
    }
}
