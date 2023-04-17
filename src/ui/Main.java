package ui;

import model.Data;

import java.sql.SQLOutput;
import java.util.Scanner;

public class Main {
    private Data data;
    private Scanner read;

    public Main(){
        read = new Scanner(System.in);
        data = new Data();
    }
    public static void main(String[] args) {

        Main run = new Main();
        run.menu();
    }
    public void menu(){
        int option = 0;

        do{
            System.out.println(
                   "Menu******   " +
                           "1.Country: " +
                           "2.Print Winners: " +
                           "3.Print Total Medals: " +
                           "4.Print Countries: "
            );
            System.out.println("Selection: ");
            option = read.nextInt();
            read.nextLine();
            System.out.println();

            execute(option);
        }while(option != 0);
    }

    public void execute(int select){
        switch(select){
            case 0:
                System.out.println("Exit");
            case 1:
                System.out.println("Enter Country");
                String country = read.nextLine();
                String[] array = country.split("::");
                data.addCountry(array);
                break;
            case 2:
                System.out.println("Show me the Winners");
                System.out.println(data.printSortedWinners());
                break;
            case 3:
                System.out.println("Show Me Total Medals");
                System.out.println(data.printSortedTotalMedals());
                break;
            case 4:
                System.out.println("Countries");
                System.out.println(data.printCountries());
                break;
            default:
                System.out.println("Not an available option");
                break;
        }
    }
}
