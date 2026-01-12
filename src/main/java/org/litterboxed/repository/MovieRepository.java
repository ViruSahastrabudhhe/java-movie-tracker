package org.litterboxed.repository;

import org.litterboxed.domain.FolderReader;

public class MovieRepository extends Repository {

    public MovieRepository() {
        super("jdbc:mysql://localhost:3306/litterbox?user=root&password=");
    }

}
