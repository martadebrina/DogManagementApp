package model;


import java.time.LocalDateTime;

public class Reminder {
    private int reminderID;             // Unique identifier for each reminder
    private int userID;                 // ID of the user associated with the reminder
    private int dogID;                  // ID of the dog associated with the reminder
    //private ReminderType type;        // Type of reminder (e.g., vaccination, medication)
    private String description;         // Description or details of the reminder
    private LocalDateTime dateTime;     // Date and time when the reminder should be triggered

    public Reminder(int reminderID, int userID, int dogID, //ReminderType type,
                    String description, LocalDateTime dateTime) {
        this.reminderID = reminderID;
        this.userID = userID;
        this.dogID = dogID;
        //this.type = type;
        this.description = description;
        this.dateTime = dateTime;
    }

    // Getter and setter methods for attributes

    // Method to trigger the reminder (e.g., send a notification to the user)
    public void trigger() {
        // Implement logic to trigger the reminder action (e.g., send a notification)
    }

    // Additional methods for data validation, if needed
}
