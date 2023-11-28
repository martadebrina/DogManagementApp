package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.List;

// represents the user's list of dogs
public class Dogs implements Writable {
    private List<Dog> dogs;     // List of dogs that user have


    public Dogs() {
        dogs = new ArrayList<>();
    }

    // getters
    public List<Dog> getDogs() {
        return dogs;
    }

    public int getNumDogs() {
        return dogs.size();
    }

    // MODIFIES: this
    // EFFECTS: adds dog to the list of user's dogs
    public void addDog(Dog dog) {
        dogs.add(dog);
        EventLog.getInstance().logEvent(new Event("Dog added to list of user's dogs"));
    }


    // REQUIRES: the given dog must already be in the dogs list
    // MODIFIES: this
    // EFFECTS: removes dog from list of user's dogs
    public void removeDog(Dog dog) {
        for (Dog d: dogs) {
            if (d.getId() == dog.getId()) {
                dogs.remove(d);
                EventLog.getInstance().logEvent(new Event("Dog removed from list of user's dog"));
                break;
            }
        }
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("dogs", dogsToJson());
        return json;
    }

    // EFFECTS: returns dogs in this Dogs as a JSON array
    private JSONArray dogsToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Dog d : dogs) {
            jsonArray.put(d.toJson());
        }

        return jsonArray;
    }


}
