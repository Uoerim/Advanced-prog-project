package components.users;

public class About {
    private String workplace;
    private String education;
    private String hometown;
    private String birthDate;
    private String gender;
    public About(String workplace,String education,String hometown,String birthDate,String gender){
       this.workplace=workplace;
       this.education=education;
       this.hometown=hometown;
       this.birthDate=birthDate;
       this.gender=gender;
    }
    public About(){
        this("","","","","");
    }
    public About(String birthDate,String gender){
        this("","","",birthDate,gender);
    }
    public About(String birthDate){
        this(birthDate,"");
    }
    public About(String birthDate,String gender,String education){
        this("",education,"",birthDate,gender);
    }
    public About(String birthDate,String gender,String education,String hometown){
        this("",education,hometown,birthDate,gender);
    }
    public void setWorkplace(String workplace){
        this.workplace=workplace;
    }
    public void setHometown(String hometown){
        this.hometown=hometown;
    }
    public void setEducation(String education){
        this.education=education;
    }
    public void setBirthDate(String birthDate){
        this.birthDate=birthDate;
    }
    public void setGender(String gender){
        this.gender=gender;
    }
    public String getWorkplace(){
        return this.workplace;
    }
    public String getHometown(){
        return this.hometown;
    }
    public String getEducation(){
        return this.education;
    }
    public String getBirthDate(){
        return this.birthDate;
    }
    public String getGender(){
        return this.gender;
    }
}
