package kz.aitu.navigation.strategy;

import kz.aitu.navigation.Location;
import kz.aitu.navigation.Route;

import java.util.List;
import java.util.Locale;

/** Cycling: prefers bike lanes (small detour), 15 km/h, plus time to lock the bike. */
public class CycleStrategy implements RouteStrategy {

    private static final double BIKE_LANE_DETOUR_FACTOR = 1.20;
    private static final double CYCLING_SPEED_KMH = 15.0;
    private static final int LOCKING_MINUTES = 2;

    @Override
    public String travelMode() {
        return "Cycle";
    }

    @Override
    public Route buildRoute(Location from, Location to) {
        double distanceKm = from.straightLineDistanceKmTo(to) * BIKE_LANE_DETOUR_FACTOR;
        int minutes = (int) Math.round(distanceKm / CYCLING_SPEED_KMH * 60) + LOCKING_MINUTES;
        List<String> steps = List.of(
                "Unlock the bike at " + from.name(),
                String.format(Locale.US, "Ride %.2f km, preferring bike lanes", distanceKm),
                "Lock the bike at " + to.name());
        return new Route(travelMode(), from, to, distanceKm, minutes, steps);
    }
}
