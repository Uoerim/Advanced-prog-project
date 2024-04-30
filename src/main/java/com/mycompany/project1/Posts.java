package com.mycompany.project1;
import java.util.*;
public class Posts {
    // Variables for the posts class
    private String postText;
    private String postImage;
    private String description;
    private int postID;
    private User user;
    private int likesCount;
    private int commentsCount;
    private static int postCounter;
    private ArrayList<Comment> comments;
    private ArrayList<Like> likes;
    private Date dateCreated;
    /////////////////////////////////

    // Constructor for posts class
    public Posts(User user,String description) {
        this.postID = postCounter;
        this.user = user;
        this.description=description;
        this.comments = new ArrayList<>();
        this.likes = new ArrayList<>();
        postCounter++;
        this.dateCreated=new Date();
    }
    public Posts(int postID,User user,String description){
        this.postID=postID;
        this.user=user;
        this.description=description;
        this.comments=new ArrayList<>();
        this.likes=new ArrayList<>();
        this.dateCreated=new Date();
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
    public void setDescription(String description){
        this.description=description;
    }
    public String getDescription(){
        return this.description;
    }
    //set PostID
    public int getPostID() {
        return this.postID;
    }
    //set user
    public User getUser() {
        return this.user;
    }

    public int getLikesCount() {
        return this.likesCount;
    }
    public int getCommentsCount(){
        return this.commentsCount;
    }
    public void sharePost(User author,String postText,String postImage,String description){
        Posts sharedPost=new Posts(this.postID,author,description);
        sharedPost.setPostText(postText);
        sharedPost.setPostImage(postImage);
        user.share(sharedPost);
    }
    /////////////////////////////////

    // Method to display add and remove interactions
    public ArrayList<Like> getLikes() {
        return likes;
    }
    public void addInteraction(Like like) {
        likes.add(like);
        likesCount++;
    }
    public void removeInteraction(Like like) {
        likes.remove(like);
        likesCount--;
    }

    // Method to display, add and remove comments
    public ArrayList<Comment> getComments() {
        return comments;
    }

    public void addComment(User user,String commentText) {
        Comment comment=new Comment(user,commentText,this);
        comments.add(comment);
        commentsCount++;
    }

    public void removeComment(Comment comment) {
        comments.remove(comment);
        commentsCount--;
    }
    public Date getDateCreated(){
        return this.dateCreated;
    }
    public String findComment(int index){
        return comments.get(index).getCommentText();
    }
    /////////////////////////////////

}
