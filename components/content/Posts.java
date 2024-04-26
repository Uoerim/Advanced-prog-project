package components.content;
import java.util.*;
public class Posts {
    // Variables for the posts class
    private String postText;
    private String postImage;
    private int postID;
    private int userID;
    private int interactionsCount;
    private ArrayList<Comment> comments;
    private ArrayList<Interaction> interactions;
    /////////////////////////////////

    // Constructor for posts class
    public Posts(String postText, String postImage, int postID, int userID) {
        this.postText = postText;
        this.postImage = postImage;
        this.postID = postID;
        this.userID = userID;
        this.interactionsCount = 0;
        this.comments = new ArrayList<Comment>();
        this.interactions = new ArrayList<Interaction>();
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

    public int getInteractionsCount() {
        return interactionsCount;
    }
    /////////////////////////////////

    // Method to display add and remove interactions
    public ArrayList<Interaction> getInteractions() {
        return interactions;
    }
    public void addInteraction(Interaction interaction) {
        interactions.add(interaction);
    }
    public void removeInteraction(Interaction interaction) {
        interactions.remove(interaction);
    }

    // Method to display, add and remove comments
    public ArrayList<Comment> getComments() {
        return comments;
    }

    public void addComment(Comment comment) {
        comments.add(comment);
    }

    public void removeComment(Comment comment) {
        comments.remove(comment);
    }
    /////////////////////////////////

    // Methods to increase and decrease interaction count
    public void incrementInteractionsCount() {
        interactionsCount++;
    }

    public void decrementInteractionsCount() {
        interactionsCount--;
    }
    /////////////////////////////////

}
