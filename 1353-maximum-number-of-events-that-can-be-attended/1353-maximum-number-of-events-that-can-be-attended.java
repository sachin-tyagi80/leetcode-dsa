class Solution {
    public int maxEvents(int[][] events) {
        Arrays.sort(events,(a,b) -> a[0]-b[0]);
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        int day = 1;
        int i=0;
        int n = events.length;
        int ans = 0;
        while(i < n || !pq.isEmpty()){
            if(pq.isEmpty()){
                day = events[i][0];
            }
            while(i<n && events[i][0] == day){
                pq.offer(events[i][1]);
                i++;
            }
            while(!pq.isEmpty() && pq.peek()< day){
                pq.poll();
            }
            if(!pq.isEmpty()){
                pq.poll();
                ans++;
            }
            day++;
        }
        return ans; 
        
    }
}



// "I solved this problem using a Greedy approach with a Min Heap. First, I sorted all events by their start day. Then I simulated each day. 
// On every day, I added all events that started on that day into a priority queue ordered by their end day. Before attending an event, 
// I removed all expired events. Finally, I always attended the event with the earliest end day because delaying such an event could make 
// it impossible to attend later. This greedy strategy maximizes the number of events attended."
