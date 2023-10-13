package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class DogTest {
    private Dog dogTest;
    private HealthRecord med1;
    private HealthRecord vacc1;

    @BeforeEach
    public void runBefore() {
        dogTest = new Dog("Brownie", "Akita", 32.5,64);
        med1 = new HealthRecord("MEDICATION","Ampisol 2 g",
                "2022-10-10");
        vacc1 = new HealthRecord("VACCINATION","Rabies",
                "2023-08-10");
        HealthRecord.resetHealthId();
    }

    @Test
    public void testConstructor(){
        assertEquals("Brownie", dogTest.getDogName());
        assertEquals("Akita", dogTest.getBreed());
        assertEquals(32.5, dogTest.getWeight());
        assertEquals(64, dogTest.getHeight());
        List<HealthRecord> healthRecord = dogTest.getHealthRecords();
        assertTrue(healthRecord.isEmpty());

        assertEquals("MEDICATION", med1.getHealthRecordType());
        assertEquals("Ampisol 2 g", med1.getHealthRecordName());
        assertEquals( "2022-10-10", med1.getHealthRecordDate());
        assertEquals("VACCINATION", vacc1.getHealthRecordType());
        assertEquals("Rabies", vacc1.getHealthRecordName());
        assertEquals( "2023-08-10", vacc1.getHealthRecordDate());
    }

    @Test
    public void testAddOneHealthRecord() {
        dogTest.addHealthRecord(med1);
        List<HealthRecord> healthRecord1 = dogTest.getHealthRecords();
        assertEquals(1, healthRecord1.size());
        assertTrue(healthRecord1.contains(med1));
    }

    @Test
    public void testAddMultipleHealthRecord(){
        dogTest.addHealthRecord(med1);
        dogTest.addHealthRecord(vacc1);
        List<HealthRecord> healthRecord = dogTest.getHealthRecords();
        assertEquals(2, healthRecord.size());
        assertEquals(med1, healthRecord.get(0));
        assertEquals(vacc1, healthRecord.get(1));
    }

    @Test
    public void testRemoveOneHealthRecord(){
        dogTest.addHealthRecord(med1);
        dogTest.addHealthRecord(vacc1);
        dogTest.removeHealthRecord(2);
        List<HealthRecord> healthRecord = dogTest.getHealthRecords();
        assertEquals(1, healthRecord.size());
        assertTrue(healthRecord.contains(med1));
    }

    @Test
    public void testRemoveMultipleHealthRecord(){
        dogTest.addHealthRecord(med1);
        dogTest.addHealthRecord(vacc1);
        dogTest.removeHealthRecord(2);
        dogTest.removeHealthRecord(1);
        List<HealthRecord> healthRecord = dogTest.getHealthRecords();
        assertTrue(healthRecord.isEmpty());
        dogTest.removeHealthRecord(2);
        assertTrue(healthRecord.isEmpty());


    }


}
