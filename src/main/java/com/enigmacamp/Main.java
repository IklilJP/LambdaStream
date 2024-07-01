package com.enigmacamp;

import com.enigmacamp.outlet.Outlet;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;


public class Main {

    public static void main(String[] args) {
        List<Outlet> outletList = new ArrayList<>();
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");

        // Input data dari tabel
        try {
            outletList.add(new Outlet("Manggarai", formatter.parse("01-01-2022"), 1000, 10));
            outletList.add(new Outlet("BSD", formatter.parse("01-01-2022"), 750, 19));
            outletList.add(new Outlet("Manggarai", formatter.parse("12-01-2022"), 1025, 32));
            outletList.add(new Outlet("Ragunan", formatter.parse("01-01-2022"), 375, 12));
            outletList.add(new Outlet("Ragunan", formatter.parse("02-01-2022"), 397, 13));
            outletList.add(new Outlet("BSD", formatter.parse("03-01-2022"), 803, 20));
            outletList.add(new Outlet("Pasar Minggu", formatter.parse("01-01-2022"), 35, 3));
            outletList.add(new Outlet("Pasar Minggu", formatter.parse("12-01-2022"), 5, 1));
            outletList.add(new Outlet("Manggarai", formatter.parse("15-01-2022"), 1005, 31));
        } catch (ParseException e) {
            e.printStackTrace(); // Handle parsing exception
        }
        //1.carillah outlet dengan omset terbesar dan terkecil
        Outlet maxOmset = outletList.stream()
                .max(Comparator.comparing(Outlet::getOmset))
                .get();
        Outlet minOmset = outletList.stream()
                .min(Comparator.comparing(Outlet::getOmset))
                .get();
        System.out.println("Outlet dengan omset terbesar: " + maxOmset);
        System.out.println("Outlet dengan omset terkecil: " + minOmset);

        int min = outletList.stream()
                .mapToInt(Outlet::getOmset)
                .min()
                .getAsInt();
        int max = outletList.stream()
                .mapToInt(Outlet::getOmset)
                .max()
                .getAsInt();
        System.out.println("Outlet dengan omset terbesar: " + max);
        System.out.println("Outlet dengan omset terkecil: " + min);
        System.out.println();

        //2.carilah data untuk omset yang kurang dari 600
        outletList.stream()
                .filter(outlet -> outlet.getOmset() < 600)
                .forEach(low -> System.out.println("outlet dengan omset kurang dari 600 : " + low));
        System.out.println();


        //3.tanggal berapapakah total transaski terkecil
        outletList.stream()
                .min(Comparator.comparing(Outlet::getTotalTrx))
                .ifPresent(minTrx -> System.out.println("Total transaksi terkecil: " + minTrx));
        System.out.println();

        Outlet minTrx = outletList.stream()
                .min(Comparator.comparing(Outlet::getTotalTrx))
                .get();
        System.out.println("Total transaksi terkecil: " + minTrx.getPeriod());
        System.out.println();


        //4.berapakah total omset dan total transaksi per outlet
        outletList.stream()
                .collect(Collectors.groupingBy(Outlet::getOutlet))
                .forEach((key, value) -> {
                    System.out.println("Outlet: " + key);
                    System.out.println("Total Omset: " + value.stream().mapToInt(Outlet::getOmset).sum());
                    System.out.println("Total Transaksi: " + value.stream().mapToInt(Outlet::getTotalTrx).sum());
                    System.out.println();
                });
        System.out.println();

        List<Outlet> totalPerOutlet = outletList.stream()
                .collect(Collectors.groupingBy(Outlet::getOutlet))
                .values()
                .stream()
                .map(list -> {
                    int totalOmset = list.stream()
                            .mapToInt(Outlet::getOmset)
                            .sum();
                    int totalTrx = list.stream()
                            .mapToInt(Outlet::getTotalTrx)
                            .sum();
                    return new Outlet(list.get(0).getOutlet(), list.get(0).getPeriod(), totalOmset, totalTrx);
                })
                .toList();
        totalPerOutlet.forEach(System.out::println);
        System.out.println();

        //5. berapakah total omset dan total transaki yang terjadi

        int totalOmset = outletList.stream()
                .mapToInt(Outlet::getOmset)
                .sum();

        int totalTransaksi = outletList.stream()
                .mapToInt(Outlet::getTotalTrx)
                .sum();

        System.out.println("Total Omset: " + totalOmset + "&" + "Total Transaksi: " + totalTransaksi);

    }
}