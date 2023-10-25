package persistence;

import model.Dog;
import model.Dogs;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

// The implementation within this class is adapted from the "JsonSerializationDemo" project
// (https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git)
class JsonWriterTest extends JsonTest {

    @Test
    void testWriterInvalidFile() {
        try {
            Dogs dogs = new Dogs();
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyDogs() {
        try {
            Dogs dogs = new Dogs();
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyDogs.json");
            writer.open();
            writer.write(dogs);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyDogs.json");
            dogs = reader.read();
            assertEquals(0, dogs.getNumDogs());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralDogs() {
        try {
            Dogs dogs = new Dogs();
            dogs.addDog(new Dog("Bubu", "Bulldog", 23.5, 44));
            dogs.addDog(new Dog("Lili", "Golden Retriever", 23.5, 58));
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralDogs.json");
            writer.open();
            writer.write(dogs);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralDogs.json");
            dogs = reader.read();
            List<Dog> dogList = dogs.getDogs();
            assertEquals(2, dogList.size());
            checkDog("Bubu", "Bulldog", 23.5, 44, dogList.get(0));
            checkDog("Lili", "Golden Retriever", 23.5, 58, dogList.get(1));

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}