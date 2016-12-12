package bsu.famcs.airline.airplanes;

import java.util.Comparator;

public class CompareAirplanesRange implements Comparator<Airplane> {
    public int compare(Airplane a, Airplane b){
        if (a.getRangeOfFlight() < b.getRangeOfFlight()){
            return -1;
        }
        else {
            if (a.getRangeOfFlight() > b.getRangeOfFlight()) {
                return 1;
            }
            else{
                return 0;
            }
        }
    }
}
