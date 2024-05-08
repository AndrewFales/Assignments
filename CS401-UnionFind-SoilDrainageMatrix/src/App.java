public class App {
    public static void main(String[] args) throws Exception {
        String pathToFile = "C:/SoilDrainage/src/sample3.txt";
        Soil soil = new Soil(pathToFile);
        if (soil.doesDrain()) {
            System.out.println("Allows water to drain");
        } else {
            System.out.println("Doesn't allow water to drain");
        }
    }
}

