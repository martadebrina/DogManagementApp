package persistence;


import model.Dog;

import static org.junit.jupiter.api.Assertions.assertEquals;

// The implementation within this class is adapted from the "JsonSerializationDemo" project
// (https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git)
public class JsonTest {
    protected void checkDog(String name, String breed, double weight, double height, Dog dog) {
        assertEquals(name, dog.getDogName());
        assertEquals(breed, dog.getBreed());
        assertEquals(weight, dog.getWeight());
        assertEquals(height, dog.getHeight());
    }
}
