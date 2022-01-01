package com.company;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Uzytkownik {

    private static int ostatnieID = 0;
    public int ID;
    public String Nazwa;
    public String Haslo;

    public Uzytkownik(String nazwa, String haslo) {
        Nazwa = nazwa;
        Haslo = haslo;
        ID = ostatnieID + 1;
    }

    public int getID() {
        return ID;
    }
    public void setID(int ID) {
        this.ID = ID;
    }

    public String getNazwa() {
        return Nazwa;
    }
    public void setNazwa(String nazwa) {
        Nazwa = nazwa;
    }

    public String getHaslo() {
        return Haslo;
    }
    public void setHaslo(String haslo) {
        Haslo = haslo;
    }

    public static int getOstatnieID() throws FileNotFoundException {
        File users = new File("uzytkownicy.txt");
        Scanner sc = new Scanner(users);
        int ID = 0;
        while(sc.hasNextLine()){
            String wiersz = sc.nextLine();
            String[] atrybuty = wiersz.split("\\|");
            String userID = atrybuty[0];
            ID = Integer.parseInt(userID);
        }

        return ID;
    }
    public static void setOstatnieID(int ostatnieID) {
        Uzytkownik.ostatnieID = ostatnieID;
    }

    public static void dodajUzytkownika(Uzytkownik user) throws IOException {
        File users = new File("uzytkownicy.txt");
        FileWriter fw = new FileWriter(users, true);
        Scanner sc = new Scanner(users);
        PrintWriter pw = new PrintWriter(fw);

        //sprawdza czy nazwa nie występuje już w liscie użytkowników
        while(sc.hasNextLine()){
            String wiersz = sc.nextLine();
            String[] atrybuty = wiersz.split("\\|");
            if(atrybuty[1].equals(user.getNazwa())){
                System.out.println("Użytkownik o podanej nazwie już istnieje.");
                return;
            }
        }

        //dodaje użytkownika do listy
        String daneUzytkownika = user.getID() + "|" + user.getNazwa() + "|" + user.getHaslo();
        pw.println(daneUzytkownika);
        pw.close();
        fw.close();
        System.out.println("Rejestracja zakończona powodzeniem, możesz się zalogować\n");

        //zapisuje date dodania użytkownika
        File rejestracja = new File("rejestracja.txt");
        FileWriter fw2 = new FileWriter(rejestracja, true);
        PrintWriter pw2 = new PrintWriter(fw2);

        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();
        String data = "ID:" + user.getID() + " | " + formatter.format(date);

        pw2.println(data);
        pw2.close();
        fw2.close();
}

    public static int zaladujUzytkownika(String nazwa, String haslo) throws FileNotFoundException {
        File users = new File("uzytkownicy.txt");
        Scanner sc = new Scanner(users);
    while(sc.hasNextLine()){
        String wiersz = sc.nextLine();
        String[] atrybuty = wiersz.split("\\|");
        String ID = atrybuty[0];
        String tempNazwa = atrybuty[1];
        String tempHaslo = atrybuty[2];
        if(tempNazwa.equals(nazwa) && tempHaslo.equals(haslo)){
            System.out.println("Udało się zalogowac!");
            return Integer.parseInt(ID);
        }
    }
    System.out.println("Błędna nazwa lub hasło");
    return -1;
}

}
