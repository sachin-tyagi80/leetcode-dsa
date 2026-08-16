class Solution {

    public boolean canVisitAllRooms(List<List<Integer>> rooms) {

        // Total number of rooms
        int n = rooms.size();

        // visited[i] tells whether room i has been visited
        boolean[] visited = new boolean[n];

        // We can enter room 0 initially,
        // so start DFS from room 0
        dfs(0, rooms, visited);

        // Check whether every room was visited
        for (int i = 0; i < n; i++) {

            // If any room is not visited,
            // we cannot visit all rooms
            if (!visited[i]) {
                return false;
            }
        }

        // All rooms were visited
        return true;
    }

    private void dfs(int room, List<List<Integer>> rooms, boolean[] visited) {

        // Mark current room as visited
        visited[room] = true;

        // Get all keys available in the current room
        for (int key : rooms.get(room)) {

            // If the room for this key is not visited,
            // visit that room using DFS
            if (!visited[key]) {
                dfs(key, rooms, visited);
            }
        }
    }
}