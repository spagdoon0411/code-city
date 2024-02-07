import javax.swing.*;
import java.util.*;
import java.util.List;

import static java.lang.System.exit;

public class Main {

    final static int FRAME_WIDTH = 800;
    final static int FRAME_HEIGHT = 600;

    final static int CANVAS_WIDTH = 9000;

    final static int CANVAS_HEIGHT = 9000;

    public static void main(String[] args)
    {
        // String recommendedTest = "https://github.com/spagdoon0411/code-city-test-remote";
        String remoteUrl = JOptionPane.showInputDialog("GitHub repository:");

        String downloadPath = JOptionPane.showInputDialog("Absolute download path:");


        if(remoteUrl == null || downloadPath == null) {
            exit(0);
        }

        // FileDownloader fd = new FileDownloader(remoteUrl, downloadPath);
        FileDownloader fd = new FileDownloader(remoteUrl, downloadPath);
        fd.downloadFiles();

        FileParser parser = new FileParser(downloadPath);
        List<FileInfo> fileInfos = parser.getFileInfos();

        Map<String, List<Building>> buildings = FilesToBuildings.processFiles(fileInfos);

        View v = new View(
                "Code City",
                new BuildingRenderer(buildings),
                FRAME_WIDTH,
                FRAME_HEIGHT,
                CANVAS_WIDTH,
                CANVAS_HEIGHT
        );

        v.produce();
    }
}
