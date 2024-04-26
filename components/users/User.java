package components.users;

import java.util.ArrayList;
//
public class User {
    private String userName;
    private String userBio;
    private String pfpPath;
    private ArrayList<Integer> friendList;
    private int friendCount;
    private String userToken;

    public User(String userName, String userToken) {
        this.userName = userName;
        this.userToken = userToken;
        this.userBio = "";
        this.pfpPath = "";
        this.friendList = new ArrayList<Integer>();
        this.friendCount = friendList.size();
    }

    // Setters and Getters
    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserBio(String userBio) {
        this.userBio = userBio;
    }

    public String getUserBio() {
        return userBio;
    }

    public void setPfpPath(String pfpPath) {
        this.pfpPath = pfpPath;
    }

    public String getPfpPath() {
        return pfpPath;
    }

    public ArrayList<Integer> getFriendList() {
        return friendList;
    }

    public int getFriendCount() {
        return friendCount;
    }

    public void setUserToken(String userToken) {
        this.userToken = userToken;
    }

    public String getUserToken() {
        return userToken;
    }

    public void addFriend(int friendID) {
        friendList.add(friendID);
        friendCount++;
    }

    public void removeFriend(int friendID) {
        friendList.remove(friendID);
        friendCount--;
    }
    ///////////////////////

    // method to delete user
    public void deleteUser() {
        // delete user from database
    }
}
