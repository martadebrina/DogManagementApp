package ui;

import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.List;
import java.io.FileNotFoundException;
import java.io.IOException;
import model.Dog;
import model.Dogs;
import model.HealthRecord;
import persistence.JsonReader;
import persistence.JsonWriter;
import javax.swing.*;
import model.EventLog;
import model.Event;

// represents user interface for the application
public class PuppyPal extends JFrame implements WindowListener {
    private static final String JSON_STORE = "./data/dogs.json";
    private Dogs dogs;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;
    private static final int WIDTH = 400;
    private static final int HEIGHT = 800;
    private static final Color BACKGROUND_COLOR = new Color(0x6d5133);
    private JPanel buttonRow;
    private JPanel mainPanel;
    private JPanel dogPanel;
    private JPanel healthRecordPanel;
    private EventLog eventLog;

    // EFFECTS: runs the PuppyPal application
    public PuppyPal() throws FileNotFoundException {
        super("PuppyPal");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(WIDTH, HEIGHT);
        setLayout(null);
        setResizable(false);
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        initializePanels();
        showMainPanel();
        dogs = new Dogs();
        this.getContentPane().setBackground(BACKGROUND_COLOR);
        setVisible(true);
        addWindowListener(this);
    }

    // EFFECTS: initializes the main, dog, and health record panels
    public void initializePanels() {
        mainPanel = new JPanel();
        mainPanel.setLayout(null);
        mainPanel.setBounds(0, 0, WIDTH, HEIGHT);
        mainPanel.setBackground(BACKGROUND_COLOR);
        placeButtons();
        placeImage();
        add(mainPanel, "welcome");

        dogPanel = new JPanel();
        dogPanel.setLayout(null);
        dogPanel.setBounds(0, 0, WIDTH, HEIGHT);
        dogPanel.setBackground(BACKGROUND_COLOR);
        add(dogPanel, "dogs");

        healthRecordPanel = new JPanel();
        healthRecordPanel.setLayout(null);
        healthRecordPanel.setBounds(0, 0, WIDTH, HEIGHT);
        healthRecordPanel.setBackground(BACKGROUND_COLOR);
        add(healthRecordPanel, "healthRecords");
    }

    // EFFECTS: displays the main panel
    public void showMainPanel() {
        mainPanel.setVisible(true);
        dogPanel.setVisible(false);
        healthRecordPanel.setVisible(false);
    }

    // EFFECTS: displays the dog panel
    public void showDogPanel() {
        mainPanel.setVisible(false);
        dogPanel.setVisible(true);
        healthRecordPanel.setVisible(false);
    }

    // EFFECTS: displays the health record panel
    public void showHealthPanel() {
        mainPanel.setVisible(false);
        dogPanel.setVisible(false);
        healthRecordPanel.setVisible(true);
    }

    // EFFECTS: creates and places buttons on the main panel
    public void placeButtons() {
        buttonRow = new JPanel();
        addAddButton();
        addViewButton();
        addRemoveButton();
        addAddHrButton();
        addViewHrButton();
        addRemoveHrButton();
        addSaveButton();
        addLoadButton();
        addExitButton();
        mainPanel.add(buttonRow);
    }

    //EFFECTS: creates and returns row with button included
    public JPanel formatButtonRow(JButton b) {
        JPanel p = new JPanel();
        p.setLayout(new GridLayout(9, 1));
        p.setBounds(100, HEIGHT / 3 + 50, 200, 400);
        p.add(b);
        p.setBackground(BACKGROUND_COLOR);

        return p;
    }

    // MODIFIES: this
    // EFFECTS: adds a new dog to the list
    public void addAddButton() {
        JButton b1 = new JButton("Add Dog");
        buttonRow = formatButtonRow(b1);
        b1.addActionListener(e -> {
            doAddDog();
        });
    }

    // MODIFIES: this
    // EFFECTS: handles viewing the list of dogs
    public void addViewButton() {
        JButton b2 = new JButton("View My Dogs");
        buttonRow.add(b2);
        b2.addActionListener(e -> {
            doViewDogs();
        });
    }

