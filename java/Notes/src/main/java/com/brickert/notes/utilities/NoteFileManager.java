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

public class NoteFileManager {
    public static String generateFilename(String title) {
        String filename = title.toLowerCase();
        filename = filename.replace(" ", "-");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd-HHmmss");
        String timestamp = LocalDateTime.now().format(formatter);

        filename = filename + "-" + timestamp + ".md";
        return filename;
    }
    
    public static String formatNoteAsYaml(Note note) {
        String output = "";
        output = output + "---\n";
        output = output + "title: " + note.getTitle() + "\n";

        String createdUtc = note.getCreated().atZone(ZoneId.systemDefault()).toInstant().toString();
        output = output + "created: " + createdUtc + "\n";

        String modifiedUtc = note.getModified().atZone(ZoneId.systemDefault()).toInstant().toString();
        output = output + "modified: " + modifiedUtc + "\n";

        output = output + "tags: " + note.getTags() + "\n";
        output = output + "---\n\n";
        output = output + note.getContent() + "\n";
        return output;
    }

    public static String saveNote(Note note) throws IOException {
        String filename = generateFilename(note.getTitle());
        Path notesDir = Config.getNotesHome();
        Path filePath = notesDir.resolve(filename);
        String content = formatNoteAsYaml(note);
        Files.writeString(filePath, content);
        return filename;
    }

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

    public static String loadNoteContent(String filename) throws IOException {
        Path notesDir = Config.getNotesHome();
        Path filePath = notesDir.resolve(filename);
        String content = Files.readString(filePath);
        return content;
    }
} 


