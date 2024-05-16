package app.controller;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import app.ServerConnection;
import app.classes.User.Session;
import app.handlers.SceneHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class SettingsController {
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
    private Button logoutBtn;
    @FXML
    private TextField newUsernameText;
    @FXML
    private Button newUsernameBtn;
    @FXML
    private TextField newEmailText;
    @FXML
    private Button newEmailBtn;
    @FXML
    private TextField newProfilePicText;
    @FXML
    private Button newProfilePicBtn;
    @FXML
    private TextField oldPasswordText;
    @FXML
    private TextField newPasswordText;
    @FXML
    private Button newPasswordBtn;
    @FXML
    private TextField changeBioText;
    @FXML
    private Button changeBioBtn;
    @FXML
    private Button deleteAccountBtn;

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
        } else if (event.getSource() == directMessagingBtn) {
            try {
                SceneHandler.changeScene(settingsBtn.getScene().getWindow(),
                        FXMLLoader.load(
                                getClass().getResource("../../resources/DirectMessagePage.fxml")));
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (event.getSource() == settingsBtn) {
            // do nothing
        } else if (event.getSource() == logoutBtn) {
            Session.setUser(null);
            try {
                SceneHandler.changeScene(logoutBtn.getScene().getWindow(),
                        FXMLLoader.load(
                                getClass().getResource("../../resources/AuthenticationPage.fxml")));
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (event.getSource() == newUsernameBtn) {
            if (newUsernameText.getText().equals("")) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Username cannot be empty!");
                alert.showAndWait();
                return;
            }
            String res1 = sendServerRequest("REQ:GETUSER:" + newUsernameText.getText());
            if (!res1.equals("FAILED")) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Username already exists!");
                alert.showAndWait();
                return;
            }
            String res = sendServerRequest(
                    "REQ:UPDATEUSERNAME:" + Session.getUser().getUsername() + ":" + newUsernameText.getText());
            System.out.println(res);
            if (res.equals("SUCCESS")) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Success");
                alert.setHeaderText("Username updated successfully!");
                Session.getUser().setUsername(newUsernameText.getText());
                alert.showAndWait();
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Username update failed!");
                alert.showAndWait();
            }
        } else if (event.getSource() == newEmailBtn) {
            if (newEmailBtn.getText().equals("")) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Email cannot be empty!");
                alert.showAndWait();
                return;
            }
            String email = newEmailText.getText();
            String emailPattern = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                    + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

            Pattern pattern = Pattern.compile(emailPattern);
            Matcher matcher = pattern.matcher(email);
            if (!matcher.matches()) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Email is not valid!");
                alert.showAndWait();
            }

            String res = sendServerRequest(
                    "REQ:UPDATEEMAIL:" + Session.getUser().getUsername() + ":" + newEmailText.getText());
            if (res.equals("SUCCESS")) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Success");
                alert.setHeaderText("Email updated successfully!");
                Session.getUser().setEmail(newEmailText.getText());
                alert.showAndWait();
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Email update failed!");
                alert.showAndWait();
            }
        } else if (event.getSource() == newProfilePicBtn) {
            if (newProfilePicBtn.getText().equals("")) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Profile picture cannot be empty!");
                alert.showAndWait();
                return;
            }
            String res = sendServerRequest(
                    "REQ:UPDATEPROFILEPICTURE:" + Session.getUser().getUsername() + ":" + newProfilePicText.getText().replace("https://", ""));
            if (res.equals("SUCCESS")) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Success");
                alert.setHeaderText("Profile Picture updated successfully!");
                Session.getUser().setPicPath(newProfilePicText.getText());
                alert.showAndWait();
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Profile Picture update failed!");
                alert.showAndWait();
            }
        } else if (event.getSource() == newPasswordBtn) {
            if (newPasswordText.getText().equals("")) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("password cannot be empty!");
                alert.showAndWait();
                return;
            }
            String res = sendServerRequest(
                "REQ:UPDATEPASSWORD:" + Session.getUser().getUsername() + ":" + newPasswordText.getText());
        if (res.equals("SUCCESS")) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Success");
            alert.setHeaderText("Bio updated successfully!");
            alert.showAndWait();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Bio update failed!");
            alert.showAndWait();
        }
        } else if (event.getSource() == changeBioBtn) {
            if (changeBioBtn.getText().equals("")) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Bio cannot be empty!");
                alert.showAndWait();
                return;
            }
            String res = sendServerRequest(
                    "REQ:UPDATEBIO:" + Session.getUser().getUsername() + ":" + changeBioText.getText());
            if (res.equals("SUCCESS")) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Success");
                alert.setHeaderText("Bio updated successfully!");
                alert.showAndWait();
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Bio update failed!");
                alert.showAndWait();
            }
        } else if (event.getSource() == deleteAccountBtn) {
            Alert alertc = new Alert(Alert.AlertType.CONFIRMATION);
            alertc.setTitle("Confirmation Dialog");
            alertc.setHeaderText("Are you sure you want to proceed?");
            alertc.setContentText("Click OK to continue or Cancel to abort.");

            ButtonType okButton = new ButtonType("OK");
            ButtonType cancelButton = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);
            alertc.getButtonTypes().setAll(okButton, cancelButton);

            alertc.showAndWait().ifPresent(buttonType -> {
                if (buttonType == okButton) {
                } else {
                    System.out.println("User clicked Cancel");
                    return;
                }
            });
            String res = sendServerRequest(
                    "REQ:DELETEACCOUNT:" + Session.getUser().getUsername());
            if (res.equals("SUCCESS")) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Success");
                alert.setHeaderText("Account deleted successfully!");
                alert.showAndWait();
                Session.setUser(null);
                try {
                    SceneHandler.changeScene(deleteAccountBtn.getScene().getWindow(),
                            FXMLLoader.load(
                                    getClass().getResource("../../resources/AuthenticationPage.fxml")));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Account deletion failed!");
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

}
