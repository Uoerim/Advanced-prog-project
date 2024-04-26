package components.content;

import java.util.ArrayList;

public class Posts {
    // Variables for the posts class
    private String postText;
    private String postImage;
    private int postID;
    private int userID;
    private int interactions;
    private ArrayList<Comment> comments;
    private ArrayList<Integer> interactedUsers;
    /////////////////////////////////

    // Constructor for posts class
    public Posts(String postText, String postImage, int postID, int userID) {
        this.postText = postText;
        this.postImage = postImage;
        this.postID = postID;
        this.userID = userID;
        this.interactions = 0;
        this.comments = new ArrayList<Comment>();
        this.interactedUsers = new ArrayList<Integer>();
    }
    /////////////////////////////////

    // Setters and getters for the posts class
    public void setPostText(String postText) {
        this.postText = postText;
    }

    public String getPostText() {
        return postText;
    }

    public void setPostImage(String postImage) {
        this.postImage = postImage;
    }

    public String getPostImage() {
        return postImage;
    }

    public int getPostID() {
        return postID;
    }

    public int getUserID() {
        return userID;
    }

    public int getInteractions() {
        return interactions;
    }
    /////////////////////////////////

    // Method to display add and remove comments
    public void addComment(Comment comment) {
        comments.add(comment);
    }

    public void removeComment(Comment comment) {
        comments.remove(comment);
    }
    /////////////////////////////////

    // Method to display all comments
    public ArrayList<Comment> getComments() {
        return comments;
    }
    /////////////////////////////////

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
