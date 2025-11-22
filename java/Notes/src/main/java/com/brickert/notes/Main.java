package com.brickert.notes;

import com.brickert.notes.config.Config;
import com.brickert.notes.note.Note;
import com.brickert.notes.utilities.NoteFileManager;
import java.nio.file.Path;
import java.io.IOException;
import java.util.Scanner;
import com.brickert.notes.utilities.NoteFileManager;;

public class Main {

    public static void main(String[] args) {
            Scanner scanner = new Scanner(System.in);

        try {
            Path notesDir = Config.ensureNotesDirectoryExists();
            System.out.println("Notes directory created/verified at: " + notesDir);
            while (true) {
                System.out.println("\n============= NOTES APP ===============");
                System.out.println("PLEASE PICK THE CORRESPONDING OPTION NUMBER");
                System.out.println("1. Create a new note");
                System.out.println("2. View all notes");
                System.out.println("3. Search notes");
                System.out.println("4. Delete a note");
                System.out.println("5. Exit");
                System.out.println("========================================");
                System.out.print("Enter your choice: ");
                int choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1:
                        System.out.println("Enter note title: ");
                        String title = scanner.nextLine();

                        System.out.print("Enter note content: ");
                        String content = scanner.nextLine();

                        Note newNote = new Note(title, content);

                        String filename = NoteFileManager.saveNote(newNote);
                        System.out.println("Note created: " + newNote.getTitle());

                        break;
                    case 2:
                        //what happens
                        break;
                    case 3:
                        //what happens
                        break;
                    case 4: 
                        //what happens
                        break;
                    case 5:
                        System.out.println("Goodbye!");
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Invalid choice. Please enter a correct choice.");
                        break;
                }

            }

            } catch (IOException e) {
            System.out.println("Error creating notes directory: " + e.getMessage());
        }
    }
}