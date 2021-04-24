 import java.util.Random;

public class SuffixTree {
  static final String ALPHABET = "abcdefghijklmnopqrstuvwxyz0123456789\1\2";
  static CharSequence s;
  static int n;

  public static class Node {
    int begin;
    int end;
    int depth; // distance in characters from root to this node
    Node parent;
    Node[] children;
    Node suffixLink;

    Node(int begin, int end, int depth, Node parent) {
      this.begin = begin;
      this.end = end;
      this.parent = parent;
      this.depth = depth;
      children = new Node[ALPHABET.length()];
    }
  }

  public static Node buildSuffixTree(CharSequence s) {
    SuffixTree.s = s;
    n = s.length();
    byte[] a = new byte[n];
    for (int i = 0; i < n; i++) a[i] = (byte) ALPHABET.indexOf(s.charAt(i));
    Node root = new Node(0, 0, 0, null);
    Node node = root;
    for (int i = 0, tail = 0; i < n; i++, tail++) {
      Node last = null;
      while (tail >= 0) {
        Node ch = node.children[a[i - tail]];
        while (ch != null && tail >= ch.end - ch.begin) {
          tail -= ch.end - ch.begin;
          node = ch;
          ch = ch.children[a[i - tail]];
        }
        if (ch == null) {
          node.children[a[i]] = new Node(i, n, node.depth + node.end - node.begin, node);
          if (last != null) last.suffixLink = node;
          last = null;
        } else {
          byte t = a[ch.begin + tail];
          if (t == a[i]) {
            if (last != null) last.suffixLink = node;
            break;
          } else {
            Node splitNode = new Node(ch.begin, ch.begin + tail, node.depth + node.end - node.begin, node);
            splitNode.children[a[i]] = new Node(i, n, ch.depth + tail, splitNode);
            splitNode.children[t] = ch;
            ch.begin += tail;
            ch.depth += tail;
            ch.parent = splitNode;
            node.children[a[i - tail]] = splitNode;
            if (last != null) last.suffixLink = splitNode;
            last = splitNode;
          }
        }
        if (node == root) {
          --tail;
        } else {
          node = node.suffixLink;
        }
      }
    }
    return root;
  }

  public static int smallestSubstring(Node root)
  {
    for (int i = 0; i < ALPHABET.length(); i++) {
      if (root.children[i] != null)
          return smallestSubstring(root.children[i]);
    }
    return root.begin;
  }

  public static int smallestKLenSubstring(Node root, int k, int curLen)
  {
    // System.out.println(root.begin + " " + root.end + " " + curLen);
    for (int i = 0; i < ALPHABET.length(); i++) {
      if (root.children[i] != null && curLen + (n - root.children[i].begin) >= k) {
        // System.out.println("i: " + i);
        int nxtLen = curLen + (root.children[i].end - root.children[i].begin);
        if (nxtLen >= k) return root.children[i].begin - curLen;
        return smallestKLenSubstring(root.children[i], k, nxtLen);
      }
    }
    return -1;
  }

  // random test
  public static void main(String[] args) {
    // String s = "bnna";
    // String s = "ddbcdeddfaf";
    // String s = "bbbbba";
    // String s = "alabala";
    // String s = "abcde";
    // String s = "abcade";
    // String s = "adeabc";
    String s = "azyzaxy";
    int len = s.length();
    s = s + s.substring(0, s.length() - 1);
    System.out.println(s);
    Node tree = buildSuffixTree(s);
    // int startIdx = smallestSubstring(tree);
    // System.out.println(startIdx);
    // s = "aaaaa";
    int startIdx = smallestKLenSubstring(tree, len, 0);
    System.out.println(startIdx);
    // System.out.println(tree.depth);
    // System.out.println(tree.children[25]);
    // System.out.println(tree.children[2]);
    // System.out.println(tree.children[0].depth);
    // System.out.println(tree.children[0].begin);
    // System.out.println(tree.children[0].end);
    // System.out.println(tree.children[0].children[1]);
    // Random rnd = new Random(1);
    // for (int step = 0; step < 100_000; step++) {
    //   int n1 = rnd.nextInt(10);
    //   int n2 = rnd.nextInt(10);
    //   String s1 = getRandomString(n1, rnd);
    //   String s2 = getRandomString(n2, rnd);
    //   // build generalized suffix tree
    //   String s = s1 + '\1' + s2 + '\2';
    //   Node tree = buildSuffixTree(s);
    //   lcsLength = 0;
    //   lcsBeginIndex = 0;
    //   // find longest common substring
    //   lcs(tree, s1.length(), s1.length() + s2.length() + 1);
    //   int res2 = slowLcs(s1, s2);
    //   if (lcsLength != res2) {
    //     System.err.println(s.substring(lcsBeginIndex - 1, lcsBeginIndex + lcsLength - 1));
    //     System.err.println(s1);
    //     System.err.println(s2);
    //     System.err.println(lcsLength + " " + res2);
    //     throw new RuntimeException();
    //   }
    // }
  }

  // static int lcsLength;
  // static int lcsBeginIndex;

  // // traverse suffix tree to find longest common substring
  // public static int lcs(Node node, int i1, int i2) {
  //   if (node.begin <= i1 && i1 < node.end) {
  //     return 1;
  //   }
  //   if (node.begin <= i2 && i2 < node.end) {
  //     return 2;
  //   }
  //   int mask = 0;
  //   for (char f = 0; f < ALPHABET.length(); f++) {
  //     if (node.children[f] != null) {
  //       mask |= lcs(node.children[f], i1, i2);
  //     }
  //   }
  //   if (mask == 3) {
  //     int curLength = node.depth + node.end - node.begin;
  //     if (lcsLength < curLength) {
  //       lcsLength = curLength;
  //       lcsBeginIndex = node.begin;
  //     }
  //   }
  //   return mask;
  // }

  // static int slowLcs(String a, String b) {
  //   int[][] lcs = new int[a.length()][b.length()];
  //   int res = 0;
  //   for (int i = 0; i < a.length(); i++) {
  //     for (int j = 0; j < b.length(); j++) {
  //       if (a.charAt(i) == b.charAt(j))
  //         lcs[i][j] = 1 + (i > 0 && j > 0 ? lcs[i - 1][j - 1] : 0);
  //       res = Math.max(res, lcs[i][j]);
  //     }
  //   }
  //   return res;
  // }

  // static String getRandomString(int n, Random rnd) {
  //   StringBuilder sb = new StringBuilder();
  //   for (int i = 0; i < n; i++) {
  //     sb.append((char) ('a' + rnd.nextInt(3)));
  //   }
  //   return sb.toString();
  // }
}
