import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.SocketException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;

public class ClientHandler extends Thread {
    private Socket socket;
    private Connection connection;

    public ClientHandler(Socket socket, Connection connection) {
        this.socket = socket;
        this.connection = connection;
    }

    @Override
    public void run() {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()))) {
            bw.write("CONNECTED");
            bw.newLine();
            bw.flush();

            String msgFromClient;
            while ((msgFromClient = br.readLine()) != null) {
                String[] msg = msgFromClient.split(":");
                String Response = "";
                switch (msgFromClient.split(":")[1]) {
                    case "CREATENEWACCOUNT":
                        // 2 - username
                        // 3 - password
                        // 4 - email
                        // 5 - fullname
                        // R - Token
                        Response = createNewAccount(connection, msg[2], msg[3], msg[4], msg[5]);
                        bw.write(Response);
                        bw.newLine();
                        bw.flush();
                        break;
                    case "LOGININTOACCOUNT":
                        // 2 : username
                        // 3 : password
                        // R : username, fullname, token, email, userBio, picPath, friendCount,
                        // friendrequestlist, friendrequestlist, friendrequestsentlist
                        Response = loginIntoAccount(connection, msg[2], msg[3]);
                        bw.write(Response);
                        bw.newLine();
                        bw.flush();
                        break;
                    case "GETUSER":
                        // 2 : username
                        // R : username, fullname, email, userBio, picPath, friendCount,
                        // friendrequestlist, friendrequestlist, friendrequestsentlist
                        System.out.println(msgFromClient);
                        Response = getUser(connection, msg[2]);
                        bw.write(Response);
                        bw.newLine();
                        bw.flush();
                        break;
                    case "NEWPOST":
                        // 2 : postText
                        // 3 : username
                        // R : postID, postText, username, {comments}, {likes}
                        Response = newPost(connection, msg[2], msg[3]);
                        bw.write(Response);
                        bw.newLine();
                        bw.flush();
                        break;
                    case "GETPOST":
                        // 2 : postID
                        // R : postID, postText, username, {comments}, {likes}
                        Response = getPost(connection, msg[2]);
                        bw.write(Response);
                        bw.newLine();
                        bw.flush();
                        break;
                    case "GETPOSTSCOUNT":
                        // R : postCount
                        Response = getPostsCount(connection);
                        bw.write(Response);
                        bw.newLine();
                        bw.flush();
                        break;
                    case "ADDLIKE":
                        // 2 : username
                        // 3 : postID
                        // R : SUCCESS/FAILED
                        Response = addLike(connection, msg[2], msg[3]);
                        bw.write(Response);
                        bw.newLine();
                        bw.flush();
                        break;
                    case "REMOVELIKE":
                        // 2 : username
                        // 3 : postID
                        // R : SUCCESS/FAILED
                        Response = removeLike(connection, msg[2], msg[3]);
                        bw.write(Response);
                        bw.newLine();
                        bw.flush();
                        break;
                    case "GETLIKE":
                        // 2 : likeID
                        // R : postID : username
                        Response = getLike(connection, msg[2]);
                        bw.write(Response);
                        bw.newLine();
                        bw.flush();
                        break;
                    case "ADDCOMMENT":
                        // 2 : username
                        // 3 : postID
                        // 4 : comment
                        // R : SUCCESS/FAILED
                        Response = addComment(connection, msg[2], msg[3], msg[4]);
                        bw.write(Response);
                        bw.newLine();
                        bw.flush();
                        break;
                    case "GETCOMMENT":
                        // 2 : comment id
                        // R : no : username : commenttext
                        Response = getComment(connection, msg[2]);
                        bw.write(Response);
                        bw.newLine();
                        bw.flush();
                        break;
                    case "ADDFRIEND":
                        // 2 : username
                        // 3 : friendUsername
                        // R : SUCCESS/FAILED
                        Response = addFriend(connection, msg[2], msg[3]);
                        bw.write(Response);
                        bw.newLine();
                        bw.flush();
                        break;
                    case "REMOVEFRIEND":
                        // 2 : username
                        // 3 : friendUsername
                        // R : SUCCESS/FAILED
                        Response = removeFriend(connection, msg[2], msg[3]);
                        bw.write(Response);
                        bw.newLine();
                        bw.flush();
                        break;
                    case "DECLINEFRIEND":
                        // 2 : username
                        // 3 : friendusername
                        // R : SUCCESS/FAILED
                        Response = declineFriend(connection, msg[2], msg[3]);
                        bw.write(Response);
                        bw.newLine();
                        bw.flush();
                        break;
                    case "SENDFRIENDREQUEST":
                        // 2 : username
                        // 3 : friendusername
                        // R : SUCCESS/FAILED
                        Response = sendFriendRequest(connection, msg[2], msg[3]);
                        bw.write(Response);
                        bw.newLine();
                        bw.flush();
                        break;
                    case "UPDATEUSERNAME":
                        // 2 : username
                        // 3 : newUsername
                        // R : SUCCESS/FAILED
                        Response = changeUsername(connection, msg[2], msg[3]);
                        bw.write(Response);
                        bw.newLine();
                        bw.flush();
                        break;
                    case "UPDATEEMAIL":
                        // 2 : username
                        // 3 : newEmail
                        // R : SUCCESS/FAILED
                        Response = changeEmail(connection, msg[2], msg[3]);
                        bw.write(Response);
                        bw.newLine();
                        bw.flush();
                        break;
                    case "UPDATEPROFILEPICTURE":
                        // 2 : username
                        // 3 : newProfilePicture
                        // R : SUCCESS/FAILED
                        Response = changeProfilePicture(connection, msg[2], msg[3]);
                        bw.write(Response);
                        bw.newLine();
                        bw.flush();
                        break;
                    case "UPDATEPASSWORD":
                        // 2 : username
                        // 3 : newPassword
                        // R : SUCCESS/FAILED
                        Response = changePassword(connection, msg[2], msg[3]);
                        bw.write(Response);
                        bw.newLine();
                        bw.flush();
                        break;
                    case "UPDATEBIO":
                        // 2 : username
                        // 3 : newBio
                        // R : SUCCESS/FAILED
                        Response = changeBio(connection, msg[2], msg[3]);
                        bw.write(Response);
                        bw.newLine();
                        bw.flush();
                        break;
                    case "DELETEACCOUNT":
                        // 2 : username
                        // R : SUCCESS/FAILED
                        Response = deleteAccount(connection, msg[2]);
                        bw.write(Response);
                        bw.newLine();
                        bw.flush();
                        break;
                    case "Ping":
                        Response = "Pong!";
                        bw.write(Response);
                        bw.newLine();
                        bw.flush();
                        break;
                    default:
                        Response = "Wrong Request";
                        bw.write(Response);
                        bw.newLine();
                        bw.flush();
                        break;
                }
            }
        } catch (SocketException e) {
            // Handle connection reset by peer
            System.out.println("Client disconnected");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    ////////////////////////////////////////////////// Registeration
    ///////////////////////////////////////////////////////////////////////////////////////////////////

    private static String createNewAccount(Connection connection, String username, String password, String email,
            String fullName) {
        int userCount = 0;
        String userCountQUERY = "SELECT * FROM users ORDER BY no DESC LIMIT 1";
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(userCountQUERY);
            statement.executeQuery();
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    userCount = resultSet.getInt("no");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        String generatedToken = generateToken(150);
        String addUserQUERY = "INSERT INTO users (username, password, email, profilepic, bio, fullname, friendcount, friendlist, friendrequestlist, friendrequestsentlist, token, no, posts) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement statement2 = null;
        try {
            statement2 = connection.prepareStatement(addUserQUERY);
            statement2.setString(1, username);
            statement2.setString(2, password);
            statement2.setString(3, email);
            statement2.setString(4, "i.pinimg.com/564x/09/21/fc/0921fc87aa989330b8d403014bf4f340.jpg");
            statement2.setString(5, "Hello there! I am using EngiVerse.");
            statement2.setString(6, fullName);
            statement2.setString(7, "0");
            statement2.setString(8, "");
            statement2.setString(9, "");
            statement2.setString(10, "");
            statement2.setString(11, generatedToken);
            statement2.setInt(12, userCount + 1);
            statement2.setString(13, "");

            int rowsInserted = statement2.executeUpdate();
            if (rowsInserted > 0) {

                return generatedToken;
            }

            statement2.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "FAILED";
    }

    ///////////////////////////////////////////////////// Login
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////

    private static String loginIntoAccount(Connection connection, String username, String password) {
        String loginQUERY = "SELECT * FROM users WHERE username = ? AND password = ?";
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(loginQUERY);
            statement.setString(1, username);
            statement.setString(2, password);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    System.out.println(password);
                    System.out.println(resultSet.getString("password"));
                    if (!resultSet.getString("password").equals(password))
                        return "FAILEDPASSWORD";

                    StringBuilder response = new StringBuilder();
                    // username : fullname : token : email : userBio : picPath : friendCount :
                    // {friendrequestlist} : {friendrequestlist} : {friendrequestsentlist}
                    response.append(resultSet.getString("username")).append(":");
                    response.append(resultSet.getString("fullname")).append(":");
                    response.append(resultSet.getString("token")).append(":");
                    response.append(resultSet.getString("email")).append(":");
                    response.append(resultSet.getString("bio")).append(":");
                    response.append(resultSet.getString("profilepic")).append(":");
                    response.append(resultSet.getString("friendcount")).append(":");
                    response.append(resultSet.getString("friendlist")).append(":");
                    response.append(resultSet.getString("friendrequestlist")).append(":");
                    response.append(resultSet.getString("friendrequestsentlist")).append(":");
                    response.append(resultSet.getString("posts"));

                    return response.toString();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "FAILED";

    }
    ///////////////////////////////////////////////////// Post
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public static String newPost(Connection connection, String postText, String username) {

        int postCount = 0;
        String postCountQUERY = "SELECT * FROM posts ORDER BY no DESC LIMIT 1";
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(postCountQUERY);
            statement.executeQuery();
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    postCount = resultSet.getInt("no");
                }
            }
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        String newPostQUERY = "INSERT INTO posts (no, posttext, username, comments, likes) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement statement1 = null;
        try {
            statement1 = connection.prepareStatement(newPostQUERY);
            statement1.setInt(1, postCount + 1);
            statement1.setString(2, postText);
            statement1.setString(3, username);
            statement1.setString(4, "");
            statement1.setString(5, "");
            System.out.println("O1");

            int rowsInserted = statement1.executeUpdate();
            if (rowsInserted > 0) {
                StringBuilder response = new StringBuilder();
                // postID : postText : postImage : username : {comments} : {likes}
                response.append(postCount + 1).append(":");
                response.append(postText).append(":");
                response.append(username);
                return response.toString();
            }

            statement1.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static String getPost(Connection connection, String postId) {
        String postQUERY = "SELECT * FROM posts WHERE no = ?";
        PreparedStatement statementgetPost = null;
        try {
            statementgetPost = connection.prepareStatement(postQUERY);
            statementgetPost.setString(1, postId);
            statementgetPost.executeQuery();

            try (ResultSet resultSet = statementgetPost.executeQuery()) {
                if (resultSet.next()) {
                    StringBuilder response = new StringBuilder();
                    // postID : postText : username : {comments} : {likes}
                    response.append(resultSet.getString("no")).append(":");
                    response.append(resultSet.getString("posttext")).append(":");
                    response.append(resultSet.getString("username")).append(":");
                    response.append(resultSet.getString("comments")).append(":");
                    response.append(resultSet.getString("likes"));
                    return response.toString();
                }
            }
            statementgetPost.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "FAILED";
    }

    private static String getPostsCount(Connection connection) {
        String postCountQUERY = "SELECT * FROM posts ORDER BY no DESC LIMIT 1";
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(postCountQUERY);
            statement.executeQuery();
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return Integer.toString(resultSet.getInt("no"));
                }
            }
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "FAILED";
    }

    ///////////////////////////////////////////////////// Like
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////
    private static String addLike(Connection connection, String username, String postId) {
        String likeQUERY = "SELECT * FROM posts WHERE no = ?";
        PreparedStatement statement = null;
        System.out.println("1");
        try {
            System.out.println("2");
            statement = connection.prepareStatement(likeQUERY);
            statement.setString(1, postId);
            statement.executeQuery();

            try (ResultSet resultSet = statement.executeQuery()) {
                System.out.println("3");
                if (resultSet.next()) {
                    System.out.println("hi");
                    String likes = resultSet.getString("likes");

                    int likeCount = 0;
                    String postCountQUERY = "SELECT * FROM likes ORDER BY no DESC LIMIT 1";
                    PreparedStatement statement2 = connection.prepareStatement(postCountQUERY);
                    statement.executeQuery();
                    try (ResultSet resultSet2 = statement2.executeQuery()) {
                        if (resultSet2.next()) {
                            likeCount = resultSet2.getInt("no") + 1;
                            System.out.println("ops");
                        } else {
                            System.out.println("rlly");
                        }

                        System.out.println("4");
                        System.out.println(likes);
                        System.out.println(likeCount);
                        if (likes.equals("")) {
                            likes += likeCount;
                        } else {
                            likes += "," + likeCount;
                        }
                        statement.close();
                        String updateLikeQUERY = "UPDATE posts SET likes = ? WHERE no = ?";
                        PreparedStatement statement5 = connection.prepareStatement(updateLikeQUERY);
                        statement5.setString(1, likes);
                        statement5.setString(2, postId);
                        statement5.executeUpdate();
                        statement5.close();
                        String newPostQUERY = "INSERT INTO likes (no, username) VALUES (?, ?)";
                        PreparedStatement statement1 = null;
                        statement1 = connection.prepareStatement(newPostQUERY);
                        statement1.setInt(1, likeCount);
                        statement1.setString(2, username);

                        int rowsInserted = statement1.executeUpdate();
                        if (rowsInserted > 0) {
                            return String.valueOf(likeCount);
                        }
                        statement1.close();
                    }
                }
            }
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "FAILED";
    }

    private static String getLike(Connection connection, String likeId) {
        String postQUERY = "SELECT * FROM likes WHERE no = ?";
        PreparedStatement statementgetPost = null;
        try {
            statementgetPost = connection.prepareStatement(postQUERY);
            statementgetPost.setString(1, likeId);
            statementgetPost.executeQuery();

            try (ResultSet resultSet = statementgetPost.executeQuery()) {
                if (resultSet.next()) {
                    StringBuilder response = new StringBuilder();
                    // likeID : likeUsername
                    response.append(resultSet.getString("no")).append(":");
                    response.append(resultSet.getString("username"));
                    return response.toString();
                }
            }
            statementgetPost.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "FAILED";
    }

    ///////////////////////////////////////////////////// Unlike
    //////////////////////////////////////////////////////////////////////////////////////////////////////////
    private static String removeLike(Connection connection, String likeId, String postId) {
        String likeQUERY = "SELECT * FROM posts WHERE no = ?";
        PreparedStatement statement = null;

        try {
            statement = connection.prepareStatement(likeQUERY);
            statement.setString(1, postId);
            statement.executeQuery();

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    String likes = resultSet.getString("likes");
                    likes = likes.replace(likeId + ",", "");
                    likes = likes.replace("," + likeId, "");
                    likes = likes.replace(likeId, "");
                    String updateLikeQUERY = "UPDATE posts SET likes = ? WHERE no = ?";
                    PreparedStatement statement1 = connection.prepareStatement(updateLikeQUERY);
                    statement1.setString(1, likes);
                    statement1.setString(2, postId);
                    statement1.executeUpdate();
                    statement1.close();
                    return "SUCCESS";

                }
            }
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "FAILED";
    }

    ///////////////////////////////////////////////////// Comment
    /////////////////////////////////////////////////////////////////////////////////////////////////////////

    private static String addComment(Connection connection, String username, String postId, String comment) {
        System.out.println(comment + "------------ahh");
        int commentCount = 0;
        String commentCountQUERY = "SELECT * FROM comments ORDER BY no DESC LIMIT 1";
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(commentCountQUERY);
            statement.executeQuery();
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    commentCount = resultSet.getInt("no");
                }
            }
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        String commentQUERY = "INSERT INTO comments (no, username, commenttext) VALUES (?, ?, ?)";
        PreparedStatement statement1 = null;
        try {
            statement1 = connection.prepareStatement(commentQUERY);
            statement1.setInt(1, commentCount + 1);
            statement1.setString(2, username);
            statement1.setString(3, comment);

            int rowsInserted = statement1.executeUpdate();
            if (rowsInserted > 0) {

            }

            statement1.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        String updateCommentQUERY = "SELECT * FROM posts WHERE no = ?";
        PreparedStatement statement2 = null;
        try {
            statement2 = connection.prepareStatement(updateCommentQUERY);
            statement2.setString(1, postId);
            statement2.executeQuery();

            try (ResultSet resultSet = statement2.executeQuery()) {
                if (resultSet.next()) {
                    String comments = resultSet.getString("comments");
                    if (comments.equals("")) {
                        comments += ++commentCount;
                    } else {
                        comments += "," + ++commentCount;
                    }
                    String updateCommentQUERY1 = "UPDATE posts SET comments = ? WHERE no = ?";
                    PreparedStatement statement3 = connection.prepareStatement(updateCommentQUERY1);
                    statement3.setString(1, comments);
                    statement3.setString(2, postId);
                    statement3.executeUpdate();
                    statement3.close();
                    return "SUCCESS";
                }
            }
            statement1.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return "FAILED";
    }

    private static String getComment(Connection connection, String commentId) {
        String postQUERY = "SELECT * FROM comments WHERE no = ?";
        PreparedStatement statementgetPost = null;
        try {
            statementgetPost = connection.prepareStatement(postQUERY);
            statementgetPost.setString(1, commentId);
            statementgetPost.executeQuery();

            try (ResultSet resultSet = statementgetPost.executeQuery()) {
                if (resultSet.next()) {
                    StringBuilder response = new StringBuilder();
                    // likeID : likeUsername
                    response.append(resultSet.getString("no")).append(":");
                    response.append(resultSet.getString("username")).append(":");
                    response.append(resultSet.getString("commenttext"));
                    return response.toString();
                }
            }
            statementgetPost.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "FAILED";
    }

    ///////////////////////////////////////////////////// AddFriend
    ///////////////////////////////////////////////////////////////////////////////////////////////////////

    private static String addFriend(Connection connection, String username, String friendUsername) {
        String friendQUERY = "SELECT * FROM users WHERE username = ?";
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(friendQUERY);
            statement.setString(1, username);
            statement.executeQuery();

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    String friendlist = resultSet.getString("friendlist");
                    if (friendlist.equals("")) {
                        friendlist += friendUsername;
                    } else {
                        friendlist += "," + friendUsername;
                    }
                    String updateFriendQUERY = "UPDATE users SET friendlist = ? WHERE username = ?";
                    PreparedStatement statement1 = connection.prepareStatement(updateFriendQUERY);
                    statement1.setString(1, friendlist);
                    statement1.setString(2, username);
                    statement1.executeUpdate();
                    statement1.close();
                    String friendRequestList = resultSet.getString("friendrequestlist");
                    friendRequestList = friendRequestList.replace("," + friendUsername, "");
                    friendRequestList = friendRequestList.replace(friendUsername + ",", "");
                    friendRequestList = friendRequestList.replace(friendUsername, "");
                    String updateFriendQUERY2 = "UPDATE users SET friendrequestlist = ? WHERE username = ?";
                    PreparedStatement statement2 = connection.prepareStatement(updateFriendQUERY2);
                    statement2.setString(1, friendRequestList);
                    statement2.setString(2, username);
                    statement2.executeUpdate();
                    statement2.close();
                    String updateFriendQUERY3 = "SELECT * FROM users WHERE username = ?";
                    PreparedStatement statement3 = connection.prepareStatement(updateFriendQUERY3);
                    statement3 = connection.prepareStatement(friendQUERY);
                    statement3.setString(1, friendUsername);
                    statement3.executeQuery();
                    try (ResultSet resultSet2 = statement3.executeQuery()) {
                        if (resultSet2.next()) {
                            System.out.println("yo");
                            String friendlist2 = resultSet.getString("friendlist");
                            System.out.println(friendlist2);
                            if (friendlist2.equals("")) {
                                friendlist2 += username;
                            } else {
                                friendlist2 += "," + username;
                            }
                            String updateFriendQUERY4 = "UPDATE users SET friendlist = ? WHERE username = ?";
                            PreparedStatement statement4 = connection.prepareStatement(updateFriendQUERY4);
                            statement4.setString(1, friendlist2);
                            statement4.setString(2, friendUsername);
                            statement4.executeUpdate();
                            statement4.close();
                            String friendRequestList2 = resultSet.getString("friendrequestsentlist");
                            friendRequestList2 = friendRequestList2.replace("," + username, "");
                            friendRequestList2 = friendRequestList2.replace(username + ",", "");
                            friendRequestList2 = friendRequestList2.replace(username, "");
                            String updateFriendQUERY5 = "UPDATE users SET friendrequestsentlist = ? WHERE username = ?";
                            PreparedStatement statement5 = connection.prepareStatement(updateFriendQUERY5);
                            statement5.setString(1, friendRequestList2);
                            statement5.setString(2, friendUsername);
                            statement5.executeUpdate();
                            statement5.close();
                        }
                    }
                    return "SUCCESS";
                }
            }
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "FAILED";
    }

    private static String sendFriendRequest(Connection connection, String username, String friendUsername){
        String friendQUERY = "SELECT * FROM users WHERE username = ?";
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(friendQUERY);
            statement.setString(1, friendUsername);
            statement.executeQuery();

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    String friendRequestList = resultSet.getString("friendrequestlist");
                    if (friendRequestList.equals("")) {
                        friendRequestList += username;
                    } else {
                        friendRequestList += "," + username;
                    }
                    String updateFriendQUERY = "UPDATE users SET friendrequestlist = ? WHERE username = ?";
                    PreparedStatement statement1 = connection.prepareStatement(updateFriendQUERY);
                    statement1.setString(1, friendRequestList);
                    statement1.setString(2, friendUsername);
                    statement1.executeUpdate();
                    statement1.close();
                    String friendRequestSentList = resultSet.getString("friendrequestsentlist");
                    if (friendRequestSentList.equals("")) {
                        friendRequestSentList += friendUsername;
                    } else {
                        friendRequestSentList += "," + friendUsername;
                    }
                    String updateFriendQUERY2 = "UPDATE users SET friendrequestsentlist = ? WHERE username = ?";
                    PreparedStatement statement2 = connection.prepareStatement(updateFriendQUERY2);
                    statement2.setString(1, friendRequestSentList);
                    statement2.setString(2, username);
                    statement2.executeUpdate();
                    statement2.close();
                    return "SUCCESS";
                }
            }
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "FAILED";
    }

    ///////////////////////////////////////////////////// RemoveFriend
    ////////////////////////////////////////////////////////////////////////////////////////////////////

    private static String removeFriend(Connection connection, String username, String friendUsername) {
        String friendQUERY = "SELECT * FROM users WHERE username = ?";
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(friendQUERY);
            statement.setString(1, username);
            statement.executeQuery();

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    String friendList = resultSet.getString("friendlist");
                    System.out.println(username);
                    System.out.println(friendUsername);
                    System.out.println(friendList);
                    friendList = friendList.replace("," + friendUsername, "");
                    friendList = friendList.replace(friendUsername + ",", "");
                    friendList = friendList.replace(friendUsername, "");
                    String updateFriendQUERY = "UPDATE users SET friendlist = ? WHERE username = ?";
                    PreparedStatement statement1 = connection.prepareStatement(updateFriendQUERY);
                    statement1.setString(1, friendList);
                    statement1.setString(2, username);
                    System.out.println(friendList);
                    statement1.executeUpdate();
                    statement1.close();

                    String friendQUERY2 = "SELECT * FROM users WHERE username = ?";
                    PreparedStatement statement3 = connection.prepareStatement(friendQUERY2);
                    statement3 = connection.prepareStatement(friendQUERY2);
                    statement3.setString(1, friendUsername);
                    statement3.executeQuery();
                    try (ResultSet resultSet2 = statement3.executeQuery()) {
                        if (resultSet2.next()) {
                            String friendList2 = resultSet2.getString("friendlist");
                            friendList2 = friendList2.replace("," + username, "");
                            friendList2 = friendList2.replace(username + ",", "");
                            friendList2 = friendList2.replace(username, "");
                            String updateFriendQUERY2 = "UPDATE users SET friendlist = ? WHERE username = ?";
                            PreparedStatement statement2 = connection.prepareStatement(updateFriendQUERY2);
                            statement2.setString(1, friendList2);
                            statement2.setString(2, friendUsername);
                            System.out.println(friendList2);
                            statement2.executeUpdate();
                            statement2.close();
                        }
                    }

                    return "SUCCESS";
                }
            }
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "FAILED";
    }

    private static String declineFriend(Connection connection, String username, String friendUsername) {
        String friendQUERY = "SELECT * FROM users WHERE username = ?";
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(friendQUERY);
            statement.setString(1, username);
            statement.executeQuery();

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    String friendList = resultSet.getString("friendrequestlist");
                    System.out.println(username);
                    System.out.println(friendUsername);
                    System.out.println(friendList);
                    friendList = friendList.replace("," + friendUsername, "");
                    friendList = friendList.replace(friendUsername + ",", "");
                    friendList = friendList.replace(friendUsername, "");
                    String updateFriendQUERY = "UPDATE users SET friendrequestlist = ? WHERE username = ?";
                    PreparedStatement statement1 = connection.prepareStatement(updateFriendQUERY);
                    statement1.setString(1, friendList);
                    statement1.setString(2, username);
                    System.out.println(friendList);
                    statement1.executeUpdate();
                    statement1.close();
                    String friendQUERY2 = "SELECT * FROM users WHERE username = ?";
                    PreparedStatement statement3 = connection.prepareStatement(friendQUERY2);
                    statement3 = connection.prepareStatement(friendQUERY2);
                    statement3.setString(1, friendUsername);
                    statement3.executeQuery();
                    try (ResultSet resultSet2 = statement3.executeQuery()) {
                        if (resultSet2.next()) {
                            String friendList2 = resultSet.getString("friendrequestsentlist");
                            friendList2 = friendList2.replace("," + friendUsername, "");
                            friendList2 = friendList2.replace(friendUsername + ",", "");
                            friendList2 = friendList2.replace(friendUsername, "");
                            String updateFriendQUERY2 = "UPDATE users SET friendrequestsentlist = ? WHERE username = ?";
                            PreparedStatement statement2 = connection.prepareStatement(updateFriendQUERY2);
                            statement2.setString(1, friendList2);
                            statement2.setString(2, friendUsername);
                            statement2.executeUpdate();
                            statement2.close();
                        }
                    }
                    return "SUCCESS";
                }
            }
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "FAILED";
    }

    ///////////////////////////////////////////////////// Share
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Add code here to handle client share requests

    ///////////////////////////////////////////////////// Profile
    /////////////////////////////////////////////////////////////////////////////////////////////////////////

    public static String getUser(Connection connection, String username) {
        String sql = "SELECT * FROM users WHERE username = ?";
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(sql);
            statement.setString(1, username);
            statement.executeQuery();

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    StringBuilder response = new StringBuilder();
                    // username : fullname : email : userBio : picPath : friendCount :
                    // friendrequestlist : friendrequestlist : friendrequestsentlist
                    response.append(resultSet.getString("username")).append(":");
                    response.append(resultSet.getString("fullname")).append(":");
                    response.append(resultSet.getString("email")).append(":");
                    response.append(resultSet.getString("bio")).append(":");
                    response.append(resultSet.getString("profilepic")).append(":");
                    response.append(resultSet.getString("friendcount")).append(":");
                    response.append(resultSet.getString("friendlist")).append(":");
                    response.append(resultSet.getString("friendrequestlist")).append(":");
                    response.append(resultSet.getString("friendrequestsentlist")).append(":");
                    response.append(resultSet.getString("posts"));
                    return response.toString();
                }
            }
            statement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "FAILED";
    }

    ///////////////////////////////////////////////////// Settings
    /////////////////////////////////////////////////////////////////////////////////////////////////////////
    // method to update username in database
    private static String changeUsername(Connection connection, String username, String newUsername) {
        String sql = "UPDATE users SET username = ? WHERE username = ?";
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(sql);
            statement.setString(1, newUsername);
            statement.setString(2, username);
            statement.executeUpdate();
            statement.close();
            return "SUCCESS";
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "FAILED";
    }

    private static String changeEmail(Connection connection, String username, String newEmail) {
        String sql = "UPDATE users SET email = ? WHERE username = ?";
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(sql);
            statement.setString(1, newEmail);
            statement.setString(2, username);
            statement.executeUpdate();
            statement.close();
            return "SUCCESS";
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "FAILED";
    }

    private static String changeProfilePicture(Connection connection, String username, String newProfilePicture) {
        String sql = "UPDATE users SET profilepic = ? WHERE username = ?";
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(sql);
            statement.setString(1, newProfilePicture);
            statement.setString(2, username);
            statement.executeUpdate();
            statement.close();
            return "SUCCESS";
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "FAILED";
    }

    private static String changePassword(Connection connection, String username, String newPassword) {
        String sql = "UPDATE users SET password = ? WHERE username = ?";
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(sql);
            statement.setString(1, newPassword);
            statement.setString(2, username);
            statement.executeUpdate();
            statement.close();
            return "SUCCESS";
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "FAILED";
    }

    private static String changeBio(Connection connection, String username, String newBio) {
        String sql = "UPDATE users SET bio = ? WHERE username = ?";
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(sql);
            statement.setString(1, newBio);
            statement.setString(2, username);
            statement.executeUpdate();
            statement.close();
            return "SUCCESS";
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "FAILED";
    }

    // method to reset account

    // method to delete account
    private static String deleteAccount(Connection connection, String username) {
        String sql = "DELETE FROM users WHERE username = ?";
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(sql);
            statement.setString(1, username);
            statement.executeUpdate();
            statement.close();
            return "SUCCESS";
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "FAILED";
    }

    ///////////////////////////////////////////////////// Search
    //////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Add code here to handle client search requests

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    // more methods as needed
    public static String generateToken(int length) {
        String lowercaseLetters = "abcdefghijklmnopqrstuvwxyz";
        String uppercaseLetters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String numbers = "0123456789";
        String combinedChars = lowercaseLetters + uppercaseLetters + numbers;

        Random random = new Random();
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < length; i++) {
            int randomIndex = random.nextInt(combinedChars.length());
            sb.append(combinedChars.charAt(randomIndex));
        }
        return sb.toString();
    }

}