package kz.aitu.navigation.strategy;

import kz.aitu.navigation.Location;
import kz.aitu.navigation.Route;

public interface RouteStrategy {

    String travelMode();


    Route buildRoute(Location from, Location to);
}
