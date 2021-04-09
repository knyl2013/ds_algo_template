static class ShortestPath {
        public int[] shortestPath(int from, List<List<Integer>> adjList, List<List<Integer>> weightList)
        {
            int n = adjList.size();
            int inf = (int) 1e9;
            int[] distance = new int[n + 1];
            boolean[] visited = new boolean[n + 1];
            Arrays.fill(distance, inf);
            PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> Integer.compare(a[0], b[0]));
            pq.offer(new int[] {0, from});
            while (!pq.isEmpty()) {
                int[] p = pq.poll();
                int d = p[0];
                int v = p[1];
                if (visited[v]) continue;
                distance[v] = p[0];
                visited[v] = true;
                for (int i = 0; i < adjList.get(v).size(); i++) {
                    int nei = adjList.get(v).get(i);
                    int w = weightList.get(v).get(i);
                    if (w == 0 || visited[nei]) 
                        continue;
                    pq.offer(new int[]{p[0] + w, nei});
                }
            }
            return distance;
        }
    }
