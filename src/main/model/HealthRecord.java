package model;

import java.time.LocalDate;

public class HealthRecord {
    private String healthRecordName;
    private String healthRecordType;
    private LocalDate healthRecordDate;

    public HealthRecord(String healthRecordType, String scheduleName, LocalDate healthRecordDate) {
        this.healthRecordType = healthRecordType;
        this.healthRecordName = scheduleName;
        this.healthRecordDate = healthRecordDate;
    }

    public String getHealthRecordName() {
        return healthRecordName;
    }

    public LocalDate getHealthRecordDate() {
        return healthRecordDate;
    }

    public String getHealthRecordType() {
        return healthRecordType;
    }

    public void setHealthRecordName(String healthRecordName) {
        this.healthRecordName = healthRecordName;
    }

    public void setHealthRecordDate(LocalDate healthRecordDate) {
        this.healthRecordDate = healthRecordDate;
    }
}
