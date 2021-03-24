import java.util.List;

public class Main {

    public static void main(String[] args) {

        List<Data> dataList = TrainingDataLoader.loadData("IMPORT/iris_training.txt");

        System.out.println(dataList);

    }
}
