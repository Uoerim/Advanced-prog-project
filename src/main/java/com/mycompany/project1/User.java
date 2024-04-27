package com.mycompany.project1;
import java.util.*;

public class User {
    private String userName;
    private String password;
    private String userBio;
    private String profilePic;
    private ArrayList<User> friendList; 
    private int friendCount;
    private ArrayList<Posts> posts;
    
    public User(String userName, String password) {
        this.userName = userName;
        this.password = password;
        this.posts=new ArrayList<Posts>();
        this.friendList = new ArrayList<User>();
    }

    // Setters and Getters
    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
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

    public ArrayList<User> getFriendList() {
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

    public void addFriend(User friend) {
        friendList.add(friend);
        friendCount++;
    }

    public void removeFriend(User friend) {
        friendList.remove(friend);
        friendCount--;
    }
    
    public void deleteUser() {
        
    }
}

