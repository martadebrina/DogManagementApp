package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class DogsTest {
    private Dog dog1;
    private Dog dog2;
    private Dog dog3;
    private Dogs test;

    @BeforeEach
    public void setUp() {
        dog1 = new Dog("Brownie", "Akita", 32.5,64);
        dog2 = new Dog("Ciko", "Beagle", 10.7,36.4);
        dog3 = new Dog("Milo", "Beagle", 10.7,35.4);
        test = new Dogs();
    }

    @Test
    public void testAddOneDog(){
        test.addDog(dog1);
        List<Dog> dogs = test.getDogs();
        assertEquals(1, dogs.size());
        assertTrue(dogs.contains(dog1));

    }

    @Test
    public void testAddMultipleDog() {
        test.addDog(dog1);
        test.addDog(dog2);
        List<Dog> dogs = test.getDogs();
        assertEquals(2, dogs.size());
        assertEquals(dog1, dogs.get(0));
        assertEquals(dog2, dogs.get(1));

    }

    @Test
    public void testRemoveOneDog() {
        test.addDog(dog1);
        test.addDog(dog2);
        test.addDog(dog3);
        test.removeDog(dog2);
        assertEquals(2, test.getDogs().size());
        assertEquals(dog1, test.getDogs().get(0));
        assertEquals(dog3, test.getDogs().get(1));
    }

    @Test
    public void testRemoveDogMultipleTimes() {
        test.addDog(dog2);
        test.addDog(dog3);
        test.addDog(dog1);
        test.removeDog(dog3);
        test.removeDog(dog1);
        List<Dog> dogs = test.getDogs();
        assertEquals(1, dogs.size());
        assertTrue(dogs.contains(dog2));
        test.removeDog(dog2);
        assertTrue(dogs.isEmpty());

    }
}
