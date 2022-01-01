package com.company;

import java.io.*;
import java.util.Scanner;
import static com.company.Main.wypozyczoneSamochody;

public class Wypozyczenia {

    int ID_wypozyczenia = 1;
    int ID_uzytkownika;
    int ID_samochodu;
    int typSamochodu;
    String dataWypozyczenia;
    String dataOddania = "null";

    public Wypozyczenia(int ID_uzytkownika, int ID_samochodu, int typSamochodu, String dataWypozyczenia) {
        this.ID_uzytkownika = ID_uzytkownika;
        this.ID_samochodu = ID_samochodu;
        this.typSamochodu = typSamochodu;
        this.dataWypozyczenia = dataWypozyczenia;
        if(!wypozyczoneSamochody.isEmpty()) ID_wypozyczenia = wypozyczoneSamochody.get(wypozyczoneSamochody.size()-1).ID_wypozyczenia + 1;
    }

    public Wypozyczenia(int ID_wypozyczenia, int ID_uzytkownika, int ID_samochodu, int typSamochodu, String dataWypozyczenia, String dataOddania) {
        this.ID_wypozyczenia = ID_wypozyczenia;
        this.ID_uzytkownika = ID_uzytkownika;
        this.ID_samochodu = ID_samochodu;
        this.typSamochodu = typSamochodu;
        this.dataWypozyczenia = dataWypozyczenia;
        this.dataOddania = dataOddania;
    }

    public int getID_wypozyczenia() {
        return ID_wypozyczenia;
    }
    public void setID_wypozyczenia(int ID_wypozyczenia) {
        this.ID_wypozyczenia = ID_wypozyczenia;
    }

    public int getID_uzytkownika() {
        return ID_uzytkownika;
    }
    public void setID_uzytkownika(int ID_uzytkownika) {
        this.ID_uzytkownika = ID_uzytkownika;
    }

    public int getID_samochodu() {
        return ID_samochodu;
    }
    public void setID_samochodu(int ID_samochodu) {
        this.ID_samochodu = ID_samochodu;
    }

    public String getDataWypozyczenia() {
        return dataWypozyczenia;
    }
    public void setDataWypozyczenia(String dataWypozyczenia) {
        this.dataWypozyczenia = dataWypozyczenia;
    }

    public String getDataOddania() {
        return dataOddania;
    }
    public void setDataOddania(String dataOddania) {
        this.dataOddania = dataOddania;
    }

    public static int getostatnieID() throws FileNotFoundException {
        File users = new File("wypozyczenia.txt");
        Scanner sc = new Scanner(users);
        int ID = 0;
        while(sc.hasNextLine()){
            String wiersz = sc.nextLine();
            String[] atrybuty = wiersz.split("\\|");
            String rentID = atrybuty[0];
            ID = Integer.parseInt(rentID);
        }
        return ID;
    }

    public static void zaladujWypozyczenia() throws IOException {
        File wypozyczenia = new File("wypozyczenia.txt");
        Scanner sc = new Scanner(wypozyczenia);

        while (sc.hasNextLine()) {
            String wiersz = sc.nextLine();
            String[] atrybuty = wiersz.split("\\|");
            wypozyczoneSamochody.add(new Wypozyczenia(
                    Integer.parseInt(atrybuty[0]),
                    Integer.parseInt(atrybuty[1]),
                    Integer.parseInt(atrybuty[2]),
                    Integer.parseInt(atrybuty[3]),
                    atrybuty[4], atrybuty[5]));
        }
    }

    public static void pokazWszystkieWypozyczenia() {
            System.out.println("Wypożyczone samochody");
            for (int i = 0; i < wypozyczoneSamochody.size(); i++) {
                Wypozyczenia wyp = wypozyczoneSamochody.get(i);
                System.out.println("ID_wyp:" + wyp.ID_wypozyczenia + " ID_uzytkownika:" +
                        wyp.ID_uzytkownika + " ID_samochodu:" + wyp.ID_samochodu
                        + " Typ_samochodu:" + wyp.typSamochodu + " Data_Wypożyczenia:" + wyp.dataWypozyczenia
                        + " Data_Oddania:" + wyp.dataOddania);
            }
        }

    public static void pokazWypozyczeniaUzytkownika(int ID){
        String typ;
        String marka;
        String kolor;
        int kosztWynajmu;
        Wypozyczenia wyp;
        for (int i = 0; i < wypozyczoneSamochody.size(); i++) {
            wyp = wypozyczoneSamochody.get(i);
            if(wyp.typSamochodu == 1){
                typ = "Osobowy";
                Osobowy os = Osobowy.zwrocPojazd(wyp.ID_samochodu);
                marka = os.marka;
                kolor = os.kolor;
                kosztWynajmu = os.kosztWynajmu;
            } else {
                typ = "Dostawczy";
                Dostawczy os = Dostawczy.zwrocPojazd(wyp.ID_samochodu);
                marka = os.marka;
                kolor = os.kolor;
                kosztWynajmu = os.kosztWynajmu;
            }
            if(wyp.ID_uzytkownika == ID && wyp.dataOddania.equals("null")){
                System.out.println("ID_wypożyczenia:" + wyp.ID_wypozyczenia + " ID_samochodu:" + wyp.ID_samochodu +
                        " Typ_samochodu:" + typ + " Marka_samochodu:" + marka + " Kolor_samochodu:" + kolor + " Koszt_wynajmu:" + kosztWynajmu);
            }
        }
    }


        public static void zapiszDoPliku() throws IOException {
            File wypozyczenia = new File("wypozyczenia.txt");
            FileWriter fw = new FileWriter(wypozyczenia);
            PrintWriter pw = new PrintWriter(fw);

            for (int i = 0; i < wypozyczoneSamochody.size(); i++) {
                Wypozyczenia wyp = wypozyczoneSamochody.get(i);
                String pojazd = wyp.ID_wypozyczenia + "|" + wyp.ID_uzytkownika + "|" + wyp.ID_samochodu + "|" + wyp.typSamochodu +
                        "|" + wyp.dataWypozyczenia + "|" + wyp.dataOddania;
                pw.println(pojazd);
            }
            pw.close();
            fw.close();
            System.out.println("Wypożyczenia został zapisane");
        }


}
