package com.matthewhaskett.commands.permissions;

import sx.blah.discord.handle.obj.IMessage;
import sx.blah.discord.handle.obj.IUser;

/**
 * @author Matthew Haskett
 * @since 21/03/2018 17:54.
 */
public interface PermissionChecker {

    /**
     * A custom permission checker.
     * @param message The message to check.
     * @param user The user to check.
     * @return Whether the user has permission.
     */
    boolean hasPermission(IMessage message, IUser user);

}
