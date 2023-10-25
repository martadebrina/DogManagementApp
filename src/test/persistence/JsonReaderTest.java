package persistence;

import model.Dog;
import model.Dogs;
import model.HealthRecord;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

// The implementation within this class is adapted from the "JsonSerializationDemo" project
// (https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git)
class JsonReaderTest extends JsonTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            Dogs dogs = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyDogs() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyDogs.json");
        try {
            Dogs dogs = reader.read();
            assertEquals(0, dogs.getDogs().size());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralDogs() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralDogs.json");
        try {
            Dogs dogs = reader.read();
            List<Dog> dogList = dogs.getDogs();
            assertEquals(2, dogList.size());
            List<HealthRecord> healthRecords1 = new ArrayList<>();
            healthRecords1.add(new HealthRecord("Medication", "med1",
                    "2023-04-03"));
            List<HealthRecord> healthRecords2 = new ArrayList<>();
            checkDog("Bubu", "Bulldog", 23.5, 44, dogList.get(0), healthRecords1);
            checkDog("Lili", "Golden Retriever", 23.5, 58, dogList.get(1), healthRecords2);

        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

}