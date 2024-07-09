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

            // Füge den Aufruf von solvePuzzle hinzu
            solvePuzzle(countStringsSolved, biteClawGarlicSolved, reverseWordsSolved);

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
        System.out.println("Du triffst auf einen Dämon. Er stellt dir eine Aufgabe.");
        Demon demon = new Demon("Dämon");
        demon.presentTask();
        // Füge hier Logik hinzu, um die Aufgabe des Dämons zu präsentieren und die Belohnung zu vergeben
    }

      /**
     * Simuliert die Begegnung des Vampirs mit einem Vampirjäger und ermöglicht eine Entscheidung.
     */
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

    /**
     * Versucht, vor dem Vampirjäger zu fliehen. Bei Misserfolg wird gekämpft.
     */
    private static void fleeVampireHunter() {
        Random random = new Random();
        if (random.nextInt(100) < 60) {
            System.out.println("Der Vampir ist erfolgreich geflohen.");
        } else {
            System.out.println("Der Vampir konnte nicht fliehen und muss kämpfen.");
            fightVampireHunter();
        }
    }

    /**
     * Simuliert den Kampf mit dem Vampirjäger. Bei Misserfolg wird der Vampir getötet.
     */
    private static void fightVampireHunter() {
        Random random = new Random();
        if (random.nextInt(100) < 50) {
            System.out.println("Der Vampir hat den Vampirjäger besiegt.");
        } else {
            System.out.println("Der Vampir wurde vom Vampirjäger getötet.");
            currentVampire.setFinallyDead(true);
        }
    }


    /**
     * Schließt das Spiel und verabschiedet sich vom Benutzer.
     */
    private static void closeGame() {
        System.out.println("Auf Wiedersehen. Das Spiel wird beendet.");
        System.exit(0);
    }

    /**
     * Beendet das Spiel und zeigt eine Abschlussmeldung an.
     */
    private static void BeatGame() {
        System.out.println("Herzlichen Glückwunsch! Du hast alle Aufgaben und Rätsel gelöst und das Spiel erfolgreich beendet.");
        System.exit(0);
    }

    /**
     * Löst das Puzzle, wenn alle anderen Puzzles gelöst wurden.
     * @param countStringsSolved 
     * @param biteClawGarlicSolved 
     * @param reverseWordsSolved 
     */
    private static void solvePuzzle(boolean countStringsSolved, boolean biteClawGarlicSolved, boolean reverseWordsSolved) {
        if (!countStringsSolved) {
            countStringsSolved = countStrings();
        } else if (!biteClawGarlicSolved) {
            biteClawGarlicSolved = playBiteClawGarlic();
        } else if (!reverseWordsSolved) {
            reverseWordsSolved = reverseWordsPuzzle();
        }

        if (countStringsSolved && biteClawGarlicSolved && reverseWordsSolved) {
            System.out.println("Du hast alle Puzzles gelöst! Der größte Vampirjäger des Jahrhunderts erscheint.");
            meetVampireHunter();
        }
    }

    /**
     * Ein Beispiel-Rätsel, bei dem die Anzahl der Zeichen in einer Zeichenfolge gezählt wird.
     * @return true, wenn das Rätsel korrekt gelöst wurde.
     */
    private static boolean countStrings() {
        System.out.println("Willkommen zum Rätsel 'CountStrings'!");
        System.out.print("Gib eine Zeichenfolge ein: ");
        String input = scanner.next();
        System.out.print("Wie viele Zeichen hat die Zeichenfolge? ");
        int count = scanner.nextInt();
        boolean correct = (input.length() == count);
        if (correct) {
            System.out.println("Richtig! Die Zeichenfolge hat " + count + " Zeichen.");
        } else {
            System.out.println("Falsch! Die Zeichenfolge hat " + input.length() + " Zeichen.");
        }
        return correct;
    }

    /**
     * Ein Beispielspiel "Bite, Claw, Garlic".
     * @return true, wenn der Spieler gewinnt.
     */
    private static boolean playBiteClawGarlic() {
        System.out.println("Willkommen zu Biss-Kralle-Knoblauch!");
        System.out.println("Wähle (1) Biss, (2) Kralle oder (3) Knoblauch:");
        int playerChoice = scanner.nextInt();
        Random random = new Random();
        int computerChoice = random.nextInt(3) + 1;
        System.out.println("Computer wählt: " + computerChoice);
        String result;
        if (playerChoice == computerChoice) {
            result = "Unentschieden!";
        } else if ((playerChoice == 1 && computerChoice == 2) || 
                   (playerChoice == 2 && computerChoice == 3) || 
                   (playerChoice == 3 && computerChoice == 1)) {
            result = "Du verlierst!";
        } else {
            result = "Du gewinnst!";
        }
        System.out.println(result);
        return result.equals("Du gewinnst!");
    }

    /**
     * Ein Beispiel-Rätsel, bei dem Wörter in einer Zeichenfolge umgekehrt werden.
     * @return true, wenn das Rätsel korrekt gelöst wurde.
     */
    private static boolean reverseWordsPuzzle() {
        System.out.println("Willkommen zum Puzzle 'Reverse Words'!");
        System.out.print("Gib einen Satz ein: ");
        scanner.nextLine(); // Consume the newline character
        String sentence = scanner.nextLine();
        String[] words = sentence.split(" ");
        StringBuilder reversedSentence = new StringBuilder();
        for (String word : words) {
            reversedSentence.append(new StringBuilder(word).reverse().toString()).append(" ");
        }
        reversedSentence.setLength(reversedSentence.length() - 1); // Remove the trailing space
        System.out.println("Der Satz mit umgekehrten Wörtern lautet: " + reversedSentence);
        System.out.print("Gib den Satz mit umgekehrten Wörtern ein: ");
        String userReversedSentence = scanner.nextLine();
        boolean correct = reversedSentence.toString().equals(userReversedSentence);
        if (correct) {
            System.out.println("Richtig! Der Satz wurde korrekt umgekehrt.");
        } else {
            System.out.println("Falsch! Der Satz wurde nicht korrekt umgekehrt.");
        }
        return correct;
    }
}