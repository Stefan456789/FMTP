package at.FMTP.chatwrite;
import at.FMTP.Main;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;


public class CMDcSay implements Listener {

	public CMDcSay(Main main) {
	}

	@EventHandler
	public void onPlayerChat(AsyncPlayerChatEvent e) {

		Player p = e.getPlayer();
		
		p.getServer().broadcastMessage("<" + p.getName() + "> " + ChatColor.translateAlternateColorCodes('&', e.getMessage() ));
		e.setCancelled(true);

	}

}
