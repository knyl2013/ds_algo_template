import java.io.*;
import java.util.*;

public class java_template {
  static void main2() {
    int T = nextInt();
    for (int t = 1; t <= T; t++) {
      out.printf("Case #%d: ", t);
    }
  }

  /* I/O Template */
  static InputStream is;
  static PrintWriter out;
  static String INPUT = "";
  static String taskName = null;

  public static void main(String[] args) throws Exception {
    long S = System.currentTimeMillis();
    if (taskName != null) {
      File initialFile = new File(taskName + ".in");
      is = new FileInputStream(initialFile);
      out = new PrintWriter(taskName + ".out");
    } else {
      is = INPUT.isEmpty() ? System.in : new ByteArrayInputStream(INPUT.getBytes());
      out = new PrintWriter(System.out);
    }

    main2();
    out.flush();
    long G = System.currentTimeMillis();
    // watch(G - S + "ms");
  }

  private static boolean eof() {
    if (lenbuf == -1)
      return true;
    int lptr = ptrbuf;
    while (lptr < lenbuf)
      if (!isSpaceChar(inbuf[lptr++]))
        return false;

    try {
      is.mark(1000);
      while (true) {
        int b = is.read();
        if (b == -1) {
          is.reset();
          return true;
        } else if (!isSpaceChar(b)) {
          is.reset();
          return false;
        }
      }
    } catch (IOException e) {
      return true;
    }
  }

  private static byte[] inbuf = new byte[1024];
  static int lenbuf = 0, ptrbuf = 0;

  private static int nextByte() {
    if (lenbuf == -1)
      throw new InputMismatchException();
    if (ptrbuf >= lenbuf) {
      ptrbuf = 0;
      try {
        lenbuf = is.read(inbuf);
      } catch (IOException e) {
        throw new InputMismatchException();
      }
      if (lenbuf <= 0)
        return -1;
    }
    return inbuf[ptrbuf++];
  }

  private static boolean isSpaceChar(int c) {
    return !(c >= 33 && c <= 126);
  }

  private static int skip() {
    int b;
    while ((b = nextByte()) != -1 && isSpaceChar(b))
      ;
    return b;
  }

  private static double nextDouble() {
    return Double.parseDouble(nextString());
  }

  private static char nextChar() {
    return (char) skip();
  }

  private static String nextString() {
    int b = skip();
    StringBuilder sb = new StringBuilder();
    while (!(isSpaceChar(b))) { // when nextLine, (isSpaceChar(b) && b != ' ')
      sb.appendCodePoint(b);
      b = nextByte();
    }
    return sb.toString();
  }

  private static char[] nextString(int n) {
    char[] buf = new char[n];
    int b = skip(), p = 0;
    while (p < n && !(isSpaceChar(b))) {
      buf[p++] = (char) b;
      b = nextByte();
    }
    return n == p ? buf : Arrays.copyOf(buf, p);
  }

  private static char[][] nextMatrix(int n, int m) {
    char[][] map = new char[n][];
    for (int i = 0; i < n; i++)
      map[i] = nextString(m);
    return map;
  }

  private static int[] nextArray(int n) {
    int[] a = new int[n];
    for (int i = 0; i < n; i++)
      a[i] = nextInt();
    return a;
  }

  private static int nextInt() {
    int num = 0, b;
    boolean minus = false;
    while ((b = nextByte()) != -1 && !((b >= '0' && b <= '9') || b == '-'))
      ;
    if (b == '-') {
      minus = true;
      b = nextByte();
    }

    while (true) {
      if (b >= '0' && b <= '9') {
        num = num * 10 + (b - '0');
      } else {
        return minus ? -num : num;
      }
      b = nextByte();
    }
  }

  private static long nextLong() {
    long num = 0;
    int b;
    boolean minus = false;
    while ((b = nextByte()) != -1 && !((b >= '0' && b <= '9') || b == '-'))
      ;
    if (b == '-') {
      minus = true;
      b = nextByte();
    }

    while (true) {
      if (b >= '0' && b <= '9') {
        num = num * 10 + (b - '0');
      } else {
        return minus ? -num : num;
      }
      b = nextByte();
    }
  }

  private static void watch(Object... o) {
    System.out.println(Arrays.deepToString(o));
  }
}
