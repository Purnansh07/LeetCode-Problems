class Solution {
    public List<String> findItinerary(List<List<String>> tickets) {
        Map<String, PriorityQueue<String>> graph = new HashMap<>();

        for (List<String> ticket : tickets) {
            graph.computeIfAbsent(ticket.get(0),
                    k -> new PriorityQueue<>())
                 .offer(ticket.get(1));
        }

        LinkedList<String> result = new LinkedList<>();

        dfs("JFK", graph, result);

        return result;
    }

    private void dfs(String airport,
                     Map<String, PriorityQueue<String>> graph,
                     LinkedList<String> result) {

        PriorityQueue<String> next = graph.get(airport);

        while (next != null && !next.isEmpty()) {
            dfs(next.poll(), graph, result);
        }

        // Add while backtracking
        result.addFirst(airport);
    }
}