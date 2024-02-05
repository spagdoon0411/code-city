package com.csc308;
import java.io.*;
import java.net.*;
import java.net.http.*;
import java.nio.file.*;

/* NOTE - this code was done last night (tuesday night), but i wanted to submit what i had in case you needed it. 
	I will try to get junit working after my classes for the day*/

/* Example usage
// Downloads all -files- from public Github repository
        String repoUrl = "https://github.com/Grant-Thacker/TestRepository";
        fileDownloader.downloadFromUrl(repoUrl);
*/
public class FileDownloader {

    public void downloadFromUrl(String url){
        url = "https://api.github.com/repos/" + url.substring(19);

        try {
            HttpClient httpClient = HttpClient.newHttpClient();

            // Get a list of files from the given repository
            HttpRequest fileListRequest = HttpRequest.newBuilder()
                    .uri(URI.create(url + "/contents"))
                    .build();

            HttpResponse<String> fileListResponse = httpClient.send(fileListRequest, HttpResponse.BodyHandlers.ofString());
            if (fileListResponse.statusCode() == 200) {
                String fileListJson = fileListResponse.body();

                String[] files = fileListJson.split("\"download_url\":\"");
                for (int i = 1; i < files.length; i++) {
                    String downloadUrl = files[i].substring(0, files[i].indexOf("\""));
                    downloadFile(httpClient, downloadUrl);
                }
            } else {
                System.err.println("Failed to fetch file list. Status code: " + fileListResponse.statusCode());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void downloadFile(HttpClient httpClient, String downloadUrl) {
        try {
            HttpRequest downloadRequest = HttpRequest.newBuilder()
                    .uri(URI.create(downloadUrl))
                    .build();

            HttpResponse<byte[]> downloadResponse = httpClient.send(downloadRequest, HttpResponse.BodyHandlers.ofByteArray());

            if (downloadResponse.statusCode() == 200) {
                // Get the file name from the URL
                String fileName = downloadUrl.substring(downloadUrl.lastIndexOf("/") + 1);

                // Make a folder for download if it doesn't exist
                String folderDownloadPath = System.getProperty("user.home") + File.separator + "Downloads";
                Path folderPath = Path.of(folderDownloadPath);
                if (!Files.exists(folderPath)) {
                    try {
                        Files.createDirectories(folderPath);
                    } catch (IOException e) {
                        e.printStackTrace();
                        return;
                    }
                }

                // Download the file to the local machine's downloads folder
                Path filePath = Path.of(folderDownloadPath, fileName);
                Files.write(filePath, downloadResponse.body(), StandardOpenOption.CREATE);
                System.out.println("Downloaded: " + fileName);
            } else {
                System.err.println("Failed to download file. Status code: " + downloadResponse.statusCode());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
