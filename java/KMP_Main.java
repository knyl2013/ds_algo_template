import java.util.*;

public class KMP_Main {
  static class KMP {
    static int[] build(char[] p) {
      int m = p.length, now = 0, i = 1;
      int[] longestPrefix = new int[m];
      while (i < m) {
        if (p[i] == p[now]) {
          longestPrefix[i++] = now + 1;
          now++;
        } else if (now > 0) {
          now = longestPrefix[now - 1];
        } else {
          i++;
        }
      }
      return longestPrefix;
    }

    static List<Integer> match(char[] s, char[] p) {
      int n = s.length, m = p.length, now = 0, i = 0;
      int[] longestPrefix = build(p);
      List<Integer> ans = new ArrayList<>();
      while (i < n) {
        if (now < m && s[i] == p[now]) {
          now++;
          i++;
        } else if (now > 0) {
          now = longestPrefix[now - 1];
        } else {
          i++;
        }
        if (now == m) {
          ans.add(i - m);
          now = longestPrefix[now - 1];
        }
      }
      return ans;
    }
  }

  public static void main(String[] args) {
    char[] s = "fixprefixsuffix".toCharArray();
    char[] p = "fix".toCharArray();
    System.out.println(KMP.match(s, p));
  }
}