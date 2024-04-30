package com.mycompany.project1;
import java.util.*;
public class Register { 
    public static User registerUser(String email,String username,String password,String confirmedPassword){
        if(password.equals(confirmedPassword)==false){ 
            return null;
        }
        User user=new User(email,username,password);
        user.setUserBio("Welcome to my profile");
        user.setProfilePic("default.png");
        user.setFriendList(new HashMap<>());
        user.setFriendRequests(new HashMap<>());
        user.setFriendRequestsSent(new HashMap<>());
        user.setPosts(new ArrayList<>());
        return user;
        
    }
    public static String validatePassword(String password){ //Password must contain at least 1 number,1 uppercase letter, and 1 lowercase letter
        int upperCaseCounter=0;
        int lowerCaseCounter=0;
        int numbersCounter=0;
        for(int i=0;i<password.length();i++){
            if(Character.isUpperCase(password.charAt(i))==true){
                upperCaseCounter++;
            }
            else if(Character.isLowerCase(password.charAt(i))==true){
                lowerCaseCounter++;
            }
            else if(Character.isDigit(password.charAt(i))==true){
                numbersCounter++;
            }
        }
        if(upperCaseCounter>0 && lowerCaseCounter>0 && numbersCounter>0){
            return "Success";
        }
        else return "Failed";
    }
    public static String  validateEmail (String email )    
    {
     String checkemail = "@" ; 
     String checkemail2 = ".com" ; 
    if  (email.contains(checkemail)  && email.contains(checkemail2))
             {
                  return "Valid" ; 
             }
    else return "Invalid" ; 
    }
    public static String validateUsername(String username){
        if(username==null || username.isEmpty()==true){
            return "Invalid";
        }
        else return "Valid";
    }
    
}
