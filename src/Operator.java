import java.util.*;
import java.util.stream.Collectors;

public class Operator {

    private final List<Data> dataList;
    private final List<Data> testList;
    int k;

    public Operator (int k, List<Data> dataList, List<Data> testList) {

        this.k = k;
        this.dataList = dataList;
        this.testList = testList;

        System.out.println(dataList.size());
        System.out.println(testList.size());
    }


    public void computeDistance () {

        testList.forEach(test -> {
            HashMap<Data,Double> distanceToOther = new HashMap<>();

            dataList.forEach(data -> distanceToOther.put(data,computeEuclideanDistance(test,data)));

            test.setDistanceToOther(distanceToOther);
        });
    }


    public void selectKClosest () {

        testList
                .forEach( test -> test.setKClosestDistance(
                test.getDistanceToOther()
                        .entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue())
                .limit(k)
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (oldValue,newValue) -> oldValue, LinkedHashMap::new)))
        );
    }


    private static Double computeEuclideanDistance (Data data1, Data data2) {

        double[] dimensions = new double[data1.getAttributes().length];

        for (int i = 0; i < dimensions.length; i++) {

            double data1Double = data1.getAttributes()[i];
            double data2Double = data2.getAttributes()[i];

            dimensions[i] = Math.pow(data1Double-data2Double,2);
        }

        return Math.sqrt(Arrays.stream(dimensions).sum());
    }
}
