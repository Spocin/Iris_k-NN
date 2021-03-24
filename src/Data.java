
public class Data {

    private final double[] attributes;
    private final String decisiveAttribute;

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
}
