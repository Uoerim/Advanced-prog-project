package com.mycompany.project1;
import java.util.*;

public class User {
    private String userName;
    private String password;
    private String userBio;
    private String profilePic;
    private HashMap<String,User> friendList; 
    private HashMap<String,User> friendRequestsSent;
    private HashMap<String,User> friendRequests;
    private int friendCount;
    private ArrayList<Posts> posts;
    
    public User(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }
    
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

    public void addFriend(User friend) {
        friendRequestsSent.put(friend.getUserName(),friend);
        //friendList.put(friend.getUserName(),friend);
        //friendCount++;
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
    public void AcceptFriend(User user){
        friendList.put(user.getUserName(),user);
    }
    public void deleteUser() {
        
    }
}

