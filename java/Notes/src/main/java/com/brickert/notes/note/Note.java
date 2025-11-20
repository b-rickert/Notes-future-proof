package com.brickert.notes.note;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.time.LocalDateTime;
import java.util.List;

public class Note {
    private String title;
    private String content;
    private LocalDateTime created;
    private LocalDateTime modified;
    private List<String> tags;
    private String author;
    private String status;
    private Integer priority;

    public Note (String title, String content) {
        this.title = title;
        this.content = content;
        this.created = LocalDateTime.now();
        this.modified = LocalDateTime.now();
        this.tags = new ArrayList<>();
        this.author = author;
        this.status = status;
        this.priority = priority;

    }

}
