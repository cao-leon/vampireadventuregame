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
        System.out.println("Der Dämon stellt dem Spieler eine Aufgabe...");
        // Logik zur Präsentation der Aufgabe hier implementieren
    }
}