package com.company;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import static com.company.Main.*;

public class Menu {

    public int typMenu;

    public Menu(int typMenu) {
        this.typMenu = typMenu;
    }

    //rejestruje użytkownika i zapisuje date oraz godzine rejestracji
    public static void Rejestracja() throws IOException {
        Uzytkownik.setOstatnieID(Uzytkownik.getOstatnieID());
        Scanner sc = new Scanner(System.in);
        System.out.println("Podaj nazwe użytkownika:");
        String nazwa = sc.next();
        System.out.println("Podaj haslo:");
        String haslo = sc.next();
        Uzytkownik.dodajUzytkownika(new Uzytkownik(nazwa,haslo));
    }

    //loguje użykownika do systemu
    public static void Logowanie() throws FileNotFoundException {
        Scanner sc = new Scanner(System.in);
        System.out.println("Podaj nazwe:");
        String nazwa = sc.next();
        System.out.println("Podaj haslo:");
        String haslo = sc.next();
        //int SessionID = -1;
        SessionID = Uzytkownik.zaladujUzytkownika(nazwa,haslo);
        if (SessionID != -1) logreg = false;
        //return SessionID;
    }

    //wyświetla menu admina(0) lub zwykłego użytkownika(1)
    public void otworz() throws IOException {
        Scanner sc = new Scanner(System.in);
        switch (typMenu) {
            case 0: // menu admina
                System.out.println("Administrator");
                System.out.println("1.Dodaj samochód");
                System.out.println("2.Usuń samochód");
                System.out.println("3.Edytuj samochód");
                System.out.println("4.Zapisz zmiany");
                System.out.println("5.Pokaż wypożyczenia");
                System.out.println("6.Pokaż wszystkie samochody");
                System.out.println("7.Wyloguj się");
                int wybor = sc.nextInt();
                switch (wybor) {
                    case 1:
                        System.out.println("1.Dodaj samochód osobowy");
                        System.out.println("2.Dodaj samochód dostawczy");
                        System.out.println("3.Anuluj");
                        wybor = sc.nextInt();
                        switch (wybor) {
                            case 1:
                                Osobowy.dodajPojazd();
                                break;
                            case 2:
                                Dostawczy.dodajPojazd();
                                break;
                            case 3:
                                break;
                            default:
                                System.out.println("Niepoprawny numer");
                                break;
                        }
                        break;
                    case 2:
                        Pojazd.pokazWszystkiePojazdy();
                        System.out.println("\nPodaj rodzaj samochodu, który ma zostać usunięty");
                        System.out.println("1.Osobowy\n2.Dostawczy\n3.Anuluj");
                        wybor = sc.nextInt();
                        if(wybor == 3) break;
                        System.out.println("Podaj Lp. samochodu, który ma zostać usunięty:");
                        int numer = sc.nextInt();
                        switch (wybor) {
                            case 1:
                                Osobowy.usunPojazd(numer);
                                break;
                            case 2:
                                Dostawczy.usunPojazd(numer);
                                break;
                            default:
                                System.out.println("Niepoprawny numer");
                                break;
                        }
                        break;
                    case 3:
                        Pojazd.pokazWszystkiePojazdy();
                        System.out.println("\nPodaj rodzaj samochodu, który ma zostać edytowany");
                        System.out.println("1.Osobowy\n2.Dostawczy\n3.Anuluj");
                        wybor = sc.nextInt();
                        if(wybor == 3) break;
                        System.out.println("Podaj Lp. samochodu, który ma zostać edytowany:");
                        numer = sc.nextInt();
                        switch (wybor) {
                            case 1:
                                Osobowy.edytujPojazd(numer);
                                break;
                            case 2:
                                Dostawczy.edytujPojazd(numer);
                                break;
                            default:
                                System.out.println("Niepoprawny numer");
                                break;
                        }
                        break;
                    case 4:
                        Pojazd.zapiszDoPliku();
                        Wypozyczenia.zapiszDoPliku();
                        System.out.println("1.Kontynuuj");
                        sc.next();
                        break;
                    case 5:
                        Wypozyczenia.pokazWszystkieWypozyczenia();
                        System.out.println("1.Kontynuuj");
                        sc.next();
                        break;
                    case 6:
                        Pojazd.pokazWszystkiePojazdy();
                        System.out.println("1.Kontynuuj");
                        sc.next();
                        break;
                    case 7:
                        logreg = true;
                        SessionID = -1;
                        break;
                }
                break;
            default: // menu zwykłego użytkownika
                System.out.println("Użytkownik");
                System.out.println("1.Wypożycz samochód");
                System.out.println("2.Oddaj samochód");
                System.out.println("3.Wyloguj się");
                wybor = sc.nextInt();
                switch (wybor) {
                    case 1:
                        Pojazd.pokazPojazdy();
                        System.out.println("\nPodaj rodzaj samochodu jaki chcesz wypożyczyć:");
                        System.out.println("1.Osobowy\n2.Dostawczy\n3.Anuluj");
                        wybor = sc.nextInt();
                        if(wybor == 3) break;
                        System.out.println("Podaj Lp. samochodu, który chcesz wypożyczyć:");
                        int numer = sc.nextInt();
                        switch (wybor) {
                            case 1:
                                Osobowy.wypozyczPojazd(numer);
                                break;
                            case 2:
                                Dostawczy.wypozyczPojazd(numer);
                                break;
                            default:
                                System.out.println("Wybrano niepoprawny rodzaj samochodu");
                                break;
                        }
                        break;
                    case 2:
                        Wypozyczenia.pokazWypozyczeniaUzytkownika(SessionID);
                        System.out.println("\nPodaj rodzaj samochodu, który chcesz oddać:");
                        System.out.println("1.Osobowy\n2.Dostawczy\n3.Anuluj");
                        wybor = sc.nextInt();
                        if(wybor == 3) break;
                        System.out.println("Podaj ID samochodu, który chcesz oddać:");
                        int ID = sc.nextInt();
                        switch(wybor) {
                            case 1:
                                Osobowy.oddajPojazd(ID);
                                break;
                            case 2:
                                Dostawczy.oddajPojazd(ID);
                                break;
                            default:
                                System.out.println("Wybrano niepoprawny numer samochodu");
                        }
                        break;
                    case 3:
                        logreg = true;
                        SessionID = -1;
                        break;
                    default:
                        System.out.println("Zły numer");
                        break;
                }
                break;
        }
    }
}
