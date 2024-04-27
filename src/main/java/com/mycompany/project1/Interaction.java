package com.mycompany.project1;
public class Interaction {
    private User user;
    private int postID;

    public Interaction(User user, int postID) {
        this.user = user;
        this.postID = postID;
    }
    public void setUser(User user) {
        this.user = user;
    }
    public User getUser() {
        return user;
    }
    public void setPostID(int postID) {
        this.postID = postID;
    }
    public int getPostID() {
        return postID;
    }
    
}

