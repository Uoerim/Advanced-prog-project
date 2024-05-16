package app.handlers;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.Window;

public class SceneHandler {
    public static void changeScene(Window window, Parent root) {
        Scene scene = new Scene(root);
        Stage stage = (Stage) window;
        stage.setScene(scene);
        stage.show();
    }
}
