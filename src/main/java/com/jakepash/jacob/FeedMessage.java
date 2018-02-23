package com.jakepash.jacob;

public class FeedMessage {

    private String title;
    private String description;
    private String author;


    public String getTitle() {
        return title;
    }
    public void setTitle(String titleInput) {
        title = titleInput;
    }

    public void setDescription(String descriptionInput) {
        title = descriptionInput;
    }
    public String getDescription() {
        return description;
    }

    public String getAuthor() {
        return author;
    }
    public void setAuthor(String authorInput) {
        title = authorInput;
    }

    @Override
    public String toString() {
        return "FeedMessage [title=" + title + ", description=" + description
                + ", author=" + author + "]";
    }




}
