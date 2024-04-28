package com.mycompany.project1;
import java.util.*;
public class Comment {
    private User user; 
    private String commentText;
    private Posts post;
    public Comment(User user, String commentText, Posts post) {
        this.user = user;
        this.commentText = commentText;
        this.post = post;

    }
    public User getUser() {
        return user;
    }

    public void setCommentText(String commentText) {
        this.commentText = commentText;
    }

    public String getCommentText() {
        return commentText;
    }

    public Posts getPost() {
        return post;
    }
    
}
