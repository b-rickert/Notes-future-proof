package com.brickert.notes.note;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


public class Note {
    private String title;
    private String content;
    private LocalDateTime created;
    private LocalDateTime modified;
    private List<String> tags; //optional
    private String author;  //optional
    private String status;  //optional
    private Integer priority;  //optional

    // constructor to create a note with title and content
    public Note (String title, String content) {
        this.title = title;
        this.content = content;
        this.created = LocalDateTime.now();
        this.modified = LocalDateTime.now(); //java.time
        this.tags = new ArrayList<>();  
    }
    // default constructor to allow user to create a note without providing title and content
    // this will set the title to "untitled" and content to an empty string
    // the created and modified timestamps will be set to the current time
    // the tags list will be initialized as an empty list
    public Note () {
        this.title = "untitled";
        this.content = "";
        this.created = LocalDateTime.now();
        this.modified = LocalDateTime.now();
        this.tags = new ArrayList<>();  
    }
    
    // getters and setters for all fields
    // these allow access and modification the fields of the note
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public LocalDateTime getModified() {
        return modified;
    }

    public void setModified(LocalDateTime modified) {
        this.modified = modified;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public void addTag(String tag) {
        this.tags.add(tag);
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }
}
