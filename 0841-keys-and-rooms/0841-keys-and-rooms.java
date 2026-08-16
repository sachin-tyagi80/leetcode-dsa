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


// "I treat each room as a node and each key as a directed edge to another room. Since only room 0 is initially accessible, I start DFS from room 0.
// I maintain a visited array so that each room is processed only once. During DFS, I mark the current room as visited and recursively visit every 
// room for which I have a key. After DFS completes, I check the visited array. If every room is visited, I return true; otherwise, I return false.

///////////////////////////////////////////////////////////////
// class Solution {

//     public boolean canVisitAllRooms(List<List<Integer>> rooms) {

//         int n = rooms.size();

//         // Track which rooms are already visited
//         boolean[] visited = new boolean[n];

//         // Queue for BFS
//         Queue<Integer> queue = new LinkedList<>();

//         // Room 0 is initially accessible
//         queue.offer(0);
//         visited[0] = true;

//         // BFS
//         while (!queue.isEmpty()) {

//             // Remove a room from queue
//             int room = queue.poll();

//             // Get all keys from current room
//             for (int key : rooms.get(room)) {

//                 // If this room is not visited
//                 if (!visited[key]) {

//                     // Mark it visited
//                     visited[key] = true;

//                     // Add it to queue for further exploration
//                     queue.offer(key);
//                 }
//             }
//         }

//         // Check if every room was visited
//         for (int i = 0; i < n; i++) {

//             if (!visited[i]) {
//                 return false;
//             }
//         }

//         return true;
//     }
// }
// Interview me BFS ke liye ek line:

// "I start BFS from room 0 using a queue. Whenever I find an unvisited room through a key, I mark it visited and add it to the queue. After BFS,
// if every room is visited, I return true."
