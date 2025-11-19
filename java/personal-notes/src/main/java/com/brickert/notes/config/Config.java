package com.brickert.notes.config;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.io.IOException;

public class Config {

    private static final Path DEFAULT_NOTES_DIRECTORY = 
        Paths.get(System.getProperty("user.home"), ".notes");

    public static Path getNotesHome() {
        String notesHomeEnv = System.getenv("NOTES_HOME");
        if (notesHomeEnv != null) {
            return Paths.get(notesHomeEnv);
        } else {
            return DEFAULT_NOTES_DIRECTORY;
        }
    }

    public static Path ensureNotesDirectoryExists() throws IOException {
        Path notesHome = getNotesHome();
        if (!Files.exists(notesHome)) {
            Files.createDirectories(notesHome);
        }
        return notesHome;
    }
}
