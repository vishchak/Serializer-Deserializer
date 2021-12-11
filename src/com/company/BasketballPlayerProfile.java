package com.company;

public class BasketballPlayerProfile {
    @Test
    private String name;
    @Test
    private double height;

    private Integer weight;
    @Test
    private double averagePointsPerGame;

    public BasketballPlayerProfile(String name, double height, Integer weight, double averagePointsPerGame) {
        this.name = name;
        this.height = height;
        this.weight = weight;
        this.averagePointsPerGame = averagePointsPerGame;
    }

    public BasketballPlayerProfile() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public double getAveragePointsPerGame() {
        return averagePointsPerGame;
    }

    public void setAveragePointsPerGame(Integer averagePointsPerGame) {
        this.averagePointsPerGame = averagePointsPerGame;
    }

    @Override
    public String toString() {
        return "BasketballPlayerProfile{" +
                "name='" + name + '\'' +
                ", height=" + height +
                ", weight=" + weight +
                ", averagePointsPerGame=" + averagePointsPerGame +
                '}';
    }
}
