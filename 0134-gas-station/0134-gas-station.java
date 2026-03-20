class Solution {
    public int canCompleteCircuit(int[] gas, int[] cost) {
        int totalBalance = 0;
        int currentTank = 0;
        int startStation = 0;
        
        for (int i = 0; i < gas.length; i++) {
            int net = gas[i] - cost[i];
            totalBalance += net;
            currentTank += net;
            
            // If we run out of gas, this start point (and all before it) failed
            if (currentTank < 0) {
                // Reset and try the next station as a start
                startStation = i + 1;
                currentTank = 0;
            }
        }
        
        // If total gas >= total cost, a unique solution must exist
        return (totalBalance >= 0) ? startStation : -1;
    }
}