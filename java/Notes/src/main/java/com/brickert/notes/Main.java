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

    public static void showLoadingAnimation() throws InterruptedException {
    String[] frames = {
        "║ [🧱                    ] Loading...        ║",
        "║ [🧱🧱                  ] Loading...        ║",
        "║ [🧱🧱🧱                ] Loading...        ║",
        "║ [🧱🧱🧱🧱              ] Loading...        ║",
        "║ [🧱🧱🧱🧱🧱            ] Loading...        ║",
        "║ [🧱🧱🧱🧱🧱🧱          ] Loading...        ║",
        "║ [🧱🧱🧱🧱🧱🧱🧱        ] Loading...        ║",
        "║ [🧱🧱🧱🧱🧱🧱🧱🧱      ] Loading...        ║",
        "║ [🧱🧱🧱🧱🧱🧱🧱🧱🧱    ] Loading...        ║",
        "║ [🧱🧱🧱🧱🧱🧱🧱🧱🧱🧱  ] Loading...        ║",
        "║ [🧱🧱🧱🧱🧱🧱🧱🧱🧱🧱🧱] Loading...        ║"
    };
    
    System.out.println("╔════════════════════════════════════════════╗");
    for (int i = 0; i < 2; i++) {
        for (String frame : frames) {
            System.out.print("\r" + frame);
            Thread.sleep(100);
        }
    }
    System.out.println("\r║ [🧱🧱🧱🧱🧱🧱🧱🧱🧱🧱🧱] Ready!║");
    System.out.println("╚════════════════════════════════════════════╝");
    }

    public static void main(String[] args) {
            Scanner scanner = new Scanner(System.in);

        try {
            Path notesDir = Config.ensureNotesDirectoryExists();
            System.out.println("Notes directory created/verified at: " + notesDir);
            System.out.println();
            showLoadingAnimation();
            java.awt.Toolkit.getDefaultToolkit().beep();
            System.out.println("╔════════════════════════════════════════════╗");
            System.out.println("║                                            ║");
            System.out.println("║  ██████╗ ██████╗ ██╗ ██████╗██╗  ██╗       ║");
            System.out.println("║  ██╔══██╗██╔══██╗██║██╔════╝██║ ██╔╝       ║");
            System.out.println("║  ██████╔╝██████╔╝██║██║     █████╔╝        ║");
            System.out.println("║  ██╔══██╗██╔══██╗██║██║     ██╔═██╗        ║");
            System.out.println("║  ██████╔╝██║  ██║██║╚██████╗██║  ██╗       ║");
            System.out.println("║  ╚═════╝ ╚═╝  ╚═╝╚═╝ ╚═════╝╚═╝  ╚═╝tionary║");
            System.out.println("║                                            ║");
            System.out.println("║    Brick's Personal Dictionary of Notes    ║");
            System.out.println("║                                            ║");
            System.out.println("╚════════════════════════════════════════════╝");
            while (true) {
                int noteCount = NoteFileManager.listAllNotes().size();
                System.out.println("\n╔════════════════════════════════════════════╗");
                System.out.println("║              BRICKtionary                  ║");
                System.out.println("║    Building knowledge brick by brick       ║");
                System.out.println("║" + padRight("          You have " + noteCount + " note(s) ", 44) + "║");
                System.out.println("╠════════════════════════════════════════════╣");
                System.out.println("║  1. Create a new note                      ║");
                System.out.println("║  2. View all notes                         ║");
                System.out.println("║  3. Search notes                           ║");
                System.out.println("║  4. Delete a note                          ║");
                System.out.println("║  5. Edit a note                            ║");
                System.out.println("║  6. View Stats                             ║");
                System.out.println("║  7. Exit                                   ║");
                System.out.println("╚════════════════════════════════════════════╝");
                System.out.print("Enter your choice: ");
                String input = scanner.nextLine();

                // Check for easter egg
                if (input.equalsIgnoreCase("brick")) {
                    System.out.println("\n🧱🧱🧱🧱🧱🧱🧱🧱🧱🧱🧱🧱🧱🧱🧱🧱🧱🧱🧱🧱🧱");
                    System.out.println("🧱   YOU FOUND THE SECRET!              🧱");
                    System.out.println("🧱 You are a true BRICKtionary          🧱");
                    System.out.println("🧱          Builder!                    🧱");
                    System.out.println("🧱    \"Still worthy.\" - Thor            🧱");
                    System.out.println("🧱🧱🧱🧱🧱🧱🧱🧱🧱🧱🧱🧱🧱🧱🧱🧱🧱🧱🧱🧱🧱");
                    java.awt.Toolkit.getDefaultToolkit().beep();
                    continue;
                }

                //parses user input as an interger 
                int choice;
                try {
                    choice = Integer.parseInt(input);
                } catch (NumberFormatException e) {
                    System.out.println("Invalid choice. Please enter a number.");
                    continue;
                }

                switch (choice) {
                    case 1:
                        System.out.println("\n╔══════════════════════════════════════╗");
                        System.out.println("║           CREATE NEW NOTE            ║");
                        System.out.println("╚══════════════════════════════════════╝");
                        System.out.print("Enter note title: ");
                        String title = scanner.nextLine();

                        System.out.println("Opening nano editor for content...");
                        System.out.println("(Save with Ctrl+O, Exit with Ctrl+X)");
                        String content = NoteFileManager.openNanoForContent();
                        System.out.print("Enter tags (comma seperated, or press Enter to skip): ");
                        String tagsInput = scanner.nextLine();
                        Note newNote = new Note(title, content);
                        if (!tagsInput.trim().isEmpty()) {
                            String[] tagArray = tagsInput.split(",");
                            for (String tag : tagArray) {
                                newNote.addTag(tag.trim()); 
                            }
                        }
                        //save note and audible success noise
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
                                
                                //Display notes content inside the box
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
                        System.out.println("\n╔══════════════════════════════════════╗");
                        System.out.println("║           SEARCH NOTES               ║");
                        System.out.println("╚══════════════════════════════════════╝");
                        System.out.print("Enter search term: ");
                        String searchTerm = scanner.nextLine();

                        List<String> results = NoteFileManager.searchNotes(searchTerm);
                        if (results.isEmpty()) {
                            System.out.println("\n╔══════════════════════════════════════╗");
                            System.out.println("║  No notes found matching search      ║");
                            System.out.println("╚══════════════════════════════════════╝");
                        } else {
                            int searchMaxLength = 20;
                            for (String note : results) {
                                if (note.length() > searchMaxLength) {
                                    searchMaxLength = note.length();
                                }
                            }
                            int searchBoxWidth = searchMaxLength + 6;
                            String searchBorder = "═".repeat(searchBoxWidth);
                            System.out.println("\n╔" + searchBorder + "╗");
                            System.out.println("║" + padRight("  Found " + results.size() + " note(s):", searchBoxWidth) + "║");
                            System.out.println("╠" + searchBorder + "╣");

                            for (int i = 0; i < results.size(); i++) {
                                String line = " " + (i + 1) + ". " + results.get(i);
                                System.out.println("║" + padRight(line, searchBoxWidth) + "║");
                            }

                            String returnOption = " " + (results.size() + 1) + ". Return to main menu";
                            System.out.println("║" + padRight(returnOption, searchBoxWidth) + "║");
                            System.out.println("╚" + searchBorder + "╝");
                            
                            System.out.print("Enter choice to view note: ");
                            int searchChoice = scanner.nextInt();
                            scanner.nextLine();

                            if (searchChoice > 0 && searchChoice <= results.size()) {
                                String selectedFile = results.get(searchChoice - 1);
                                String noteContent = NoteFileManager.loadNoteContent(selectedFile);

                                String[] lines = noteContent.split("\n");
                                int contentMaxLength = 15;
                                for (String line : lines) {
                                    if (line.length() > contentMaxLength) {
                                        contentMaxLength = line.length();
                                    }
                                }
                            int contentBoxWidth = contentMaxLength + 2;
                            String contentBorder = "═".repeat(contentBoxWidth);
                            System.out.println("\n╔" + contentBorder + "╗");
                            System.out.println("║" + padRight(" BRICKtionary Entry", contentBoxWidth) + "║");
                            System.out.println("╠" + contentBorder + "╣");

                            for (String line : lines) {
                                System.out.println("║" + padRight(" " + line, contentBoxWidth) + "║");
                            }
                            System.out.println("╚" + contentBorder + "╝");
                            }
                        }
                        break;
                    case 4: 
                        List<String> notesToDelete = NoteFileManager.listAllNotes();
                        if (notesToDelete.isEmpty()) {
                            System.out.println("\n╔══════════════════════════════════════╗");
                            System.out.println("║          No notes found              ║");
                            System.out.println("╚══════════════════════════════════════╝");
                        } else {
                            int deleteMaxLength = 15;
                            for (String note : notesToDelete) {
                                if (note.length() > deleteMaxLength) {
                                    deleteMaxLength = note.length();
                                }
                            }
                            int deleteBoxWidth = deleteMaxLength + 6;
                            String deleteBorder = "═".repeat(deleteBoxWidth);
                            
                            System.out.println("\n╔" + deleteBorder + "╗");
                            System.out.println("║" + padRight("        DELETE NOTE", deleteBoxWidth) + "║");
                            System.out.println("╠" + deleteBorder + "╣");

                            for (int i = 0; i < notesToDelete.size(); i++) {
                                String line = " " + (i + 1) + ". " + notesToDelete.get(i);
                                System.out.println("║" + padRight(line, deleteBoxWidth) + "║");
                            }

                            String cancelOption = " " + (notesToDelete.size() + 1) + ". Cancel";
                            System.out.println("║" + padRight(cancelOption, deleteBoxWidth) + "║");
                            System.out.println("╚" + deleteBorder + "╝");   
                            
                            System.out.print("Enter note to delete: ");
                            int deleteChoice = scanner.nextInt();
                            scanner.nextLine();

                            if (deleteChoice > 0 && deleteChoice <= notesToDelete.size()) {
                                String fileToDelete = notesToDelete.get(deleteChoice - 1);
                                System.out.print("Are you sure you want to delete '" + fileToDelete + "'? (Y/N): ");
                                String confirm = scanner.nextLine();

                                if (confirm.equalsIgnoreCase("Y") || confirm.equalsIgnoreCase("yes")) {
                                    NoteFileManager.deleteNote(fileToDelete);
                                    java.awt.Toolkit.getDefaultToolkit().beep();
                                    System.out.println("✓ Note deleted successfully!");
                                } else {
                                    System.out.println("Delete cancelled.");
                                }
                            } else if (deleteChoice != notesToDelete.size() + 1) {
                                System.out.println("Invalid choice.");
                            }
                        }
                        break;
                    case 5:
                        List<String> notesToEdit = NoteFileManager.listAllNotes();
                        if (notesToEdit.isEmpty()) {
                            System.out.println("\n╔══════════════════════════════════════╗");
                            System.out.println("║          No notes found              ║");
                            System.out.println("╚══════════════════════════════════════╝");
                        } else {
                            int editMaxLength = 15;
                            for (String note : notesToEdit) {
                                if (note.length() > editMaxLength) {
                                    editMaxLength = note.length();
                                }
                            }
                            int editBoxWidth = editMaxLength + 6;
                            String editBorder = "═".repeat(editBoxWidth);
                            System.out.println("\n╔" + editBorder + "╗");
                            System.out.println("║" + padRight("        EDIT NOTE", editBoxWidth) + "║");
                            System.out.println("╠" + editBorder + "╣");

                            for (int i = 0; i < notesToEdit.size(); i++) {
                                String line = " " + (i + 1) + ". " + notesToEdit.get(i);
                                System.out.println("║" + padRight(line, editBoxWidth) + "║");    
                            }
                            String cancelOption = " " + (notesToEdit.size() + 1) + ". Cancel";
                            System.out.println("║" + padRight(cancelOption, editBoxWidth) + "║");
                            System.out.println("╚" + editBorder + "╝");
                            System.out.print("Select note to edit: ");
                            int editChoice = scanner.nextInt();
                            scanner.nextLine();

                            if (editChoice > 0 && editChoice <= notesToEdit.size()) {
                                String fileToEdit = notesToEdit.get(editChoice - 1);
                                String oldContent = NoteFileManager.loadNoteContent(fileToEdit);
                                System.out.println("\n╔══════════════════════════════════════╗");
                                System.out.println("║         EDITING NOTE                 ║");
                                System.out.println("╚══════════════════════════════════════╝");
                                System.out.print("Enter new title (or press Enter to keep current): ");  //edit title 
                                String newTitle = scanner.nextLine();
                                System.out.println("Opening nano to edit content..."); 
                                System.out.println("(Save with Ctrl+O, Exit with Ctrl+X)");
                                String newContent = NoteFileManager.openNanoForEdit(oldContent);   //edit content in nano
                                System.out.print("Enter new tags (comma seperated, or press Enter to keep current): ");
                                String newTagsInput = scanner.nextLine(); //edit tags

                                Note updatedNote;
                                if (newTitle.trim().isEmpty()) {
                                    String oldTitle = fileToEdit.substring(0, fileToEdit.lastIndexOf("-")).replace("-", " ");
                                    updatedNote = new Note(oldTitle, newContent);
                                } else {
                                    updatedNote = new Note(newTitle, newContent);
                                }
                                if (!newTagsInput.trim().isEmpty()) {
                                    String[] tagArray = newTagsInput.split(",");
                                    for (String tag : tagArray) {
                                        updatedNote.addTag(tag.trim());
                                    }
                                }
                                NoteFileManager.deleteNote(fileToEdit);
                                String newFilename = NoteFileManager.saveNote(updatedNote);
                                java.awt.Toolkit.getDefaultToolkit().beep();
                                System.out.println("✓ Note updated successfully!");
                            }
                        }
                        break;
                    case 6:
                        int totalNotes = NoteFileManager.listAllNotes().size();
                        int totalWords = NoteFileManager.getTotalWordCount();
                        int avgWords = (totalNotes > 0) ? totalWords / totalNotes : 0;
                        String longestNoteFile = NoteFileManager.getLongestNote();
                        String longestNote = NoteFileManager.getTitleFromFilename(longestNoteFile);
                        int uniqueTags = NoteFileManager.getAllUniqueTags().size();
                        String mostUsedTag = NoteFileManager.getMostUsedTag();
                        
                        System.out.println("\n╔════════════════════════════════════════════╗");
                        System.out.println("║            📊 STATISTICS 📊                ║");
                        System.out.println("╠════════════════════════════════════════════╣");
                        System.out.println("║" + padRight("  Total notes:        " + totalNotes, 44) + "║");
                        System.out.println("║" + padRight("  Total words:        " + totalWords, 44) + "║");
                        System.out.println("║" + padRight("  Average words/note: " + avgWords, 44) + "║");
                        System.out.println("║" + padRight("  Longest note:       " + longestNote, 44) + "║");
                        System.out.println("║" + padRight("  Unique tags:        " + uniqueTags, 44) + "║");
                        System.out.println("║" + padRight("  Most used tag:      " + mostUsedTag, 44) + "║");
                        System.out.println("╚════════════════════════════════════════════╝");
                        break;
                    case 7:
                        java.awt.Toolkit.getDefaultToolkit().beep();
                        System.out.println("Goodbye!");
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Invalid choice. Please enter a number 1-7.");
                        break;
                }
            }

            } catch (IOException | InterruptedException e) {
            System.out.println("Error creating notes directory: " + e.getMessage());
        }
    }
}