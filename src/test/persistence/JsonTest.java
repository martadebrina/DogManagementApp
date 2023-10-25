package persistence;


import model.Dog;
import model.HealthRecord;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

// The implementation within this class is adapted from the "JsonSerializationDemo" project
// (https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git)
public class JsonTest {
    protected void checkDog(String name, String breed, double weight, double height, Dog dog,
                            List<HealthRecord> healthRecords) {
        assertEquals(name, dog.getDogName());
        assertEquals(breed, dog.getBreed());
        assertEquals(weight, dog.getWeight());
        assertEquals(height, dog.getHeight());
        checkHealthRecordDog(dog, healthRecords);

    }

    protected void checkHealthRecordDog(Dog dog, List<HealthRecord> hr) {
        List<HealthRecord> healthRecords = dog.getHealthRecords();
        int hSize = hr.size();
        assertEquals(hSize, healthRecords.size());
        for (HealthRecord h: healthRecords) {
            int i=0;
            HealthRecord hr1 = hr.get(i);
            assertEquals(hr1.getHealthRecordName(), h.getHealthRecordName());
            assertEquals(hr1.getHealthRecordType(), h.getHealthRecordType());
            assertEquals(hr1.getHealthRecordDate(), h.getHealthRecordDate());
            i++;
        }

    }


}
