package com.luzko.libraryapp.controller.command;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ActionProvider {
    private static final Logger logger = LogManager.getLogger(ActionProvider.class);

    private ActionProvider() {

    }

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

//TODO public static Optional<Command> defineCommand(String commandName) {