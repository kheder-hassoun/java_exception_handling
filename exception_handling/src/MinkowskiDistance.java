import java.util.List;

public class MinkowskiDistance implements ISimilarity{

    private int degree;

    public MinkowskiDistance(int k){
        degree = k;
    }

    @Override
    public Double getSimilarity(List<Double> first_patient, List<Double> second_patient) {
        double distance = 0;
        for (int index = 0; index < second_patient.size(); index++) {
            distance += Math.pow(Math.abs(first_patient.get(index) - second_patient.get(index)), degree);
        }
        distance=Math.pow(distance, 1.0 / degree);

        return distance;

    }
}
