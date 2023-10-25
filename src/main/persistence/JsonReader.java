package persistence;

import model.Dog;
import model.Dogs;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import model.HealthRecord;
import org.json.*;

// Represents a reader that reads dogs from JSON data stored in file
// The implementation within this class is adapted from the "JsonSerializationDemo" project
// (https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git)
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads dogs from file and returns it;
    // throws IOException if an error occurs reading data from file
    public Dogs read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseDogs(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses dogs from JSON object and returns it
    private Dogs parseDogs(JSONObject jsonObject) {
        Dogs dogs = new Dogs();
        addDogs(dogs, jsonObject);
        return dogs;
    }

    // MODIFIES: dogs
    // EFFECTS: parses dogs from JSON object and adds them to Dogs
    private void addDogs(Dogs dogs, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("dogs");
        for (Object json : jsonArray) {
            JSONObject nextDog = (JSONObject) json;
            addDog(dogs, nextDog);
        }
    }

    // MODIFIES: dogs
    // EFFECTS: parses dog from JSON object and adds it to Dogs
    private void addDog(Dogs dogs, JSONObject jsonObject) {
        String name = jsonObject.getString("dogName");
        String breed = jsonObject.getString("breed");
        double weight = jsonObject.getDouble("weight");
        double height = jsonObject.getDouble("height");

        Dog dog = new Dog(name, breed, weight, height);
        addHealths(dog, jsonObject);

        dogs.addDog(dog);
    }

    // MODIFIES: dogs
    // EFFECTS: parses dogs from JSON object and adds them to Dogs
    private void addHealths(Dog dog, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("healthRecords");
        for (Object json : jsonArray) {
            JSONObject nextHealthRecord = (JSONObject) json;
            addHealth(dog, nextHealthRecord);
        }
    }

    private void addHealth(Dog dog, JSONObject jsonObject) {
        String healthRecordType = jsonObject.getString("healthRecordType");
        String scheduleName = jsonObject.getString("healthRecordName");
        String healthRecordDate = jsonObject.getString("healthRecordDate");
        HealthRecord healthRecord = new HealthRecord(healthRecordType, scheduleName, healthRecordDate);
        dog.addHealthRecord(healthRecord);
    }

}
