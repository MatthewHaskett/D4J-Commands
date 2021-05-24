package com.matthewhaskett.commands.permissions;

import sx.blah.discord.handle.obj.IMessage;
import sx.blah.discord.handle.obj.IUser;
import sx.blah.discord.handle.obj.Permissions;

/**
 * @author Matthew Haskett
 * @since 21/03/2018 17:55.
 */
public class Permission {

    /**
     * The type of permission.
     */
    private final Type type;

    /**
     * The discord permission (if applicable).
     */
    private Permissions permission;

    /**
     * TOur custom permission (if applicable).
     */
    private PermissionChecker checker;

    /**
     * Construct a permission with a discord permission.
     * @param permission Permission to use.
     */
    public Permission(Permissions permission){
        this.type = Type.DISCORD;
        this.permission = permission;
    }

    /**
     * Construct a permission with a custom permission.
     * @param checker Permission to use.
     */
    public Permission(PermissionChecker checker){
        this.type = Type.CUSTOM;
        this.checker = checker;
    }

    /**
     * An enum for storing the permission type.
     * @author Matthew Haskett
     * @since 21/03/2018 18:00
     */
    public enum Type {

        /**
         * A permission supplied by Discord itself.
         */
        DISCORD,

        /**
         * A permission supplied by the developer.
         */
        CUSTOM;
    }

    /**
     * Check if a user has this permission.
     * @param user The user.
     * @param message The message to check.
     * @return Whether they have the permission.
     */
    public boolean hasPermission(IUser user, IMessage message){
        if(type == Type.CUSTOM)
            return checker.hasPermission(message, user);

        else if(type == Type.DISCORD)
            return user.getPermissionsForGuild(message.getGuild()).contains(permission);

        else
            return false;

    }

}
