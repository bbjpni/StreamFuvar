package com.company;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Feladat {
    private ArrayList<Taxi> lista;

    public Feladat()
    {
        lista = Beolvasas();
        utazasokSzama();
        azonTaxiBevetelFuvar(6185);
        fizetesiModokStatisztika();
        osszestaxiMegtettTavolsaga();
        leghosszabfuvarAdatai();
    }

    private ArrayList<Taxi> Beolvasas() {
        ArrayList<Taxi> back = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader("fuvar.csv"));
            String sor = br.readLine();
            sor = br.readLine();
            while (sor != null) {
                back.add(new Taxi(sor));
                sor = br.readLine();
            }
            br.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return back;
    }

    public void utazasokSzama()
    {
        long temp = lista.stream()
                .count();
        System.out.println("3.feladat: "+temp+" fuvar");

    }

    public void azonTaxiBevetelFuvar(int id)
    {
        Double bevetel = lista.stream()
                .filter(t-> t.getAzon()==id)
                .mapToDouble(t-> t.getViteldij())
                .sum();
        long fuvarok = lista.stream()
                .filter(t-> t.getAzon()==id)
                .count();
        System.out.println("4.feladat: "+fuvarok+" fuvar alatt: "+bevetel+"$");

    }

    public void fizetesiModokStatisztika()
    {
        System.out.println("5.feladat:");
        List tipusok = lista.stream()
                .distinct()
                .map(f-> f.getFizMod())
                .collect(Collectors.toList());
        for (int i = 0; i < tipusok.size(); i++) {
            String mod = (String) tipusok.get(i);
            long db = lista.stream()
                    .filter(t-> t.getFizMod().equals(mod))
                    .count();
            System.out.println(mod+": "+db+" fuvar");
        }


        System.out.println("5.feladat");

    }

    public void osszestaxiMegtettTavolsaga()
    {
        double osszKm = lista.stream()
                .mapToDouble(m-> m.getMegtettTavolsag())
                .sum()*1.6;

        System.out.print("6.feladat: "+Math.round(osszKm*100)/100.0+"Km");
    }

    public void leghosszabfuvarAdatai()
    {

        System.out.println("7.feladat: A leghosszab fuvar");
        lista.stream()
                .sorted((a, b)-> -1*Integer.compare(a.getIdotartam(), b.getIdotartam()))
                .limit(1)
                .forEach(i-> System.out.println(String.format("\tFuvar %d hossza: másodperc\n" +
                        "\tTaxi azonosító:%d\n" +
                        "\tMegtett tárloság: %.1f km\n" +
                        "\tViteldíj: %.3f$"
                        , i.getIdotartam(), i.getAzon(), i.getMegtettTavolsag()*1.6, i.getViteldij())));
    }
}
