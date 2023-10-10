package model;

import java.util.ArrayList;
import java.util.List;

public class Dogs {
    private int id;             // User's ID
    private List<Dog> dogs;     // List of dogs that user have


    public Dogs() {
        dogs = new ArrayList<>();
    }

    // getters
    public List<Dog> getDogs() {
        return dogs;
    }

    // MODIFIES: this
    // EFFECTS: adds dog to the list of user's dogs
    public void addDog(Dog dog) {
        dogs.add(dog);
    }

    // REQUIRES: the given dog must already be in the dogs list
    // MODIFIES: this
    // EFFECTS: removes dog from list of user's dogs
    public void removeDog(Dog dog) {
        dogs.remove(dog);
    }
}
