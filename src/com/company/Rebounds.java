package com.company;

public class Rebounds {
    @Test
    private double defensiveRebounds;
    @Test
    private double offensiveRebounds;

    public Rebounds() {
    }

    public Rebounds(double defenciveRebounds, double offensiveRebounds) {
        this.defensiveRebounds = defenciveRebounds;
        this.offensiveRebounds = offensiveRebounds;
    }

    @Override
    public String toString() {
        return "Rebounds{" +
                "defenciveRebounds=" + defensiveRebounds +
                ", offenciveRebounds=" + offensiveRebounds +
                '}';
    }
}
