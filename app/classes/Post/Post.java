package app.classes.Post;
import java.util.ArrayList;

public class Post {
    private String postId;
    private String postText;
    private String username;
    private ArrayList<Comment> comments;
    private ArrayList<Like> interactions;

    public Post(String postId, String postText, String username) {
        this.postId = postId;
        this.postText = postText;
        this.username = username;
        this.comments = new ArrayList<Comment>();
        this.interactions = new ArrayList<Like>();
    }

    public void setPostId(String postId) {
        this.postId = postId;
    }
    public String getPostId() {
        return this.postId;
    }
    public void setPostText(String postText) {
        this.postText = postText;
    }

    public String getPostText() {
        return this.postText;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return this.username;
    }

    public void setComments(ArrayList<Comment> comments) {
        this.comments = comments;
    }

    public ArrayList<Comment> getComments() {
        return this.comments;
    }

    public void setInteractions(ArrayList<Like> interactions) {
        this.interactions = interactions;
    }

    public ArrayList<Like> getLikes() {
        return this.interactions;
    }

    public void addComment(Comment comment) {
        this.comments.add(comment);
    }

    public void addInteraction(Like interaction) {
        this.interactions.add(interaction);
    }

    public void removeComment(Comment comment) {
        this.comments.remove(comment);
    }

    public void removeInteraction(Like interaction) {
        this.interactions.remove(interaction);
    }

    public void viewData(){
        System.out.println("Post Text: " + this.postText);
        System.out.println("Username: " + this.username);        
    }
}
