package com.brickert.notes;

import com.brickert.notes.config.Config;
import com.brickert.notes.note.Note;
import java.nio.file.Path;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        try {
            Path notesDir = Config.ensureNotesDirectoryExists();
            System.out.println("Notes directory created/verified at: " + notesDir);

            Note myNote = new Note("Java Note #1", "This is my first test note for my Java project, add information in this section.");
            System.out.println("Title: " + myNote.getTitle());
            System.out.println("Content: " + myNote.getContent());
            System.out.println("Created: " + myNote.getCreated());
            System.out.println("Note created successfully");

        } catch (IOException e) {
            System.out.println("Error creating notes directory: " + e.getMessage());
        }
    }
}