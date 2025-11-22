package com.brickert.notes;

import com.brickert.notes.config.Config;
import com.brickert.notes.note.Note;
import com.brickert.notes.utilities.NoteFileManager;
import java.nio.file.Path;
import java.io.IOException;
import java.util.Scanner;
import java.util.List;

public class Main {

    public static String padRight(String text, int length) {
        if (text.length() >= length) {
            return text;
        }
        return text + " ".repeat(length - text.length());
    }

    public static void main(String[] args) {
            Scanner scanner = new Scanner(System.in);

        try {
            Path notesDir = Config.ensureNotesDirectoryExists();
            System.out.println("Notes directory created/verified at: " + notesDir);
            java.awt.Toolkit.getDefaultToolkit().beep();
            System.out.println();
            while (true) {
                System.out.println("\n╔══════════════════════════════════════╗");
                System.out.println("║           BRICKtionary               ║");
                System.out.println("║    Your Personal Dictionary of Notes ║");
                System.out.println("╠══════════════════════════════════════╣");
                System.out.println("║ 1. Create a new note                 ║");
                System.out.println("║ 2. View all notes                    ║");
                System.out.println("║ 3. Search notes                      ║");
                System.out.println("║ 4. Delete a note                     ║");
                System.out.println("║ 5. Exit                              ║");
                System.out.println("╚══════════════════════════════════════╝");
                System.out.print("Enter your choice: ");
                int choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1:
                        System.out.println("\n╔══════════════════════════════════════╗");
                        System.out.println("║           CREATE NEW NOTE            ║");
                        System.out.println("╚══════════════════════════════════════╝");
                        System.out.print("Enter note title: ");
                        String title = scanner.nextLine();
                        System.out.print("Enter note content: ");
                        String content = scanner.nextLine();
                        Note newNote = new Note(title, content);
                        String filename = NoteFileManager.saveNote(newNote);
                        java.awt.Toolkit.getDefaultToolkit().beep();
                        
                        // Make box adaptive to title length
                        String successMsg = "  ✓ Note created: " + newNote.getTitle();
                        int successBoxWidth = Math.max(38, successMsg.length() + 2);
                        String successBorder = "═".repeat(successBoxWidth);
                        
                        System.out.println("╔" + successBorder + "╗");
                        System.out.println("║" + padRight(successMsg, successBoxWidth) + "║");
                        System.out.println("╚" + successBorder + "╝");
                        break;
                    case 2:
                        List<String> notes = NoteFileManager.listAllNotes();
                        if (notes.isEmpty()) {
                            System.out.println("\n╔══════════════════════════════════════╗");
                            System.out.println("║          No notes found              ║");
                            System.out.println("╚══════════════════════════════════════╝");
                        } else {
                            // Find the longest filename
                            int maxLength = 15; // Minimum width for "MY NOTES" header
                            for (String note : notes) {
                                if (note.length() > maxLength) {
                                    maxLength = note.length();
                                }
                            }
                            int boxWidth = maxLength + 6; // Add padding for "║ 1. " and " ║"
                            
                            // Build the box
                            String topBottom = "═".repeat(boxWidth);
                            String divider = "═".repeat(boxWidth);
                            
                            System.out.println("\n╔" + topBottom + "╗");
                            System.out.println("║" + padRight("        MY NOTES", boxWidth) + "║");
                            System.out.println("╠" + divider + "╣");
                            
                            for (int i = 0; i < notes.size(); i++) {
                                String line = " " + (i + 1) + ". " + notes.get(i);
                                System.out.println("║" + padRight(line, boxWidth) + "║");
                            }
                            
                            String returnOption = " " + (notes.size() + 1) + ". Return to main menu";
                            System.out.println("║" + padRight(returnOption, boxWidth) + "║");
                            System.out.println("╚" + topBottom + "╝");
                            
                            System.out.print("Enter your choice: ");
                            int noteChoice = scanner.nextInt();
                            scanner.nextLine();
                            
                            if (noteChoice > 0 && noteChoice <= notes.size()) {
                                String selectedFile = notes.get(noteChoice - 1);
                                String noteContent = NoteFileManager.loadNoteContent(selectedFile);
                                
                                // Split content into lines
                                String[] lines = noteContent.split("\n");
                                
                                // Find the longest line for box width
                                int contentMaxLength = 15;
                                for (String line : lines) {
                                    if (line.length() > contentMaxLength) {
                                        contentMaxLength = line.length();
                                    }
                                }
                                int contentBoxWidth = contentMaxLength + 2;
                                
                                String contentTopBottom = "═".repeat(contentBoxWidth);
                                
                                System.out.println("\n╔" + contentTopBottom + "╗");
                                System.out.println("║" + padRight(" BRICKtionary Entry", contentBoxWidth) + "║");
                                System.out.println("╠" + contentTopBottom + "╣");
                                
                                // Print each line inside the box
                                for (String line : lines) {
                                    System.out.println("║" + padRight(" " + line, contentBoxWidth) + "║");
                                }
                                
                                System.out.println("╚" + contentTopBottom + "╝");
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
                        java.awt.Toolkit.getDefaultToolkit().beep();  // ← Goodbye beep
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