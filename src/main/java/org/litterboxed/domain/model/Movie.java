package org.litterboxed.domain.model;

public class Movie {
    private String title;

    public Movie() {
        this.title = "";
    }

    public void setTitle(String title) {
        this.title=title;
    }

    public String getTitle() {
        return this.title;
    }
}
