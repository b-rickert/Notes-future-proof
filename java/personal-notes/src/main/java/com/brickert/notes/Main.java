package com.brickert.notes;

import com.brickert.notes.config.Config;
import java.nio.file.Path;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        try {
            Path notesDir = Config.ensureNotesDirectoryExists();
            System.out.println("Notes directory created/verified at: " + notesDir);
        } catch (IOException e) {
            System.err.println("Error creating notes directory: " + e.getMessage());
        }
    }
}