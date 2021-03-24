import java.io.File;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class DataLoader {

    public static List<Data> loadData (String path) {
        File file = new File(path);

        try {
            if (file.exists() && !file.isDirectory()) {

                List<Data> resultList = new ArrayList<>();

                List<String> lines = Files.readAllLines(file.toPath());

                lines.forEach( line -> {
                    line = line.replaceAll(",",".");
                    line = line.trim();
                    resultList.add(new Data(line.split("\\s+")));
                });

                return resultList;
            } else {
                throw new Exception("File doesnt exsist or is a directory");
            }
        } catch (Exception e) {
            e.printStackTrace();

            return null;
        }
    }
}
