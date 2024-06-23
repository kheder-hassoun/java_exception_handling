
import java.util.*;

public class Patient {
    protected List<Double> medicalTestResults = new ArrayList<>();
    protected String status;


    public Patient(List<Double> medicalTestResults, String status) {
        this.medicalTestResults = medicalTestResults;
        this.status = status;
    }
    public Patient(List<Double> medicalTestResults) {
        this.medicalTestResults = medicalTestResults;
    }


    public void setMedicalTestResults(List<Double> medicalTestResults) {
        this.medicalTestResults = medicalTestResults;
    }
    public List<Double> getMedicalTestResults() {
        return medicalTestResults;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    public String getStatus() {
        return status;
    }


    @Override
    public String toString() {
        return medicalTestResults + "," + status;
    }
}
