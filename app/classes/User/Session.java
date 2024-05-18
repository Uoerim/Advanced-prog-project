package app.classes.User;

import java.util.ArrayList;

import app.classes.Post.Post;

public class Session {
    private static User user;
    private static User viewUser;
    private static Post viewPost;
    private static String dmUser;
    private static ArrayList<String> searchUsers = new ArrayList<String>();

    public static ArrayList<String> getSearchUsers() {
        return searchUsers;
    }
    public static void setSearchUsers(ArrayList<String> searchUsers) {
        Session.searchUsers = searchUsers;
    }
    public static String getDmUser() {
        return dmUser;
    }
    public static void setDmUser(String dmUser) {
        Session.dmUser = dmUser;
    }

    public static Post getViewPost() {
        return viewPost;
    }
    public static void setViewPost(Post viewPost) {
        Session.viewPost = viewPost;
    }

    public static User getViewUser() {
        return viewUser;
    }
    public static void setViewUser(User viewUser) {
        Session.viewUser = viewUser;
    }

    public static void setUser(User user) {
        Session.user = user;
    }

    public static User getUser() {
        return Session.user;
    }

    public void destroy() {
        Session.user = null;
    }
}

