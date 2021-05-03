import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        boolean pass = true;

        while (pass) {
            Scanner sc = new Scanner(System.in);

            List<Data> dataList = DataLoader.loadData("IMPORT/iris_training.txt");
            List<Data> testList = DataLoader.loadData("IMPORT/iris_test.txt");

            System.out.print("Enter K: ");
            int k = sc.nextInt();
            System.out.println();

            Operator operator = new Operator(k, dataList, testList);
            operator.computeDistance();
            operator.selectKClosest();
            operator.classify();

            System.out.println();
            System.out.println("Do you want to enter K again? Or input your own flower for classification? (Y/N/MY)");

            switch (sc.next()) {
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

    private static void classifyForOne (List<Data> testList,List<Data> dataList) {
        Scanner sc = new Scanner(System.in);
        List<Data> inputList = new ArrayList<>();

        String[] tmp = new String[testList.get(0).getAttributes().length+1];

        System.out.print("Podaj wartosc k: ");
        int k = sc.nextInt();

        for (int i = 0; i < tmp.length; i++) {
            if (i < tmp.length-1) {
                System.out.print("Podaj wartosc " + "x" + i + ": ");
            } else {
                System.out.print("Podaj klasyfikator: ");
            }
            tmp[i] = sc.next();
        }

        inputList.add(new Data(tmp));

        Operator op = new Operator(k,dataList,inputList);
        op.computeDistance();
        op.selectKClosest();
        op.classify();
    }
}
