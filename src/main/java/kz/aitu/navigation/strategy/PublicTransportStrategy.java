package kz.aitu.navigation.strategy;

import kz.aitu.navigation.Location;
import kz.aitu.navigation.Route;

import java.util.List;
import java.util.Locale;

/** Bus: added later to demonstrate Open/Closed - no existing class except the client registry changed. */
public class PublicTransportStrategy implements RouteStrategy {

    private static final double BUS_ROUTE_DETOUR_FACTOR = 1.40;
    private static final double AVERAGE_SPEED_KMH = 22.0;
    private static final int WAITING_MINUTES = 8;

    @Override
    public String travelMode() {
        return "Bus";
    }

    @Override
    public Route buildRoute(Location from, Location to) {
        double distanceKm = from.straightLineDistanceKmTo(to) * BUS_ROUTE_DETOUR_FACTOR;
        int minutes = (int) Math.round(distanceKm / AVERAGE_SPEED_KMH * 60) + WAITING_MINUTES;
        List<String> steps = List.of(
                "Walk to the nearest bus stop near " + from.name(),
                String.format(Locale.US, "Ride the bus for %.2f km (wait ~%d min)", distanceKm, WAITING_MINUTES),
                "Get off and walk to " + to.name());
        return new Route(travelMode(), from, to, distanceKm, minutes, steps);
    }
}
