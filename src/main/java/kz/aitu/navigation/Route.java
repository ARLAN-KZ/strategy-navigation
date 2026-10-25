package kz.aitu.navigation;

import java.util.List;
import java.util.Locale;

/** Immutable result of route planning. */
public record Route(String travelMode, Location from, Location to,
                    double distanceKm, int durationMinutes, List<String> steps) {

    public Route {
        steps = List.copyOf(steps);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format(Locale.US, "[%s] %s -> %s: %.2f km, ~%d min%n",
                travelMode, from.name(), to.name(), distanceKm, durationMinutes));
        for (int i = 0; i < steps.size(); i++) {
            sb.append(String.format("   %d. %s%n", i + 1, steps.get(i)));
        }
        return sb.toString();
    }
}
