import java.util.Arrays;

class SegmentTree_Main {
  static class SegmentTree { // This code is contributed by sanjeev2552
    static int[] constructST(int arr[], int n) {
      int x = (int) Math.ceil(Math.log(n) / Math.log(2));
      int max_size = 2 * (int) Math.pow(2, x) - 1;
      int[] st = new int[max_size];
      constructSTUtil(arr, 0, n - 1, st, 0);
      return st;
    }

    static int MaxUtil(int[] st, int ss,
        int se, int l,
        int r, int node) {

      if (l <= ss && r >= se)
        return st[node];

      if (se < l || ss > r)
        return -1;

      int mid = getMid(ss, se);

      return Math.max(
          MaxUtil(st, ss, mid, l, r,
              2 * node + 1),
          MaxUtil(st, mid + 1, se, l, r,
              2 * node + 2));
    }

    static void updateValue(int arr[], int[] st, int index, int value) {
      updateValue(arr, st, 0, arr.length - 1, index, value, 0);
    }

    static void updateValue(int arr[], int[] st, int ss,
        int se, int index,
        int value,
        int node) {
      if (index < ss || index > se) {
        System.out.println("Invalid Input");
        return;
      }

      if (ss == se) {
        arr[index] = value;
        st[node] = value;
      } else {
        int mid = getMid(ss, se);
        if (index >= ss && index <= mid)
          updateValue(arr, st, ss, mid,
              index, value,
              2 * node + 1);
        else
          updateValue(arr, st, mid + 1, se, index,
              value, 2 * node + 2);

        st[node] = Math.max(st[2 * node + 1],
            st[2 * node + 2]);
      }
      return;
    }

    static int getMax(int[] st, int n, int l, int r) {
      if (l < 0 || r > n - 1 || l > r) {
        System.out.printf("Invalid Input\n");
        return -1;
      }

      return MaxUtil(st, 0, n - 1, l, r, 0);
    }

    static int constructSTUtil(int arr[],
        int ss, int se,
        int[] st, int si) {
      if (ss == se) {
        st[si] = arr[ss];
        return arr[ss];
      }

      int mid = getMid(ss, se);

      st[si] = Math.max(
          constructSTUtil(arr, ss, mid,
              st, si * 2 + 1),
          constructSTUtil(arr, mid + 1,
              se, st,
              si * 2 + 2));

      return st[si];
    }

    static int getMid(int s, int e) {
      return s + (e - s) / 2;
    }
  }

  public static void main(String[] args) {
    int[] arr = { 1, 3, 5, 7, 9, 11 };
    int n = arr.length;
    int[] st = SegmentTree.constructST(arr, n);
    System.out.println("Max of values in given range = "
        + SegmentTree.getMax(st, n, 1, 3));

    SegmentTree.updateValue(arr, st, 1, 8);

    System.out.println(Arrays.toString(arr));

    // Find max after the value is updated
    System.out.println(
        "Updated max of values in given range = "
            + SegmentTree.getMax(st, n, 0, 3));
  }
}
