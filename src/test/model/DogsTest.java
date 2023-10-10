package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

public class DogsTest {
    private Dog dog1;
    private Dog dog2;
    private Dogs dogs;

    @BeforeEach
    public void setUp() {
        dog1 = new Dog(01,"Brownie", "Akita", 32.5,64);
        dog2 = new Dog(02,"Ciko", "Beagle", 10.7,36.4);
        dogs = new Dogs();
    }

    @Test
    public void testCreateDog(){
        assertEquals("Brownie", dog1.getName());

    }

    @Test
    public void testDogs(){
        dogs.addDog(dog1);
        assertEquals(1, dogs.getDogs().size());
        assertEquals(dog1, dogs.getDogs().get(0));

        dogs.addDog(dog2);
        assertEquals(2, dogs.getDogs().size());
        assertEquals(dog1, dogs.getDogs().get(0));
        assertEquals(dog2, dogs.getDogs().get(1));

        dogs.removeDog(dog2);
        assertEquals(1, dogs.getDogs().size());
        assertEquals(dog1, dogs.getDogs().get(0));

    }
}
