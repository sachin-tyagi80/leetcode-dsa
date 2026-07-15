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





// "I use a Greedy strategy. First, I sort all balloons by their ending coordinate. Then I shoot the first arrow at the end of the first balloon. 
// This arrow bursts every balloon whose starting coordinate is less than or equal to the arrow position. Whenever I find a balloon whose start is greater 
// than the current arrow position, I need a new arrow, so I increase the arrow count and move the arrow to that balloon's end. 
// This greedy choice minimizes the total number of arrows."
