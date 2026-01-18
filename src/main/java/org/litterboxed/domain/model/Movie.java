package org.litterboxed.domain.model;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Movie {
    private long id;
    private String title;
    private final SimpleStringProperty movieTitle;
    private final SimpleIntegerProperty movieID;

    public Movie() {
        this.id = 1;
        this.title = "";
        this.movieTitle = new SimpleStringProperty(this, "movieTitle");
        this.movieID = new SimpleIntegerProperty(this, "movieID");
    }

    public Movie(int movieID, String movieTitle) {
        this.id = 1;
        this.title = "";
        this.movieTitle = new SimpleStringProperty(movieTitle);
        this.movieID = new SimpleIntegerProperty(movieID);
    }

    public SimpleStringProperty movieTitleProperty() { return movieTitle; }
    public SimpleIntegerProperty movieIDProperty() { return movieID; }

    public void setId(long id) {
        this.id = id;
    }

    public long getId() {
        return this.id;
    }

    public void setTitle(String title) {
        this.title=title;
    }

    public String getTitle() {
        return this.title;
    }

    @Override
    public String toString() {
        return "Movie: " + this.title;
    }
}
