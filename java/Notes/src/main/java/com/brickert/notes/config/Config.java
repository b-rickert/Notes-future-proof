package com.brickert.notes.config;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.io.IOException;

public class Config {

    private static final Path DEFAULT_NOTES_DIRECTORY = 
        Paths.get(System.getProperty("user.home"), ".notes"); //notes is stored HERE (relevant to each user with user.home)

    //creates the /.notes folder 
    public static Path getNotesHome() {
        String notesHomeEnv = System.getenv("NOTES_HOME");
        if (notesHomeEnv != null) {
            return Paths.get(notesHomeEnv);
        } else {
            return DEFAULT_NOTES_DIRECTORY; //uses default location if user doesn't set the path themselves
        }
    }

    
    public static Path ensureNotesDirectoryExists() throws IOException {  //looks to see if /.notes exists already and creates one if none exists
        Path notesHome = getNotesHome();
        if (!Files.exists(notesHome)) {  //throw error if folder is already created
            Files.createDirectories(notesHome);
        }
        return notesHome;
    }
}
