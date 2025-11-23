package com.brickert.notes.utilities;

import com.brickert.notes.note.Note;
import com.brickert.notes.config.Config;
import java.nio.file.Path;
import java.nio.file.Files;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.ArrayList;
import java.util.stream.Stream;

//manages all my note files, performing CRUD operations and some extra that I added
public class NoteFileManager {
    public static String generateFilename(String title) {
        String filename = title.toLowerCase();
        filename = filename.replace(" ", "-");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd-HHmmss");
        String timestamp = LocalDateTime.now().format(formatter);

        filename = filename + "-" + timestamp + ".md";
        return filename;
    }
    
    //Formates note to YAML for Title, created, modified, and tags
    public static String formatNoteAsYaml(Note note) {
        String output = "";
        output = output + "---\n";
        output = output + "title: " + note.getTitle() + "\n";

        //converts timestamps to UTC 
        String createdUtc = note.getCreated().atZone(ZoneId.systemDefault()).toInstant().toString();
        output = output + "created: " + createdUtc + "\n";

        String modifiedUtc = note.getModified().atZone(ZoneId.systemDefault()).toInstant().toString();
        output = output + "modified: " + modifiedUtc + "\n";

        output = output + "tags: " + note.getTags() + "\n";
        output = output + "---\n\n";
        output = output + note.getContent() + "\n";
        return output;
    }

    //saves Note to directory as a markdown file and returns the filename
    public static String saveNote(Note note) throws IOException {
        String filename = generateFilename(note.getTitle());
        Path notesDir = Config.getNotesHome();
        Path filePath = notesDir.resolve(filename);
        String content = formatNoteAsYaml(note);
        Files.writeString(filePath, content);
        return filename;
    }

    //list all markdown files in note directory, more specifically the ones ending with "".md" (Markdown file)
    public static List<String> listAllNotes() throws IOException {
        Path notesDir = Config.getNotesHome();
        List <String> filenames = new ArrayList<>();

        Files.list(notesDir).forEach(path -> {
            String filename = path.getFileName().toString();
            if (filename.endsWith(".md")) {
                filenames.add(filename);
            }
        });
        return filenames;
    }

    //loads complete content of note file as a string
    public static String loadNoteContent(String filename) throws IOException {
        Path notesDir = Config.getNotesHome();
        Path filePath = notesDir.resolve(filename);
        String content = Files.readString(filePath);
        return content;
    }

    //deletes a note file from directory
    public static void deleteNote(String filename) throws IOException {
        Path notesDir = Config.getNotesHome();
        Path filePath = notesDir.resolve(filename);
        Files.delete(filePath);
    }

    //searches all notes for a keyword, checks file name and content(tags), and returns a list of matching items
    public static List<String> searchNotes(String keyword) throws IOException {
        List<String> allNotes = listAllNotes();
        List<String> matchingNotes = new ArrayList<>();

        for (String filename : allNotes) {
            String content = loadNoteContent(filename);
            if (filename.toLowerCase().contains(keyword.toLowerCase()) || 
                content.toLowerCase().contains(keyword.toLowerCase())) {
                    matchingNotes.add(filename);
                }
        }
        return matchingNotes;
    }

    //opens nano text editor for creating new notes, uses temp file that is deleted after editing, returns content entered to user
    public static String openNanoForContent() throws IOException, InterruptedException {  
        Path tempFile = Files.createTempFile("bricktionary-", ".md");   //create temp file

        //launches nano editor with temp file
        ProcessBuilder pb = new ProcessBuilder("nano", tempFile.toString());    //open nano with temp file
        pb.inheritIO();      //connect nano to the terminal
        Process process = pb.start();  
        process.waitFor();      //wait until user closes nano
        String content = Files.readString(tempFile);  //read the content from temp file
        Files.delete(tempFile);     //deletes temp file
        return content.trim();
    }

    //opens nano editor to edit a note, similar to openNanoForContent but pre-populates the editor
    public static String openNanoForEdit(String existingContent) throws IOException, InterruptedException {
        Path tempFile = Files.createTempFile("bricktionary-edit-", ".md");
        ProcessBuilder pb = new ProcessBuilder("nano", tempFile.toString());
        pb.inheritIO();
        Process process = pb.start();
        process.waitFor();
        String content = Files.readString(tempFile);
        Files.delete(tempFile);
        return content.trim();
    }

