package app.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.FileHandler;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import app.ServerConnection;
import app.classes.User.Session;
import app.classes.User.User;
import app.handlers.SceneHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class AuthenticationController {
    @FXML
    private VBox loginField;
    @FXML
    private VBox registerField;
    @FXML
    private Button toggleBtnLogin;
    @FXML
    private Button toggleBtnRegister;
    @FXML
    private Button loginBtn;
    @FXML
    private Button registerBtn;
    @FXML
    private TextField usernameINP;
    @FXML
    private TextField emailINP;
    @FXML
    private TextField fullNameINP;
    @FXML
    private PasswordField passwordINP;
    @FXML
    private PasswordField rePassINP;
    @FXML
    private Label errorView;
    @FXML
    private TextField usernameInpLogin;
    @FXML
    private PasswordField passwordInpLogin;
    @FXML
    private Button closeBtn;
    @FXML
    private Button minimizeBtn;
    @FXML
    private HBox menuBar;

    @FXML
    private void handleButtonAction(ActionEvent event) {
        try {
            System.out.println(ServerConnection.sendRequest("REQ:Ping"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (event.getSource() == toggleBtnLogin) {
            loginField.setVisible(false);
            registerField.setVisible(true);
        } else if (event.getSource() == toggleBtnRegister) {
            loginField.setVisible(true);
            registerField.setVisible(false);
        } else if (event.getSource() == loginBtn) {
            if (usernameInpLogin.getText().isEmpty() || passwordInpLogin.getText().isEmpty()) {
                errorView.setVisible(true);
                errorView.setText("Empty Field Detected!");
                errorView.setLayoutX(580);
                return;
            }
            String res = sendServerRequest(
                    "REQ:LOGININTOACCOUNT:" + usernameInpLogin.getText() + ":" + passwordInpLogin.getText());
            if (res.equals("FAILEDPASSWORD")) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Username or password is incorrect!");
                alert.showAndWait();
                return;
            } else if (res.equals("FAILED")) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Username or password is incorrect!");
                alert.showAndWait();
                return;
            }
            if (res.charAt(res.length() - 1) == ':') {
                res += ":";
            }
            res = res.replaceAll("::", ":null:");
            if (res.charAt(res.length() - 1) == ':') {
                res += ":";
            }
            res = res.replaceAll("::", ":null:");
            System.out.println(res);
            String arr2[] = res.split(":");

            // response.append(resultSet.getString("friendlist")).append(":");
            // response.append(resultSet.getString("friendrequestlist")).append(":");
            // response.append(resultSet.getString("friendrequestsentlist")).append(":");
            // response.append(resultSet.getString("posts"));
            User user = new User(arr2[0], arr2[1], arr2[3], arr2[4], "https://" +
                    arr2[5], arr2[2]);
            ArrayList<String> friendList = new ArrayList<>();
            ArrayList<String> friendRequestList = new ArrayList<>();
            ArrayList<String> friendRequestSentList = new ArrayList<>();
            ArrayList<Integer> posts = new ArrayList<>();
            if (!arr2[7].equals("null")) {

                String[] friendListArray = arr2[7].split(",");
                for (String friend : friendListArray) {
                    friendList.add(friend);
                }
                user.setFriendList(friendList);
            }
            if (!arr2[8].equals("null")) {

                String[] friendRequestListArray = arr2[8].split(",");
                for (String friendRequest : friendRequestListArray) {
                    friendRequestList.add(friendRequest);
                }
                user.setFriendRequestList(friendRequestList);
            }
            if (!arr2[9].equals("null")) {

                String[] friendRequestSentListArray = arr2[9].split(",");
                for (String friendRequestSent : friendRequestSentListArray) {
                    friendRequestSentList.add(friendRequestSent);
                }
                user.setFriendRequestSentList(friendRequestSentList);
            }
            if (!arr2[10].equals("null")) {

                String[] postsArray = arr2[10].split(",");
                for (String post : postsArray) {
                    posts.add(Integer.parseInt(post));
                }
                user.setPosts(posts);
            }
            Session.setUser(user);

            user.viewData();

            try {
                SceneHandler.changeScene(registerBtn.getScene().getWindow(),
                        FXMLLoader.load(
                                getClass().getResource("../../resources/HomePage.fxml")));
            } catch (IOException e) {
                e.printStackTrace();
            }

        } else if (event.getSource() == registerBtn) {
            System.out.println("Register button clicked");
            //
            if (usernameINP.getText().isEmpty() || emailINP.getText().isEmpty() || passwordINP.getText().isEmpty()
                    || rePassINP.getText().isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Success");
                alert.setHeaderText("You must fill all fields!");
                alert.showAndWait();
                return;
            }
            //
            if (!passwordINP.getText().equals(rePassINP.getText())) {

                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Passwords do not match!");
                alert.showAndWait();
                return;
            }
            //
            if (passwordINP.getText().length() < 8) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Password Must be at least 8 characters long!");
                alert.showAndWait();
                return;
            }
            String email = emailINP.getText();

            String emailPattern = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                    + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

            Pattern pattern = Pattern.compile(emailPattern);
            Matcher matcher = pattern.matcher(email);
            if (!matcher.matches()) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Email is not valid!");
                alert.showAndWait();
                return;
            }

            String res1 = sendServerRequest("REQ:GETUSER:" + usernameINP.getText());
            System.out.println(usernameINP.getText() + "----------------------------------------- ");
            if (!res1.equals("FAILED")) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Username already exists!");
                alert.showAndWait();
                return;
            }
            String res = sendServerRequest("REQ:CREATENEWACCOUNT:" + usernameINP.getText() + ":" + passwordINP.getText()
                    + ":" + emailINP.getText() + ":" + fullNameINP.getText());
            Session.setUser(new User(usernameINP.getText(), fullNameINP.getText(), emailINP.getText(),
                    "Hello there! I am using EngiVerse.",
                    "https://i.pinimg.com/564x/98/1c/7c/981c7c47ab2b7dbd9197b7ffb64c50ec.jpg",
                    "0"));
            try {
                SceneHandler.changeScene(registerBtn.getScene().getWindow(),
                        FXMLLoader.load(
                                getClass().getResource("../../resources/HomePage.fxml")));
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    @FXML
    public void handleMouseEntered(MouseEvent event) {

        if (event.getSource() == toggleBtnLogin) {
            toggleBtnLogin.setStyle(
                    "-fx-cursor: hand; -fx-text-fill: #6b6b6b; -fx-background-color: transparent;");
        } else if (event.getSource() == toggleBtnRegister) {
            toggleBtnRegister.setStyle(
                    "-fx-cursor: hand; -fx-text-fill: #6b6b6b; -fx-background-color: transparent;");

        } else if (event.getSource() == loginBtn) {
            loginBtn.setStyle(
                    "-fx-cursor: hand; -fx-background-color: #3b00c7;");
        } else if (event.getSource() == registerBtn) {
            registerBtn.setStyle(
                    "-fx-cursor: hand; -fx-background-color: #3b00c7;");

        }
    }

    @FXML
    public void handleMouseExited(MouseEvent event) {

        if (event.getSource() == toggleBtnLogin) {
            toggleBtnLogin.setStyle(
                    "-fx-cursor: hand; -fx-text-fill: #ffffff; -fx-background-color: transparent;");
        } else if (event.getSource() == toggleBtnRegister) {
            toggleBtnRegister.setStyle(
                    "-fx-cursor: hand; -fx-text-fill: #ffffff; -fx-background-color: transparent;");

        } else if (event.getSource() == loginBtn) {
            loginBtn.setStyle(
                    "-fx-cursor: hand; -fx-background-color: #4c00ff;");
        } else if (event.getSource() == registerBtn) {
            registerBtn.setStyle(
                    "-fx-cursor: hand; -fx-background-color: #4c00ff;");
        }

    }

    public String sendServerRequest(String request) {
        try {
            return ServerConnection.sendRequest(request);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "ERR";
    }
}
