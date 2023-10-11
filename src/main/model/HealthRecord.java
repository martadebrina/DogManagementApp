package model;

import java.time.LocalDate;

public class HealthRecord {
    private int healthId;             // Unique identifier for each dog profile
    private static int nextHealthId = 1;             // Unique identifier for each dog profile
    private String healthRecordName;
    private String healthRecordType;
    private LocalDate healthRecordDate;

    //constructor
    public HealthRecord(String healthRecordType, String scheduleName, LocalDate healthRecordDate) {
        this.healthId = nextHealthId++;
        this.healthRecordType = healthRecordType;
        this.healthRecordName = scheduleName;
        this.healthRecordDate = healthRecordDate;
    }

    //getters

    public String getHealthRecordName() {
        return healthRecordName;
    }

    public LocalDate getHealthRecordDate() {
        return healthRecordDate;
    }

    public String getHealthRecordType() {
        return healthRecordType;
    }

    public int getHealthId() {
        return healthId;
    }

    public void resetHealthId() {
        nextHealthId = 1;
    }

}
