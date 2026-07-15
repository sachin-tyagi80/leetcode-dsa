class Solution {
    public int findMinArrowShots(int[][] points) {
        if (points.length == 0) {
            return 0;
        }

        // Sort balloons based on ending coordinate
        Arrays.sort(points, (a, b) -> Long.compare((long) a[1], (long) b[1]));

        // First arrow at first balloon's end
        int arrows = 1;
        long arrowPosition = points[0][1];

        // Traverse remaining balloons
        for (int i = 1; i < points.length; i++) {

            // Current balloon cannot be burst
            if (points[i][0] > arrowPosition) {

                arrows++;

                arrowPosition = points[i][1];
            }
        }

        return arrows;
        
    }
}