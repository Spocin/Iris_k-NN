import java.util.Arrays;

public class Operator {

    public static Double computeEuclideanDistance (Data data1, Data data2) {

        double[] dimensions = new double[data1.getAttributes().length];

        for (int i = 0; i < dimensions.length; i++) {

            double data1Double = data1.getAttributes()[i];
            double data2Double = data2.getAttributes()[i];

            dimensions[i] = Math.pow(data1Double-data2Double,2);
        }

        return Math.sqrt(Arrays.stream(dimensions).sum());
    }
}
