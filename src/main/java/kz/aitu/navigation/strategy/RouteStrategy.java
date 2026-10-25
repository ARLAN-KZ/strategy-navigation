package kz.aitu.navigation.strategy;

import kz.aitu.navigation.Location;
import kz.aitu.navigation.Route;

/** Strategy interface: one algorithm for building a route between two points. */
public interface RouteStrategy {

    /** Human-readable name of the travel mode, e.g. "Car". */
    String travelMode();

    /** Plans a route from {@code from} to {@code to} using this strategy's algorithm. */
    Route buildRoute(Location from, Location to);
}
