package com.mycompany.project1;
import java.util.*;
public class Session {
    private static HashMap<String,User> users=new HashMap<>(); //
    private User user;
    
    public User getSession() {
        return this.user;
    }
    public void setSession(User user){
        this.user=user;
    }
    public static User getUser(String username){
        return users.get(username);
    }
    public void destroy() {
        this.user = null;
    }
    public void addUser(User user){
        users.put(user.getUserName(), user);
    }
    public void removeUser(User user){
        users.remove(user.getUserName());
    }
}

