package app.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javax.swing.UIDefaults.LazyValue;

import app.ServerConnection;
import app.classes.Post.Comment;
import app.classes.Post.Like;
import app.classes.Post.Post;
import app.classes.User.Session;
import app.classes.User.User;
import app.handlers.SceneHandler;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class HomePageController implements Initializable {

    @FXML
    private Button homeBtn;
    @FXML
    private Button viewProfileBtn;
    @FXML
    private Button viewFriendsBtn;
    @FXML
    private Button viewFriendsRequestsBtn;
    @FXML
    private Button directMessagingBtn;
    @FXML
    private Button settingsBtn;
    @FXML
    private Button postBtn;
    @FXML
    private Button viewPostBtn;
    @FXML
    private Button logoutBtn;
    @FXML
    private Label postTextLabel;
    @FXML
    private TextArea postText;
    @FXML
    private VBox postsArea;
    @FXML
    private VBox mainView;
    @FXML
    private Button SearchBtn;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Task<Void> task = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                // String res2 = sendServerRequest("REQ:LOGININTOACCOUNT:uoerim:12345678");
                // String arr2[] = res2.split(":");
                // User user = new User(arr2[0], arr2[1], arr2[3], arr2[4], "https://" +
                // arr2[5], arr2[2]);
                // ArrayList<String> friendList = new ArrayList<>();
                // ArrayList<String> friendRequestList = new ArrayList<>();
                // ArrayList<String> friendRequestSentList = new ArrayList<>();
                // ArrayList<Integer> posts = new ArrayList<>();
                // String[] postsArray = arr2[10].split(",");
                // String[] friendListArray = arr2[7].split(",");
                // String[] friendRequestListArray = arr2[8].split(",");
                // String[] friendRequestSentListArray = arr2[9].split(",");
                // for (String friend : friendListArray) {
                // friendList.add(friend);
                // }
                // for (String friendRequest : friendRequestListArray) {
                // friendRequestList.add(friendRequest);
                // }
                // for (String friendRequestSent : friendRequestSentListArray) {
                // friendRequestSentList.add(friendRequestSent);
                // }
                // for (String post : postsArray) {
                // posts.add(Integer.parseInt(post));
                // }
                // System.out.println(posts);
                // user.setFriendList(friendList);
                // user.setFriendRequestList(friendRequestList);
                // user.setFriendRequestSentList(friendRequestSentList);
                // user.setPosts(posts);
                // Session.setUser(user);

                loadPosts();
                return null;
            }
        };
        new Thread(task).start();
    }

    @FXML
    private void handleButtonAction(ActionEvent event) {
        if (event.getSource() == homeBtn) {
            // do nothing
        } else if (event.getSource() == viewProfileBtn) {
            try {
                SceneHandler.changeScene(settingsBtn.getScene().getWindow(),
                        FXMLLoader.load(
                                getClass().getResource("../../resources/ProfilePage.fxml")));
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (event.getSource() == viewFriendsBtn) {
            try {
                SceneHandler.changeScene(settingsBtn.getScene().getWindow(),
                        FXMLLoader.load(
                                getClass().getResource("../../resources/ViewFriendsPage.fxml")));
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (event.getSource() == viewFriendsRequestsBtn) {
            try {
                SceneHandler.changeScene(settingsBtn.getScene().getWindow(),
                        FXMLLoader.load(
                                getClass().getResource("../../resources/ViewFriendsRequestPage.fxml")));
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (event.getSource() == SearchBtn) {
            try {
                SceneHandler.changeScene(settingsBtn.getScene().getWindow(),
                        FXMLLoader.load(
                                getClass().getResource("../../resources/SearchPage.fxml")));
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (event.getSource() == directMessagingBtn) {
            try {
                SceneHandler.changeScene(settingsBtn.getScene().getWindow(),
                        FXMLLoader.load(
                                getClass().getResource("../../resources/DirectMessagePage.fxml")));
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (event.getSource() == settingsBtn) {
            try {
                SceneHandler.changeScene(settingsBtn.getScene().getWindow(),
                        FXMLLoader.load(
                                getClass().getResource("../../resources/SettingsPage.fxml")));
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (event.getSource() == postBtn) {
            String res = sendServerRequest("REQ:NEWPOST:" + postText.getText() + ":" + Session.getUser().getUsername());
            String arr[] = res.split(":");
            Session.getUser().getPosts().add(Integer.parseInt(arr[0]) - 1);
            VBox rootVBox = new VBox();
            rootVBox.setMinHeight(234);
            rootVBox.setMinWidth(1020);
            rootVBox.setStyle("-fx-border-color: white; -fx-background-color: #313131;");

            HBox postTextHBox = new HBox();
            postTextHBox.setMinHeight(164);
            postTextHBox.setMinWidth(1020);
            postTextHBox.setAlignment(javafx.geometry.Pos.CENTER);
            Label postTextLabel = new Label();
            postTextLabel.setText(arr[1]);
            postTextLabel.setFont(new Font("Arial", 19));
            postTextLabel.setTextFill(Color.web("#ffffff"));
            postTextHBox.getChildren().add(postTextLabel);

            HBox postBtnHBox = new HBox();
            postBtnHBox.setMinHeight(70);
            postBtnHBox.setMinWidth(1020);
            postBtnHBox.setAlignment(javafx.geometry.Pos.CENTER);
            Button viewUserProfileBtn = new Button("View Author Profile");
            viewUserProfileBtn.setUserData(arr[2]);
            viewUserProfileBtn.setPrefHeight(41.0);
            viewUserProfileBtn.setPrefWidth(176.0);
            viewUserProfileBtn.setStyle(
                    "-fx-cursor: hand; -fx-background-color: #441bf7; -fx-text-fill: white; -fx-background-radius: 10px;");
            viewUserProfileBtn.setOnAction((e) -> {
                System.out.println(arr[1]);
            });
            viewUserProfileBtn.setOnMouseEntered((e) -> {
                viewUserProfileBtn.setStyle(
                        "-fx-cursor: hand; -fx-background-color: #3205f7; -fx-text-fill: white; -fx-background-radius: 10px;");
            });
            viewUserProfileBtn.setOnMouseExited((e) -> {
                viewUserProfileBtn.setStyle(
                        "-fx-cursor: hand; -fx-background-color: #441bf7; -fx-text-fill: white; -fx-background-radius: 10px;");
            });
            Button viewPostBtn = new Button("View Post");
            viewPostBtn.setUserData(arr[0]);
            viewPostBtn.setPrefHeight(41.0);
            viewPostBtn.setPrefWidth(176.0);
            viewPostBtn.setStyle(
                    "-fx-cursor: hand; -fx-background-color: #441bf7; -fx-text-fill: white; -fx-background-radius: 10px;");
            viewPostBtn.setOnAction((e) -> {
                try {
                    SceneHandler.changeScene(logoutBtn.getScene().getWindow(),
                            FXMLLoader.load(
                                    getClass().getResource("../../resources/ViewProfile.fxml")));
                } catch (IOException r) {
                    r.printStackTrace();
                }
            });
            viewPostBtn.setOnMouseEntered((e) -> {
                viewPostBtn.setStyle(
                        "-fx-cursor: hand; -fx-background-color: #3205f7; -fx-text-fill: white; -fx-background-radius: 10px;");
            });
            viewPostBtn.setOnMouseExited((e) -> {
                viewPostBtn.setStyle(
                        "-fx-cursor: hand; -fx-background-color: #441bf7; -fx-text-fill: white; -fx-background-radius: 10px;");
            });
            postBtnHBox.setSpacing(20);
            postBtnHBox.getChildren().addAll(viewPostBtn, viewUserProfileBtn);

            rootVBox.getChildren().addAll(postTextHBox, postBtnHBox);
            postsArea.getChildren().add(0, rootVBox);
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Success");
            alert.setHeaderText("You have posted your thought successfully!");
            alert.showAndWait();
        } else if (event.getSource() == viewPostBtn) {
            System.out.println("View Post");
        } else if (event.getSource() == logoutBtn) {
            Session.setUser(null);
            try {
                SceneHandler.changeScene(logoutBtn.getScene().getWindow(),
                        FXMLLoader.load(
                                getClass().getResource("../../resources/AuthenticationPage.fxml")));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    public void handleMouseEntered(MouseEvent event) {
        Node sourceNode = (Node) event.getSource();
        sourceNode.setStyle(
                "-fx-cursor: hand; -fx-background-color: #3205f7; -fx-text-fill: white; -fx-background-radius: 10px;");
    }

    @FXML
    public void handleMouseExited(MouseEvent event) {
        Node sourceNode = (Node) event.getSource();
        sourceNode.setStyle(
                "-fx-cursor: hand; -fx-background-color: #441bf7; -fx-text-fill: white; -fx-background-radius: 10px;");
    }

    public String sendServerRequest(String request) {
        try {
            return ServerConnection.sendRequest(request);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "ERR";
    }

    private void loadPosts() {
        String res = sendServerRequest("REQ:GETPOSTSCOUNT");
        int postCount = Integer.parseInt(res);
        for (int i = 1; i <= postCount; i++) {
            final int postIndex = i;
            Task<Void> task2 = new Task<Void>() {
                @Override
                protected Void call() throws Exception {
                    String res2 = sendServerRequest("REQ:" + "GETPOST" + ":" + String.valueOf(postIndex));
                    if (res2.charAt(res2.length() - 1) == ':') {
                        res2 += ":";
                    }
                    res2 = res2.replaceAll("::", ":null:");
                    if (res2.charAt(res2.length() - 1) == ':') {
                        res2 += ":";
                    }
                    res2 = res2.replaceAll("::", ":null:");
                    System.out.println(res2);
                    String arr2[] = res2.split(":");
                    Post post = new Post(arr2[0], arr2[1], arr2[2]);

                    Platform.runLater(() -> {
                        if (!arr2[3].equals("null")) {
                            String comm[] = arr2[3].split(",");
                            for (String i : comm) {
                                String res4 = sendServerRequest("REQ:" + "GETCOMMENT" + ":" + i);
                                String arr4[] = res4.split(":");
                                Comment comment = new Comment(arr4[1], arr4[2]);
                                post.addComment(comment);
                            }
                        }
                        System.out.print("\n");
                        if (!arr2[4].equals("null")) {
                            System.out.println(arr2 + "---------------------------------");
                            String lk[] = arr2[4].split(",");
                            for (String i : lk) {
                                System.out.println("------" + i);
                                String res3 = sendServerRequest("REQ:" + "GETLIKE" + ":" + i);
                                String arr3[] = res3.split(":");
                                Like like = new Like(arr3[0], arr3[1]);
                                post.getLikes().add(like);
                            }
                        } else {
                        }
                        VBox rootVBox = new VBox();
                        rootVBox.setMinHeight(234);
                        rootVBox.setMinWidth(1020);
                        rootVBox.setStyle("-fx-border-color: white; -fx-background-color: #313131;");

                        HBox postTextHBox = new HBox();
                        postTextHBox.setMinHeight(164);
                        postTextHBox.setMinWidth(1020);
                        postTextHBox.setAlignment(javafx.geometry.Pos.CENTER);
                        Label postTextLabel = new Label();
                        postTextLabel.setText(post.getPostText());
                        postTextLabel.setFont(new Font("Arial", 19));
                        postTextLabel.setTextFill(Color.web("#ffffff"));
                        postTextHBox.getChildren().add(postTextLabel);

                        HBox postBtnHBox = new HBox();
                        postBtnHBox.setMinHeight(70);
                        postBtnHBox.setMinWidth(1020);
                        postBtnHBox.setAlignment(javafx.geometry.Pos.CENTER);
                        Button viewUserProfileBtn = new Button("View Author Profile");
                        viewUserProfileBtn.setUserData(post.getUsername());
                        viewUserProfileBtn.setPrefHeight(41.0);
                        viewUserProfileBtn.setPrefWidth(176.0);
                        viewUserProfileBtn.setStyle(
                                "-fx-cursor: hand; -fx-background-color: #441bf7; -fx-text-fill: white; -fx-background-radius: 10px;");
                        viewUserProfileBtn.setOnAction((e) -> {
                            String res3 = sendServerRequest("REQ:GETUSER:" + post.getUsername());
                            if (res3.charAt(res3.length() - 1) == ':') {
                                res3 += ":";
                            }
                            res3 = res3.replaceAll("::", ":null:");
                            if (res3.charAt(res3.length() - 1) == ':') {
                                res3 += ":";
                            }
                            res3 = res3.replaceAll("::", ":null:");
                            String arr[] = res3.split(":");
                            User user = new User(arr[0], arr[1], arr[2], arr[3], "https://" + arr[4], "0");
                            ArrayList<String> friendList = new ArrayList<>();
                            ArrayList<String> friendRequestList = new ArrayList<>();
                            ArrayList<String> friendRequestSentList = new ArrayList<>();
                            ArrayList<Integer> posts = new ArrayList<>();

                            if (!arr[6].equals("null")) {
                                String[] friendListArray = arr[6].split(",");
                                for (String friend : friendListArray) {
                                    friendList.add(friend);
                                }
                                user.setFriendList(friendList);

                            }
                            if (!arr[7].equals("null")) {

                                String[] friendRequestListArray = arr[7].split(",");
                                for (String friendRequest : friendRequestListArray) {
                                    friendRequestList.add(friendRequest);
                                }
                                user.setFriendRequestList(friendRequestList);

                            }
                            if (!arr[8].equals("null")) {

                                String[] friendRequestSentListArray = arr[8].split(",");
                                for (String friendRequestSent : friendRequestSentListArray) {
                                    friendRequestSentList.add(friendRequestSent);
                                }
                                user.setFriendRequestSentList(friendRequestSentList);
                            }
                            if (!arr[9].equals("null")) {

                                String[] postsArray = arr[9].split(",");
                                for (String post2 : postsArray) {
                                    posts.add(Integer.parseInt(post2));
                                }
                                user.setPosts(posts);
                            }

                            Session.setViewUser(user);
                            System.out.println(user.getPicPath());
                            try {
                                SceneHandler.changeScene(logoutBtn.getScene().getWindow(),
                                        FXMLLoader.load(
                                                getClass().getResource("../../resources/ViewProfilePage.fxml")));
                            } catch (IOException r) {
                                r.printStackTrace();
                            }
                        });
                        viewUserProfileBtn.setOnMouseEntered((e) -> {
                            viewUserProfileBtn.setStyle(
                                    "-fx-cursor: hand; -fx-background-color: #3205f7; -fx-text-fill: white; -fx-background-radius: 10px;");
                        });
                        viewUserProfileBtn.setOnMouseExited((e) -> {
                            viewUserProfileBtn.setStyle(
                                    "-fx-cursor: hand; -fx-background-color: #441bf7; -fx-text-fill: white; -fx-background-radius: 10px;");
                        });
                        Button viewPostBtn = new Button("View Post");
                        viewPostBtn.setUserData(post.getPostId());
                        viewPostBtn.setPrefHeight(41.0);
                        viewPostBtn.setPrefWidth(176.0);
                        viewPostBtn.setStyle(
                                "-fx-cursor: hand; -fx-background-color: #441bf7; -fx-text-fill: white; -fx-background-radius: 10px;");
                        viewPostBtn.setOnAction((event) -> {
                            Session.setViewPost(post);
                            try {
                                SceneHandler.changeScene(settingsBtn.getScene().getWindow(),
                                        FXMLLoader.load(
                                                getClass().getResource("../../resources/ViewPostPage.fxml")));
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        });
                        viewPostBtn.setOnMouseEntered((event) -> {
                            viewPostBtn.setStyle(
                                    "-fx-cursor: hand; -fx-background-color: #3205f7; -fx-text-fill: white; -fx-background-radius: 10px;");
                        });
                        viewPostBtn.setOnMouseExited((event) -> {
                            viewPostBtn.setStyle(
                                    "-fx-cursor: hand; -fx-background-color: #441bf7; -fx-text-fill: white; -fx-background-radius: 10px;");
                        });
                        postBtnHBox.setSpacing(20);
                        postBtnHBox.getChildren().addAll(viewPostBtn, viewUserProfileBtn);

                        rootVBox.getChildren().addAll(postTextHBox, postBtnHBox);
                        postsArea.getChildren().add(0, rootVBox);
                    });

                    return null;
                }
            };
            new Thread(task2).start();
        }
    }
}
