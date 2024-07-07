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
     * @return Die vom Benutzer eingegebene Zahl
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
        System.out.println("       `-'             `-'");
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
 * Der Spieler kann entscheiden, ob er die Aufgabe des Dämons annimmt und löst. Bei erfolgreicher
 * Lösung erhält der Spieler eine spezielle Fähigkeit als Belohnung.
 */
private static void meetDemon() {
    System.out.println("Du triffst auf einen Dämon. Er stellt dir eine Aufgabe.");
    Demon demon = new Demon("Dämon");
    demon.presentTask(); // Präsentiere die Aufgabe des Dämons

    Scanner scanner = new Scanner(System.in);
    System.out.println("Bist du bereit, die Aufgabe zu erfüllen? (ja/nein)");
    String answer = scanner.nextLine().toLowerCase();

    if (answer.equals("ja")) {
        boolean taskCompleted = demon.solveTask(); // Methode zur Lösung der Aufgabe des Dämons
        if (taskCompleted) {
            System.out.println("Glückwunsch! Du hast die Aufgabe des Dämons erfolgreich gelöst.");
            System.out.println("Als Belohnung erhältst du eine spezielle Fähigkeit.");
            // Implementiere hier die Logik für die Belohnung (z.B., Fähigkeit vergeben)

            // Rufe das Rätsel "CountStrings" auf
            countStrings();
        } else {
            System.out.println("Schade, du hast die Aufgabe des Dämons nicht gelöst.");
            System.out.println("Der Dämon verschwindet wieder im Nebel...");
        }
    } else {
        System.out.println("Du entscheidest dich, dich vom Dämon zu entfernen...");
    }

    // Schließe den Scanner, um Ressourcen freizugeben
    scanner.close();
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
     * Kämpft gegen den Vampirjäger. Endet das Spiel, wenn der Vampir besiegt wird.
     */
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

    /**
     * Beendet das Spiel und beendet das Programm.
     */
    private static void closeGame() {
        System.out.println("Das Spiel wird beendet.");
        System.exit(0);
    }

    /**
     * Simuliert das Rätsel "CountStrings". Der Spieler muss die Anzahl der Vorkommen der Zeichenfolge "tam"
     * in einer zufällig generierten Zeichenkette innerhalb von 20 Sekunden zählen und eingeben. Bei richtiger
     * Antwort erhält der Vampir die Fähigkeit "Double Power".
     */
    private static void countStrings() {
        System.out.println("Willkommen zum Rätsel 'CountStrings'!");
        System.out.println("In der folgenden Zeichenkette kommt 'tam' x-mal vor.");

        Random random = new Random();
        int x = random.nextInt(16); // Zufällige Anzahl von 'tam' Vorkommen (0 bis 15)
        int y = random.nextInt(3);  // Zufällige Anzahl von 'rex' Vorkommen (0 bis 2)

        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < x; i++) {
            stringBuilder.append("tam");
            if (i < y) {
                stringBuilder.append("rex");
            }
        }
        String randomString = stringBuilder.toString();

        System.out.println("String: " + randomString);
        System.out.println("Du hast 20 Sekunden, um die Anzahl der 'tam' Vorkommen zu zählen.");

        Scanner scanner = new Scanner(System.in);
        System.out.print("Anzahl von 'tam': ");

        int answer = 0;
        try {
            // Setze einen Timer auf 20 Sekunden
            long startTime = System.currentTimeMillis();
            while ((System.currentTimeMillis() - startTime) < 20000) {
                if (scanner.hasNextInt()) {
                    answer = scanner.nextInt();
                    break;
                }
            }
        } catch (Exception e) {
            System.out.println("Falsche Eingabe oder Zeit abgelaufen. Das Rätsel wird abgebrochen...");
        }

        // Überprüfe die Antwort des Spielers
        if (answer == x) {
            System.out.println("Richtig! Du hast das Rätsel 'CountStrings' gelöst und bekommst die Fähigkeit 'Double Power'.");
            // Implementiere hier die Logik für die Fähigkeit 'Double Power'
        } else {
            System.out.println("Falsche Antwort oder Zeit abgelaufen. Das Rätsel wird abgebrochen...");
        }

        // Schließe den Scanner, um Ressourcen freizugeben
        scanner.close();
    }




    /**
     * Startet das Spiel "Biss-Kralle-Knoblauch", bei dem der Spieler gegen den Computer spielt.
     */
    @SuppressWarnings("unused")
    private static void playBiteClawGarlic() {
        System.out.println("Willkommen zu Biss-Kralle-Knoblauch!");
        System.out.println("Wähle deine Aktion: (1) Biss, (2) Kralle, (3) Knoblauch");

        int playerChoice = scanner.nextInt();
        String playerMove = getMove(playerChoice);

        Random random = new Random();
        int computerChoice = random.nextInt(3) + 1;
        String computerMove = getMove(computerChoice);

        System.out.println("Deine Wahl: " + playerMove);
        System.out.println("Wahl des Computers: " + computerMove);

        String result = determineWinner(playerMove, computerMove);
        System.out.println(result);

        if (result.equals("Du gewinnst!")) {
            System.out.println("Herzlichen Glückwunsch! Du hast die Fähigkeit 'Transparency' erhalten.");
            // Implementiere die Logik für die Fähigkeit 'Transparency'
            currentVampire.gainAbility("Transparency");
        }
    }

    /**
     * Gibt die Zeichenfolge für die gewählte Aktion zurück.
     * @param choice Die Wahl des Spielers oder Computers
     * @return Die Zeichenfolge für die gewählte Aktion
     */
    private static String getMove(int choice) {
        switch (choice) {
            case 1:
                return "Biss";
            case 2:
                return "Kralle";
            case 3:
                return "Knoblauch";
            default:
                return "";
        }
    }

    /**
     * Bestimmt den Gewinner basierend auf den Spielregeln.
     * @param playerMove Die Wahl des Spielers
     * @param computerMove Die Wahl des Computers
     * @return Das Ergebnis des Spiels
     */
    private static String determineWinner(String playerMove, String computerMove) {
        if (playerMove.equals(computerMove)) {
            return "Unentschieden!";
        } else if (playerMove.equals("Biss") && computerMove.equals("Kralle")) {
            return "Du gewinnst!";
        } else if (playerMove.equals("Kralle") && computerMove.equals("Knoblauch")) {
            return "Du gewinnst!";
        } else if (playerMove.equals("Knoblauch") && computerMove.equals("Biss")) {
            return "Du gewinnst!";
        } else {
            return "Der Computer gewinnt!";
        }
    }
}
