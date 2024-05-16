package app.classes.User;
import java.util.ArrayList;

import app.classes.Post.Post;

public class User {
    private String username;
    private String fullName;
    private String token; //not used
    private String email;
    private String userBio;
    private String picPath;
    private ArrayList<Integer> posts;
    private ArrayList<String> friendList;
    private ArrayList<String> friendRequestList;
    private ArrayList<String> friendRequestSentList;
    // private ArrayList<Integer> blockedUserList;
    // private ArrayList<Integer> blockedByList;
    // private Boolean isAdmin;
    // private Boolean isBanned;
    // private Boolean isVerified;
    // private Boolean isPrivate;

    public User(String username, String fullName, String email, String userBio,String picPath, String token ) {
        this.username = username;
        this.fullName = fullName;
        this.email = email;
        this.userBio = userBio;
        this.picPath = picPath;
        this.token = token;
        this.posts = new ArrayList<Integer>();
        this.friendList = new ArrayList<String>();
        this.friendRequestList = new ArrayList<String>();
        this.friendRequestSentList = new ArrayList<String>();
    }

    // Setters and Getters
    public void setPosts(ArrayList<Integer> posts) {
        this.posts = posts;
    }
    public ArrayList<Integer> getPosts() {
        return this.posts;
    }
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
    public String getFullName() {
        return this.fullName;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getEmail() {
        return this.email;
    }
    public void setToken(String token) {
        this.token = token;
    }
    public String getToken() {
        return this.token;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUserBio(String userBio) {
        this.userBio = userBio;
    }

    public String getUserBio() {
        return this.userBio;
    }

    public void setPicPath(String picPath) {
        this.picPath = picPath;
    }

    public String getPicPath() {
        return this.picPath;
    }

    public void setFriendList(ArrayList<String> friendList) {
        this.friendList = friendList;
    }

    public ArrayList<String> getFriendList() {
        return this.friendList;
    }

    public void setFriendRequestList(ArrayList<String> friendRequestList) {
        this.friendRequestList = friendRequestList;
    }

    public ArrayList<String> getFriendRequestList() {
        return this.friendRequestList;
    }

    public void setFriendRequestSentList(ArrayList<String> friendRequestSentList) {
        this.friendRequestSentList = friendRequestSentList;
    }

    public ArrayList<String> getFriendRequestSentList() {
        return this.friendRequestSentList;
    }

    // public void setBlockedUserList(ArrayList<Integer> blockedUserList) {
    //     this.blockedUserList = blockedUserList;
    // }

    // public ArrayList<Integer> getBlockedUserList() {
    //     return this.blockedUserList;
    // }

    // public void setBlockedByList(ArrayList<Integer> blockedByList) {
    //     this.blockedByList = blockedByList;
    // }

    // public ArrayList<Integer> getBlockedByList() {
    //     return this.blockedByList;
    // }

    // public void setIsAdmin(Boolean isAdmin) {
    //     this.isAdmin = isAdmin;
    // }

    // public Boolean getIsAdmin() {
    //     return this.isAdmin;
    // }

    // public void setIsBanned(Boolean isBanned) {
    //     this.isBanned = isBanned;
    // }

    // public Boolean getIsBanned() {
    //     return this.isBanned;
    // }

    // public void setIsVerified(Boolean isVerified) {
    //     this.isVerified = isVerified;
    // }

    // public Boolean getIsVerified() {
    //     return this.isVerified;
    // }

    // public void setIsPrivate(Boolean isPrivate) {
    //     this.isPrivate = isPrivate;
    // }

    // public Boolean getIsPrivate() {
    //     return this.isPrivate;
    // }

    // Methods

    public void addFriend(String friendId) {
        this.friendList.add(friendId);
    }

    public void removeFriend(String friendId) {
        this.friendList.remove(friendId);
    }

    public void addFriendRequest(String friendId) {
        this.friendRequestList.add(friendId);
    }

    public void removeFriendRequest(String friendId) {
        this.friendRequestList.remove(friendId);
    }

    public void addFriendRequestSent(String friendId) {
        this.friendRequestSentList.add(friendId);
    }

    public void removeFriendRequestSent(String friendId) {
        this.friendRequestSentList.remove(friendId);
    }

    // public void blockUser(int userId) {
    //     this.blockedUserList.add(userId);
    // }

    // public void unblockUser(int userId) {
    //     this.blockedUserList.remove(userId);
    // }

    // public void blockBy(int userId) {
    //     this.blockedByList.add(userId);
    // }

    // public void unblockBy(int userId) {
    //     this.blockedByList.remove(userId);
    // }

    // public void setAdmin() {
    //     this.isAdmin = true;
    // }

    // public void removeAdmin() {
    //     this.isAdmin = false;
    // }

    // public void setBanned() {
    //     this.isBanned = true;
    // }

    // public void removeBanned() {
    //     this.isBanned = false;
    // }

    // public void setVerified() {
    //     this.isVerified = true;
    // }

    // public void removeVerified() {
    //     this.isVerified = false;
    // }

    // public void setPrivate() {
    //     this.isPrivate = true;
    // }

    // public void removePrivate() {
    //     this.isPrivate = false;
    // }

    public void updateBio(String bio) {
        this.userBio = bio;
    }

    public void updatePic(String picPath) {
        this.picPath = picPath;
    }

    public void updateUsername(String username) {
        this.username = username;
    }

    public void clearFriendList() {
        this.friendList.clear();
    }

    public void clearFriendRequestList() {
        this.friendRequestList.clear();
    }

    public void clearFriendRequestSentList() {
        this.friendRequestSentList.clear();
    }

    // public void clearBlockedUserList() {
    //     this.blockedUserList.clear();
    // }

    // public void clearBlockedByList() {
    //     this.blockedByList.clear();
    // }

    public void viewData(){
        System.out.println("Username: " + this.username);
        System.out.println("Full Name: " + this.fullName);
        System.out.println("Email: " + this.email);
        System.out.println("User Bio: " + this.userBio);
        System.out.println("Pic Path: " + this.picPath);
        System.out.println("Token: " + this.token);
        System.out.println("Friend List: " + this.friendList);
        System.out.println("Friend Request List: " + this.friendRequestList);
        System.out.println("Friend Request Sent List: " + this.friendRequestSentList);
    }
}
