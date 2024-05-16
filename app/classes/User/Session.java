package app.classes.User;

import app.classes.Post.Post;

public class Session {
    private static User user;
    private static User viewUser;
    private static Post viewPost;

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

