package model;

/**
 * The DogProfile class represents a dog's profile within the "Puppy Pal" application.
 * It stores essential details about a dog, including its ID, name, breed, age, weight, and height.
 * Each dog profile is associated with a unique identifier (dogID) to distinguish between
 * different dogs in the application. Users can create, update, and retrieve dog profiles
 * to keep track of their dogs.
 */
import java.util.ArrayList;
import java.util.List;

public class Dog {
    private int id;             // Unique identifier for each dog profile
    private static int nextId = 1;             // Unique identifier for each dog profile
    private String dogName;     // Dog's name
    private String breed;       // Dog's breed
    private double weight;      // Dog's weight in pounds
    private double height;      // Dog's height in centimeters
    private List<HealthRecord> healthRecord;
    //private Medication med;

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
        healthRecord = new ArrayList<>();
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

    public List<HealthRecord> getHealthRecord() {
        return healthRecord;
    }

    public void addHealthRecord(HealthRecord healthRecord) {
        this.healthRecord.add(healthRecord);
    }

    public void removeHealthRecord(HealthRecord healthRecord) {
        this.healthRecord.remove(healthRecord);
    }

}
