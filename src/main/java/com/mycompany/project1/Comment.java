package com.mycompany.project1;
import java.util.*;
public class Comment extends Interaction {
    private String commentText;
    public Comment(User user, String commentText, Posts post) {
        super(user,post);
        this.commentText = commentText;
    }
    
    public void setCommentText(String commentText) {
        this.commentText = commentText;
    }

    public String getCommentText() {
        return commentText;
    }
    
}
