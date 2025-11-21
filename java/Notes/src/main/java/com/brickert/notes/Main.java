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
            System.out.println();
            System.out.println();
            System.out.println("----------------");

            Note myNote = new Note("Java Note #1", "This is my first test note for my Java project, add information in this section. \n\n\nTesting bullet points:\n\n -Bullet 1\n -Bullet 2\n -Bullet 3");
            myNote.getTags().add("Testing");
            myNote.getTags().add("For");
            myNote.getTags().add("Java");
            myNote.getTags().add("Presentation");
            
            
            System.out.println("Title: " + myNote.getTitle());
            System.out.println("Created: " + myNote.getCreated());
            System.out.println("Tags: " + myNote.getTags());
            System.out.println("----------------");
            System.out.println();
            System.out.println();
            System.out.println("Content: " + myNote.getContent());
            System.out.println();
            System.out.println();
            System.out.println("Note created successfully");

            



        } catch (IOException e) {
            System.out.println("Error creating notes directory: " + e.getMessage());
        }
    }
}