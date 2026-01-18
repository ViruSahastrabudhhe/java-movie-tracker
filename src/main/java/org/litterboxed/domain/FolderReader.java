package org.litterboxed.domain;

import org.litterboxed.domain.model.Movie;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Stream;

public interface FolderReader<T> {
    Collection<T> readFolderContents(String folderName);
}
