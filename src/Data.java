import java.util.HashMap;
import java.util.Map;

public class Data {

    private Map<String,Double> attributes;
    private String decisiveAttribute;

    public Data (String[] stringData) {

        this.decisiveAttribute = stringData[stringData.length-1];
        attributes = new HashMap<>();

        for (int i = 0; i < stringData.length-1; i++) {

            String key = "x" + (i + 1);
            attributes.put(key,Double.parseDouble(stringData[i]));
        }
    }

}