    // MODIFIES: this
    // EFFECTS: handles removing a dog from the list
    public void addRemoveButton() {
        JButton b3 = new JButton("Remove Dog");
        buttonRow.add(b3);
        b3.addActionListener(e -> {
            doRemoveDog();
        });
    }

    // MODIFIES: this
    // EFFECTS: handles adding a health record to a dog
    public void addAddHrButton() {
        JButton b4 = new JButton("Add Health Record");
        buttonRow.add(b4);
        b4.addActionListener(e -> {
            doAddHealthRecord();
        });
    }

    // MODIFIES: this
    // EFFECTS: handles viewing health records for a specific dog
    public void addViewHrButton() {
        JButton b5 = new JButton("View Health Records");
        buttonRow.add(b5);
        b5.addActionListener(e -> {
            doViewHealthRecords();
        });
    }

    // MODIFIES: this
    // EFFECTS: handles removing a health record from a dog
    public void addRemoveHrButton() {
        JButton b6 = new JButton("Remove Health Record");
        buttonRow.add(b6);
        b6.addActionListener(e -> {
            doRemoveHealthRecord();
        });
    }

    // MODIFIES: this
    // EFFECTS: saves the list of dogs to a file
    public void addSaveButton() {
        JButton b7 = new JButton("Save");
        buttonRow.add(b7);
        b7.addActionListener(e -> {
            doSaveDogs();
        });
    }

    // MODIFIES: this
    // EFFECTS: loads the list of dogs from a file
    public void addLoadButton() {
        JButton b8 = new JButton("Load");
        buttonRow.add(b8);
        b8.addActionListener(e -> {
            doLoadDogs();
        });
    }

    // MODIFIES: this
    // EFFECTS: exits the application
    public void addExitButton() {
        JButton b9 = new JButton("Exit");
        buttonRow.add(b9);
        b9.addActionListener(e -> {
            printLoggedEvents();
            System.exit(1);
        });
    }


    // MODIFIES: this
    // EFFECTS: add a dog to user's dogs
    public void doAddDog() {
        String name = JOptionPane.showInputDialog("What is the dog's name?: ");
        String breed = JOptionPane.showInputDialog("What is the dog's breed?: ");
        double weight = Double.parseDouble(JOptionPane.showInputDialog("What is the dog's weight?: "));
        double height = Double.parseDouble(JOptionPane.showInputDialog("What is the dog's height?: "));
        Dog dog = new Dog(name, breed, weight, height);
        dogs.addDog(dog);
        JOptionPane.showMessageDialog(null, "Welcome " + name + "!");

    }

    // EFFECTS: handles viewing the list of dogs
    public void doViewDogs() {
        if (dogs.getDogs().isEmpty()) {
            JOptionPane.showMessageDialog(null,
                    "Oops, you don't have any dogs yet. Add or load the dog first!");
        } else {
            // Create the text area for the dogs
            JTextArea dogsTextArea = new JTextArea();
            dogsTextArea.setEditable(false);

            // Fill the text area with the list of dogs
            for (Dog d : dogs.getDogs()) {
                dogsTextArea.append(String.format("Dog's ID: %d, Name: %s, Breed: %s, Weight: %.2fkg, Height: %.2fcm\n",
                        d.getId(), d.getDogName(), d.getBreed(), d.getWeight(), d.getHeight()));
            }

            // Create a scroll pane and add the text area to it
            JScrollPane scrollPane = new JScrollPane(dogsTextArea);
            scrollPane.setBounds(20, 20, 350, 500);

            dogPanel.removeAll();

            // Add the scroll pane to the dog panel
            dogPanel.add(scrollPane);
            addBackButton(dogPanel);

            dogPanel.revalidate();
            dogPanel.repaint();

            showDogPanel();
        }
    }

