package com.mycompany.project1;
import java.util.*;
public class Project1 {
    public static void main(String[] args) {
        //////////////////////////////////////////////////code for testing Register
        Session s=new Session();
        String email;
        String username;
        String password;
        Scanner input=new Scanner(System.in);
        System.out.println("Register:");
        boolean validation=false; boolean usernameValidation=false; boolean emailValidation=false;
        do{
        System.out.println("Please enter your email:");
        email=input.nextLine();
        String emailResult=Register.validateEmail(email);
        if("Invalid".equals(emailResult)){
            System.out.println("Invalid email");
        }else emailValidation=true;
        }while(emailValidation==false);
        do{
        System.out.println("Please enter your username:");
        username=input.nextLine();
        String usernameResult=Register.validateUsername(username);
        if("Invalid".equals(usernameResult)){
            System.out.println("Username cannot be empty");
        } else usernameValidation=true;
        }while(usernameValidation==false);
        do{
        System.out.println("Please enter your password:");
        password=input.nextLine();
        String result=Register.validatePassword(password);
        if("Failed".equals(result)){
            System.out.println("Password is too weak. Password must contain at least 1 number,1 uppercase letter, and 1 lowercase letter");
        } else validation=true;
        }while(validation==false);
        String confirmedPassword; User user1; boolean confirmingValidation=false;
        do{
        System.out.println("Please confirm your password:");
        confirmedPassword=input.nextLine();
        user1=Register.registerUser(email,username,password,confirmedPassword);
        try{
        
        Session.addUser(user1);
        confirmingValidation=true;
        
        }
            
        catch(NullPointerException ex){
            System.out.println("Confirmed password is not the same as your password");
        }
        }while(confirmingValidation==false);
        ///////////////////////////////////////////////////code for testing login
        System.out.println("Login:");
       
        boolean loginChecker=false;
        //User user2;
        //String username; String password;
        //Scanner input=new Scanner(System.in);
        do{
        System.out.println("Please enter your username:");
        username=input.nextLine();
        System.out.println("Please enter your password:");
        password=input.nextLine();
        try{
        user1=Login.signIn(username, password);
        loginChecker=true;
        }
        catch(NullPointerException ex){
            System.out.println("Wrong password, please try again");
        }
        catch(IllegalArgumentException ex){
            System.out.println("username cannot be empty");
        }
        }while(loginChecker==false);
        /////////////////////////////testing creating posts and adding comments
        Posts post=user1.createPost(user1,"Hey","","Posted 5 minutes ago");
        post.addComment(user1,"Hello");
        post.addComment(user1,"Hello world");
        System.out.println(post.getCommentsCount());
        for(int i=0;i<post.getComments().size();i++){
        System.out.println(post.findComment(i));
        }
        for(int i=0;i<user1.getPosts().size();i++){
            System.out.println(user1.findPost(i));
        }
    }    
}
