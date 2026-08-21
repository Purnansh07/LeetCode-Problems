class Solution {
    public int[] findOrder(int numCourses, int[][] prerequisites) {

        List<Integer>[] graph = new ArrayList[numCourses];

        for (int i = 0; i < numCourses; i++) {
            graph[i] = new ArrayList<>();
        }

        int[] indegree = new int[numCourses];

        // Build graph
        for (int[] prerequisite : prerequisites) {

            int course = prerequisite[0];
            int prerequisiteCourse = prerequisite[1];

            graph[prerequisiteCourse].add(course);

            indegree[course]++;
        }

        Queue<Integer> queue = new LinkedList<>();

        // Courses with no prerequisites
        for (int i = 0; i < numCourses; i++) {

            if (indegree[i] == 0) {
                queue.offer(i);
            }
        }

        int[] order = new int[numCourses];
        int index = 0;

        while (!queue.isEmpty()) {

            int course = queue.poll();

            order[index++] = course;

            for (int next : graph[course]) {

                indegree[next]--;

                if (indegree[next] == 0) {
                    queue.offer(next);
                }
            }
        }

        // Cycle exists
        if (index != numCourses) {
            return new int[0];
        }

        return order;
    }
}