import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        boolean pass = true;

        String trainingPath;
        String testPath;

        System.out.print("Enter path for training data set: ");
        trainingPath = sc.nextLine();
        System.out.println();

        System.out.print("Enter path for test data set: ");
        testPath = sc.nextLine();
        System.out.println();

        List<Data> dataList = DataLoader.loadData(trainingPath);
        List<Data> testList = DataLoader.loadData(testPath);

        while (pass) {

            System.out.println("Do you want to classify all? Or input your own flower for classification? (Y/N/MY)");

            switch (sc.next()) {

                case "Y":
                    classifyForAll(dataList,testList);
                    break;

                case "N":
                    pass = false;
                    break;

                case "MY":
                    System.out.println();
                    classifyForOne(testList,dataList);
                    break;
            }

            System.out.println();
        }
    }

    private static void classifyForAll(List<Data> dataList,List<Data> testList) {

        System.out.print("Enter K: ");
        int k = sc.nextInt();
        System.out.println();

        Operator operator = new Operator(k, dataList, testList);
        operator.computeDistance();
        operator.selectKClosest();
        operator.classify();

        System.out.println();
    }

    private static void classifyForOne (List<Data> testList,List<Data> dataList) {
        List<Data> inputList = new ArrayList<>();

        //Creates array with the same dimensions as test set + 1 for classificator
        String[] tmp = new String[testList.get(0).getAttributes().length+1];

        System.out.print("Enter K: ");
        int k = sc.nextInt();

        for (int i = 0; i < tmp.length; i++) {
            if (i < tmp.length-1) {
                System.out.print("Enter " + "X" + i + ": ");
            } else {
                System.out.print("Enter classificator: ");
            }
            tmp[i] = sc.next();
        }
        System.out.println();

        inputList.add(new Data(tmp));

        Operator op = new Operator(k,dataList,inputList);
        op.computeDistance();
        op.selectKClosest();
        op.classify();

        System.out.println();
    }
}
