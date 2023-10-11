package model;

import java.util.ArrayList;
import java.util.List;

// represents a dog's profile within the "Puppy Pal" application that contains details about a dog,
// including its ID, name, breed, age, weight, and height.
// Each dog profile is associated with a unique identifier (id)
public class Dog {
    private int id;             // Unique identifier for each dog profile
    private static int nextId = 1;             // Unique identifier for each dog profile
    private String dogName;     // Dog's name
    private String breed;       // Dog's breed
    private double weight;      // Dog's weight in pounds
    private double height;      // Dog's height in centimeters
    private List<HealthRecord> healthRecords;


    // Constructor
    // REQUIRES:
    // MODIFIES:this
    // EFFECTS: construct
    public Dog(String name, String breed, double weight, double height) {
        this.id = nextId++;
        this.dogName = name;
        this.breed = breed;
        this.weight = weight;
        this.height = height;
        healthRecords = new ArrayList<>();
    }

    public int getId() {
        return this.id;
    }

    public String getDogName() {
        return dogName;
    }

    public String getBreed() {
        return breed;
    }

    public double getWeight() {
        return weight;
    }

    public double getHeight() {
        return height;
    }

    public List<HealthRecord> getHealthRecords() {
        return healthRecords;
    }

    public void addHealthRecord(HealthRecord healthRecord) {
        this.healthRecords.add(healthRecord);
    }

    // REQUIRES:
    // MODIFIES: this
    // EFFECTS: removes health record from list of dog's health record
    public void removeHealthRecord(int healthId) {
        for (HealthRecord h: healthRecords) {
            if (h.getHealthId() == healthId) {
                healthRecords.remove(h);
                break;
            }
        }
    }

    public void resetId() {
        nextId = 1;
    }
}
