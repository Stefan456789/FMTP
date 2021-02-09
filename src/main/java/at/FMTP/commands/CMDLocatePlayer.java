package at.FMTP.commands;

import at.FMTP.Main;
import com.mojang.nbt.CompoundTag;
import com.mojang.nbt.IntTag;
import com.mojang.nbt.ListTag;
import com.mojang.nbt.NbtIo;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.chat.hover.content.Text;
import org.bukkit.Color;
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

public class CMDLocatePlayer implements CommandExecutor, TabCompleter {
    private Main main;

    public CMDLocatePlayer(Main main) {
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
                    TextComponent locMessage = new TextComponent("[" + (int)playerloc.getX() + ", " + (int)playerloc.getY() + ", " + (int)playerloc.getZ() + "]");
                    locMessage.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new Text("Click to teleport")));
                    locMessage.setClickEvent(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, "/tp " + sender.getName() + " " + (int)playerloc.getX() + " " + (int)playerloc.getY() + " " + (int)playerloc.getZ()));
                    locMessage.setColor(ChatColor.GREEN);
                    sender.spigot().sendMessage(new TextComponent(args[0] + " is at "), locMessage, new TextComponent(" and in the world " + playerloc.getWorld().getName() + "."));
                    return true;

                } else {

                    File playdataFile = new File(sender.getServer().getWorldContainer().getAbsoluteFile() + main.getPlayerdataPath() + player.getUniqueId() + ".dat");
                    FileInputStream inputStream = new FileInputStream(playdataFile);
                    CompoundTag playerdata = NbtIo.readCompressed(inputStream);
                    ListTag<IntTag> pos = (ListTag<IntTag>) playerdata.getList("Pos");
                    String dim = playerdata.getString("Dimension");

                    
                    TextComponent posMessage = new TextComponent("[" + pos.get(0)  + ", " + pos.get(1) + ", " + pos.get(2) + "]");
                    posMessage.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new Text("Click to teleport")));
                    posMessage.setClickEvent(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, "/tp " + sender.getName() + " " + pos.get(0) + " " + pos.get(1) + " " + pos.get(2)));
                    posMessage.setColor(ChatColor.GREEN);

                    sender.sendMessage("[" + pos.get(0)  + ", " + pos.get(1) + ", " + pos.get(2) + "]");
                    sender.spigot().sendMessage(new TextComponent(args[0] + " is at "), posMessage, new TextComponent(" and in the dimension " + dim + "."));
                    return true;


                }
            } else {
                sender.sendMessage("You do not have the permissions for that command!");
                return true;
            }

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
