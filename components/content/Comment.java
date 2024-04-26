package components.content;

import java.util.ArrayList;

public class Comment {
    // Variables for the comments class
    private int commentID;
    private String userID;
    private String commentText;
    private int postID;
    private int interactions;
    private ArrayList<Integer> interactedUsers;
    /////////////////////////////////

    // Constructor for posts class
    public Comment(int commentID, String userID, String commentText, int postID) {
        this.commentID = commentID;
        this.userID = userID;
        this.commentText = commentText;
        this.postID = postID;
        this.interactions = 0;
        this.interactedUsers = new ArrayList<Integer>();

    }
    /////////////////////////////////

    // Setters and getters for the posts class
    public void setCommentID(int commentID) {
        this.commentID = commentID;
    }

    public int getCommentID() {
        return commentID;
    }

    public String getUserID() {
        return userID;
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
    public int getInteractions() {
        return interactions;
    }
    //////////////////////////////

    // Methods to increase and decrease interaction count
    public void incrementInteractions() {
        interactions++;
    }

    public void decrementInteractions() {
        interactions--;
    }

    // Methods to add and remove interacted users
    public void addInteractedUser(int userID) {
        interactedUsers.add(userID);
    }

    public void removeInteractedUser(int userID) {
        interactedUsers.remove(userID);
    }
    /////////////////////////////////

}
