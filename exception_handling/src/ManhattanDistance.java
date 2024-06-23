import java.util.List;

public class ManhattanDistance implements ISimilarity {
    @Override
    public Double getSimilarity(List<Double> first_patient, List<Double> second_patient) {
        double distance = 0;
        for (int index = 0; index < second_patient.size(); index++) {
            distance += Math.abs(first_patient.get(index) - second_patient.get(index));
        }
        return distance;
    }
}
