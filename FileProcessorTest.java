import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.*;

class FileProcessorTest {

    @Test
    void testProcessFilesBasic() {
        List<FileInfo> fileInfos = Arrays.asList(
                new FileInfo("Package1", "Class1", 10, 5),
                new FileInfo("Package1", "Class2", 7, 8),
                new FileInfo("Package2", "Class3", 3, 12)
        );

        Map<String, List<Building>> cityMap = FilesToBuildings.processFiles(fileInfos);

        // Basic checks
        assertEquals(2, cityMap.size(), "Package count mismatch");
        assertTrue(cityMap.containsKey("Package1"), "Package1 missing");
        assertTrue(cityMap.containsKey("Package2"), "Package2 missing");
    }

    @Test
    void testProcessFilesBuildingCount() {
        List<FileInfo> fileInfos = Arrays.asList(
                new FileInfo("Package1", "Class1", 10, 5),
                new FileInfo("Package1", "Class2", 7, 8),
                new FileInfo("Package2", "Class3", 3, 12)
        );

        Map<String, List<Building>> cityMap = FilesToBuildings.processFiles(fileInfos);

        // Check the number of buildings in each package
        assertEquals(2, cityMap.get("Package1").size(), "Building count in Package1 mismatch");
        assertEquals(1, cityMap.get("Package2").size(), "Building count in Package2 mismatch");
    }

    @Test
    void testProcessFilesBuildingAttributes() {
        List<FileInfo> fileInfos = Arrays.asList(
                new FileInfo("Package1", "Class1", 10, 5)
        );

        Map<String, List<Building>> cityMap = FilesToBuildings.processFiles(fileInfos);

        // Check attributes of a specific building
        Building building = cityMap.get("Package1").get(0);
        assertEquals("Class1", building.getClassName(), "Class name mismatch");
        assertEquals(10, building.getHeight(), "Height mismatch");
        assertEquals(5, building.getWidth(), "Width mismatch");
    }

    @Test
    void testProcessFilesEmptyInput() {
        List<FileInfo> fileInfos = Arrays.asList();

        Map<String, List<Building>> cityMap = FilesToBuildings.processFiles(fileInfos);

        // Check processing of empty input
        assertTrue(cityMap.isEmpty(), "City map should be empty for empty input");
    }

    @Test
    void testProcessFilesNullInput() {
        // Check handling of null input gracefully
        Exception exception = assertThrows(NullPointerException.class, () -> {
            FilesToBuildings.processFiles(null);
        });

        String expectedMessage = "fileInfos cannot be null";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage), "Exception message mismatch");
    }
}
