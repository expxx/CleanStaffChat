package it.frafol.cleanstaffchat.velocity.hooks;

import com.imaginarycode.minecraft.redisbungee.events.PubSubMessageEvent;
import com.velocitypowered.api.event.PostOrder;
import com.velocitypowered.api.event.Subscribe;
import it.frafol.cleanstaffchat.velocity.CleanStaffChat;
import it.frafol.cleanstaffchat.velocity.enums.VelocityConfig;
import it.frafol.cleanstaffchat.velocity.objects.PlayerCache;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;

public class RedisListener {

    public final CleanStaffChat PLUGIN;
    private final MiniMessage miniMessage = MiniMessage.miniMessage();

    public RedisListener(CleanStaffChat plugin) {
        this.PLUGIN = plugin;
    }

    @Subscribe(order = PostOrder.FIRST)
    public void onRedisBungeeMessage(PubSubMessageEvent event) {

        if (event.getChannel().equals("CleanStaffChat-StaffMessage-RedisBungee")) {
            Component toSend = miniMessage.deserialize(event.getMessage());

            CleanStaffChat.getInstance().getServer().getAllPlayers().stream().filter
                            (players -> players.hasPermission(VelocityConfig.STAFFCHAT_USE_PERMISSION.get(String.class))
                                    && !(PlayerCache.getToggled().contains(players.getUniqueId())))
                    .forEach(players -> players.sendMessage(toSend));

            PLUGIN.getServer().getConsoleCommandSource().sendMessage(toSend);

        }

        if (event.getChannel().equals("CleanStaffChat-DonorMessage-RedisBungee")) {
            Component toSend = miniMessage.deserialize(event.getMessage());

            CleanStaffChat.getInstance().getServer().getAllPlayers().stream().filter
                            (players -> players.hasPermission(VelocityConfig.DONORCHAT_USE_PERMISSION.get(String.class))
                                    && !(PlayerCache.getToggled().contains(players.getUniqueId())))
                    .forEach(players -> players.sendMessage(toSend));

            PLUGIN.getServer().getConsoleCommandSource().sendMessage(toSend);

        }

        if (event.getChannel().equals("CleanStaffChat-AdminMessage-RedisBungee")) {
            Component toSend = miniMessage.deserialize(event.getMessage());

            CleanStaffChat.getInstance().getServer().getAllPlayers().stream().filter
                            (players -> players.hasPermission(VelocityConfig.ADMINCHAT_USE_PERMISSION.get(String.class))
                                    && !(PlayerCache.getToggled().contains(players.getUniqueId())))
                    .forEach(players -> players.sendMessage(toSend));

            PLUGIN.getServer().getConsoleCommandSource().sendMessage(toSend);

        }

        if (event.getChannel().equals("CleanStaffChat-StaffOtherMessage-RedisBungee")) {
            Component toSend = miniMessage.deserialize(event.getMessage());

            CleanStaffChat.getInstance().getServer().getAllPlayers().stream().filter
                            (players -> players.hasPermission(VelocityConfig.STAFFCHAT_USE_PERMISSION.get(String.class))
                                    && !(PlayerCache.getToggled().contains(players.getUniqueId())))
                    .forEach(players -> players.sendMessage(toSend));

        }

        if (event.getChannel().equals("CleanStaffChat-StaffAFKMessage-RedisBungee")) {
            Component toSend = miniMessage.deserialize(event.getMessage());

            CleanStaffChat.getInstance().getServer().getAllPlayers().stream().filter
                            (players -> players.hasPermission(VelocityConfig.STAFFCHAT_AFK_PERMISSION.get(String.class))
                                    && !(PlayerCache.getToggled().contains(players.getUniqueId())))
                    .forEach(players -> players.sendMessage(toSend));

        }

        if (event.getChannel().equals("CleanStaffChat-MuteStaffChat-RedisBungee")) {

            if (PlayerCache.getMuted().contains("true")) {

                PlayerCache.getMuted().remove("true");

            } else {

                PlayerCache.getMuted().add("true");

            }
        }

        if (event.getChannel().equals("CleanStaffChat-MuteAdminChat-RedisBungee")) {

            if (PlayerCache.getMuted_admin().contains("true")) {

                PlayerCache.getMuted_admin().remove("true");

            } else {

                PlayerCache.getMuted_admin().add("true");

            }
        }

        if (event.getChannel().equals("CleanStaffChat-MuteDonorChat-RedisBungee")) {

            if (PlayerCache.getMuted_donor().contains("true")) {

                PlayerCache.getMuted_donor().remove("true");

            } else {

                PlayerCache.getMuted_donor().add("true");

            }
        }
    }
}
