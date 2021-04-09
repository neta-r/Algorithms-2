package LargestSumOfSunArray;

import LargestSumOfSunArray.circular.bestInCircular;

public class gasStationProblem {
    public static int gasStation (int[] stations, int[] costs){
        int sumStations = 0 , sumCosts = 0;
        int helpArr[]= new int[stations.length];
        for (int i=0; i<stations.length; i++){
            sumStations+=stations[i];
            sumCosts+=costs[i];
            helpArr[i] = stations[i]-costs[i];
        }
        if (sumStations<sumCosts) return -1;
        int[] help = bestInCircular.largestSumOfArr(helpArr);
        return help[1];
    }

    public static void main(String[] args) {
        int[] stations = {3,6,2,8};
        int[] costs = {5,4,3,4};
        gasStation(stations,costs);
    }
}
