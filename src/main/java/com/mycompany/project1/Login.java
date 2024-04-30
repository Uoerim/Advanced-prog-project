package com.mycompany.project1;
import java.util.*;
public class Login {
    public static User signIn(String username, String password)  {
    User user=Session.getUser(username);
    if(username==null || username.isEmpty()==true) throw new IllegalArgumentException();
    else{
        if(password.equals(user.getPassword())){
            return user;
        }
        else{
            return null;
        }
    }
}
}


 