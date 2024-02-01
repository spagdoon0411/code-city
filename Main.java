import java.io.*;

public class Main {

    public static void main(String[] args)
    {
        String remoteUrl = "https://github.com/spagdoon0411/code-city-test-remote";
        String downloadPath = "C:\\Users\\Spandan\\Downloads\\codecitytestdownload";

        FileDownloader fd = new FileDownloader(remoteUrl, downloadPath);
    }
}
