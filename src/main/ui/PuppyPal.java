package ui;

import java.awt.*;
import java.util.Scanner;
import java.util.List;
import java.io.FileNotFoundException;
import java.io.IOException;

import model.Dog;
import model.Dogs;
import model.HealthRecord;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;

// represents user interface for the application
public class PuppyPal extends JFrame {
    private static final String JSON_STORE = "./data/dogs.json";

    private Dogs dogs;
    private Scanner input;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    private static final int WIDTH = 700;
    private static final int HEIGHT = 800;

    private static final String INIT_GREETING = "Welcome to PuppyPal!";
    private JLabel greeting;
    private JPanel buttonRow;

    // EFFECTS: runs the PuppyPal application
    public PuppyPal() throws FileNotFoundException {
        super("PuppyPal");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setSize(WIDTH, HEIGHT);
        setLayout(null);

        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);

        placeGreeting();
        placeButtons();
        dogs = new Dogs();
        //runPuppyPal();

    }

    public void addAddButton() {
        JButton b1 = new JButton(ButtonNames.ADD.getValue());
        buttonRow = formatButtonRow(b1);
        b1.addActionListener(e -> {
            doAddDog();
        });
    }

    public void addViewButton() {
        JButton b2 = new JButton(ButtonNames.VIEW.getValue());
        buttonRow.add(b2);
        b2.addActionListener(e -> {
            doViewDogs();
        });
    }

    public void addRemoveButton() {
        JButton b3 = new JButton(ButtonNames.REMOVE.getValue());
        buttonRow.add(b3);
        b3.addActionListener(e -> {
            doRemoveDog();
        });
    }

    public void addAddHrButton() {
        JButton b4 = new JButton(ButtonNames.ADDHR.getValue());
        buttonRow.add(b4);
        b4.addActionListener(e -> {
            doAddHealthRecord();
        });
    }

    public void addViewHrButton() {
        JButton b5 = new JButton(ButtonNames.VIEWHR.getValue());
        buttonRow.add(b5);
        b5.addActionListener(e -> {
            doViewHealthRecords();
        });
    }

    public void addRemoveHrButton() {
        JButton b6 = new JButton(ButtonNames.REMOVEHR.getValue());
        buttonRow.add(b6);
        b6.addActionListener(e -> {
            doRemoveHealthRecord();
        });
    }

    public void addSaveButton() {
        JButton b7 = new JButton(ButtonNames.SAVE.getValue());
        buttonRow.add(b7);
        b7.addActionListener(e -> {
            doSaveDogs();
        });
    }

    public void addLoadButton() {
        JButton b8 = new JButton(ButtonNames.LOAD.getValue());
        buttonRow.add(b8);
        b8.addActionListener(e -> {
            doLoadDogs();
        });
    }

    public void addExitButton() {
        JButton b9 = new JButton(ButtonNames.EXIT.getValue());
        buttonRow.add(b9);
        b9.addActionListener(e -> {
            System.exit(1);
        });
    }

    public void placeButtons() {
        addAddButton();
        addViewButton();
        addRemoveButton();
        addAddHrButton();
        addViewHrButton();
        addRemoveHrButton();
        addSaveButton();
        addLoadButton();
        addExitButton();
        this.add(buttonRow);
    }


    //EFFECTS: creates and returns row with button included
    public JPanel formatButtonRow(JButton b) {
        JPanel p = new JPanel();
        p.setLayout(new GridLayout(3,3));
        p.setBounds(WIDTH / 2 - 300, HEIGHT / 4, 600, 300);
        p.add(b);

        return p;
    }

    //EFFECTS: creates greeting at top of console
    private void placeGreeting() {
        greeting = new JLabel(INIT_GREETING, JLabel.CENTER);
        greeting.setSize(WIDTH, HEIGHT / 3);
        this.add(greeting);
    }

    // MODIFIES: this
    // EFFECTS: add a dog to user's dogs
    private void doAddDog() {
        String name = JOptionPane.showInputDialog("What is the dog's name?: ");
        String breed = JOptionPane.showInputDialog("What is the dog's breed?: ");
        double weight = Double.parseDouble(JOptionPane.showInputDialog("What is the dog's weight?: "));
        double height = Double.parseDouble(JOptionPane.showInputDialog("What is the dog's height?: "));
        Dog dog = new Dog(name, breed, weight, height);
        dogs.addDog(dog);
        JOptionPane.showMessageDialog(null, "Welcome " + name + "!");

    }

    // MODIFIES: this
    // EFFECTS: return user's dogs
    private void doViewDogs() {
        if (dogs.getDogs().isEmpty()) {
            System.out.println("Oops, you don't have any dogs yet. Add or load the dog first!");
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
        int dogId = Integer.parseInt(JOptionPane.showInputDialog("Enter the ID of the dog you want to remove: "));
        //int dogId = input.nextInt();
        //input.nextLine();
        boolean foundDog = false;
        for (Dog d : dogs.getDogs()) {
            if (dogId == d.getId()) {
                dogs.removeDog(d);
                JOptionPane.showMessageDialog(null, "Dog removed successfully!");
                foundDog = true;
                break;
            }
        }
        if (!foundDog) {
            JOptionPane.showMessageDialog(null, "INVALID DOG ID!");
        }

        foundDog = false;
    }

    // MODIFIES: this
    // EFFECTS: add a health record to a dog
    private void doAddHealthRecord() {
        int dogId = Integer.parseInt(JOptionPane.showInputDialog("Add health record to which dog? ID: "));
        String type = JOptionPane.showInputDialog("Enter health record type:");
        String name = JOptionPane.showInputDialog("Enter health record name:");
        String date = JOptionPane.showInputDialog("Enter health record date(YYYY-MM-DD):");
        boolean foundDog = false;
        for (Dog d : dogs.getDogs()) {
            if (dogId == d.getId()) {
                HealthRecord healthRecord = new HealthRecord(type, name, date);
                d.addHealthRecord(healthRecord);
                foundDog = true;

                JOptionPane.showMessageDialog(null, "Health record added successfully!");
                break;
            }
        }

        if (!foundDog) {
            JOptionPane.showMessageDialog(null, "INVALID DOG ID!");
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
        int dogId = Integer.parseInt(JOptionPane.showInputDialog("Remove health record of which dog? ID: "));
        int healthId = Integer.parseInt(JOptionPane.showInputDialog("Enter Health ID that you want to remove:"));
        boolean foundHealth = false;
        for (Dog d : dogs.getDogs()) {
            if (dogId == d.getId()) {
                d.removeHealthRecord(healthId);
                foundHealth = true;
                break;
            }
        }

        if (!foundHealth) {
            JOptionPane.showMessageDialog(null, "REQUEST ERROR - INVALID ID!");
        }


    }

    // EFFECTS: saves the dogs to file
    private void doSaveDogs() {
        try {
            jsonWriter.open();
            jsonWriter.write(dogs);
            jsonWriter.close();
            JOptionPane.showMessageDialog(null, "Saved dogs to " + JSON_STORE, "Save dog",
                    JOptionPane.PLAIN_MESSAGE);
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(null, "Unable to write to file: " + JSON_STORE, "Save dog",
                    JOptionPane.PLAIN_MESSAGE);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads dogs from file
    private void doLoadDogs() {
        try {
            dogs = jsonReader.read();
            JOptionPane.showMessageDialog(null, "Loaded dogs from " + JSON_STORE);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Unable to read from file: " + JSON_STORE);
        }
    }


















//    // MODIFIES: this
//    // EFFECTS: initializes dogs
//    private void init() {
//        dogs = new Dogs();
//        input = new Scanner(System.in);
//        input.useDelimiter("\n");
//    }
//
//    // MODIFIES: this
//    // EFFECTS: processes user input
//    private void runPuppyPal() {
//        boolean keepGoing = true;
//        String command = "";
//
//        init();
//
//        while (keepGoing) {
//            displayMenu();
//            command = input.next();
//            command = command.toLowerCase();
//            input.nextLine();
//
//            if (command.equals("9")) {
//                keepGoing = false;
//            } else {
//                processCommand(command);
//            }
//        }
//
//        System.out.println("\nGoodbye woof woof!");
//    }
//
//    // MODIFIES: this
//    // EFFECTS: processes user command
//    private void processCommand(String command) {
//        if (command.equals("1")) {
//            doAddDog();
//        } else if (command.equals("2")) {
//            doViewDogs();
//        } else if (command.equals("3")) {
//            doRemoveDog();
//        } else if (command.equals("4")) {
//            doAddHealthRecord();
//        } else if (command.equals("5")) {
//            doViewHealthRecords();
//        } else if (command.equals("6")) {
//            doRemoveHealthRecord();
//        } else if (command.equals("7")) {
//            doSaveDogs();
//        } else if (command.equals("8")) {
//            doLoadDogs();
//        } else {
//            System.out.println("Selection not valid...");
//        }
//    }
//
//
//
//
//    // EFFECTS: displays menu of options to user
//    private void displayMenu() {
//        System.out.println("\nWelcome to PuppyPal! Please select an option: ");
//        System.out.println("\t1 -> Add a dog profile");
//        System.out.println("\t2 -> View my dogs");
//        System.out.println("\t3 -> Remove a dog profile");
//        System.out.println("\t4 -> Add health record");
//        System.out.println("\t5 -> View my dog's health record");
//        System.out.println("\t6 -> Remove my dog's health record");
//        System.out.println("\t7 -> Save my dogs to file");
//        System.out.println("\t8 -> Load my dogs from file");
//        System.out.println("\t9 -> Exit");
//    }


}
