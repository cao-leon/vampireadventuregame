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
     * Getter-Methoden für alle Attribute.
     */
    public String getName() {
        return name;
    }

    public String getVampireAbility() {
        return vampireAbility;
    }

    /**
     * Setter-Methoden für alle Attribute.
     */
    public void setName(String name) {
        this.name = name;
    }

    public void setVampireAbility(String vampireAbility) {
        this.vampireAbility = vampireAbility;
    }

    // Weitere Methoden
    /**
     * Startet die Aufgabe für den Spieler.
     */
    public void presentTask() {
        System.out.println("Der Dämon stellt dem Spieler eine Aufgabe...");
        // Logik zur Präsentation der Aufgabe hier implementieren
    }
}