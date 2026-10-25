package kz.aitu.navigation;

import kz.aitu.navigation.strategy.CarStrategy;
import kz.aitu.navigation.strategy.CycleStrategy;
import kz.aitu.navigation.strategy.RouteStrategy;
import kz.aitu.navigation.strategy.WalkStrategy;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

/** Client: chooses and configures strategies; switches them at runtime. */
public class Main {

    public static void main(String[] args) {
        Location aitu = new Location("Astana IT University", 51.0905, 71.3987);
        Location khanShatyr = new Location("Khan Shatyr", 51.1327, 71.4033);

        Map<String, RouteStrategy> strategies = registerStrategies();
        Navigator navigator = new Navigator(strategies.get("car"));

        if (args.length > 0 && args[0].equals("--interactive")) {
            runInteractive(navigator, strategies, aitu, khanShatyr);
        } else {
            runDemo(navigator, strategies, aitu, khanShatyr);
        }
    }

    /** The only place that knows concrete strategy classes. */
    private static Map<String, RouteStrategy> registerStrategies() {
        Map<String, RouteStrategy> strategies = new LinkedHashMap<>();
        strategies.put("car", new CarStrategy());
        strategies.put("walk", new WalkStrategy());
        strategies.put("cycle", new CycleStrategy());
        return strategies;
    }

    private static void runDemo(Navigator navigator, Map<String, RouteStrategy> strategies,
                                Location from, Location to) {
        System.out.println("=== Runtime strategy switching ===");
        for (RouteStrategy strategy : strategies.values()) {
            navigator.setStrategy(strategy);   // switch algorithm at runtime
            System.out.println(navigator.planRoute(from, to));
        }
    }

    private static void runInteractive(Navigator navigator, Map<String, RouteStrategy> strategies,
                                       Location from, Location to) {
        Scanner in = new Scanner(System.in);
        System.out.println("Route: " + from.name() + " -> " + to.name());
        while (true) {
            System.out.println("Choose mode " + strategies.keySet() + " or 'exit':");
            String choice = in.nextLine().trim().toLowerCase();
            if (choice.equals("exit")) {
                return;
            }
            RouteStrategy chosen = strategies.get(choice);
            if (chosen == null) {
                System.out.println("Unknown mode: " + choice);
                continue;
            }
            navigator.setStrategy(chosen);
            System.out.println(navigator.planRoute(from, to));
        }
    }
}
