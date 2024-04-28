package com.mycompany.project1;
public class Interaction {
    private User user;
    private Posts post;
    public Interaction(User user, Posts post) {
        this.user = user;
        this.post = post;
    }
    public void setUser(User user) {
        this.user = user;
    }
    public User getUser() {
        return user;
    }
    /*public void setPost(Posts post) {
        this.post = post;
    }*/
    public Posts getPost() {
        return post;
    }
    
}