    // MODIFIES: this
    // EFFECTS: remove a dog from user's dogs
    public void doRemoveDog() {
        int dogId = Integer.parseInt(JOptionPane.showInputDialog("Enter the ID of the dog you want to remove: "));
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
    public void doAddHealthRecord() {
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

    // EFFECTS: handles viewing health records for a specific dog
    public void doViewHealthRecords() {
        int dogId = Integer.parseInt(JOptionPane.showInputDialog("View health record of which dog? ID: "));
        StringBuilder healthRecordList = new StringBuilder();

        Dog selectedDog = findDogById(dogId);
        if (selectedDog == null) {
            JOptionPane.showMessageDialog(null, "INVALID DOG ID!");
        } else {
            List<HealthRecord> healthRecords = selectedDog.getHealthRecords();
            if (healthRecords.isEmpty()) {
                healthRecordList.append("There are no health records for this dog.");
            } else {
                appendHealthRecord(selectedDog, healthRecordList);
            }
            healthRecordPanel.removeAll();


            JTextArea textArea = new JTextArea(healthRecordList.toString());
            textArea.setEditable(false);

            JScrollPane scrollPane = new JScrollPane(textArea);
            scrollPane.setBounds(20, 20, 350, 500);
            healthRecordPanel.add(scrollPane);
            addBackButton(healthRecordPanel);

            showHealthPanel();
        }
    }


    // MODIFIES: this
    // EFFECTS: remove a health record of a dog
    public void doRemoveHealthRecord() {
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
    public void doSaveDogs() {
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
    public void doLoadDogs() {
        try {
            this.dogs = jsonReader.read();
            JOptionPane.showMessageDialog(null, "Loaded dogs from " + JSON_STORE);
            System.out.println(dogs);
            System.out.println(dogs.getDogs().size());
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Unable to read from file: " + JSON_STORE);
        }
    }


    //EFFECTS: creates image logo at top of console
    private void placeImage() {
        ImageIcon image = new ImageIcon(getClass().getResource("logo.png"));
        JLabel displayField = new JLabel(image, JLabel.CENTER);
        displayField.setBounds(5, 50, 400, 250);
        mainPanel.add(displayField);
    }

    // REQUIRES: panel p is not null
    // MODIFIES: this
    // EFFECTS: adds a back button to the specified panel
    private void addBackButton(JPanel p) {
        JButton goBackButton = new JButton("BACK");
        goBackButton.addActionListener(e -> showMainPanel());
        p.add(goBackButton);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(goBackButton);
        buttonPanel.setBounds(130, 550, 100, 50);
        buttonPanel.setBackground(BACKGROUND_COLOR);
        p.add(buttonPanel);
    }


    // MODIFIES: stringBuilder
    // EFFECTS: appends health records to the specified string builder
    private void appendHealthRecord(Dog d, StringBuilder stringBuilder) {
        stringBuilder.append(String.format("Health records for %s (Dog ID: %d):\n",
                d.getDogName(), d.getId()));
        for (HealthRecord h : d.getHealthRecords()) {
            stringBuilder.append(String.format("Health ID: %d, Type: %s, Name: %s, Date: %s\n",
                    h.getHealthId(), h.getHealthRecordType(), h.getHealthRecordName(), h.getHealthRecordDate()));
        }
    }

    // EFFECTS: finds a dog by its ID
    private Dog findDogById(int id) {
        for (Dog d : dogs.getDogs()) {
            if (id == d.getId()) {
                return d;
            }
        }
        return null;
    }

    // EFFECTS: prints all logged events to the console
    private void printLoggedEvents() {
        System.out.println("Logged Events:");
        eventLog = EventLog.getInstance();
        for (Event event : eventLog) {
            System.out.println(event.getDate() + " -- " + event.getDescription());
        }
    }


    @Override
    public void windowOpened(WindowEvent e) {

    }

    @Override
    public void windowClosing(WindowEvent e) {
        printLoggedEvents();
    }

    @Override
    public void windowClosed(WindowEvent e) {

    }

    @Override
    public void windowIconified(WindowEvent e) {

    }

    @Override
    public void windowDeiconified(WindowEvent e) {

    }

    @Override
    public void windowActivated(WindowEvent e) {

    }

    @Override
    public void windowDeactivated(WindowEvent e) {

    }
}











