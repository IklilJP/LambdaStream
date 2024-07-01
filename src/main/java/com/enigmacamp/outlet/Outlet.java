package com.enigmacamp.outlet;


import java.util.Date;


public class Outlet{
    private final String outlet ;
    private final Date period;
    private final int omset;
    private final int totalTrx;

    public Outlet(String outlet, Date period, int omset, int totalTrx) {
        this.outlet = outlet;
        this.period = period;
        this.omset = omset;
        this.totalTrx = totalTrx;
    }

    public String getOutlet() {
        return outlet;
    }



    public Date getPeriod() {
        return period;
    }



    public int getOmset() {
        return omset;
    }



    public int getTotalTrx() {
        return totalTrx;
    }



    @Override
    public String toString() {
        return "Outlet{" +
                "outlet='" + outlet + '\'' +
                ", period=" + period +
                ", omset=" + omset +
                ", totalTrx=" + totalTrx +
                '}';
    }
}
