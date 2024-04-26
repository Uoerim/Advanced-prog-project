package components.content;
public class Interaction {
    private int userID;
    private int postID;

    public Interaction(String userID, int postID) {
        this.userID = userID;
        this.postID = postID;
    }

    // Setters and getters
    public void setUserID(String userID) {
        this.userID = userID;
    }
    public String getUserID() {
        return userID;
    }
    public void setPostID(int postID) {
        this.postID = postID;
    }
    public int getPostID() {
        return postID;
    }
    
}
