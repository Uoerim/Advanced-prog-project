package com.mycompany.project1;
import java.util.*;
public class Comment {
    private User user; 
    private String commentText;
    private int postID;
    public Comment(User user, String commentText, int postID) {
        this.user = user;
        this.commentText = commentText;
        this.postID = postID;

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

    public int getPostID() {
        return postID;
    }
    
}
