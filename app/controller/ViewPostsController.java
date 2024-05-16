package app.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.concurrent.CompletableFuture;

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
import javafx.scene.text.Text;

public class ViewPostsController implements Initializable {

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
    private Label postLikesLabel;
    @FXML
    private Label postCommentsLabel;
    @FXML
    private Button viewAuthorBtn;
    @FXML
    private Button likeBtn;
    @FXML
    private TextArea commentsTextArea;
    @FXML
    private Button addCommentBtn;
    @FXML
    private VBox commentsArea;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        postTextLabel.setText(Session.getViewPost().getPostText());
        postLikesLabel.setText(Session.getViewPost().getLikes().size() + " Likes");
        postCommentsLabel.setText(Session.getViewPost().getComments().size() + " Comments");

        for (Like like : Session.getViewPost().getLikes()) {
            if (like.getUserID().equals(Session.getUser().getUsername())) {
                likeBtn.setText("Unlike");
                break;
            }
        }

        Task<Void> task = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                loadComments();
                return null;
            }
        };
        new Thread(task).start();
    }

    @FXML
    private void handleButtonAction(ActionEvent event) {
        if (event.getSource() == homeBtn) {
            try {
                SceneHandler.changeScene(settingsBtn.getScene().getWindow(),
                        FXMLLoader.load(
                                getClass().getResource("../../resources/HomePage.fxml")));
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (event.getSource() == viewProfileBtn) {
            // do nothing
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
                                getClass().getResource("../../resources/FriendsRequestsPage.fxml")));
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
        } else if (event.getSource() == logoutBtn) {
            Session.setUser(null);
            try {
                SceneHandler.changeScene(logoutBtn.getScene().getWindow(),
                        FXMLLoader.load(
                                getClass().getResource("../../resources/AuthenticationPage.fxml")));
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (event.getSource() == viewAuthorBtn) {
            try {
                SceneHandler.changeScene(viewAuthorBtn.getScene().getWindow(),
                        FXMLLoader.load(
                                getClass().getResource("../../resources/ViewProfilePage.fxml")));
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (event.getSource() == likeBtn) {
            System.out.println("Like Button is clicked");
            Boolean isliked = false;
            ArrayList<Like> likesAL = Session.getViewPost().getLikes();
            System.out.println(likesAL);
            for (Like i : likesAL) {
                String res3 = sendServerRequest("REQ:" + "GETLIKE" + ":" + i.getLikeId());
                System.out.println(res3);
                String arr3[] = res3.split(":");
                if (i.getUserID().equals(arr3[1])) {
                    isliked = true;
                }
            }
            if (isliked) {
                System.out.println("Liked");
            try{
                for (Like i : Session.getViewPost().getLikes()) {
                    if (i.getUserID().equals(Session.getUser().getUsername())) {
                        String res = sendServerRequest(
                                "REQ:" + "REMOVELIKE" + ":" + i.getLikeId() + ":" + Session.getViewPost().getPostId());
                        if (res.equals("SUCCESS")) {
                            postLikesLabel.setText((Session.getViewPost().getLikes().size()) - 1 + " Likes");
                            likeBtn.setText("Like");
                            Session.getViewPost().getLikes().remove(i);
                        } else {
                            Alert alert = new Alert(Alert.AlertType.ERROR);
                            alert.setTitle("Error");
                            alert.setHeaderText("Error");
                            alert.setContentText("An error occurred while unliking the post");
                            alert.showAndWait();
                        }
                    }
                }
            }catch(Exception e){
                
            }
            } else {
                System.out.println("Not liked");
                String res = sendServerRequest("REQ:" + "ADDLIKE" + ":" + Session.getUser().getUsername() + ":" + Session.getViewPost().getPostId());
                System.out.println(res);
                if (!res.equals("FAILED")) {
                    postLikesLabel.setText((Session.getViewPost().getLikes().size()) + 1 + " Likes");
                    likeBtn.setText("Unlike");
                    Like like = new Like(res, Session.getUser().getUsername());
                    Session.getViewPost().getLikes().add(like);
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText("Error");
                    alert.setContentText("An error occurred while liking the post");
                    alert.showAndWait();
                }
            }
        } else if (event.getSource() == addCommentBtn) {
            System.out.println("OMGGGG");
            System.out.println(commentsTextArea.getText() + "----------------------------------AHHHH");
            String res = sendServerRequest("REQ:" + "ADDCOMMENT" + ":" + Session.getUser().getUsername() + ":" + Session.getViewPost().getPostId() + ":" + commentsTextArea.getText());
            System.out.println(res + "THIS IS AAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
            if (res.equals("SUCCESS")) {
                Comment comment = new Comment(Session.getUser().getUsername(), commentsTextArea.getText());
                Session.getViewPost().getComments().add(comment);
                VBox rootVBox = new VBox();
                rootVBox.setMinHeight(200);
                rootVBox.setMinWidth(100);
                rootVBox.setStyle("-fx-border-color: #414141; -fx-background-color: transparent;");
                rootVBox.setAlignment(javafx.geometry.Pos.CENTER);

                Label commentUsername = new Label();
                commentUsername.setText(comment.getUsername());
                commentUsername.setFont(new Font("Arial", 15));
                commentUsername.setTextFill(Color.web("#ffffff"));

                VBox textHBox = new VBox();
                textHBox.setMinHeight(200);
                textHBox.setMinWidth(100);
                textHBox.setAlignment(javafx.geometry.Pos.CENTER);

                Label commentText = new Label();
                commentText.setText(comment.getCommentText());
                commentText.setFont(new Font("Arial", 14));
                commentText.setTextFill(Color.web("#ffffff"));
                textHBox.getChildren().addAll(commentText);

                rootVBox.getChildren().addAll(commentUsername, textHBox);
                commentsArea.getChildren().add(rootVBox);
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Error");
                alert.setContentText("An error occurred while Commenting on the post");
                alert.showAndWait();
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

    private void loadComments() {
        if (Session.getViewPost().getComments().isEmpty()) {
            System.out.println("No comments");
            return;
        }
        for (Comment comment : Session.getViewPost().getComments()) {
            Task<Void> task1 = new Task<Void>() {
                @Override
                protected Void call() throws Exception {
                    Platform.runLater(() -> {
                        VBox rootVBox = new VBox();
                        rootVBox.setMinHeight(200);
                        rootVBox.setMinWidth(100);
                        rootVBox.setStyle("-fx-border-color: #414141; -fx-background-color: transparent;");
                        rootVBox.setAlignment(javafx.geometry.Pos.CENTER);

                        Label commentUsername = new Label();
                        commentUsername.setText(comment.getUsername());
                        commentUsername.setFont(new Font("Arial", 15));
                        commentUsername.setTextFill(Color.web("#ffffff"));

                        VBox textHBox = new VBox();
                        textHBox.setMinHeight(200);
                        textHBox.setMinWidth(100);
                        textHBox.setAlignment(javafx.geometry.Pos.CENTER);

                        Label commentText = new Label();
                        commentText.setText(comment.getCommentText());
                        commentText.setFont(new Font("Arial", 14));
                        commentText.setTextFill(Color.web("#ffffff"));
                        textHBox.getChildren().addAll(commentText);

                        rootVBox.getChildren().addAll(commentUsername, textHBox);
                        commentsArea.getChildren().add(rootVBox);
                    });

                    return null;
                }
            };
            new Thread(task1).start();
        }
    }
}
