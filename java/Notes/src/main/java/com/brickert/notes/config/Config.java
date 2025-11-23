package com.brickert.notes.config;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.io.IOException;

public class Config {
    //default location for notes directory
    private static final Path DEFAULT_NOTES_DIRECTORY = 
        Paths.get(System.getProperty("user.home"), ".bricktionary"); //notes is stored HERE (relevant to each user with user.home)

    // method to get the notes home directory
    // checks for environment variable NOTES_HOME, if it exists, use that as the notes directory
    // if it doesn't exist, use the default location
    public static Path getNotesHome() {
        String notesHomeEnv = System.getenv("NOTES_HOME");
        if (notesHomeEnv != null) {
            return Paths.get(notesHomeEnv);
        } else {
            return DEFAULT_NOTES_DIRECTORY; //uses default location if user doesn't set the path themselves
        }
    }

    // method to ensure the notes directory exists
    // if it doesn't exist, create it
    public static Path ensureNotesDirectoryExists() throws IOException {  //looks to see if /.notes exists already and creates one if none exists
        Path notesHome = getNotesHome();
        if (!Files.exists(notesHome)) {  //throw error if folder is already created
            Files.createDirectories(notesHome);
        }
        return notesHome;
    }
}
