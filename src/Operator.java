import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class Operator {

    private final List<Data> dataList;
    private final List<Data> testList;
    int k;

    public Operator (int k, List<Data> dataList, List<Data> testList) {

        this.k = k;
        this.dataList = dataList;
        this.testList = testList;
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


    public void classify() {

        int percent = 0;
        AtomicInteger positiveValues= new AtomicInteger();

        for (Data data : testList) {

            System.out.println(data);

            AtomicInteger tmp = new AtomicInteger();

            data.getKClosestDistance()
                    .forEach((key, value) -> {
                        if (key.getDecisiveAttribute().equals(data.getDecisiveAttribute())) {

                            tmp.getAndIncrement();
                            positiveValues.getAndIncrement();
                            System.out.println("[+] " + key + "| " + value);
                        } else {
                            System.out.println("[-] " + key + "| " + value);
                        }
                    });

            if (tmp.get() > k/2) {
                percent++;
                System.out.println("Classification: POSITIVE");
            } else {
                System.out.println("Classification: NEGATIVE");
            }
            System.out.println();
        }

        System.out.println("Positively classified examples: " + percent + "/" + testList.size());
        double accuracy = (double) positiveValues.get()/(testList.size()*k);

        BigDecimal tmp = new BigDecimal(accuracy*100);
        tmp = tmp.setScale(2, RoundingMode.HALF_UP);

        System.out.println("Accuracy: " + tmp + "%");
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
