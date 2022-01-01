package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Clock;
import java.util.Scanner;

import static com.company.Main.wypozyczoneSamochody;
import static com.company.Main.samochodyOsobowe;
import static com.company.Main.SessionID;

public class Osobowy extends Pojazd {

    String rodzajPaliwa;
    int liczbaMiejsc;
    int masa;

    public Osobowy(int typ, String marka, String kolor, String rodzajPaliwa, int liczbaMiejsc, int masa, int rokProdukcji, int kosztWynajmu) {
        super(typ, marka, kolor, rokProdukcji, kosztWynajmu);
        this.rodzajPaliwa = rodzajPaliwa;
        this.liczbaMiejsc = liczbaMiejsc;
        this.masa = masa;
        if(!samochodyOsobowe.isEmpty()) ID = samochodyOsobowe.get(samochodyOsobowe.size()-1).ID + 1;
    }

    public Osobowy(int ID, int typ, String marka, String kolor, String rodzajPaliwa, int liczbaMiejsc, int masa, int rokProdukcji, int kosztWynajmu, boolean czyWypozyczony) {
        super(ID, typ, marka, kolor, rokProdukcji, kosztWynajmu, czyWypozyczony);
        this.rodzajPaliwa = rodzajPaliwa;
        this.liczbaMiejsc = liczbaMiejsc;
        this.masa = masa;
    }

    //ładuje samochody osobowe z pliku do listy samochodyOsobowe
    public static void zaladujSamochody() throws FileNotFoundException {
        File pojazdy = new File("pojazdy.txt");
        Scanner sc = new Scanner(pojazdy);

        while (sc.hasNextLine()) {
            String wiersz = sc.nextLine();
            String[] atrybuty = wiersz.split("\\|");
            int typ = Integer.parseInt(atrybuty[1]);
            if(typ == 1) {
                int ID = Integer.parseInt(atrybuty[0]);
                String marka = atrybuty[2];
                String kolor = atrybuty[3];
                String rodzajPaliwa = atrybuty[4];
                int liczbaMiejsc = Integer.parseInt(atrybuty[5]);
                int masa = Integer.parseInt(atrybuty[6]);
                int rokProdukcji = Integer.parseInt(atrybuty[7]);
                int kosztWynajmu = Integer.parseInt(atrybuty[8]);
                boolean czyWypozyczony = Boolean.parseBoolean(atrybuty[9]);
                samochodyOsobowe.add(new Osobowy(ID, typ, marka, kolor, rodzajPaliwa,liczbaMiejsc, masa, rokProdukcji, kosztWynajmu, czyWypozyczony));
            }
        }
        sc.close();
    }

    public static void dodajPojazd() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Podaj marke");
        String marka = sc.next();
        System.out.println("Podaj kolor");
        String kolor = sc.next();
        System.out.println("Podaj rodzaj paliwa");
        String rodzajPaliwa = sc.next();
        System.out.println("Podaj liczbe miejsc");
        int liczbaMiejsc = sc.nextInt();
        System.out.println("Podaj mase (kg)");
        int masa = sc.nextInt();
        System.out.println("Podaj rok produkcji");
        int rokProdukcji = sc.nextInt();
        System.out.println("Podaj koszt wynajmu");
        int kosztWynajmu = sc.nextInt();

