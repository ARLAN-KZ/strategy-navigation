package kz.aitu.navigation.strategy;

import kz.aitu.navigation.Location;
import kz.aitu.navigation.Route;

import java.util.List;
import java.util.Locale;

/** Walking: nearly straight path through pedestrian zones at 5 km/h. */
public class WalkStrategy implements RouteStrategy {

    private static final double PEDESTRIAN_DETOUR_FACTOR = 1.10;
    private static final double WALKING_SPEED_KMH = 5.0;

    @Override
    public String travelMode() {
        return "Walk";
    }

    @Override
    public Route buildRoute(Location from, Location to) {
        double distanceKm = from.straightLineDistanceKmTo(to) * PEDESTRIAN_DETOUR_FACTOR;
        int minutes = (int) Math.round(distanceKm / WALKING_SPEED_KMH * 60);
        List<String> steps = List.of(
                "Leave " + from.name() + " on foot",
                String.format(Locale.US, "Walk %.2f km using sidewalks and pedestrian crossings", distanceKm),
                "Arrive at " + to.name());
        return new Route(travelMode(), from, to, distanceKm, minutes, steps);
    }
}
