package kz.aitu.navigation;

import kz.aitu.navigation.strategy.RouteStrategy;

import java.util.Objects;


public class Navigator {

    private RouteStrategy strategy;

    public Navigator(RouteStrategy initialStrategy) {
        setStrategy(initialStrategy);
    }

    public void setStrategy(RouteStrategy strategy) {
        this.strategy = Objects.requireNonNull(strategy, "strategy must not be null");
    }

    public RouteStrategy getStrategy() {
        return strategy;
    }

    public Route planRoute(Location from, Location to) {
        return strategy.buildRoute(from, to);
    }
}
