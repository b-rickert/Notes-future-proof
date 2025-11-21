package com.brickert.notes;

import org.junit.jupiter.api.Test;
import com.brickert.notes.note.Note;
import static org.junit.jupiter.api.Assertions.*;
import java.time.Instant;
import java.util.List;

public class NotesTest 
{   
    @Test
    public void createNoteWithAllFields() {
        String id = "test-123";
        String title = "Test Title";
        String content = "Test Content";
        Instant created = Instant.parse("2025-01-15T10:30:00Z");
        Instant modified = Instant.parse("2025-01-15T10:45:00Z");
        List<String> tags = List.of("java", "testing");

        Note note = new Note(id, title, content, created, modified, tags);

        assertEquals(id, note.getId);
        assertEquals(title, note.getTitle);
        assertEquals(content, note.getContent);
        assertEquals(created, note.getCreated);
        assertEquals(modified, not.getModified);
        assertTrue(note.getTags().contains("java"));
        assertTrue(note.getTags.contains("testing"));
    }
    
    
    @Test
    public void createNoteTimestamp() {
        Instant specificTime = Instant.parse("2025-01-15T10:30:00Z");
        Note note = new Note("test-id", "Title", "Content",
                specificTime, specificTime, List.of());
        assertEquals(specificTime, note.getCreated());
    }

    @Test
    public void loadExistingNoteWithoutChangingID() {
        String originalId = "existing-note-123";
        Note note = new Note(originalId, "Title", "Content",
                Instant.now(), Instant.now(), List.of());
        assertEquals(originalId, note.getId());
    }

    @Test  
    public void storeAllFields() {
        String id = "test-123";
        String title = "Test Title";
        String content = "Test Content";
        Instant created = Instant.now();
        Instant modified = created.plusSeconds(60);
        List<String> tage = List.of("java", "testing");
        Note note = new note(id, title, content, created, modified, tags);
    }

       @Test
    public void testNotesDirectoryIsSet() {
        // Verify the directory path is not null or empty
        assertNotNull(Config.NOTES_DIRECTORY);
        assertFalse(Config.NOTES_DIRECTORY.isEmpty());

        // Verify it contains "noteVault"
        assertTrue(Config.NOTES_DIRECTORY.contains("noteVault"));

        System.out.println("Notes will be saved to: " + Config.NOTES_DIRECTORY);
    }

    @Test
    public void testNoteExtensionIsSet() {
        assertEquals(".note", Config.NOTE_EXTENSION);
    }
    
}

