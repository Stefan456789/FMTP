package at.FMTP.commands;



import java.util.Arrays;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import at.FMTP.Main;

public class CMDColouredSay implements CommandExecutor, TabCompleter {

	public CMDColouredSay(Main main) {
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String arg2, String[] args) {

		if (sender instanceof Player) {


			sender.getServer().broadcastMessage("[" + sender.getName() + "] " + ChatColor.translateAlternateColorCodes('&', String.join(" ", args)));


		}
		return true;

	}


	@Override
	public List<String> onTabComplete(CommandSender arg0, Command arg1, String arg2, String[] args) {
		List<String> a = Arrays.asList("&4", "&c", "&6", "&e", "&2", "&a", "&b", "&3", "&1", "&9", "&d", "&5", "&f", "&7", "&8", "&0", "&r", "&l", "&o", "&m", "&k");
		return a;
	}
}