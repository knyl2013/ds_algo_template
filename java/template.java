import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.InputMismatchException;

public class template {
  static void start() {
    int T = readint();
    for (int t = 1; t <= T; t++) {
      out.printf("Case #%d: ", t);
    }
  }

  /* I/O Template */
  static InputStream is;
  static PrintWriter out;
  static String INPUT = "";
  static String task = null;

  public static void main(String[] args) throws Exception {
    long S = System.currentTimeMillis();
    if (task != null) {
      File initialFile = new File(task + ".in");
      is = new FileInputStream(initialFile);
      out = new PrintWriter(task + ".out");
    } else {
      is = INPUT.isEmpty() ? System.in : new ByteArrayInputStream(INPUT.getBytes());
      out = new PrintWriter(System.out);
    }

    start();
    out.flush();
    long G = System.currentTimeMillis();
    // watch(G - S + "ms");
  }

  private static boolean eof() {
    if (lenbuf == -1)
      return true;
    int lptr = ptrbuf;
    while (lptr < lenbuf)
      if (!isspace(inbuf[lptr++]))
        return false;

    try {
      is.mark(1000);
      while (true) {
        int b = is.read();
        if (b == -1) {
          is.reset();
          return true;
        } else if (!isspace(b)) {
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

  private static int readbyte() {
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

  private static boolean isspace(int c) {
    return !(c >= 33 && c <= 126);
  }

  private static int skip() {
    int b;
    while ((b = readbyte()) != -1 && isspace(b))
      ;
    return b;
  }

  private static double readdouble() {
    return Double.parseDouble(read());
  }

  private static char readchar() {
    return (char) skip();
  }

  private static String read() {
    int b = skip();
    StringBuilder sb = new StringBuilder();
    while (!(isspace(b))) { // when nextLine, (isSpaceChar(b) && b != ' ')
      sb.appendCodePoint(b);
      b = readbyte();
    }
    return sb.toString();
  }

  private static char[] read(int n) {
    char[] buf = new char[n];
    int b = skip(), p = 0;
    while (p < n && !(isspace(b))) {
      buf[p++] = (char) b;
      b = readbyte();
    }
    return n == p ? buf : Arrays.copyOf(buf, p);
  }

  private static char[][] readmat(int n, int m) {
    char[][] map = new char[n][];
    for (int i = 0; i < n; i++)
      map[i] = read(m);
    return map;
  }

  private static int[] readarr(int n) {
    int[] a = new int[n];
    for (int i = 0; i < n; i++)
      a[i] = readint();
    return a;
  }

  private static int readint() {
    int num = 0, b;
    boolean minus = false;
    while ((b = readbyte()) != -1 && !((b >= '0' && b <= '9') || b == '-'))
      ;
    if (b == '-') {
      minus = true;
      b = readbyte();
    }

    while (true) {
      if (b >= '0' && b <= '9') {
        num = num * 10 + (b - '0');
      } else {
        return minus ? -num : num;
      }
      b = readbyte();
    }
  }

  private static long readlong() {
    long num = 0;
    int b;
    boolean minus = false;
    while ((b = readbyte()) != -1 && !((b >= '0' && b <= '9') || b == '-'))
      ;
    if (b == '-') {
      minus = true;
      b = readbyte();
    }

    while (true) {
      if (b >= '0' && b <= '9') {
        num = num * 10 + (b - '0');
      } else {
        return minus ? -num : num;
      }
      b = readbyte();
    }
  }

  private static void watch(Object... o) {
    System.out.println(Arrays.deepToString(o));
  }
}
