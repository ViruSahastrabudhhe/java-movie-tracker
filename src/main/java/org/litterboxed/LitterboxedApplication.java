package org.litterboxed;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import org.litterboxed.domain.model.Movie;
import org.litterboxed.repository.MovieRepository;
import org.litterboxed.repository.TablesManager;
import org.litterboxed.ui.HomeView;

import java.io.IOException;
import java.sql.SQLException;

import static org.litterboxed.domain.MoviesLoader.MOVIES_INIT_DATA;

public class LitterboxedApplication extends Application {

    private final HomeView homeView;
    private final int WIDTH = 1280;
    private final int HEIGHT = 720;

    public LitterboxedApplication() {
        this.homeView = new HomeView();
    }

    @Override
    public void start(Stage window) {
        Parent layout = this.homeView.getView();

        Scene scene = new Scene(layout);

        window.setScene(scene);
        window.setTitle("Hello!");
        window.show();
    }

    public static void main(String[] args) {
        launch(LitterboxedApplication.class, args);
    }
}
