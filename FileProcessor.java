import java.util.*;

public class FileProcessor {
    /**
     * Processes a list of FileInfo objects and groups them into buildings by package.
     *
     * @param fileInfos List of FileInfo objects representing the files to be processed.
     * @return A map where each key is a package name and the value is a list of Building objects belonging to that package.
     */
    public static Map<String, List<Building>> processFiles(List<FileInfo> fileInfos) {
        if (fileInfos == null) {
            throw new NullPointerException("fileInfos cannot be null");
        }
        // Initialize a map to hold lists of buildings grouped by their package name
        Map<String, List<Building>> buildingsByPackage = new HashMap<>();

        // Iterate through each FileInfo object
        for (FileInfo fileInfo : fileInfos) {
            // Convert each FileInfo object into a Building object
            Building building = new Building(fileInfo.getClassName(), fileInfo.getNumberOfMethods(), fileInfo.getNumberOfAttributes());
            // Group the Building objects by their package, adding them to the corresponding list in the map
            buildingsByPackage.computeIfAbsent(fileInfo.getPackageName(), k -> new ArrayList<>()).add(building);
        }
        return buildingsByPackage;
    }
}


