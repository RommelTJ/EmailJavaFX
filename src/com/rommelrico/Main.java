package com.rommelrico;

import com.rommelrico.view.ViewFactory;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        ViewFactory viewFactory = ViewFactory.defaultFactory;
        Scene scene = viewFactory.getMainScene();
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
