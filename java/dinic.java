import java.util.*;
public class dinic {
    static class Dinic {
        public static int N;
        public static int INF = 1 << 29;
        public static int[] eadj, eprev, elast;
        public static int eidx;
        private static int[] flow, capa, now;
      
        public static void init(int _N, int M) {
          N = _N;
          eadj = new int[M];
          eprev = new int[M];
          elast = new int[N];
          flow = new int[M];
          capa = new int[M];
          now = new int[N];
          level = new int[N];
          eidx = 0;
          Arrays.fill(elast, -1);
        }
      
        private static void add_edge(int a, int b, int c) {
          eadj[eidx] = b; flow[eidx] = 0; capa[eidx] = c; eprev[eidx] = elast[a]; elast[a] = eidx++;
          eadj[eidx] = a; flow[eidx] = c; capa[eidx] = c; eprev[eidx] = elast[b]; elast[b] = eidx++;
        }
      
        private static int dinic(int source, int sink) {
          int res, flow = 0;
          while (bfs(source, sink)) { // see if there is an augmenting path
            System.arraycopy(elast, 0, now, 0, N);
            while ((res = dfs(source, INF, sink)) > 0)
              // push all possible flow through
              flow += res;
          }
          return flow;
        }
      
        private static int[] level;
      
        private static boolean bfs(int source, int sink) {
          Arrays.fill(level, -1);
          int front = 0, back = 0;
          int[] queue = new int[N];
          level[source] = 0;
          queue[back++] = source;
          while (front < back && level[sink] == -1) {
            int node = queue[front++];
            for (int e = elast[node]; e != -1; e = eprev[e]) {
              int to = eadj[e];
              if (level[to] == -1 && flow[e] < capa[e]) {
                level[to] = level[node] + 1;
                queue[back++] = to;
              }
            }
          }
      
          return level[sink] != -1;
        }
      
        private static int dfs(int cur, int curflow, int goal) {
          if (cur == goal)
            return curflow;
      
          for (int e = now[cur]; e != -1; now[cur] = e = eprev[e]) {
            if (level[eadj[e]] > level[cur] && flow[e] < capa[e]) {
              int res = dfs(eadj[e], Math.min(curflow, capa[e] - flow[e]), goal);
              if (res > 0) {
                flow[e] += res;
                flow[e ^ 1] -= res;
                return res;
              }
            }
          }
          return 0;
        }
      }
      
    static class Edge {
        int v;
        int flow;
        int C;
        int rev;
        public Edge(int v, int flow, int C, int rev)
        {
            this.v = v;
            this.flow = flow;
            this.C = C;
            this.rev = rev;
        }
    }
    static class Graph {
        int V;
        int[] level;
        List<List<Edge>> adj;
        public Graph(int V) {
            this.V = V;
            level = new int[V];
            adj = new ArrayList<>();
            for (int i = 0; i < V; i++) adj.add(new ArrayList<>());
        }
        void addEdge(int u, int v, int C)
        {
            Edge a = new Edge(v, 0, C, adj.get(v).size());
            Edge b = new Edge(u, 0, 0, adj.get(u).size());
            adj.get(u).add(a);
            adj.get(v).add(b);
        }
        boolean BFS(int s, int t)
        {
            for (int i = 0 ; i < V ; i++)
                level[i] = -1;

            level[s] = 0; // Level of source vertex

            Deque<Integer> q = new ArrayDeque<>();
            q.offer(s);
            

            while (!q.isEmpty())
            {
                int u = q.poll();
                q.poll();
                List<Edge> lst = adj.get(u);
                for (int i = 0; i < lst.size(); i++)
                {
                    Edge e = lst.get(i);
                    if (level[e.v] < 0 && e.flow < e.C)
                    {
                        level[e.v] = level[u] + 1;
                        q.offer(e.v);
                    }
                }
            }

            return level[t] < 0 ? false : true ;
        }

        int sendFlow(int u, int flow, int t, int[] start)
        {
            // Sink reached
            if (u == t)
                return flow;

            // Traverse all adjacent edges one -by - one.
            for ( ; start[u] < adj.get(u).size(); start[u]++)
            {
                // Pick next edge from adjacency list of u
                Edge e = adj.get(u).get(start[u]); 
                System.out.println("edge: " + e.v);
                if (level[e.v] == level[u]+1 && e.flow < e.C)
                {
                    // find minimum flow from u to t
                    int curr_flow = Math.min(flow, e.C - e.flow);

                    int temp_flow = sendFlow(e.v, curr_flow, t, start);

                    // flow is greater than zero
                    if (temp_flow > 0)
                    {
                        // add flow to current edge
                        e.flow += temp_flow;

                        // subtract flow from reverse edge
                        // of current edge
                        adj.get(e.v).get(e.rev).flow -= temp_flow;
                        return temp_flow;
                    }
                }
            }

            return 0;
        }

        int dinic(int s, int t)
        {
            // Corner case
            if (s == t)
                return -1;

            int total = 0; // Initialize result

            // Augment the flow while there is path
            // from source to sink
            while (BFS(s, t) == true)
            {
                // System.out.println("b");
                // store how many edges are visited
                // from V { 0 to V }
                int[] start = new int[V+1];

                // while flow is not zero in graph from S to D
                int flow = sendFlow(s, Integer.MAX_VALUE, t, start);
                while (flow > 0) {
                    
                    // System.out.println("c");
                    // Add path flow to overall flow
                    total += flow;
                    System.out.println(flow);
                    flow = sendFlow(s, Integer.MAX_VALUE, t, start);
                    
                }
            }

            // return maximum flow
            return total;
        }


    }
    public static void main(String[] args) 
    {
        Graph g = new Graph(6);
        g.addEdge(0, 1, 16 );
        g.addEdge(0, 2, 13 );
        g.addEdge(1, 2, 10 );
        g.addEdge(1, 3, 12 );
        g.addEdge(2, 1, 4 );
        g.addEdge(2, 4, 14);
        g.addEdge(3, 2, 9 );
        g.addEdge(3, 5, 20 );
        g.addEdge(4, 3, 7 );
        g.addEdge(4, 5, 4);

        // next exmp
        /*g.addEdge(0, 1, 3 );
        g.addEdge(0, 2, 7 ) ;
        g.addEdge(1, 3, 9);
        g.addEdge(1, 4, 9 );
        g.addEdge(2, 1, 9 );
        g.addEdge(2, 4, 9);
        g.addEdge(2, 5, 4);
        g.addEdge(3, 5, 3);
        g.addEdge(4, 5, 7 );
        g.addEdge(0, 4, 10);

        // next exp
        g.addEdge(0, 1, 10);
        g.addEdge(0, 2, 10);
        g.addEdge(1, 3, 4 );
        g.addEdge(1, 4, 8 );
        g.addEdge(1, 2, 2 );
        g.addEdge(2, 4, 9 );
        g.addEdge(3, 5, 10 );
        g.addEdge(4, 3, 6 );
        g.addEdge(4, 5, 10 ); */
        // System.out.println("a");
        System.out.println(g.dinic(0, 5));
    }
}