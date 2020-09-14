package com.luzko.libraryapp.controller;

import com.luzko.libraryapp.controller.command.Command;
import com.luzko.libraryapp.controller.command.CommandType;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ActionProvider {
    private static Logger logger = LogManager.getLogger(ActionProvider.class);

    public static Command defineCommand(String commandName) {
        Command command = null;
        if (commandName != null && !commandName.isBlank()) {
            try {
                CommandType commandType = CommandType.valueOf(commandName.toUpperCase());
                command = commandType.getCommand();
            } catch (IllegalArgumentException e) {
                logger.log(Level.ERROR, "Incorrect command: {}", commandName, e);
            }
        }

        return command;
    }
}
