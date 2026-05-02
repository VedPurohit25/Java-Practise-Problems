import java.util.PriorityQueue;

class KthLargest {
    private PriorityQueue<Integer> minHeap;
    private int k;

    public KthLargest(int k, int[] nums) {
        this.k = k;
        this.minHeap = new PriorityQueue<>(k);
        
        // Build the initial "Elite Room"
        for (int num : nums) {
            add(num);
        }
    }
    
    public int add(int val) {
        // Add the new score to the heap
        minHeap.offer(val);
        
        // If the room is too full, remove the lowest score
        if (minHeap.size() > k) {
            minHeap.poll();
        }
        
        // The head of the min-heap is the kth largest (the cut-off)
        return minHeap.peek();
    }
}
/**
 * Your KthLargest object will be instantiated and called as such:
 * KthLargest obj = new KthLargest(k, nums);
 * int param_1 = obj.add(val);
 */