class Solution {
    public int minMoves(String[] classroom, int energy) {

        int m = classroom.length;
        int n = classroom[0].length();

        int[][] litterId = new int[m][n];

        int startR = 0;
        int startC = 0;
        int litterCount = 0;

        // Find start and assign an ID to every litter.
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {

                char c = classroom[i].charAt(j);

                if (c == 'S') {
                    startR = i;
                    startC = j;
                } 
                else if (c == 'L') {
                    litterId[i][j] = litterCount++;
                }
            }
        }

        // No litter to collect.
        if (litterCount == 0) {
            return 0;
        }

        int totalMasks = 1 << litterCount;

        /*
         * mask:
         * 1 = litter still needs to be collected
         * 0 = litter already collected
         */
        int fullMask = (1 << litterCount) - 1;

        boolean[][][][] visited =
            new boolean[m][n][energy + 1][totalMasks];

        Queue<int[]> queue = new ArrayDeque<>();

        queue.offer(new int[]{
            startR,
            startC,
            energy,
            fullMask
        });

        visited[startR][startC][energy][fullMask] = true;

        int[] dr = {-1, 1, 0, 0};
        int[] dc = {0, 0, -1, 1};

        int moves = 0;

        while (!queue.isEmpty()) {

            int size = queue.size();

            while (size-- > 0) {

                int[] state = queue.poll();

                int r = state[0];
                int c = state[1];
                int currEnergy = state[2];
                int mask = state[3];

                // All litter collected.
                if (mask == 0) {
                    return moves;
                }

                // Cannot move without energy.
                if (currEnergy == 0) {
                    continue;
                }

                for (int d = 0; d < 4; d++) {

                    int nr = r + dr[d];
                    int nc = c + dc[d];

                    // Outside grid
                    if (nr < 0 || nr >= m ||
                        nc < 0 || nc >= n) {
                        continue;
                    }

                    // Obstacle
                    if (classroom[nr].charAt(nc) == 'X') {
                        continue;
                    }

                    char cell = classroom[nr].charAt(nc);

                    // Moving costs one energy.
                    int nextEnergy = currEnergy - 1;

                    // Reset cell restores energy.
                    if (cell == 'R') {
                        nextEnergy = energy;
                    }

                    int nextMask = mask;

                    // Collect litter.
                    if (cell == 'L') {
                        int id = litterId[nr][nc];
                        nextMask &= ~(1 << id);
                    }

                    if (!visited[nr][nc][nextEnergy][nextMask]) {

                        visited[nr][nc][nextEnergy][nextMask] = true;

                        queue.offer(new int[]{
                            nr,
                            nc,
                            nextEnergy,
                            nextMask
                        });
                    }
                }
            }

            moves++;
        }

        return -1;
    }
}