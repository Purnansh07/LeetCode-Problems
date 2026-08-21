class Solution {
    public boolean canFinish(int numCourses, int[][] prerequisites) {

        // Adjacency list
        List<Integer>[] graph = new ArrayList[numCourses];

        for (int i = 0; i < numCourses; i++) {
            graph[i] = new ArrayList<>();
        }

        // Indegree of each course
        int[] indegree = new int[numCourses];

        // Build graph
        for (int[] prerequisite : prerequisites) {

            int course = prerequisite[0];
            int prerequisiteCourse = prerequisite[1];

            graph[prerequisiteCourse].add(course);

            indegree[course]++;
        }

        // Courses with no prerequisites
        Queue<Integer> queue = new LinkedList<>();

        for (int i = 0; i < numCourses; i++) {
            if (indegree[i] == 0) {
                queue.offer(i);
            }
        }

        int completed = 0;

        while (!queue.isEmpty()) {

            int course = queue.poll();

            completed++;

            for (int nextCourse : graph[course]) {

                indegree[nextCourse]--;

                if (indegree[nextCourse] == 0) {
                    queue.offer(nextCourse);
                }
            }
        }

        return completed == numCourses;
    }
}