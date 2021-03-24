import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        List<Data> dataList = DataLoader.loadData("IMPORT/iris_training.txt");
        List<Data> testList = DataLoader.loadData("IMPORT/iris_test.txt");

        System.out.print("Podaj k: ");
        int k = sc.nextInt();





    }


}
