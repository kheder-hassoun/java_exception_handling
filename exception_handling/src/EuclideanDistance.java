import java.util.ArrayList;
import java.util.List;

public class EuclideanDistance implements ISimilarity{
    @Override
    public Double getSimilarity(List<Double> first_patient, List<Double> second_patient) {

        double distance = 0;

        for (int index = 0; index < second_patient.size(); index++) {
            double difference=first_patient.get(index)-second_patient.get(index);
            distance += Math.pow(difference, 2);
        }
        distance=Math.sqrt(distance);
        return distance;
    }
}
