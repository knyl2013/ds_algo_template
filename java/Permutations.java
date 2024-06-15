import java.util.ArrayList;
import java.util.List;

public class Permutations {
  List<int[]> permutations(int[] arr) {
    int n = arr.length, i = 0;
    int[] indexes = new int[n];
    List<int[]> result = new ArrayList<>();
    while (i < n) {
      if (indexes[i] < i) {
        int i1 = i % 2 == 0 ? 0 : indexes[i];
        int i2 = i;
        // Swap i1, i2
        int tmp = arr[i1];
        arr[i1] = arr[i2];
        arr[i2] = tmp;
        result.add(arr.clone());
        indexes[i]++;
        i = 0;
      } else {
        indexes[i] = 0;
        i++;
      }
    }
    return result;
  }
}
