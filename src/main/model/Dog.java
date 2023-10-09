package model;

/**
 * The DogProfile class represents a dog's profile within the "Puppy Pal" application.
 * It stores essential details about a dog, including its ID, name, breed, age, weight, and height.
 * Each dog profile is associated with a unique identifier (dogID) to distinguish between
 * different dogs in the application. Users can create, update, and retrieve dog profiles
 * to keep track of their dogs.
 */

public class Dog {
    private int id;          // Unique identifier for each dog profile
    private String name;        // Dog's name
    private String breed;       // Dog's breed
    private double weight;      // Dog's weight in pounds
    private double height;      // Dog's height in centimeters

    // Constructor
    // REQUIRES:
    // MODIFIES:this
    // EFFECTS: construct
    public Dog(int id, String name, String breed, double weight, double height) {
        this.id = id;
        this.name = name;
        this.breed = breed;
        this.weight = weight;
        this.height = height;
    }

    public int getId() {
        return id;
    }

    public double getWeight() {
        return weight;
    }

    public double getHeight() {
        return height;
    }



    // Method to delete the dog profile
    public void deleteProfile() {
        // Implement logic to delete the profile (e.g., remove from database)
    }
}
