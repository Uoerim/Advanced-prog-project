package components.users;

import java.util.ArrayList; // import the ArrayList class

public class User {
    private String username;
    private String bio;
    private String profilepic;
    private ArrayList<User> friendList;
    private About about;
    private int friends;

    public User(String username, String bio, String profilepic, About about) {
        this.username = username;
        this.bio = bio;
        this.profilepic = profilepic;
        this.about = about;
    }

    public User(String username) {
        this(username, "", "", new About());
    }

    public User(String username, String profilepic) {
        this(username, "", profilepic, new About());
    }

    public User(String username, String bio, String profilepic) {
        this(username, bio, profilepic, new About());
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public void setProfilePic(String profilepic) {
        this.profilepic = profilepic;
    }

    public void setAbout(About about) {
        this.about = about;
    }

    public String getUsername() {
        return this.username;
    }

    public String getBio() {
        return this.bio;
    }

    public String getProfilePic() {
        return this.profilepic;
    }

    public int getFriends() {
        return this.friends;
    }

    public About getAbout() {
        return this.about;
    }

    public ArrayList getFriendList() {
        return this.friendList;
    }

    public void addFriend(User user) {
        friendList.add(user);
        friends++;
    }

    public void removeFriend(User user) {
        friendList.remove(user);
        friends--;
    }

}
