package com.csc308.tests;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertTrue;

import com.csc308.*;

public class FileDownloaderTest {

    @TempDir
    File tempFolder;

    @Test
    void testDownloads() throws IOException {
        String remoteUrl = "https://github.com/spagdoon0411/code-city-test-remote";


        FileDownloader fd = new FileDownloader(remoteUrl, tempFolder.getAbsolutePath()
                + File.pathSeparator + "repo");
        File downloadDirectory = fd.getFolder();

        System.out.println(tempFolder.exists());

        // Ensure new directory was actually created
        assertTrue(downloadDirectory.exists());
        assertTrue(downloadDirectory.isDirectory());

        // Files expected to be in downloaded repo
        String[] expectedFiles = {"Bench.java", "Bird.java", "Car.java", "Duck.java", "README.md", "Spaceship.java"};

        // Ensure the directory contains all files expected
        ArrayList<String> namesFilesInDir = Arrays.stream(Objects.requireNonNull(downloadDirectory.listFiles()))
                .map(File::getName)
                .collect(Collectors.toCollection(ArrayList::new));

        namesFilesInDir.forEach(System.out::println);

        Arrays.stream(expectedFiles).forEach(s -> assertTrue(namesFilesInDir.contains(s)));
    }
}
