import java.util.ArrayList;
import java.util.List;  
// A Trie of all suffixes
class SuffixTree {
    static class SuffixTrieNode {
  
        final static int MAX_CHAR = 256;
      
        SuffixTrieNode[] children = new SuffixTrieNode[MAX_CHAR];
        List<Integer> indexes;
      
        SuffixTrieNode() // Constructor
        {
            // Create an empty linked list for indexes of
            // suffixes starting from this node
            indexes = new ArrayList<Integer>();
      
            // Initialize all child pointers as NULL
            for (int i = 0; i < MAX_CHAR; i++)
                children[i] = null;
        }
      
        // A recursive function to insert a suffix of 
        // the text in subtree rooted with this node
        void insertSuffix(String s, int index) {
              
            // Store index in linked list
            indexes.add(index);
      
            // If string has more characters
            if (s.length() > 0) {
              
                // Find the first character
                char cIndex = s.charAt(0);
      
                // If there is no edge for this character,
                // add a new edge
                if (children[cIndex] == null)
                    children[cIndex] = new SuffixTrieNode();
      
                // Recur for next suffix
                children[cIndex].insertSuffix(s.substring(1),
                                                  index + 1);
            }
        }
      
        // A function to search a pattern in subtree rooted
        // with this node.The function returns pointer to a 
        // linked list containing all indexes where pattern  
        // is present. The returned indexes are indexes of  
        // last characters of matched text.
        List<Integer> search(String s) {
              
            // If all characters of pattern have been 
            // processed,
            if (s.length() == 0)
                return indexes;
      
            // if there is an edge from the current node of
            // suffix tree, follow the edge.
            if (children[s.charAt(0)] != null)
                return (children[s.charAt(0)]).search(s.substring(1));
      
            // If there is no edge, pattern doesnt exist in 
            // text
            else
                return null;
        }
    }
  
    SuffixTrieNode root = new SuffixTrieNode();
  
    // Constructor (Builds a trie of suffies of the
    // given text)
    SuffixTree(String txt) {
      
        // Consider all suffixes of given string and
        // insert them into the Suffix Trie using 
        // recursive function insertSuffix() in 
        // SuffixTrieNode class
        for (int i = 0; i < txt.length(); i++)
            root.insertSuffix(txt.substring(i), i);
    }
  
    /* Prints all occurrences of pat in the Suffix Trie S
    (built for text) */
    void search_tree(String pat) {
      
        // Let us call recursive search function for 
        // root of Trie.
        // We get a list of all indexes (where pat is 
        // present in text) in variable 'result'
        List<Integer> result = root.search(pat);
  
        // Check if the list of indexes is empty or not
        if (result == null)
            System.out.println("Pattern not found");
        else {
  
            int patLen = pat.length();
  
            for (Integer i : result)
                System.out.println("Pattern found at position " +
                                                (i - patLen));
        }
    }

    List<Integer> search(String pat) {
        List<Integer> temp = root.search(pat);
        List<Integer> ans = new ArrayList<>();
        int patLen = pat.length();
        for (int i : temp) {
            ans.add(i - patLen);
        }
        return ans;
    }
} // This code is contributed by Sumit Ghosh