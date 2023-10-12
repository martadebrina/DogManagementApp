package model;

import java.util.ArrayList;
import java.util.List;

// represents the user's list of dogs
public class Dogs {
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
        for (Dog d: dogs) {
            if (d.getId() == dog.getId()) {
                dogs.remove(d);
                break;
            }
        }
    }


}
