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
            System.out.println("Do you want to enter K again? (Y/N)");
            if (sc.next().equals("N")) {
                pass = false;
            }

        }

    }
}
