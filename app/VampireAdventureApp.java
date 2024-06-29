package app;

import java.util.Scanner;
import model.Vampire;
import model.Human;
import model.Demon;
import model.VampireHunter;
import java.util.Random;

public class VampireAdventureApp {
    private static Scanner scanner = new Scanner(System.in);
    private static Vampire currentVampire;

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
        System.out.print("\nBitte, geben Sie die Nummer des gewählten Menüpunkts ein: ");
        return scanner.nextInt();
    }

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
                startAdventure();
                break;
            case 5:
                CloseGame();
                System.out.println("Spiel wird beendet");
                return;
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
        //This ASCII pic can be found at
        //https://asciiart.website/index.php?art=animals/bats
        System.out.println("   __       __   ____       ____");
        System.out.println("   ) \\     / (   )   \\     /   (");
        System.out.println("  )_  \\_V_/  _(   )_  \\_V_/  _(");
        System.out.println("    )__   __(       )__   __(             cjr");
        System.out.println("       `-'             `-'");
    }

    private static void createVampire() {
        System.out.println("Wähle deinen Namen und das Alter:");
        System.out.print("Name: ");
        String name = scanner.next();
        System.out.print("Alter: ");
        int age = scanner.nextInt();
        currentVampire = new Vampire(name, age);
        System.out.println("Vampir erstellt: " + name + ", Alter: " + age);
    }

    private static void ShowVampireData() {
        if (currentVampire != null) {
            System.out.println("Vampirdaten:");
            System.out.println("Name: " + currentVampire.getName());
            System.out.println("Alter: " + currentVampire.getAge());
            System.out.println("Großartigkeit: " + currentVampire.getGrandness());
            System.out.println("Hunger: " + currentVampire.getHunger());
            System.out.println("Energie: " + currentVampire.getEnergy());
            System.out.println("Endgültig tot: " + currentVampire.isFinallyDead());
        } else {
            System.out.println("Kein Vampir vorhanden.");
        }
    }

    private static void DeleteVampire() {
        System.out.println("Drück (1) zum Löschen");
        System.out.println("Drück (2) zum Abbrechen");

        int choice = scanner.nextInt();

        if (choice == 1) {
            currentVampire = null;
            System.out.println("Vampir wurde gelöscht. Ein neuer Vampir kann erstellt werden.");
        } else {
            System.out.println("Löschen abgebrochen.");
        }
    }

    private static void startAdventure() {
        System.out.println("Steht auf, Vampire, die Sonne ist untergegangen und es gibt noch viel zu tun.");
        Random random = new Random();
        for (int round = 1; round <= 12; round++) {
            System.out.println("Die Zeit läuft: Runde " + round);
            int encounter = random.nextInt(100);
            if (encounter < 60) {
                meetHuman();
            } else if (encounter < 80) {
                meetDemon();
            } else if (encounter < 90) {
                meetVampireHunter();
            } else {
                System.out.println("Es passiert nichts.");
            }
    
            if (round % 2 == 0) {
                currentVampire.updateHunger();
            }
    
            if (currentVampire.isFinallyDead()) {
                System.out.println("Das Abenteuer ist vorbei, weil der Vampir tot ist.");
                break;
            }
        }
        System.out.println("Die Nacht ist vorbei.");
    }

    private static void meetHuman() {
        System.out.println("Du begegnest einem Menschen. Möchtest du ihn angreifen? (ja/nein)");
        String choice = scanner.next();
        if (choice.equalsIgnoreCase("ja")) {
            System.out.println("Du greifst den Menschen an.");
            currentVampire.attackHuman(new Human());
            System.out.print("Wie viel Blut möchtest du trinken? ");
            double amount = scanner.nextDouble();
            currentVampire.drinkBlood(amount);
        } else {
            System.out.println("Du lässt den Menschen in Ruhe.");
        }
    }

    private static void meetDemon() {
        System.out.println("Du triffst auf einen Dämon. Er stellt dir eine Aufgabe.");
        Demon demon = new Demon("Dämon");
        demon.presentTask();
        // Füge hier Logik hinzu, um die Aufgabe des Dämons zu präsentieren und die Belohnung zu vergeben
    }

    private static void meetVampireHunter() {
        System.out.println("Ein Vampirjäger hat deinen Weg gekreuzt. Deine Zeit ist gekommen...");
        System.out.println("(1) Fliehen");
        System.out.println("(2) Kämpfen");
        int choice = scanner.nextInt();
        if (choice == 1) {
            fleeVampireHunter();
        } else if (choice == 2) {
            fightVampireHunter();
        }
    }

    private static void fleeVampireHunter() {
        Random random = new Random();
        if (random.nextInt(100) < 60) {
            System.out.println("Der Vampir ist erfolgreich geflohen.");
        } else {
            System.out.println("Der Vampir konnte nicht fliehen und muss kämpfen.");
            fightVampireHunter();
        }
    }

    private static void fightVampireHunter() {
        System.out.println("Der Vampir kämpft gegen den Vampirjäger.");
        VampireHunter hunter = new VampireHunter("Jäger", 30);
        hunter.attack(currentVampire);
        if (currentVampire.getEnergy() <= 0) {
            System.out.println("Der Vampir wurde getötet. Das Spiel ist vorbei.");
            System.exit(0);
        } else {
            currentVampire.attack(hunter);
            if (hunter.getEnergy() <= 0) {
                System.out.println("Der Vampirjäger wurde getötet.");
            }
        }
    }

    private static void CloseGame() {
        System.out.println("Das Spiel wird beendet.");
        System.exit(0);
    }
}