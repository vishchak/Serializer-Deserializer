package com.company;

public class BasketballPlayerProfile {
    @Test
    private String name;
    @Test
    private double height;

    private Integer weight;
    @Test
    private double averagePointsPerGame;
    @Test
    private Rebounds averageRebounds;

    public BasketballPlayerProfile() {
    }

    public BasketballPlayerProfile(String name, double height, Integer weight, double averagePointsPerGame, Rebounds averageRebounds) {
        this.name = name;
        this.height = height;
        this.weight = weight;
        this.averagePointsPerGame = averagePointsPerGame;
        this.averageRebounds = averageRebounds;
    }

    public void setAverageRebounds(Rebounds averageRebounds) {
        this.averageRebounds = averageRebounds;
    }

    @Override
    public String toString() {
        return "BasketballPlayerProfile{" +
                "name='" + name + '\'' +
                ", height=" + height +
                ", weight=" + weight +
                ", averagePointsPerGame=" + averagePointsPerGame +
                ", averageRebounds=" + averageRebounds +
                '}';
    }
}
