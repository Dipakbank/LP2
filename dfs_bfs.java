import java.util.*;

public class dfs_bfs {
    private int V; // number of vertices
    private LinkedList<Integer>[] adj; // adjacency list

    // constructor
    public dfs_bfs(int V) {
        this.V = V;
        adj = new LinkedList[V];
        for (int i = 0; i < V; i++) {
            adj[i] = new LinkedList<>();
        }

    }

    // add edge to the graph
    public void addEdge(int v, int w) {
        adj[v].add(w);
        adj[w].add(v);
    }

    // Depth-first search
    public void dfs(int v) {
        boolean[] visited = new boolean[V];
        dfsUtil(v, visited);
    }

    private void dfsUtil(int v, boolean[] visited) {
        visited[v] = true;
        System.out.print(v + " ");

        Iterator<Integer> it = adj[v].listIterator();
        while (it.hasNext()) {
            int n = it.next();
            if (!visited[n]) {
                dfsUtil(n, visited);
            }
        }
    }

    // Breadth-first search
    public void bfs(int v) {
        boolean[] visited = new boolean[V];
        LinkedList<Integer> queue = new LinkedList<>();
        visited[v] = true;
        queue.add(v);

        while (!queue.isEmpty()) {
            int n = queue.poll();
            System.out.print(n + " ");

            Iterator<Integer> it = adj[n].listIterator();
            while (it.hasNext()) {
                int m = it.next();
                if (!visited[m]) {
                    visited[m] = true;
                    queue.add(m);
                }
            }
        }
    }

    public static void main(String[] args) {
        dfs_bfs g = new dfs_bfs(6);
        g.addEdge(0, 1);
        g.addEdge(0, 2);
        g.addEdge(1, 3);
        g.addEdge(2, 4);
        g.addEdge(3, 4);
        g.addEdge(3, 5);
    
        System.out.print("DFS: ");
        g.dfs(0);
        System.out.println();
    
        System.out.print("BFS: ");
        g.bfs(0);
        System.out.println();
    
    
    }
}
