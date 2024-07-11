package app;

import java.util.Scanner;
import java.util.Random;
import model.Vampire;
import model.Human;
import model.Demon;
import model.VampireHunter;

/**
 * Die Hauptklasse der Anwendung VampireAdventureApp.
 * Diese Klasse enthält das Hauptmenü und die Spiellogik.
 */
public class VampireAdventureApp {
    private static Scanner scanner = new Scanner(System.in);
    private static Vampire currentVampire;
    private static boolean countStringsSolved = false;
    private static boolean biteClawGarlicSolved = false;
    private static boolean reverseWordsSolved = false;

    /**
     * Der Einstiegspunkt der Anwendung.
     * Zeigt das Startbild an und startet das Hauptmenü.
     * @param args Kommandozeilenargumente
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
     * Liest die Benutzereingabe für die Menüwahl.
     * @return Die vom Benutzer eingegebene Zahl.
     */
    private static int readUserInput() {
        System.out.print("\nBitte, geben Sie die Nummer des gewählten Menüpunkts ein: ");
        return scanner.nextInt();
    }

    /**
     * Verarbeitet die Benutzerwahl und führt die entsprechende Aktion aus.
     * @param choice Die gewählte Menüoption
     */
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
                return;
            default:
                System.out.println("Ungültige Eingabe. Bitte überprüfen Sie Ihre Eingabe.");
                break;
        }
    }

    /**
     * Zeigt das Hauptmenü der Anwendung an.
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

    /**
     * Zeigt eine ASCII-Grafik einer Fledermaus an.
     */
    private static void showBat() {
        // Diese ASCII-Grafik ist von
        // https://asciiart.website/index.php?art=animals/bats
        System.out.println("   __       __   ____       ____");
        System.out.println("   ) \\     / (   )   \\     /   (");
        System.out.println("  )_  \\_V_/  _(   )_  \\_V_/  _(");
        System.out.println("    )__   __(       )__   __(             cjr");
        System.out.println("       -'             -'");
    }

    /**
     * Erstellt einen neuen Vampir basierend auf Benutzereingaben.
     */
    private static void createVampire() {
        System.out.println("Wähle deinen Namen und das Alter:");
        System.out.print("Name: ");
        String name = scanner.next();
        System.out.print("Alter: ");
        int age = scanner.nextInt();
        currentVampire = new Vampire(name, age);
        System.out.println("Vampir erstellt: " + name + ", Alter: " + age);
    }

    /**
     * Zeigt die Daten des aktuellen Vampirs an.
     */
    private static void showVampireData() {
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

    /**
     * Löscht den aktuellen Vampir, wenn der Benutzer dies bestätigt.
     */
    private static void deleteVampire() {
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

    /**
     * Startet ein Abenteuer, bei dem der Vampir verschiedenen Ereignissen begegnet.
     */
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

            // Überprüfe, ob alle Rätsel gelöst wurden und rufe BeatGame auf
            if (countStringsSolved && biteClawGarlicSolved && reverseWordsSolved) {
                BeatGame();
                break;
            }
        }
        System.out.println("Die Nacht ist vorbei.");
    }

    /**
     * Simuliert die Begegnung des Vampirs mit einem Menschen und ermöglicht eine Entscheidung.
     */
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

    /**
     * Simuliert die Begegnung des Vampirs mit einem Dämon und stellt eine Aufgabe.
     */
    private static void meetDemon() {
        Demon demon = new Demon("Shadowfiend");
        System.out.println("Ein Dämon namens " + demon.getName() + " erscheint...");
        System.out.println("(1) Mit dem Dämon sprechen");
        System.out.println("(2) Sich entfernen");
        int choice = scanner.nextInt();
        if (choice == 1) {
            talkToDemon(demon);
        } else {
            System.out.println("Der Vampir entfernt sich und der Dämon verschwindet wieder im Nebel.");
        }
    }

    /**
     * Simuliert das Gespräch mit dem Dämon und bietet eine Aufgabe an.
     */
    private static void talkToDemon(Demon demon) {
        System.out.println("Der Dämon " + demon.getName() + " erzählt dir seine Geschichte und fragt, ob du bereit bist, eine Aufgabe zu erfüllen.");
        System.out.println("(1) Aufgabe erfüllen");
        System.out.println("(2) Entfernen");
        int choice = scanner.nextInt();
        if (choice == 1) {
            demon.presentTask();
            solvePuzzle();
        } else {
            System.out.println("Der Vampir entfernt sich vom Dämon, und der Dämon verschwindet wieder im Nebel.");
        }
    }

    /**
     * Simuliert die Begegnung des Vampirs mit einem Vampirjäger und ermöglicht eine Entscheidung.
     */
    private static void meetVampireHunter() {
        VampireHunter vampireHunter = new VampireHunter("Van Helsing");
        System.out.println("Ein Vampirjäger namens " + vampireHunter.getName() + " hat deinen Weg gekreuzt. Deine Zeit ist gekommen...");
        System.out.println("(1) Fliehen");
        System.out.println("(2) Kämpfen");
        int choice = scanner.nextInt();
        if (choice == 1) {
            fleeVampireHunter(vampireHunter);
        } else if (choice == 2) {
            fightVampireHunter(vampireHunter);
        }
    }

    /**
     * Simuliert die Flucht vor dem Vampirjäger.
     */
    private static void fleeVampireHunter(VampireHunter vampireHunter) {
        Random random = new Random();
        int success = random.nextInt(100);
        if (success < 50) {
            System.out.println("Du konntest erfolgreich fliehen!");
        } else {
            System.out.println("Der Vampirjäger hat dich erwischt und dich endgültig getötet.");
            currentVampire.setFinallyDead(true);
        }
    }

    /**
     * Simuliert den Kampf mit dem Vampirjäger.
     */
    private static void fightVampireHunter(VampireHunter vampireHunter) {
        Random random = new Random();
        int success = random.nextInt(100);
        if (success < 50) {
            System.out.println("Du hast den Vampirjäger erfolgreich besiegt!");
        } else {
            System.out.println("Der Vampirjäger hat dich besiegt und dich endgültig getötet.");
            currentVampire.setFinallyDead(true);
        }
    }

    /**
     * Beendet das Spiel.
     */
    private static void closeGame() {
        scanner.close();
    }

    /**
     * Beendet das Spiel und zeigt eine Siegesnachricht an, wenn alle Rätsel gelöst wurden.
     */
    private static void BeatGame() {
        System.out.println("Du hast alle Rätsel gelöst und das Spiel gewonnen!");
        System.exit(0);
    }

    /**
     * Stellt eine Aufgabe und überprüft die Lösung.
     */
    private static void solvePuzzle() {
        System.out.println("Hier ist dein Rätsel: Zähle die Vorkommen von Zeichen in einem String.");
        System.out.print("Gib den String ein: ");
        String input = scanner.next();
        System.out.print("Gib das zu zählende Zeichen ein: ");
        char ch = scanner.next().charAt(0);
        int count = countOccurrences(input, ch);
        System.out.println("Das Zeichen '" + ch + "' kommt " + count + " Mal in dem String vor.");
        countStringsSolved = true;
    }

    /**
     * Zählt die Vorkommen eines Zeichens in einem String.
     * @param str Der String
     * @param ch Das zu zählende Zeichen
     * @return Die Anzahl der Vorkommen
     */
    private static int countOccurrences(String str, char ch) {
        int count = 0;
        for (char c : str.toCharArray()) {
            if (c == ch) {
                count++;
            }
        }
        return count;
    }
}