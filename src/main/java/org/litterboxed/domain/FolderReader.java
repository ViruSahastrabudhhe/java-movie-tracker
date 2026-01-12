package org.litterboxed.domain;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class FolderReader {
    public static void readFolderContents(String input) throws IOException {
        try (Stream<Path> stream = Files.walk(Paths.get(input), 1)) {
            stream.map(Path::toFile)
                    .filter(File::isDirectory)
                    .map(file -> String.valueOf(file.getName()))
                    .map(file -> file.replace(".", " "))
                    .forEach(System.out::println);
        } catch (IOException e) {
            System.out.println("Error reading folder: " + e.getMessage());
        }

        //E:\Videos\qbittorrents\Movies
    }
}
