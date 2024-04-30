package com.mycompany.project1;
import java.util.*;

public class User {
    private String userName;
    private String email;
    private String password;
    private String userBio;
    private String profilePic;
    private HashMap<String,User> friendList; 
    private HashMap<String,User> friendRequestsSent;
    private HashMap<String,User> friendRequests;
    private int friendCount;
    private ArrayList<Posts> posts;
    
    public User(String email,String username,String password) {
        this.email = email;
        this.password = password;
        this.userName=username;
    }
    
    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserName() {
        return this.userName;
    }
    public void setPosts(ArrayList<Posts> posts){
        this.posts=posts;
    }
    public ArrayList getPosts(){
        return this.posts;
    }

    public void setUserBio(String userBio) {
        this.userBio = userBio;
    }

    public String getUserBio() {
        return userBio;
    }

    public void setProfilePic(String profilePic) {
        this.profilePic = profilePic;
    }

    public String getProfilePic() {
        return profilePic;
    }
    public void setFriendList(HashMap<String,User> friendList){
        this.friendList=friendList;
    }

    public HashMap<String,User> getFriendList() {
        return friendList;
    }

    public int getFriendCount() {
        return friendCount;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return this.password;
    }
    
    public void setEmail(String email){
        this.email=email;
    }
    
    public String getEmail(){
        return this.email;
    }
    public void addFriend(User friend) {
        friendRequestsSent.put(friend.getUserName(),friend);
    }

    public void removeFriend(User friend) {
        friendList.remove(friend.getUserName());
        friendCount--;
    }
    public HashMap<String,User> getFriendRequestsSent(){
        return friendRequestsSent;
    }
    public HashMap<String,User> getFriendRequests(){
        return friendRequests;
    }
    public void setFriendRequestsSent( HashMap<String,User> friendRequestsSent){
        this.friendRequestsSent=friendRequestsSent;
    }
    public void setFriendRequests(HashMap<String,User> friendRequests){
        this.friendRequests=friendRequests;
    }
    public void acceptFriend(User user){
        friendList.put(user.getUserName(),user);
    }
    public void rejectFriend(User user){
        friendRequests.remove(user.getUserName());
    }
    public Posts createPost(User author,String postText,String postImage,String description){
        Posts post=new Posts(author,description);
        post.setPostText(postText);
        post.setPostImage(postImage);
        posts.add(post);
        return post;
    }
    public void share(Posts post){
        posts.add(post);
    }
    public void deletePost(Posts post){
        posts.remove(post);
    }
    public void displayFriendList(){
        System.out.println(this.friendList);
    }
    public String findPost(int index){
        return posts.get(index).getPostText();
    }
    
}

