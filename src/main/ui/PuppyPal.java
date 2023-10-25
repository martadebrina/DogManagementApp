package ui;

import java.util.Scanner;
import java.util.List;
import java.io.FileNotFoundException;
import java.io.IOException;

import model.Dog;
import model.Dogs;
import model.HealthRecord;
import persistence.JsonReader;
import persistence.JsonWriter;

// represents user interface for the application
public class PuppyPal {
    private static final String JSON_STORE = "./data/dogs.json";
    private Dogs dogs;
    private Scanner input;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    // EFFECTS: runs the PuppyPal application
    public PuppyPal() throws FileNotFoundException {
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        runPuppyPal();
    }

    // MODIFIES: this
    // EFFECTS: processes user input
    private void runPuppyPal() {
        boolean keepGoing = true;
        String command = "";

        init();

        while (keepGoing) {
            displayMenu();
            command = input.next();
            command = command.toLowerCase();
            input.nextLine();

            if (command.equals("9")) {
                keepGoing = false;
            } else {
                processCommand(command);
            }
        }

        System.out.println("\nGoodbye woof woof!");
    }

    // MODIFIES: this
    // EFFECTS: processes user command
    private void processCommand(String command) {
        if (command.equals("1")) {
            doAddDog();
        } else if (command.equals("2")) {
            doViewDogs();
        } else if (command.equals("3")) {
            doRemoveDog();
        } else if (command.equals("4")) {
            doAddHealthRecord();
        } else if (command.equals("5")) {
            doViewHealthRecords();
        } else if (command.equals("6")) {
            doRemoveHealthRecord();
        } else if (command.equals("7")) {
            doSaveDogs();
        } else if (command.equals("8")) {
            doLoadDogs();
        } else {
            System.out.println("Selection not valid...");
        }
    }


    // MODIFIES: this
    // EFFECTS: initializes dogs
    private void init() {
        dogs = new Dogs();
        input = new Scanner(System.in);
        input.useDelimiter("\n");
    }


    // EFFECTS: displays menu of options to user
    private void displayMenu() {
        System.out.println("\nWelcome to PuppyPal! Please select an option: ");
        System.out.println("\t1 -> Add a dog profile");
        System.out.println("\t2 -> View my dogs");
        System.out.println("\t3 -> Remove a dog profile");
        System.out.println("\t4 -> Add health record");
        System.out.println("\t5 -> View my dog's health record");
        System.out.println("\t6 -> Remove my dog's health record");
        System.out.println("\t7 -> Save my dogs to file");
        System.out.println("\t8 -> Load my dogs from file");
        System.out.println("\t9 -> Exit");
    }


    // MODIFIES: this
    // EFFECTS: add a dog to user's dogs
    private void doAddDog() {
        System.out.println("Please add details of your dog:");
        System.out.println("Name: ");
        String name = input.nextLine();
        System.out.println("Breed: ");
        String breed = input.nextLine();
        System.out.println("Weight: ");
        double weight = input.nextDouble();
        System.out.println("Height: ");
        double height = input.nextDouble();

        Dog dog = new Dog(name, breed, weight, height);
        dogs.addDog(dog);

        System.out.println("\nWelcome " + name + "!");

    }

    // MODIFIES: this
    // EFFECTS: return user's dogs
    private void doViewDogs() {
        if (dogs.getDogs().isEmpty()) {
            System.out.println("Oops, you don't have any dogs yet. Add the dog first!");
        } else {
            System.out.println("List of your dogs:");
            for (Dog d : dogs.getDogs()) {
                System.out.println("Dog's ID: " + d.getId() + ", Name: " + d.getDogName() + ", Breed: " + d.getBreed()
                        + ", Weight: " + d.getWeight() + "kg, Height: " + d.getHeight() + "cm");
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: remove a dog from user's dogs
    private void doRemoveDog() {
        System.out.println("Which dog that you want to remove? ");
        System.out.println("Enter dog's ID: ");
        int dogId = input.nextInt();
        input.nextLine();
        boolean foundDog = false;
        for (Dog d : dogs.getDogs()) {
            if (dogId == d.getId()) {
                dogs.removeDog(d);
                System.out.println("Dog removed successfully!");
                foundDog = true;
                break;
            }
        }
        if (!foundDog) {
            System.out.println("INVALID DOG ID!");
        }

        foundDog = false;
    }

    // MODIFIES: this
    // EFFECTS: add a health record to a dog
    private void doAddHealthRecord() {
        System.out.println("Add health record to which dog?");
        System.out.println("ID: ");
        int dogId = input.nextInt();
        input.nextLine();
        System.out.println("Enter health record type:");
        String type = input.nextLine();
        System.out.println("Enter health record name:");
        String name = input.nextLine();
        System.out.println("Enter health record date(YYYY-MM-DD):");
        String date = input.nextLine();
        boolean foundDog = false;
        for (Dog d : dogs.getDogs()) {
            if (dogId == d.getId()) {
                HealthRecord healthRecord = new HealthRecord(type, name, date);
                d.addHealthRecord(healthRecord);
                foundDog = true;

                System.out.println("Health record added successfully!");
                break;
            }
        }

        if (!foundDog) {
            System.out.printf("INVALID DOG ID!");
        }

    }

    // MODIFIES: this
    // EFFECTS: return the health records of a dog
    private void doViewHealthRecords() {
        System.out.println("View health record of which dog?");
        System.out.println("ID:");
        int dogId = input.nextInt();
        input.nextLine();
        boolean foundDog = false;
        for (Dog d : dogs.getDogs()) {
            if (dogId == d.getId()) {
                List<HealthRecord> healthRecords = d.getHealthRecords();
                if (healthRecords.isEmpty()) {
                    System.out.println("There is no health records for this dog.");
                } else {
                    for (HealthRecord h : healthRecords) {
                        System.out.println("Health ID:" + h.getHealthId() + ", Type:" + h.getHealthRecordType()
                                + ", Name:" + h.getHealthRecordName() + ", Date:" + h.getHealthRecordDate());
                    }
                }
                foundDog = true;
            }
        }
        if (!foundDog) {
            System.out.println("INVALID DOG ID!");
        }

    }

    // MODIFIES: this
    // EFFECTS: remove a health record of a dog
    private void doRemoveHealthRecord() {
        System.out.println("Remove health record of which dog?");
        System.out.println("ID:");
        int dogId = input.nextInt();
        input.nextLine();
        System.out.println("Enter Health ID of health record that you want to remove:");
        int healthId = input.nextInt();
        input.nextLine();
        boolean foundHealth = false;
        for (Dog d : dogs.getDogs()) {
            if (dogId == d.getId()) {
                d.removeHealthRecord(healthId);
                foundHealth = true;
                break;
            }
        }

        if (!foundHealth) {
            System.out.println("REQUEST ERROR - INVALID ID!");
        }


    }

    // EFFECTS: saves the dogs to file
    private void doSaveDogs() {
        try {
            jsonWriter.open();
            jsonWriter.write(dogs);
            jsonWriter.close();
            System.out.println("Saved dogs to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads dogs from file
    private void doLoadDogs() {
        try {
            dogs = jsonReader.read();
            System.out.println("Loaded dogs from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }


}
