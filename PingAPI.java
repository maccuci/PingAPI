package me.alegb.ping;

import java.lang.reflect.Field;
import java.lang.reflect.Method;


/**
 * @author Zukian
 * @version 1.0
 */

public class PingAPI {
	
	public static String getServerVersion() {
		return Bukkit.getServer().getClass().getPackage().getName().replace(".", ",").split(",")[3];
	}
	
	public static int getPing(Player player) {
		try {
			Class<?> craftPlayer = Class.forName("org.bukkit.craftbukkit." + getServerVersion() + ".entity.CraftPlayer");
			Method getHandle = craftPlayer.getMethod("getHandle");
			Object toInvoke = getHandle.invoke(player);
			Field ping = toInvoke.getClass().getDeclaredField("ping");
			return (int) ping.get(toInvoke);
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
	}
}
