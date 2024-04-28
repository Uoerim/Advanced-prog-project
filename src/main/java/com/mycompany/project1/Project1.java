package com.mycompany.project1;
public class Project1 {
    public static void main(String[] args) {
    
        Session s=new Session();
        
        s.setSession(Register.registerUser("YosifIbrahim","12345","12345"));
        System.out.println(s.getSession().getUserName());
        /*User x=new User("Mohammad","123");
        s.addUser(x);
        s.setSession(Login.signIn("Mohammad","123"));
        System.out.println(s.getSession().getUserName());*/
    }
}
