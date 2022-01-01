package com.company;

import java.io.*;
import static com.company.Main.samochodyOsobowe;
import static com.company.Main.samochodyDostawcze;

public abstract class Pojazd {

    public String marka;
    public String kolor;
    public int ID = 1;
    public int typ;
    public int rokProdukcji;
    public int kosztWynajmu;
    public boolean czyWypozyczony = false;


    public Pojazd(int typ, String marka, String kolor, int rokProdukcji, int kosztWynajmu) {
        this.marka = marka;
        this.kolor = kolor;
        this.typ = typ;
        this.rokProdukcji = rokProdukcji;
        this.kosztWynajmu = kosztWynajmu;
    }

    public Pojazd(int ID, int typ, String marka, String kolor, int rokProdukcji, int kosztWynajmu, boolean czyWypozyczony) {
        this.marka = marka;
        this.kolor = kolor;
        this.typ = typ;
        this.ID = ID;
        this.rokProdukcji = rokProdukcji;
        this.kosztWynajmu = kosztWynajmu;
        this.czyWypozyczony = czyWypozyczony;
    }

    public String getMarka() {
        return marka;
    }
    public void setMarka(String marka) {
        this.marka = marka;
    }

    public String getKolor() {
        return kolor;
    }
    public void setKolor(String kolor) {
        this.kolor = kolor;
    }

    public int getID() {
        return ID;
    }
    public void setID(int ID) {
        this.ID = ID;
    }

    public int getRokProdukcji() {
        return rokProdukcji;
    }
    public void setRokProdukcji(int rokProdukcji) {
        this.rokProdukcji = rokProdukcji;
    }

    public int getKosztWynajmu() {
        return kosztWynajmu;
    }
    public void setKosztWynajmu(int kosztWynajmu) {
        this.kosztWynajmu = kosztWynajmu;
    }

    public static void zapiszDoPliku() throws IOException {
        File pojazdy = new File("pojazdy.txt");
        FileWriter fw = new FileWriter(pojazdy);
        PrintWriter pw = new PrintWriter(fw);
        String pojazd;
        //ID, typ, marka, kolor, rodzajPaliwa,liczbaMiejsc, masa, rokProdukcji, kosztWynajmu
        for (int i = 0; i < samochodyOsobowe.size(); i++) {
            pojazd = samochodyOsobowe.get(i).ID + "|" + samochodyOsobowe.get(i).typ + "|" + samochodyOsobowe.get(i).marka +
                    "|" + samochodyOsobowe.get(i).kolor + "|" + samochodyOsobowe.get(i).rodzajPaliwa + "|" + samochodyOsobowe.get(i).liczbaMiejsc +
                    "|" + samochodyOsobowe.get(i).masa + "|" + samochodyOsobowe.get(i).rokProdukcji + "|" + samochodyOsobowe.get(i).kosztWynajmu +
                    "|" + samochodyOsobowe.get(i).czyWypozyczony;
            pw.println(pojazd);
        }
        //ID,marka,kolor,rozstawOsi,zbiornikPaliwa,rokProdukcji,kosztWynajmu
        for(int i = 0; i < samochodyDostawcze.size(); i++) {
            pojazd = samochodyDostawcze.get(i).ID + "|" + samochodyDostawcze.get(i).typ + "|" + samochodyDostawcze.get(i).marka + "|" +
                    samochodyDostawcze.get(i).kolor + "|" + samochodyDostawcze.get(i).rozstawOsi + "|" +
                    samochodyDostawcze.get(i).zbiornikPaliwa + "|" + samochodyDostawcze.get(i).rokProdukcji + "|" +
                    samochodyDostawcze.get(i).kosztWynajmu + "|" + samochodyDostawcze.get(i).czyWypozyczony;
            pw.println(pojazd);
        }
        pw.close();
        fw.close();
        System.out.println("Pojazdy zostały zapisane");
    }

    public static void pokazWszystkiePojazdy(){
        System.out.println("Samochody osobowe:");
        for (int i = 0; i < samochodyOsobowe.size(); i++) {
            Osobowy os = samochodyOsobowe.get(i);
                System.out.println(i+1 + "." + " ID:" + os.ID + " Marka:" + os.marka + " Kolor:" + os.kolor + " Rodzaj paliwa:" + os.rodzajPaliwa +
                        " Liczba miejsc:" + os.liczbaMiejsc + " Masa(kg):" + os.masa + " Rok produkcji:" + os.rokProdukcji +
                        " Koszt wynajmu:" + os.kosztWynajmu + " Wypożyczony:" + os.czyWypozyczony);
        }

        System.out.println("Samochody dostawcze:");
        for (int i = 0; i < samochodyDostawcze.size(); i++) {
            Dostawczy dos = samochodyDostawcze.get(i);
                System.out.println(i+1 + "." + " ID:" + dos.ID + " Marka:" + dos.marka + " Kolor:" + dos.kolor + " Rozstaw osi(mm):" + dos.rozstawOsi +
                        " Zbiornik paliwa(l):" + dos.zbiornikPaliwa + " Rok produkcji:" + dos.rokProdukcji + " Koszt wynajmu:" + dos.kosztWynajmu +
                " Wypożyczony:" + dos.czyWypozyczony);

        }
    }

    public static void pokazPojazdy(){
        System.out.println("Samochody osobowe:");
        for (int i = 0; i < samochodyOsobowe.size(); i++) {
            Osobowy os = samochodyOsobowe.get(i);
            if(!os.czyWypozyczony)
            System.out.println(i+1 + "." + " ID:" + os.ID + " Marka:" + os.marka + " Kolor:" + os.kolor + " Rodzaj paliwa:" + os.rodzajPaliwa +
                    " Liczba miejsc:" + os.liczbaMiejsc + " Masa(kg):" + os.masa + " Rok produkcji:" + os.rokProdukcji +
                    " Koszt wynajmu:" + os.kosztWynajmu);
        }

        System.out.println("Samochody dostawcze:");
        for (int i = 0; i < samochodyDostawcze.size(); i++) {
            Dostawczy dos = samochodyDostawcze.get(i);
            if(!dos.czyWypozyczony)
            System.out.println(i+1 + "." + " ID:" + dos.ID + " Marka:" + dos.marka + " Kolor:" + dos.kolor + " Rozstaw osi(mm):" + dos.rozstawOsi +
                    " Zbiornik paliwa(l):" + dos.zbiornikPaliwa + " Rok produkcji:" + dos.rokProdukcji + " Koszt wynajmu:" + dos.kosztWynajmu);

        }

    }


}
