import java.util.ArrayList;

public class Util {

    // returns the index + 1 of the rightmost desired element
    public static int BinarySearchRight(ArrayList<InputLineObject> A, int key,int rightBorder) {
        int left = -1;
        int right = rightBorder;
        int middle;
        while (left + 1 < right) {
            middle = (left + right) >> 1;
            if (A.get(middle).getSum() <= key) {
                left = middle;
            } else {
                right = middle;
            }
        }
        return left + 1;
    }

    public static void sort(ArrayList<InputLineObject> array) {
        array.sort((o1, o2) -> Byte.compare(o1.getSum(), o2.getSum()));
    }
}
