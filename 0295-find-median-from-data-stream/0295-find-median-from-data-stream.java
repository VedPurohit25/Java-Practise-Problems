import java.util.Collections;
import java.util.PriorityQueue;

class MedianFinder {
    // Max-heap to store the smaller half of the numbers
    private PriorityQueue<Integer> small;
    // Min-heap to store the larger half of the numbers
    private PriorityQueue<Integer> large;

    public MedianFinder() {
        // use Collections.reverseOrder() to make it a Max-Heap
        small = new PriorityQueue<>(Collections.reverseOrder());
        large = new PriorityQueue<>();
    }
    
    public void addNum(int num) {
        // 1. Add to small (max-heap)
        small.add(num);
        
        // 2. Balancing step: Move the largest of the small half to the large half
        large.add(small.poll());
        
        // 3. Maintain the size property: 
        // Small heap can have at most 1 more element than large heap
        if (small.size() < large.size()) {
            small.add(large.poll());
        }
    }
    
    public double findMedian() {
        if (small.size() > large.size()) {
            // Odd number of elements: top of small heap is the median
            return (double) small.peek();
        } else {
            // Even number of elements: average of both tops
            return (small.peek() + large.peek()) / 2.0;
        }
    }
}

/**
 * Your MedianFinder object will be instantiated and called as such:
 * MedianFinder obj = new MedianFinder();
 * obj.addNum(num);
 * double param_2 = obj.findMedian();
 */