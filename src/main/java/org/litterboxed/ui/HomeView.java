package org.litterboxed.ui;

import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.DirectoryChooser;
import javafx.util.Callback;
import org.litterboxed.domain.MovieFolderReader;
import org.litterboxed.domain.model.Movie;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class HomeView {

    private Collection<Movie> moviesInDirectory = new ArrayList<>();
    private final MovieFolderReader folderReader = new MovieFolderReader();

    public Parent getView() {
        BorderPane layout =  new BorderPane();

        TableView<Movie> table = new TableView<>();

        Button openFileButton = new Button("Open Folder");
        HBox hBox = new HBox();
        hBox.getChildren().add(openFileButton);
        hBox.getChildren().add(new Button("Hello, World!"));

        openFileButton.setOnAction(e -> {
            DirectoryChooser folderChooser = new DirectoryChooser();
            folderChooser.setTitle("Open Resource File");

            File file = folderChooser.showDialog(null);

            if (file != null) {
                moviesInDirectory = folderReader.readFolderContents(file.getAbsolutePath());
                ObservableList<Movie> movies = FXCollections.observableArrayList(
                        moviesInDirectory
                );
                table.setItems(movies);

                TableColumn<Movie, String> titleCol = new TableColumn<>("Movie Title");
                titleCol.setCellValueFactory(data -> data.getValue().movieTitleProperty());

                TableColumn<Movie, Number> idCol = new TableColumn<>("Movie ID");
                idCol.setCellValueFactory(data -> data.getValue().movieIDProperty());

                table.getColumns().setAll(idCol, titleCol);
            }

        });

        layout.setTop(hBox);
        layout.setCenter(table);

        return layout;
    }
}
