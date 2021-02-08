package at.FMTP;

import at.FMTP.commands.CMDFindPlayer;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import at.FMTP.commands.CMDColouredSay;
import at.FMTP.chat.CMDcSay;
import at.FMTP.commands.CMDDifficulty;
import at.FMTP.commands.CMDGamemode;

import java.io.File;

public class Main extends JavaPlugin {

	@Override
	public void onEnable() {		
		


		super.onEnable();
		getServer().getConsoleSender().sendMessage("[Stefan] plugin loaded!!!");
		
		CMDDifficulty dfHandler = new CMDDifficulty(this);
		
		getCommand("mobgrdifficulty").setExecutor(dfHandler);
		getCommand("mobgrdifficulty").setTabCompleter(dfHandler);
		
		CMDGamemode gmHandler = new CMDGamemode(this);
		

		getCommand("gm").setExecutor(gmHandler);

		getCommand("gm").setTabCompleter(gmHandler);
		
		CMDColouredSay csHandler = new CMDColouredSay(this);
		
		getCommand("csay").setExecutor(csHandler);
		getCommand("csay").setTabCompleter(csHandler);

		CMDFindPlayer fpHandler = new CMDFindPlayer(this);

		getCommand("findplayer").setExecutor(fpHandler);
		getCommand("findplayer").setTabCompleter(fpHandler);
		
		Bukkit.getServer().getPluginManager().registerEvents(new CMDcSay(this), this);



		


	}

	public String getPlayerdataPath() {
		String worldname = getServer().getWorlds().get(0).getName();
		return String.format("#%s#playerdata#", worldname).replace("#", File.separator);
	}
}
