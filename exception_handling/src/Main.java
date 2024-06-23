import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        ReadFile fileReader = new ReadFile(new File("data.csv"));
       // fileReader.print();
        List<Double>infrormationOfPatient=new ArrayList<Double>();

        Scanner scanner = new Scanner(System.in);

        System.out.println("enter the value of Suger (must be double) : ");
        double value=scanner.nextDouble();
        infrormationOfPatient.add(value);

        System.out.println("enter the value of Pressure (must be double) : ");
        double valueOfPressure=scanner.nextDouble();
        infrormationOfPatient.add(valueOfPressure);

        System.out.println("enter the value of Iron  (must be double) : ");
        double valueOfIron=scanner.nextDouble();
        infrormationOfPatient.add(valueOfIron);

        System.out.println("enter the value of Liver (must be double) : ");
        double valueOfLiver=scanner.nextDouble();
        infrormationOfPatient.add(valueOfLiver);

        System.out.println("enter the value of Pigment (must be double) : ");
        double valueOfPigment=scanner.nextDouble();
        infrormationOfPatient.add(valueOfPigment);

        System.out.println("enter the value of Vitamin_D (must be double) : ");
        double valueOfVitamin_D=scanner.nextDouble();
        infrormationOfPatient.add(valueOfVitamin_D);

        System.out.println("enter the value of Leukocyte (must be double) : ");
        double valueOfLeukocyte=scanner.nextDouble();
        infrormationOfPatient.add(valueOfLeukocyte);

        System.out.println("input the number of the way you want to predict by:");
        System.out.println("1_ Manhattan distance");
        System.out.println("2_ MinkowskiDistance");
        System.out.println("3_ EuclideanDistance");

        int theNumberOfTheWay;
        Scanner scanner0 = new Scanner(System.in);
        theNumberOfTheWay = scanner0.nextInt();

        System.out.println("input k");
        int k;
        Scanner scanner2 = new Scanner(System.in);
        k = scanner2.nextInt();


        if (theNumberOfTheWay == 1) {

            ManhattanDistance manhattanDistance = new ManhattanDistance();
            GetExpectation y=new GetExpectation(manhattanDistance,k);
            try {
                // because this function throw an exception (class we already build DisallowedValueOfK )
                y.GetExpectation2(manhattanDistance,k,infrormationOfPatient);

            } catch (DisallowedValueOfK e) {
                // handel our exception
                System.out.println("incorrect value of k  " + e.getMessage());
            }
        }

        if (theNumberOfTheWay == 2) {

            EuclideanDistance euclideanDistance = new EuclideanDistance();
            GetExpectation y=new GetExpectation(euclideanDistance,k);
            try {
                y.GetExpectation2(euclideanDistance,k,infrormationOfPatient);

            } catch (DisallowedValueOfK e) {
                System.out.println("incorrect value of k  " + e.getMessage());
            }
        }

        if (theNumberOfTheWay == 3) {

            MinkowskiDistance minkowskiDistance = new MinkowskiDistance(4);
            GetExpectation y=new GetExpectation(minkowskiDistance,k);
            try {
                y.GetExpectation2(minkowskiDistance,k,infrormationOfPatient);

            } catch (DisallowedValueOfK e) {
                System.out.println("incorrect value of k  " + e.getMessage());
            }
        }
    }
}