//import com.sun.xml.internal.ws.api.model.wsdl.WSDLOutput;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.io.File;
public class GetExpectation {
    private int k;
    private ISimilarity measurement;

    public void setK(int k) throws DisallowedValueOfK {
        if ((k > 0) && (k % 2 == 1)) this.k = k;
        else {
            throw new DisallowedValueOfK();
        }
    }

    public void setMeasurement(ISimilarity measurement) {
        this.measurement = measurement;
    }

    public GetExpectation(ISimilarity measurement, int k) {
        setMeasurement(measurement);
        try {
            // because this function throw an exception (class we already build DisallowedValueOfK )
            setK(k);
        } catch (DisallowedValueOfK e) {
            // handel our exception
            System.out.println(e.getMessage());
        }

    }


    public void GetExpectation2(ISimilarity measurement, int k, List<Double> patient) throws DisallowedValueOfK {

        ReadFile fileReader = new ReadFile(new File("data.csv"));

        //first let's check the value of k if it's ok or not
        if ((k < 0) || (k % 2 == 0) || (k > fileReader.rows.size() - 1)) {
            throw new DisallowedValueOfK();
        }

        List<List<Double>> resultOfTests1 = new ArrayList<>();
        int length = 0;
        for (List<Double> subList : fileReader.getResultOfTests()) {
            length = subList.size();
            List<Double> copiedSubList = new ArrayList<>();
            copiedSubList.addAll(subList);
            resultOfTests1.add(copiedSubList);
        }


        int i = 0;
        for (List<Double> sublist : resultOfTests1) {
            if (fileReader.getStatusOfPatients().get(i).equals("High") )
                sublist.add(1.0);
            else sublist.add(0.0);

            double t = measurement.getSimilarity(sublist, patient);
            sublist.add(t);
            i++;
        }


        Collections.sort(resultOfTests1, new Comparator<List<Double>>() {
            @Override
            public int compare(List<Double> o1, List<Double> o2) {
                Double lastElement1 = o1.get(o1.size() - 1);
                Double lastElement2 = o2.get(o2.size() - 1);
                return lastElement1.compareTo(lastElement2);
            }
        });

        int numberOfLow = 0;
        int numberOfHigh = 0;
        int j = 0;
        for (List<Double> sublist : resultOfTests1) {
            if (sublist.get(sublist.size() - 2).equals(0.0)) {
                numberOfLow++;
            } else numberOfHigh++;
            j++;
            if (j == k) break;
        }

        if (numberOfLow > numberOfHigh) {
            System.out.println("Low");
        } else System.out.println("High");

    }
}
