package components.users;
import java.util.*;

public class User {
    private String userName;
    private String userBio;
    private String profilePic;
    private ArrayList<Integer> friendList; //user or integer?
    private int friendCount;
    private String userToken;
    private ArrayList<Posts> posts;
    // private About about;

    public User(String userName, String userToken) {
        this.userName = userName;
        this.userToken = userToken;
        this.userBio = "";
        this.profilePic = "";
        this.posts=new ArrayList<Posts>();
        this.friendList = new ArrayList<Integer>();
        this.friendCount = friendCount;
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
