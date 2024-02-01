import java.util.*;

public class fileProcessor {
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


class FileInfo {
    String packageName;
    String className;
    int numberOfMethods;
    int numberOfAttributes;

    // Constructor
    public FileInfo(String packageName, String className, int numberOfMethods, int numberOfAttributes) {
        this.packageName = packageName;
        this.className = className;
        this.numberOfMethods = numberOfMethods;
        this.numberOfAttributes = numberOfAttributes;
    }

    // Getters
    public String getPackageName() {
        return packageName;
    }

    public String getClassName() {
        return className;
    }

    public int getNumberOfMethods() {
        return numberOfMethods;
    }

    public int getNumberOfAttributes() {
        return numberOfAttributes;
    }
}


class Building {
    String className;
    int height; // Based on number of methods
    int width;  // Based on number of attributes

    // Constructor
    public Building(String className, int height, int width) {
        this.className = className;
        this.height = height;
        this.width = width;
    }

    // Getters
    public String getClassName() {
        return className;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }


    // Setters for height and width
    public void setHeight(int height) {
        this.height = height;
    }

    public void setWidth(int width) {
        this.width = width;
    }


}