        samochodyOsobowe.add(new Osobowy(1, marka, kolor, rodzajPaliwa, liczbaMiejsc, masa, rokProdukcji, kosztWynajmu));
        System.out.println("Samochód został dodany");
    }

    public static void usunPojazd(int numer) {

        if(numer > samochodyOsobowe.size() || numer < 1){
            System.out.println("Wybrano złą Lp. samochodu");
            return;
        }
        Osobowy sam = samochodyOsobowe.get(numer-1);
        for (int i = 0; i < wypozyczoneSamochody.size(); i++) {
            Wypozyczenia wyp = wypozyczoneSamochody.get(i);
            if(wyp.ID_samochodu == sam.ID && wyp.typSamochodu == sam.typ){
                wypozyczoneSamochody.remove(i);
            }
        }
        samochodyOsobowe.remove(numer-1);
        System.out.println("Usunięto samochód");
    }

    public static void edytujPojazd(int numer) {
        if(numer > samochodyOsobowe.size() || numer < 1){
            System.out.println("Wybrano złą Lp. samochodu");
            return;
        }
        Osobowy sam = samochodyOsobowe.get(numer-1);
        Scanner sc = new Scanner(System.in);
        System.out.println("Który element samochodu chcesz edytować:");
        System.out.println("1.Marka\n2.Kolor\n3.Rodzaj paliwa\n4.Liczba miejsc\n5.Masa(kg)\n6.Rok produkcji\n7.Koszt Wynajmu\n8.Anuluj");
        int wybor = sc.nextInt();
        switch(wybor){
            case 1:
                System.out.println("Nowa marka:");
                sam.marka = sc.next();
                System.out.println("Edytowano samochód");
                break;
            case 2:
                System.out.println("Nowy kolor:");
                sam.kolor = sc.next();
                System.out.println("Edytowano samochód");
                break;
            case 3:
                System.out.println("Nowy rodzaj paliwa:");
                sam.rodzajPaliwa = sc.next();
                System.out.println("Edytowano samochód");
                break;
            case 4:
                System.out.println("Nowa liczba miejsc:");
                sam.liczbaMiejsc = sc.nextInt();
                System.out.println("Edytowano samochód");
                break;
            case 5:
                System.out.println("Nowa masa:");
                sam.masa = sc.nextInt();
                System.out.println("Edytowano samochód");
                break;
            case 6:
                System.out.println("Nowy rok produkcji:");
                sam.rokProdukcji = sc.nextInt();
                System.out.println("Edytowano samochód");
                break;
            case 7:
                System.out.println("Nowy koszt wynajmu:");
                sam.kosztWynajmu = sc.nextInt();
                System.out.println("Edytowano samochód");
                break;
            case 8:
                break;
            default:
                System.out.println("Podano zły numer");
                break;
        }
    }

    public static void wypozyczPojazd(int numer) throws IOException {
            if(numer > samochodyOsobowe.size() || numer < 1){
                System.out.println("Wybrano złą Lp. samochodu");
                return;
            }
            samochodyOsobowe.get(numer-1).czyWypozyczony = true;
            wypozyczoneSamochody.add(new Wypozyczenia(SessionID,
                    samochodyOsobowe.get(numer-1).ID,
                    samochodyOsobowe.get(numer-1).typ,
                    Clock.systemUTC().instant().toString()));
            Wypozyczenia.zapiszDoPliku();
            Pojazd.zapiszDoPliku();
            System.out.println("Samochód został wypożyczony");
    }

    public static void oddajPojazd(int ID) throws IOException {
        for (int i = 0; i < samochodyOsobowe.size(); i++) {
            if(samochodyOsobowe.get(i).ID == ID){
            samochodyOsobowe.get(i).czyWypozyczony = false;
                for (int j = 0; j < wypozyczoneSamochody.size(); j++) {
                    if(wypozyczoneSamochody.get(j).typSamochodu == 1 && wypozyczoneSamochody.get(j).ID_samochodu == ID)
                        wypozyczoneSamochody.get(j).dataOddania = Clock.systemUTC().instant().toString();
                }
                Pojazd.zapiszDoPliku();
                Wypozyczenia.zapiszDoPliku();
            return;
            }
        }
        System.out.println("Błędne ID");
    }

    public static Osobowy zwrocPojazd(int ID){
        for (int i = 0; i < samochodyOsobowe.size(); i++) {
            if(samochodyOsobowe.get(i).ID == ID)
                return samochodyOsobowe.get(i);
        }
        return null;
    }
}


