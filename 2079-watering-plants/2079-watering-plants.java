class Solution {
    public int wateringPlants(int[] plants, int capacity) {

        int water = capacity;
        int steps = 0;

        for (int i = 0; i < plants.length; i++) {

            if (water < plants[i]) {
                steps += 2 * i;
                water = capacity;
            }

            steps++;
            water -= plants[i];
        }

        return steps;
    }
}