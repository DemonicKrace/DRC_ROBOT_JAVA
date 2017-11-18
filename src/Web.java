/**
 * @(#)Web.java
 *
 *
 * @author 
 * @version 1.00 2017/9/27
 */

import java.io.IOException;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

public class Web extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        StackPane root = new StackPane();

        WebView view = new WebView();
		
        WebEngine engine = view.getEngine();
        engine.load("http://192.168.123.1:8080/");
        root.getChildren().add(view);
        
        Scene scene = new Scene(root, 350, 300);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) throws IOException {
        Application.launch(args);
    }
}