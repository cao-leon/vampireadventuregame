package model;

/**
 * Repräsentiert einen Dämon mit einem Namen und einer Vampirfähigkeit.
 */
public class Demon {

    // Attribute
    private String name;
    private String vampireAbility;

    /**
     * Konstruktor für die Klasse Demon.
     * @param name Der Name des Dämons.
     */
    public Demon(String name) {
        this.name = name;
    }

    /**
     * Gibt den Namen des Dämons zurück.
     * @return Der Name des Dämons
     */
    public String getName() {
        return name;
    }

    /**
     * Gibt die Vampirfähigkeit des Dämons zurück.
     * @return Die Vampirfähigkeit des Dämons
     */
    public String getVampireAbility() {
        return vampireAbility;
    }

    /**
     * Setzt den Namen des Dämons.
     * @param name Der neue Name des Dämons
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Setzt die Vampirfähigkeit des Dämons.
     * @param vampireAbility Die neue Vampirfähigkeit des Dämons
     */
    public void setVampireAbility(String vampireAbility) {
        this.vampireAbility = vampireAbility;
    }

    /**
     * Startet die Aufgabe für den Spieler.
     */
    public void presentTask() {
        System.out.println("Einer der großen Dämonen erscheint aus den Schatten...");
        System.out.println("Der Dämon stellt dir eine Aufgabe.");

        // Hier wird die Logik eingefügt, um die Aufgabe des Dämons zu präsentieren
        System.out.println("Der Dämon stellt eine Frage oder präsentiert eine Herausforderung.");
    }

    /**
     * Löst die vom Dämon gestellte Aufgabe.
     * @return true, wenn die Aufgabe erfolgreich gelöst wurde, false sonst.
     */
    public boolean solveTask() {
        // Hier wird die Logik implementiert, um die vom Dämon gestellte Aufgabe zu lösen
        System.out.println("Du versuchst, die Aufgabe des Dämons zu lösen...");

        // Beispiel: Zähle das Vorkommen von "tam" in einem String
        String str = "tamrexrextamtamrextamrextamtamrex";
        int anzahl = zaehleVorkommen(str, "tam");

        // Beispiel: Der Spieler hat 20 Sekunden, um die korrekte Anzahl einzugeben
        int antwort = 3; // Beispiel: Die korrekte Antwort
        boolean geloest = (anzahl == antwort);

        if (geloest) {
            System.out.println("Glückwunsch! Du hast die Aufgabe des Dämons gelöst.");
        } else {
            System.out.println("Sorry, du hast die Aufgabe des Dämons nicht richtig gelöst.");
        }

        return geloest;
    }

    /**
     * Zählt die Anzahl von Vorkommen eines Teilstrings in einem String.
     * @param str Der Eingabestring.
     * @param teilStr Der zu zählende Teilstring.
     * @return Die Anzahl der Vorkommen von teilStr in str.
     */
    private int zaehleVorkommen(String str, String teilStr) {
        int letztesIndex = 0;
        int anzahl = 0;

        while (letztesIndex != -1) {
            letztesIndex = str.indexOf(teilStr, letztesIndex);
            if (letztesIndex != -1) {
                anzahl++;
                letztesIndex += teilStr.length();
            }
        }

        return anzahl;
    }
}