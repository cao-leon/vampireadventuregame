package app;

import java.util.Scanner;


/**
 * @author Ayman Othman, Leon Cao
 */
public class VampireAdventureApp {
    private static Scanner scanner = new Scanner(System.in);

    /**
     * @param args
     */
    public static void main(String[] args) {
        showBat();
        while (true) {
            showMenu();
            int choice = readUserInput();
            handle(choice);
            System.out.println("====================");
        }
    }

    /**
     * @return
     */
    private static int readUserInput() {
        System.out.print("\nBitte, geben Sie die Nummer des gewaehlten Menueeintrags ein:\t");
        int choiceInternal = scanner.nextInt();
        return choiceInternal;
    }

    /**
     * @param choice
     */
    private static void handle(int choice) {
        switch (choice) {
            case 1:
                createVampire();
                break;
            case 2:
                ShowVampireData();
                break;
            case 3:
                DeleteVampire();
                break;
            case 4:
                StartNightlyAdventure();
                break;
            case 5:
                CloseGame();
                System.out.println("Spiel wird beendet");
                return;
            default: {
                System.out.println("Ungueltige Eingabe. Bitte ueberpruefen Sie Ihre Eingabe");}
            break;}}

    /**
     *
     */
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
        //This ASCII pic can be found at
        //https://asciiart.website/index.php?art=animals/bats
        System.out.println("   __       __   ____       ____");
        System.out.println("   ) \\     / (   )   \\     /   (");
        System.out.println("  )_  \\_V_/  _(   )_  \\_V_/  _(");
        System.out.println("    )__   __(       )__   __(             cjr");
        System.out.println("       `-'             `-'");
    }

    /**
     *
     */
    private static void createVampire() {
        System.out.println("\nWähle deinen namen,alter und großartigkeit!\r\n" + //
                        "name: \r\n" + //
                        "alter: \r\n" + //
                        "großartigkeit: \n");
    }

    private static void ShowVampireData() {
        System.out.println("\nDein Vampir:\r\n" + //
                        "name:-        \r\n" + //
                        "alter:-      \r\n" + //
                        "großartigkeit:-        \r\n" + //
                        "hunger:-       \r\n" + //
                        "energie:-       \r\n" + //
                        "endlichtot:-      \n");
    }

    private static void DeleteVampire() {
        System.out.println("\nDrück (1) zum Löschen\r\n" + 
                           "Drück (2) zum Abbrechen\n");
    
        try (Scanner scanner = new Scanner(System.in)) {
            int choice = scanner.nextInt();
    
            if (choice == 1) {
                System.out.println("Vampir wurde gelöscht. Ein neuer Vampir kann erstellt werden.");
            } else {
                System.out.println("Löschen abgebrochen.");
            }
        } catch (Exception e) {
            System.out.println("Fehler beim Einlesen der Eingabe: " + e.getMessage());
        }
    }    


    private static void StartNightlyAdventure() {
        System.out.println("\nSteht auf, Vampire, die Sonne ist untergegangen und es gibt noch viel zu tun.\r\n" + //
                                "Die Zeit läuft: Runde 1\n");


    }

    private static void CloseGame() {
        System.out.println("\n!\r\n");

    }

}