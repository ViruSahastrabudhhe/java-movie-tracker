package org.litterboxed.domain;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class FolderReader {
    public static List<String> readFolderContents(String input) {
        List<String> files = new ArrayList<>();

        try (Stream<Path> stream = Files.walk(Paths.get(input), 1)) {
            stream.map(Path::toFile)
                    .filter(File::isDirectory)
                    .map(file -> String.valueOf(file.getName()))
                    .map(file -> file.replace(".", " "))
                    .forEach(files::add);
        } catch (IOException e) {
            System.out.println("Error reading folder: " + e.getMessage());
        }

        // prevents the directory name from being persisted into the database
        files.remove(files.get(0));

        return files;
        // E:\Videos\qbittorrents\Movies
    }
}
