package model;

import model.healthrecords.Vaccination;
import model.healthrecords.Medication;
import java.util.List;

public class HealthRecord {
    private List<Medication> medications;
    private List<Vaccination> vaccinations;

    // Constructor
    public HealthRecord(List<Medication> medications, List<Vaccination> vaccinations) {
        this.medications = medications;
        this.vaccinations = vaccinations;
    }

    public List<Medication> getMedications() {
        return medications;
    }

    public List<Vaccination> getVaccinations() {
        return vaccinations;
    }

    public void addMedication(Medication medication) {
        medications.add(medication);
    }

    public void addVaccination(Vaccination vaccination) {
        vaccinations.add(vaccination);
    }
}
