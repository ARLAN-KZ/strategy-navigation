package kz.aitu.navigation;

import kz.aitu.navigation.strategy.CarStrategy;
import kz.aitu.navigation.strategy.CycleStrategy;
import kz.aitu.navigation.strategy.RouteStrategy;
import kz.aitu.navigation.strategy.WalkStrategy;

/** Dependency-free tests: run with scripts/run-tests.sh (exit code 1 on failure). */
public class NavigatorTest {

    private static final Location A = new Location("A", 51.0905, 71.3987);
    private static final Location B = new Location("B", 51.1327, 71.4033);
    private static int failures = 0;

    public static void main(String[] args) {
        switchingStrategyChangesResult();
        eachStrategyReportsOwnMode();
        walkingIsSlowerThanCyclingAndCyclingIsSlowerThanDriving();
        nullStrategyRejected();
        System.out.println(failures == 0 ? "ALL TESTS PASSED" : failures + " TEST(S) FAILED");
        System.exit(failures == 0 ? 0 : 1);
    }

    private static void switchingStrategyChangesResult() {
        Navigator navigator = new Navigator(new CarStrategy());
        Route car = navigator.planRoute(A, B);
        navigator.setStrategy(new WalkStrategy());
        Route walk = navigator.planRoute(A, B);
        check("switch at runtime changes mode", !car.travelMode().equals(walk.travelMode()));
    }

    private static void eachStrategyReportsOwnMode() {
        check("car mode", new Navigator(new CarStrategy()).planRoute(A, B).travelMode().equals("Car"));
        check("walk mode", new Navigator(new WalkStrategy()).planRoute(A, B).travelMode().equals("Walk"));
        check("cycle mode", new Navigator(new CycleStrategy()).planRoute(A, B).travelMode().equals("Cycle"));
    }

    private static void walkingIsSlowerThanCyclingAndCyclingIsSlowerThanDriving() {
        int car = new CarStrategy().buildRoute(A, B).durationMinutes();
        int cycle = new CycleStrategy().buildRoute(A, B).durationMinutes();
        int walk = new WalkStrategy().buildRoute(A, B).durationMinutes();
        check("walk > cycle > car duration", walk > cycle && cycle > car);
    }

    private static void nullStrategyRejected() {
        try {
            new Navigator(null);
            check("null strategy rejected", false);
        } catch (NullPointerException expected) {
            check("null strategy rejected", true);
        }
    }

    private static void check(String name, boolean condition) {
        System.out.println((condition ? "PASS  " : "FAIL  ") + name);
        if (!condition) failures++;
    }
}
