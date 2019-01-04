import com.google.gson.JsonArray;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/* Class for the input strings, each instance for each line in json.
   Field sum represents the sum of including elements.
   For example if we have a self-sufficient line ["a1","a2","a3","a4"] it means that we have a binary code 1 1 1 1 in decimal sum will be 15
   [ "b1",   null,   null,   "b4"   ]  -> 1 0 0 1 - >  sum will be 9
*/
@Getter
@Setter
@AllArgsConstructor
public class InputLineObject {

    private String first;
    private String second;
    private String third;
    private String fourth;
    private byte sum;

    InputLineObject(JsonArray json) {
        this.sum = 0;
        if (!json.get(0).isJsonNull()) {
            this.first = json.get(0).getAsString();
            sum += 8;
        }
        if (!json.get(1).isJsonNull()) {
            this.second = json.get(1).getAsString();
            sum += 4;
        }
        if (!json.get(2).isJsonNull()) {
            this.third = json.get(2).getAsString();
            sum += 2;
        }
        if (!json.get(3).isJsonNull()) {
            this.fourth = json.get(3).getAsString();
            sum += 1;
        }
    }

    // makes the new InpLineObj that becomes from sum of the 2 arguments
    static InputLineObject add(InputLineObject o1, InputLineObject o2) {
        String tmp1;
        String tmp2;
        String tmp3;
        String tmp4;
        if (o1.first == null) {
            tmp1 = o2.first;
        } else {
            tmp1 = o1.first;
        }
        if (o1.second == null) {
            tmp2 = o2.second;
        } else {
            tmp2 = o1.second;
        }
        if (o1.third == null) {
            tmp3 = o2.third;
        } else {
            tmp3 = o1.third;
        }
        if (o1.fourth == null) {
            tmp4 = o2.fourth;
        } else {
            tmp4 = o1.fourth;
        }
        return new InputLineObject(tmp1, tmp2, tmp3, tmp4, (byte) (o1.sum + o2.sum));
    }

    JsonArray toJsonArray() {
        JsonArray jsonArray = new JsonArray();
        jsonArray.add(this.first);
        jsonArray.add(this.second);
        jsonArray.add(this.third);
        jsonArray.add(this.fourth);
        return jsonArray;
    }


}