        // Get total word count across all my notes, and splits content by whitespace to count words.
    public static int getTotalWordCount() throws IOException {
        List<String> allNotes = listAllNotes();
        int totalWords = 0;
        
        for (String filename : allNotes) {
            String content = loadNoteContent(filename);
            String[] words = content.trim().split("\\s+"); //splits whitespace 
            totalWords += words.length;
        }
        
        return totalWords;
    }

    // Get longest note (returns filename)
    public static String getLongestNote() throws IOException {
        List<String> allNotes = listAllNotes();
        String longestNote = "";
        int maxWords = 0;
        
        for (String filename : allNotes) {
            String content = loadNoteContent(filename);
            String[] words = content.trim().split("\\s+");
            if (words.length > maxWords) {
                maxWords = words.length;
                longestNote = filename;
            }
        }
        
        return longestNote;
    }

    // looks at all unique tags between all my notes, parses YAML formatter tags field, and returns tags without repeating them
    public static List<String> getAllUniqueTags() throws IOException {
        List<String> allNotes = listAllNotes();
        List<String> allTags = new ArrayList<>();
        
        for (String filename : allNotes) {
            String content = loadNoteContent(filename);
            // Find tags line in YAML
            for (String line : content.split("\n")) {
                if (line.startsWith("tags:")) {
                    String tagsStr = line.substring(5).trim();
                    // Remove brackets [ ]
                    tagsStr = tagsStr.replace("[", "").replace("]", "");
                    if (!tagsStr.isEmpty()) {
                        String[] tags = tagsStr.split(",");
                        for (String tag : tags) {
                            String cleanTag = tag.trim();
                            if (!cleanTag.isEmpty() && !allTags.contains(cleanTag)) {
                                allTags.add(cleanTag);
                            }
                        }
                    }
                }
            }
        }
        
        return allTags;
    }

    // searches notes for most frequently used tags and returns tag name or None if null
    public static String getMostUsedTag() throws IOException {
        List<String> allNotes = listAllNotes();
        java.util.Map<String, Integer> tagCount = new java.util.HashMap<>();
        
        for (String filename : allNotes) { //count occurences of each individual tag
            String content = loadNoteContent(filename);
            for (String line : content.split("\n")) {
                if (line.startsWith("tags:")) {
                    String tagsStr = line.substring(5).trim();
                    tagsStr = tagsStr.replace("[", "").replace("]", "");
                    if (!tagsStr.isEmpty()) {
                        String[] tags = tagsStr.split(",");
                        for (String tag : tags) {
                            String cleanTag = tag.trim();
                            if (!cleanTag.isEmpty()) {
                                tagCount.put(cleanTag, tagCount.getOrDefault(cleanTag, 0) + 1);
                            }
                        }
                    }
                }
            }
        }
        //finds the tag with the highest count of usage
        String mostUsed = "None";
        int maxCount = 0;
        for (java.util.Map.Entry<String, Integer> entry : tagCount.entrySet()) {
            if (entry.getValue() > maxCount) {
                maxCount = entry.getValue();
                mostUsed = entry.getKey();
            }
        }
        
        return mostUsed;
    }

    // Get title from filename (removes timestamp and .md)
    public static String getTitleFromFilename(String filename) {
        // Remove .md extension
        String name = filename.replace(".md", "");
        // Remove timestamp (last part after final dash with numbers)
        int lastDash = name.lastIndexOf("-");
        if (lastDash > 0) {
            // Check if it's a timestamp section, remove it
            String possibleTimestamp = name.substring(lastDash + 1);
            if (possibleTimestamp.matches("\\d+")) {
                name = name.substring(0, lastDash);
                // Remove the date part too
                lastDash = name.lastIndexOf("-");
                if (lastDash > 0) {
                    possibleTimestamp = name.substring(lastDash + 1);
                    if (possibleTimestamp.matches("\\d+")) {
                        name = name.substring(0, lastDash);
                    }
                }
            }
        }
        // Replace dashes with spaces and capitalize
        name = name.replace("-", " ");
        return name;
}
} 


