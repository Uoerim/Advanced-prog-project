
package Components;


public class Login {
    private String   username  ; 
    private String password   ; 
    private boolean isLinked  ; 
    private boolean isVerified  ; 
    private boolean isLoggedin ; 
    
    
    public Login (String username , String password , boolean isLinked , boolean isVerified  , boolean isLoggedin  )
    {
        this.username = username  ; 
        this.password = password  ; 
        this.isLinked =  false   ;
        this.isVerified =false   ; 
        this.isLoggedin = false ; 
        
       
    }
 

    public void setUser (String username) {
        this.username = username;
    }
     
    public void setPassword (String password ) {
        this.password = password  ; 
        
    }
    
     

    public void setLinkeddevice (boolean isLinked ) {
        this.isLinked =  isLinked ; 
        }
    
    
    public void setVerified  (boolean isVerified  ) {
      this.isVerified =isVerified  ; 
      
        }
      

    public void setLoggedin(boolean isLoggedin ) {
        this.isLoggedin = isLoggedin;
    }
    
        public String getUser () {
        return username;
    }
        public String getPasword ()
        {
            return password ; 
            
        }
        
        public boolean isLinked () {
        return isLinked ;
    }
        public boolean isVerified  () {
        return isVerified   ;
    }
        public boolean isLoggedin() {
        return isLoggedin;
    }
        
        
         public void logout(boolean isLoggedin ) {
        if (isLoggedin == true ) {
            isLoggedin = false; 
             
            System.out.println("User is logged out ");
        }
        else 
        {
            System.out.println("User is already logged out ");
        }
    }
          
         public void resetPassword (String password ) 
         {
             if(password != null )
             {
                 this.password = password ; 
                 System.out.println("you have reseted your password successfully ! ");
                     
             }
             else 
             {
                 System.out.println("you don't have password yet .");
                 
             }
             
             
             
         }
    
     
    
}
