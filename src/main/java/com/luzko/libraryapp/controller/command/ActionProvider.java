package com.luzko.libraryapp.controller.command;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Optional;

public class ActionProvider {
    private static final Logger logger = LogManager.getLogger(ActionProvider.class);

    private ActionProvider() {

    }

    public static Optional<Command> defineCommand(String commandName) {
        Optional<Command> command = Optional.empty();
        if (commandName != null && !commandName.isBlank()) {
            try {
                CommandType commandType = CommandType.valueOf(commandName.toUpperCase());
                command = Optional.ofNullable(commandType.getCommand());
            } catch (IllegalArgumentException e) {
                logger.log(Level.ERROR, "Incorrect command: {}", commandName, e);
            }
        }
        return command;
    }
}