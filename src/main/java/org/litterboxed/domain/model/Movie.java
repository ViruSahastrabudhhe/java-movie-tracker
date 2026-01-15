package org.litterboxed.domain.model;

public class Movie {
    private long id;
    private String title;

    public Movie() {
        this.id = 0;
        this.title = "";
    }

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
