package kz.aitu.navigation.strategy;

import kz.aitu.navigation.Location;
import kz.aitu.navigation.Route;

import java.util.List;
import java.util.Locale;

/** Driving: follows the road network (detour factor), average city speed, parking at the end. */
public class CarStrategy implements RouteStrategy {

    private static final double ROAD_DETOUR_FACTOR = 1.30;
    private static final double AVERAGE_SPEED_KMH = 40.0;
    private static final int PARKING_MINUTES = 5;

    @Override
    public String travelMode() {
        return "Car";
    }

    @Override
    public Route buildRoute(Location from, Location to) {
        double distanceKm = from.straightLineDistanceKmTo(to) * ROAD_DETOUR_FACTOR;
        int minutes = (int) Math.round(distanceKm / AVERAGE_SPEED_KMH * 60) + PARKING_MINUTES;
        List<String> steps = List.of(
                "Start the car at " + from.name(),
                String.format(Locale.US, "Drive %.2f km along main roads", distanceKm),
                "Park near " + to.name());
        return new Route(travelMode(), from, to, distanceKm, minutes, steps);
    }
}
