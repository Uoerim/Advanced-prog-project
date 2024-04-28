package com.mycompany.project1;
import java.util.*;
public class Register {
    public static User registerUser(String username,String password,String confirmedPassword){
        if(password!=confirmedPassword){
            return null; //NullPointerException
        }
        User user=new User(username,password);
        Session.addUser(user);
        user.setUserBio("Welcome to my profile");
        user.setProfilePic("default.png");
        user.setFriendList(new HashMap<>());
        user.setFriendRequests(new HashMap<>());
        user.setFriendRequestsSent(new HashMap<>());
        return user;
    }
}
