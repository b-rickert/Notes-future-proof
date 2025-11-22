package com.brickert.notes;

import com.brickert.notes.config.Config;
import com.brickert.notes.note.Note;
import com.brickert.notes.utilities.NoteFileManager;
import java.nio.file.Path;
import java.io.IOException;
import java.util.Scanner;
import java.util.List;

public class Main {

    public static void main(String[] args) {
            Scanner scanner = new Scanner(System.in);

        try {
            Path notesDir = Config.ensureNotesDirectoryExists();
            System.out.println("Notes directory created/verified at: " + notesDir);
            System.out.println();
            while (true) {
                System.out.println("\n+--------------------------------------+");
                System.out.println("|           BRICKtionary               |");
                System.out.println("|    Your Personal Dictionary of Notes |");
                System.out.println("+--------------------------------------+");
                System.out.println("| 1. Create a new note                 |");
                System.out.println("| 2. View all notes                    |");
                System.out.println("| 3. Search notes                      |");
                System.out.println("| 4. Delete a note                     |");
                System.out.println("| 5. Exit                              |");
                System.out.println("+--------------------------------------+");
                System.out.print("Enter your choice: ");
                int choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1:
                        System.out.println("\n+--------------------------------------+");
                        System.out.println("|           CREATE NEW NOTE            |");
                        System.out.println("+--------------------------------------+");
                        System.out.print("Enter note title: ");
                        String title = scanner.nextLine();
                        System.out.print("Enter note content: ");
                        String content = scanner.nextLine();
                        Note newNote = new Note(title, content);
                        String filename = NoteFileManager.saveNote(newNote);
                        System.out.println("+--------------------------------------+");
                        System.out.println("  Note created: " + newNote.getTitle());
                        System.out.println("+--------------------------------------+");
                        break;
                    case 2:
                        List<String> notes = NoteFileManager.listAllNotes();
                        if (notes.isEmpty()) {
                            System.out.println("\n+--------------------------------------+");
                            System.out.println("|            No notes found            |");
                            System.out.println("+--------------------------------------+");
                        } else {
                            System.out.println("\n+--------------------------------------+");
                            System.out.println("|             MY NOTES                 |");
                            System.out.println("+--------------------------------------+");
                            for (int i = 0; i < notes.size(); i++) {
                                System.out.println("  " + (i + 1) + ". " + notes.get(i));
                            }
                            System.out.println("  " + (notes.size() + 1) + ". Return to main menu");
                            System.out.println("+--------------------------------------+");
                            
                            System.out.print("Enter your choice: ");
                            int noteChoice = scanner.nextInt();
                            scanner.nextLine();
                            
                            if (noteChoice > 0 && noteChoice <= notes.size()) {
                                String selectedFile = notes.get(noteChoice - 1);
                                String noteContent = NoteFileManager.loadNoteContent(selectedFile);
                                System.out.println("\n+--------------------------------------+");
                                System.out.println("|            NOTE CONTENT              |");
                                System.out.println("+--------------------------------------+");
                                System.out.println(noteContent);
                                System.out.println("+--------------------------------------+");
                            } else if (noteChoice != notes.size() + 1) {
                                System.out.println("Invalid choice.");
                            }
                        }
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