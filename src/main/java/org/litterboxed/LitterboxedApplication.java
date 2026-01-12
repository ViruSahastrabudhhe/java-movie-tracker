package org.litterboxed;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import org.litterboxed.domain.FolderReader;
import org.litterboxed.ui.HomeView;
import org.litterboxed.repository.MovieRepository;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;
import java.sql.Connection;

public class LitterboxedApplication extends Application {

    private final HomeView homeView;

    public LitterboxedApplication() {
        this.homeView = new HomeView();
    }

    @Override
    public void start(Stage window) {
        Parent layout = this.homeView.getView();

        Scene scene = new Scene(layout, 1280, 720);

        window.setScene(scene);
        window.setTitle("Hello!");
        window.show();
    }

    public static void main(String[] args) throws SQLException {
//        launch(LitterboxedApplication.class, args);
        MovieRepository movieRepository = new MovieRepository();
        movieRepository.getConn();
    }
}
