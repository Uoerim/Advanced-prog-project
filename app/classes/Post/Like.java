package app.classes.Post;

public class Like {
    private String likeId;
    private String username;

    public Like(String likeId, String username) {
        this. likeId = likeId;
        this.username = username;
    }

    public void setUserID(String userID) {
        this.username = userID;
    }

    public String getUserID() {
        return username;
    }

    public void setLikeId(String likeId){
        this.likeId = likeId;
    }

    public String getLikeId(){
        return likeId;
    }
}
