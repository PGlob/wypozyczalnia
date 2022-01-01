package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Clock;
import java.util.Scanner;

import static com.company.Main.*;

public class Dostawczy extends Pojazd{

    int rozstawOsi;
    int zbiornikPaliwa;

    public Dostawczy(int typ, String marka, String kolor, int rozstawOsi, int zbiornikPaliwa, int rokProdukcji, int kosztWynajmu) {
        super(typ, marka, kolor, rokProdukcji, kosztWynajmu);
        this.rozstawOsi = rozstawOsi;
        this.zbiornikPaliwa = zbiornikPaliwa;
        if(!samochodyDostawcze.isEmpty()) ID = samochodyDostawcze.get(samochodyDostawcze.size()-1).ID + 1;
    }

    public Dostawczy(int ID, int typ, String marka, String kolor, int rozstawOsi, int zbiornikPaliwa, int rokProdukcji, int kosztWynajmu, boolean czyWypozyczony) {
        super(ID, typ, marka, kolor, rokProdukcji, kosztWynajmu, czyWypozyczony);
        this.rozstawOsi = rozstawOsi;
        this.zbiornikPaliwa = zbiornikPaliwa;
    }


    public static void zaladujSamochody() throws FileNotFoundException {
        File pojazdy = new File("pojazdy.txt");
        Scanner sc = new Scanner(pojazdy);

        while (sc.hasNextLine()) {
            String wiersz = sc.nextLine();
            String[] atrybuty = wiersz.split("\\|");
            int typ = Integer.parseInt(atrybuty[1]);
            if(typ == 2) {
                int ID = Integer.parseInt(atrybuty[0]);
                String marka = atrybuty[2];
                String kolor = atrybuty[3];
                int rozstawOsi = Integer.parseInt(atrybuty[4]);
                int zbiornikPaliwa = Integer.parseInt(atrybuty[5]);
                int rokProdukcji = Integer.parseInt(atrybuty[6]);
                int kosztWynajmu = Integer.parseInt(atrybuty[7]);
                boolean czyWypozyczony = Boolean.parseBoolean(atrybuty[8]);

                samochodyDostawcze.add(new Dostawczy(ID,typ,marka,kolor,rozstawOsi,zbiornikPaliwa,rokProdukcji,kosztWynajmu,czyWypozyczony));
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
        System.out.println("Podaj rozstaw osi");
        int rozstawOsi = sc.nextInt();
        System.out.println("Podaj pojemnosc zbiornika");
        int zbiornikPaliwa = sc.nextInt();
        System.out.println("Podaj rok produkcji");
        int rokProdukcji = sc.nextInt();
        System.out.println("Podaj koszt wynajmu");
        int kosztWynajmu = sc.nextInt();

        samochodyDostawcze.add(new Dostawczy(2, marka, kolor, rozstawOsi, zbiornikPaliwa, rokProdukcji, kosztWynajmu));
        System.out.println("Samochód został dodany");
    }

    public static void usunPojazd(int numer) {
        if (numer - 1 > samochodyDostawcze.size() || numer < 1) {
            System.out.println("Wybrano złą Lp. samochodu");
            return;
        }
        Dostawczy sam = samochodyDostawcze.get(numer-1);
        for (int i = 0; i < wypozyczoneSamochody.size(); i++) {
            Wypozyczenia wyp = wypozyczoneSamochody.get(i);
            if(wyp.ID_samochodu == sam.ID && wyp.typSamochodu == sam.typ){
                wypozyczoneSamochody.remove(i);
            }
        }
        samochodyDostawcze.remove(numer-1);
        System.out.println("Usunięto samochód");
    }

    public static void edytujPojazd(int numer) {
        if (numer - 1 > samochodyDostawcze.size() || numer < 1) {
            System.out.println("Wybrano złą Lp. samochodu");
            return;
        }
        Dostawczy sam = samochodyDostawcze.get(numer-1);
        Scanner sc = new Scanner(System.in);
        System.out.println("Który element samochodu chcesz edytować:");
        System.out.println("1.Marka\n2.Kolor\n3.Rozstaw osi(mm)\n4.Zbiornik paliwa\n5.Rok produkcji\n6.Koszt wynajmu\n7.Anuluj");
        int wybor = sc.nextInt();
        switch(wybor) {
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
                sam.rozstawOsi = sc.nextInt();
                System.out.println("Edytowano samochód");
                break;
            case 4:
                System.out.println("Nowa liczba miejsc:");
                sam.zbiornikPaliwa = sc.nextInt();
                System.out.println("Edytowano samochód");
                break;
            case 5:
                System.out.println("Nowa masa:");
                sam.rokProdukcji = sc.nextInt();
                System.out.println("Edytowano samochód");
                break;
            case 6:
                System.out.println("Nowy rok produkcji:");
                sam.kosztWynajmu = sc.nextInt();
                System.out.println("Edytowano samochód");
                break;
            case 7:
                break;
            default:
                System.out.println("Podano zły numer");
                break;
        }

    }

    public static void wypozyczPojazd(int numer) throws IOException {
        if (numer - 1 > samochodyDostawcze.size() || numer < 1) {
            System.out.println("Wybrano złą Lp. samochodu");
            return;
        }
        samochodyDostawcze.get(numer-1).czyWypozyczony = true;
        wypozyczoneSamochody.add(new Wypozyczenia(SessionID,
                samochodyDostawcze.get(numer-1).ID,
                samochodyDostawcze.get(numer-1).typ,
                Clock.systemUTC().instant().toString()));
        Wypozyczenia.zapiszDoPliku();
        Pojazd.zapiszDoPliku();
        System.out.println("Samochód został wypożyczony");
    }

    public static void oddajPojazd(int ID) throws IOException {
        for (int i = 0; i < samochodyDostawcze.size(); i++) {
            if(samochodyDostawcze.get(i).ID == ID){
                samochodyDostawcze.get(i).czyWypozyczony = false;
                for (int j = 0; j < wypozyczoneSamochody.size(); j++) {
                    if(wypozyczoneSamochody.get(j).typSamochodu == 2 && wypozyczoneSamochody.get(j).ID_samochodu == ID)
                        wypozyczoneSamochody.get(j).dataOddania = Clock.systemUTC().instant().toString();
                }
                Pojazd.zapiszDoPliku();
                Wypozyczenia.zapiszDoPliku();
                return;
            }
        }
        System.out.println("Błędne ID");
    }

    public static Dostawczy zwrocPojazd(int ID) {
        for (int i = 0; i < samochodyDostawcze.size(); i++) {
            if (samochodyDostawcze.get(i).ID == ID)
                return samochodyDostawcze.get(i);
        }
        return null;
    }

}
