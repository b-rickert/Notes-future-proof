package com.brickert.config;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.io.IOException;

public class Config {

    private static final Path defaultNotesDir = Paths.get(System.getProperty("user.home"), ".notes");

    public static Path getNotesHome(); {
        String notesHomeEnv = System.getenv("NOTES_HOME");
        
        if (notesHomeEnv != null) {
            return notesHomeEnv;
        } else {
            return defaultNotesDir;
        }
        
        
        
        // TODO: Check environment variable "NOTES_HOME"
        // If it exists, use it
        // Otherwise, use defaultNotesDir
        // Return as a Path object

    }

    public static Path notesDirectoryExists() throws IOException {
        // TODO: Get the notes home path
        // Check if directory exists
        // If not, create it
        // Return the path

    }
    
}
