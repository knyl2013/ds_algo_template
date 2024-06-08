import java.io.IOException;

public class CountCycles {
  static int MAXN = 100;
  static int N = 10;
  static boolean[] visited = new boolean[MAXN];
  static boolean[] onStack = new boolean[MAXN];
  static int numberOfCycles = 0;
  static int[] nextNode = new int[MAXN];

  public static void main(String[] args) throws IOException {
    // Take in input
    for (int i = 1; i != N; i++) {
      if (!visited[i]) {
        dfs(i);
      }
    }
  }

  public static void dfs(int n) {
    visited[n] = onStack[n] = true;
    int u = nextNode[n];
    if (onStack[u]) {
      numberOfCycles++;
    } else if (!visited[u]) {
      dfs(u);
    }
    onStack[n] = false;
  }
}