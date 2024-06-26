package app;

import java.util.Scanner;
import model.Vampire;

public class VampireAdventureApp {
    private static Scanner scanner = new Scanner(System.in);
    private static Vampire currentVampire; // Diese Variable speichert den aktuellen Vampir

    public static void main(String[] args) {
        showBat();
        while (true) {
            showMenu();
            int choice = readUserInput();
            handle(choice);
            System.out.println("====================");
        }
    }

    private static int readUserInput() {
        System.out.print("\nBitte, geben Sie die Nummer des gewählten Menüeintrags ein:\t");
        int choiceInternal = scanner.nextInt();
        return choiceInternal;
    }

    private static void handle(int choice) {
        switch (choice) {
            case 1:
                createVampire();
                break;
            case 2:
                showVampireData();
                break;
            case 3:
                deleteVampire();
                break;
            case 4:
                startAdventure();
                break;
            case 5:
                closeGame();
                System.out.println("Spiel wird beendet");
                System.exit(0);
            default:
                System.out.println("Ungültige Eingabe. Bitte überprüfen Sie Ihre Eingabe.");
                break;
        }
    }

    private static void showMenu() {
        System.out.println("\n======= Vampire Adventure 1.0 =======\n");

        final String[] menuItems = {
                "(1)\t Vampir anlegen",
                "(2)\t Vampirdaten anzeigen",
                "(3)\t Vampir entfernen",
                "(4)\t Abenteuer starten",
                "(5)\t Beenden"
        };
        for (String menuItem : menuItems) {
            System.out.println(menuItem);
        }
    }

    private static void showBat() {
        System.out.println("   __       __   ____       ____");
        System.out.println("   ) \\     / (   )   \\     /   (");
        System.out.println("  )_  \\_V_/  _(   )_  \\_V_/  _(");
        System.out.println("    )__   __(       )__   __(             cjr");
        System.out.println("       `-'             `-'");
    }

    private static void createVampire() {
        System.out.println("\nVampir anlegen\n");

        // Eingabe des Namens und Alters
        System.out.print("Name des Vampirs: ");
        String name = scanner.next();
        System.out.print("Alter des Vampirs: ");
        int age = scanner.nextInt();

        // Vampir erstellen und speichern
        currentVampire = new Vampire(name, age);

        System.out.println("Vampir wurde erstellt: " + currentVampire.getName());
    }

    private static void showVampireData() {
        System.out.println("\nVampirdaten anzeigen\n");

        if (currentVampire != null) {
            System.out.println("Name: " + currentVampire.getName());
            System.out.println("Alter: " + currentVampire.getAge());
            System.out.println("Großartigkeit: " + currentVampire.getGreatness());
            System.out.println("Hunger: " + currentVampire.getHunger());
            System.out.println("Energie: " + currentVampire.getEnergy());
            System.out.println("Endgültig tot: " + currentVampire.isFinallyDead());
        } else {
            System.out.println("Es wurde noch kein Vampir erstellt.");
        }
    }

    private static void deleteVampire() {
        System.out.println("\nVampir entfernen\n");

        if (currentVampire != null) {
            System.out.println("Drücken Sie (1), um den Vampir zu löschen");
            System.out.println("Drücken Sie (2), um abzubrechen");

            int choice = scanner.nextInt();
            if (choice == 1) {
                currentVampire = null;
                System.out.println("Vampir wurde gelöscht. Ein neuer Vampir kann erstellt werden.");
            } else {
                System.out.println("Löschen abgebrochen.");
            }
        } else {
            System.out.println("Es wurde noch kein Vampir erstellt.");
        }
    }

    private static void startAdventure() {
        System.out.println("\nAbenteuer starten\n");
    }

    private static void closeGame() {
        System.out.println("\nSpiel wird beendet\n");
        scanner.close();
    }
}