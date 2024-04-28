package com.mycompany.project1;
import java.util.*;
public class Session {
    private static HashMap<String,User> users=new HashMap<>(); 
    private User user;
    
    public User getSession() {
        return this.user;
    }
    public void setSession(User user){
        this.user=user;
    }
    public static User getUser(String username){
        if(findUser(username)==true){
        return users.get(username);
        }
        return null;
    }
    public void destroy() {
        this.user = null;
    }
    public static void addUser(User user){
        users.put(user.getUserName(), user);
    }
    public void removeUser(User user){
        users.remove(user.getUserName());
    }
    public static boolean findUser(String username){
        if(users.containsKey("username")) return true;
        else return false;
    }
    
}

