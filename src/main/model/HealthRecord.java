package model;

// represent an health record that have type, name, and date
public class HealthRecord {
    private int healthId;                   // Unique identifier for each health record
    private static int nextHealthId = 1;
    private String healthRecordName;
    private String healthRecordType;
    private String healthRecordDate;

    //constructor
    // MODIFIES:this
    // EFFECTS: construct a health record with a given type, name, and date,
    //          health id increased everytime a health record constructed so not assigned to any other health record
    public HealthRecord(String healthRecordType, String scheduleName, String healthRecordDate) {
        this.healthId = nextHealthId++;
        this.healthRecordType = healthRecordType;
        this.healthRecordName = scheduleName;
        this.healthRecordDate = healthRecordDate;
    }

    //getters
    public String getHealthRecordName() {
        return healthRecordName;
    }

    public String getHealthRecordDate() {
        return healthRecordDate;
    }

    public String getHealthRecordType() {
        return healthRecordType;
    }

    public int getHealthId() {
        return healthId;
    }

    public static void resetHealthId() {
        nextHealthId = 1;
    }

}
