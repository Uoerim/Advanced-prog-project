package components.content;

public class Comment {
    // Variables for the comments class
    private int commentID;
    private int userID; //userID or User
    private String commentText;
    private int postID;
    /////////////////////////////////

    // Constructor for posts class
    public Comment(int commentID, int userID, String commentText, int postID) {
        this.commentID = commentID;
        this.userID = userID;
        this.commentText = commentText;
        this.postID = postID;

    }
    /////////////////////////////////

    // Setters and getters for the posts class
    public void setCommentID(int commentID) {
        this.commentID = commentID;
    }

    public int getCommentID() {
        return commentID;
    }

    public int getUserID() {
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
    /////////////////////////////////
}
