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