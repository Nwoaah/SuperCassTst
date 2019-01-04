import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Main {
    public static ArrayList<InputLineObject> data = new ArrayList<>();
    public static JsonArray output = new JsonArray();
    public static String inputPath = "src\\main\\resources\\input.json";
    public static String outputPath = "src\\output.json";
    public static final byte fullInputLineObject = 15;

    public static void main(String[] args)  {

        data = createArray(inputPath);
        Util.sort(data);
        doGluing(data);

        makeOutputFile();
    }

    // для наглядности вывода json как в примерах тестового, решил изменить стандартный вывод, но в целом, считаю, можно было воспользоваться одной строкой
    // file.write(output.toString());
    public static void makeOutputFile() {
        try (FileWriter file = new FileWriter(outputPath)) {
            file.write("[ \r");
            int i = 0;
            for (JsonElement element : output) {
                file.write(element.toString());
                if (i < output.size() - 1) {
                    file.write(", \r");
                }
                i++;
            }
            file.write("\r]");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    // point of entry in Gluing
    public static void doGluing(ArrayList<InputLineObject> array) {

        ExecutorService service = Executors.newFixedThreadPool(5);
        // >= 8 because of algorithm. u cant reach fullInputLineObject(15) if your first gluing object sum is less than 8
        for (int i = array.size() - 1; array.get(i).getSum() >= 8; i--) {
            int searchIndex = Util.BinarySearchRight(array, fullInputLineObject - array.get(i).getSum());
            int index = i;
            //Semaphore
            service.execute(new Runnable() {
                @Override
                public void run() {
                    gluingTogether(array, searchIndex - 1, array.get(index));

                }
            });

        }
        service.shutdown();
        try {
            service.awaitTermination(10, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    // this method creates a full LineObject with sum == 15 and add it to output, if there is no chance to make a full LineObject then method doing nothing
    public static void gluingTogether(ArrayList<InputLineObject> array, int rightIndex, InputLineObject inputLineObject) {

        if (inputLineObject.getSum() == fullInputLineObject) {
           synchronized (output) {
                output.add(inputLineObject.toJsonArray());
            }
        } else {
            for (int k = rightIndex; k >= 0; k--) {
                if ((inputLineObject.getSum() & array.get(k).getSum()) == 0) {
                    gluingTogether(array, Util.BinarySearchRight(array, fullInputLineObject - array.get(k).getSum() - 1), InputLineObject.add(inputLineObject, array.get(k)));
                }
            }
        }
    }

    // json parser. creates the arraylist of InputLineObjects, which will be used in gluing, self-sufficient(sum == fullInputLineObject) strings promptly goes to output.
    // if the string is full of nulls we won't use it(sum == 0)
    public static ArrayList<InputLineObject> createArray(String path) {
        JsonParser jsonParser = new JsonParser();
        ArrayList<InputLineObject> array = new ArrayList<>();
        try {
            JsonElement jsonElement = jsonParser.parse(new FileReader(path));
            JsonArray jsonArray = jsonElement.getAsJsonArray();
            for (JsonElement elem : jsonArray) {
                InputLineObject inputLineObject = new InputLineObject(elem.getAsJsonArray());
                if (inputLineObject.getSum() == fullInputLineObject) {
                    output.add(inputLineObject.toJsonArray());
                } else {
                    if (inputLineObject.getSum() != 0) {
                        array.add(inputLineObject);
                    }
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return array;
    }


}
