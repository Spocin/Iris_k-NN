import java.util.HashMap;
import java.util.LinkedHashMap;

public class Data {

    private final double[] attributes;
    private final String decisiveAttribute;

    private HashMap<Data,Double> distanceToOther;
    private LinkedHashMap<Data,Double> kClosestDistance;

    public Data (String[] stringData) {

        this.decisiveAttribute = stringData[stringData.length-1];
        this.attributes = new double[stringData.length-1];

        for (int i = 0; i < stringData.length-1; i++) {
            attributes[i] = Double.parseDouble(stringData[i]);
        }
    }

    @Override
    public String toString() {

        StringBuilder tmp = new StringBuilder();
        for (Double attribute : attributes) {
            tmp.append(attribute).append(" ");
        }

        return decisiveAttribute + ": " + tmp.toString() + "\n";
    }

    public double[] getAttributes() {
        return attributes;
    }

    public String getDecisiveAttribute() {
        return decisiveAttribute;
    }

    public HashMap<Data, Double> getDistanceToOther() {
        return distanceToOther;
    }

    public void setDistanceToOther(HashMap<Data, Double> distanceToOther) {
        this.distanceToOther = distanceToOther;
    }

    public HashMap<Data, Double> getkClosestDistance() {
        return kClosestDistance;
    }

    public void setKClosestDistance(LinkedHashMap<Data, Double> kClosestDistance) {
        this.kClosestDistance = kClosestDistance;
    }
}
