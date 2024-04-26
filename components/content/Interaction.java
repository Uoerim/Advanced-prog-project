package components.content;
public class Interaction {
    private int userID;
    private int postID;

    public Interaction(int userID, int postID) {
        this.userID = userID;
        this.postID = postID;
    }

    // Setters and getters
    public void setUserID(int userID) {
        this.userID = userID;
    }
    public int getUserID() {
        return userID;
    }
    public void setPostID(int postID) {
        this.postID = postID;
    }
    public int getPostID() {
        return postID;
    }
    
}
