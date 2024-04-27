package com.mycompany.project1;
import java.util.*;
public class Posts {
    // Variables for the posts class
    private String postText;
    private String postImage;
    private int postID;
    private User user;
    private int interactionsCount;
    private int commentsCount; 
    private ArrayList<Comment> comments;
    private ArrayList<Interaction> interactions;
    /////////////////////////////////

    // Constructor for posts class
    public Posts(int postID, User user) {
        this.postID = postID;
        this.user = user;
        this.comments = new ArrayList<Comment>();
        this.interactions = new ArrayList<Interaction>();
    }
    /////////////////////////////////

    // Setters and getters for the posts class
    public void setPostText(String postText) {
        this.postText = postText;
    }

    public String getPostText() {
        return this.postText;
    }

    public void setPostImage(String postImage) {
        this.postImage = postImage;
    }

    public String getPostImage() {
        return this.postImage;
    }

    public int getPostID() {
        return this.postID;
    }

    public User getUser() {
        return this.user;
    }

    public int getInteractionsCount() {
        return this.interactionsCount;
    }
    public int getCommentsCount(){
        return this.commentsCount;
    }
    /////////////////////////////////

    // Method to display add and remove interactions
    public ArrayList<Interaction> getInteractions() {
        return interactions;
    }
    public void addInteraction(Interaction interaction) {
        interactions.add(interaction);
        interactionsCount++;
    }
    public void removeInteraction(Interaction interaction) {
        interactions.remove(interaction);
        interactionsCount--;
    }

    // Method to display, add and remove comments
    public ArrayList<Comment> getComments() {
        return comments;
    }

    public void addComment(Comment comment) {
        comments.add(comment);
        commentsCount++;
    }

    public void removeComment(Comment comment) {
        comments.remove(comment);
        commentsCount--;
    }
    /////////////////////////////////

}
