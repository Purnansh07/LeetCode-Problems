
import java.util.*;

class Solution {
    private List<Integer>[] graph;
    private int[] parent, depth, tin, tout, charMask;
    private int[][] up;
    private int timer, log;
    private Fenwick bit;

    public List<Boolean> palindromePath(
            int n, int[][] edges, String s, String[] queries) {

        graph = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int[] e : edges) {
            graph[e[0]].add(e[1]);
            graph[e[1]].add(e[0]);
        }

        parent = new int[n];
        depth = new int[n];
        tin = new int[n];
        tout = new int[n];
        charMask = new int[n];

        for (int i = 0; i < n; i++) {
            charMask[i] = 1 << (s.charAt(i) - 'a');
        }

        buildEulerTour(n);

        log = 1;
        while ((1 << log) <= n) {
            log++;
        }

        up = new int[log][n];
        for (int i = 0; i < n; i++) {
            up[0][i] = parent[i];
        }

        for (int j = 1; j < log; j++) {
            for (int i = 0; i < n; i++) {
                up[j][i] = up[j - 1][up[j - 1][i]];
            }
        }

        bit = new Fenwick(2 * n);

        // Store each node's character mask at entry and exit.
        for (int u = 0; u < n; u++) {
            bit.update(tin[u], charMask[u]);
            bit.update(tout[u], charMask[u]);
        }

        List<Boolean> ans = new ArrayList<>();

        for (String q : queries) {
            String[] parts = q.split(" ");
            int u = Integer.parseInt(parts[1]);

            if (parts[0].equals("update")) {
                int newMask = 1 << (parts[2].charAt(0) - 'a');
                int delta = charMask[u] ^ newMask;

                if (delta != 0) {
                    bit.update(tin[u], delta);
                    bit.update(tout[u], delta);
                    charMask[u] = newMask;
                }
            } else {
                int v = Integer.parseInt(parts[2]);
                int w = lca(u, v);

                int mask = bit.query(tin[u] + 1)
                         ^ bit.query(tin[v] + 1)
                         ^ charMask[w];

                // At most one character may have odd frequency.
                ans.add((mask & (mask - 1)) == 0);
            }
        }

        return ans;
    }

    private void buildEulerTour(int n) {
        // Each node is processed twice: entry and exit.
        int[] stNode = new int[2 * n];
        int[] stParent = new int[2 * n];
        int[] stState = new int[2 * n];
        int top = 0;

        stNode[top] = 0;
        stParent[top] = 0;
        stState[top++] = 0;

        timer = 0;

        while (top > 0) {
            --top;
            int u = stNode[top];
            int p = stParent[top];
            int state = stState[top];

            if (state == 0) {
                parent[u] = p;
                tin[u] = timer++;

                // Schedule the exit event.
                stNode[top] = u;
                stParent[top] = p;
                stState[top++] = 1;

                // Push children in reverse order.
                for (int i = graph[u].size() - 1; i >= 0; i--) {
                    int v = graph[u].get(i);

                    if (v == p) {
                        continue;
                    }

                    depth[v] = depth[u] + 1;
                    stNode[top] = v;
                    stParent[top] = u;
                    stState[top++] = 0;
                }
            } else {
                tout[u] = timer++;
            }
        }
    }

    private int lca(int u, int v) {
        if (depth[u] < depth[v]) {
            int temp = u;
            u = v;
            v = temp;
        }

        int diff = depth[u] - depth[v];

        for (int j = 0; j < log; j++) {
            if ((diff & (1 << j)) != 0) {
                u = up[j][u];
            }
        }

        if (u == v) {
            return u;
        }

        for (int j = log - 1; j >= 0; j--) {
            if (up[j][u] != up[j][v]) {
                u = up[j][u];
                v = up[j][v];
            }
        }

        return parent[u];
    }

    private static class Fenwick {
        private final int[] tree;
        private final int n;

        Fenwick(int n) {
            this.n = n;
            tree = new int[n + 1];
        }

        void update(int index, int delta) {
            for (int i = index + 1; i <= n; i += i & -i) {
                tree[i] ^= delta;
            }
        }

        int query(int count) {
            int result = 0;

            for (int i = count; i > 0; i -= i & -i) {
                result ^= tree[i];
            }

            return result;
        }
    }
}
