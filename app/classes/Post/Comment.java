package app.classes.Post;

public class Comment {

    private String username;
    private String commentText;

    public Comment(String username, String commentText) {
        this.username = username;
        this.commentText = commentText;
    }

    public void setUsername(String userID) {
        this.username = userID;
    }

    public String getUsername() {
        return username;
    }

    public void setCommentText(String commentText) {
        this.commentText = commentText;
    }

    public String getCommentText() {
        return commentText;
    }

}
