package com.matthewhaskett.commands;

import com.matthewhaskett.commands.permissions.Permission;
import sx.blah.discord.handle.obj.IMessage;
import sx.blah.discord.handle.obj.IUser;
import sx.blah.discord.util.DiscordException;
import sx.blah.discord.util.RequestBuffer;

import java.util.List;

/**
 * @author Matthew Haskett
 * @since 21/03/2018 17:28.
 */
public abstract class Command {

    /**
     * Permission required to execute the command.
     */
    private final Permission permission;

    /**
     * The CommandManager managing this command.
     */
    private CommandManager manager;

    /**
     * Create a command.
     * @param permission Permission required to execute the command.
     */
    public Command(Permission permission){
        this.permission = permission;
    }

    /**
     * Called when registered.
     * @param manager The manager to register to.
     */
    void setManager(CommandManager manager){
        this.manager = manager;
    }

    /**
     * Pre-run checks.
     * @param message Message of which executed the command.
     * @param sender User who executed the command.
     * @param args Command arguments
     */
    void run(IMessage message, IUser sender, List<String> args){

        if(permission != null && !permission.hasPermission(sender, message)){

            RequestBuffer.request(() -> {
                try{
                    message.getChannel().sendMessage(manager.getNoPermissionMessage());
                }

                catch (DiscordException e){
                    System.err.println("No permission message could not be sent: ");
                    e.printStackTrace();
                }
            });
        }

        onCommand(message, sender, args);
    }

    /**
     * Execute the command.
     * @param message Message of which executed the command.
     * @param sender User who executed the command.
     * @param args Command arguments
     */
    public abstract void onCommand(IMessage message, IUser sender, List<String> args);

}
