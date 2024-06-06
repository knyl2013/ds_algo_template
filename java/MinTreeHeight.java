import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class MinTreeHeight {
  int minHeightRoot(List<List<Integer>> adj) {
    int sz = adj.size();
    int[] deg = new int[sz];
    Queue<Integer> leaves = new LinkedList<>();
    List<Integer> bestRoots = new ArrayList<>();
    for (int i = 0; i < sz; i++) {
      deg[i] = adj.get(i).size();
      if (deg[i] == 1) {
        leaves.offer(i);
      }
    }

    while (!leaves.isEmpty()) {
      int len = leaves.size();
      bestRoots.clear();
      for (int i = 0; i < len; i++) {
        int p = leaves.poll();
        bestRoots.add(p);
        for (int nei : adj.get(p)) {
          deg[nei]--;
          if (deg[nei] == 1) {
            leaves.offer(nei);
          }
        }
      }
    }

    return bestRoots.get(0);
  }
}