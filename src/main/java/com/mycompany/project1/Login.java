package com.mycompany.project1;
import java.util.*;
public class Login {
    public static User signIn(String username,String password){
        User user=Session.getUser(username);     
        if(password==user.getPassword()){
            return user;
        }
        return null; //NullPointerException 
    }
}
