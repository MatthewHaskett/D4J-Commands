package me.enderaura.commands;

import sx.blah.discord.api.IDiscordClient;
import sx.blah.discord.api.events.EventSubscriber;
import sx.blah.discord.handle.impl.events.guild.channel.message.MessageReceivedEvent;

import java.util.*;

/**
 * @author Enderaura
 * @since 21/03/2018 17:26.
 */
public class CommandManager {

    /**
     * A {@link Map} of all command names and
     * relevant {@link Command} objects.
     */
    private final Map<String, Command> commands = new HashMap<>();

    /**
     * The prefix for bot commands.
     */
    private final String prefix;

    /**
     * The {@link IDiscordClient} that is handling
     * these commands.
     */
    private final IDiscordClient client;

    /**
     * The message to send when the user does no have
     * the required permissions to execute the command.
     */
    private String noPermissionMessage = "";

    /**
     * Initialise the {@link CommandManager}
     * @param client The {@link IDiscordClient} that is handling these commands.
     * @param prefix The prefix for bot commands.
     */
    public CommandManager(IDiscordClient client, String prefix){
        this.prefix = prefix;
        this.client = client;

        client.getDispatcher().registerListener(this);
    }

    /**
     * Register a command into the command map.
     * @param command The command that is to be run.
     * @param aliases The aliases that can be used to run the command.
     */
    public void registerCommand(Command command, String... aliases){
        for (String name : aliases) {
            commands.put(name.toLowerCase(), command);
            command.setManager(this);
        }

    }

    /**
     * Change the {@code noPermissionMessage}.
     * @param message The message to send.
     */
    public void setNoPermissionMessage(String message){
        this.noPermissionMessage = message;
    }

    /**
     * Get the {@code noPermissionMessage}.
     * @return The message to send.
     */
    String getNoPermissionMessage(){
        return noPermissionMessage;
    }

    /**
     * Get an instance of the {@link IDiscordClient} passed at initialisation.
     * @return The client.
     */
    IDiscordClient getClient() {
        return client;
    }

    /**
     * Handle received messages.
     * @param event The {@link MessageReceivedEvent} to handle.
     */
    @EventSubscriber
    public void onMessageReceive(MessageReceivedEvent event){
        List<String> args = new ArrayList<>(Arrays.asList(event.getMessage().getContent().split(" ")));

        if(args.size() != 0 && args.get(0).startsWith(prefix)){
            String name = args.get(0).substring(prefix.length());
            args.remove(0);

            if(commands.containsKey(name.toLowerCase()))
                commands.get(name.toLowerCase()).run(event.getMessage(), event.getAuthor(), args);

        }

    }

}
