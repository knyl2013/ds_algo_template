import java.io.*;
import java.util.*;

public class java_template {
  void solve() {

  }

  static boolean multiple = false;
  static String task = "";
  static Kattio io;

  public static void main(String[] args) throws IOException {
    var main = new java_template(); 
    if (!task.isEmpty()) {
      io = new Kattio(task);
    } else {
      io = new Kattio();
    }
    int t = 1;
    if (multiple) {
      t = io.nextInt();
    }
    for (int i = 0; i < t; i++) {
      main.solve();
    }
    io.close();
  }

  static class Kattio extends PrintWriter {
    private BufferedReader r;
    private StringTokenizer st;

    // standard input
    public Kattio() {
      this(System.in, System.out);
    }

    public Kattio(InputStream i, OutputStream o) {
      super(o);
      r = new BufferedReader(new InputStreamReader(i));
    }

    // USACO-style file input
    public Kattio(String problemName) throws IOException {
      super(problemName + ".out");
      r = new BufferedReader(new FileReader(problemName + ".in"));
    }

    // returns null if no more input
    public String next() {
      try {
        while (st == null || !st.hasMoreTokens())
          st = new StringTokenizer(r.readLine());
        return st.nextToken();
      } catch (Exception e) {
      }
      return null;
    }

    public int nextInt() {
      return Integer.parseInt(next());
    }

    public double nextDouble() {
      return Double.parseDouble(next());
    }

    public long nextLong() {
      return Long.parseLong(next());
    }
  }
}
