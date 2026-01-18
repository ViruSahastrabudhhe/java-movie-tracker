package org.litterboxed.domain;

import org.litterboxed.domain.model.Movie;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

public class MovieFolderReader implements FolderReader<Movie> {

    @Override
    public Collection<Movie> readFolderContents(String input) {
        Collection<Movie> files = new ArrayList<>();
        AtomicInteger i = new AtomicInteger(1);

        try (Stream<Path> stream = Files.walk(Paths.get(input), 1)) {
            stream.map(Path::toFile)
                    .filter(File::isDirectory)
                    .map(File::getName)
                    .map(file -> file.replace(".", " "))
                    .map(file -> {
                        Movie movie = new Movie(i.get(), file);
                        i.getAndIncrement();
                        return movie;
                    })
                    .forEach(files::add);
        } catch (IOException e) {
            System.out.println("Error reading folder: " + e.getMessage());
        }

//        if (files.isEmpty()) {
//            return files;
//        }

        // prevents the directory name from being persisted into the database

        return files;
        // E:\Videos\qbittorrents\Movies
    }

}
