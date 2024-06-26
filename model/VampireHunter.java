package model;

import java.util.Random;

/**
 * Repräsentiert einen Vampirjäger mit verschiedenen Attributen und Fähigkeiten.
 */
public class VampireHunter {

    // Attribute
    private String name;
    private int energy;

    /**
     * Konstruktor für die Klasse VampireHunter.
     * @param name Der Name des Vampirjägers.
     */
    public VampireHunter(String name) {
        this.name = name;
        // Ein normaler Vampirjäger hat eine Energie von 30
        this.energy = 30;
    }

    /**
     * Gibt den Namen des Vampirjägers zurück.
     * @return Der Name des Vampirjägers.
     */
    public String getName() {
        return name;
    }

    /**
     * Gibt die Energie des Vampirjägers zurück.
     * @return Die Energie des Vampirjägers.
     */
    public int getEnergy() {
        return energy;
    }

    /**
     * Setzt den Namen des Vampirjägers.
     * @param name Der neue Name des Vampirjägers.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Setzt die Energie des Vampirjägers.
     * @param energy Die neue Energie des Vampirjägers.
     */
    public void setEnergy(int energy) {
        this.energy = energy;
    }

    // Weitere Methoden
    /**
     * Der Vampirjäger greift einen Vampir an.
     * @param vampire Der Vampir, der angegriffen wird.
     */
    public void attack(Vampire vampire) {
        Random random = new Random();
        if (random.nextInt(100) < 50) { // 50% Chance zu treffen
            vampire.takeDamage(3); // Verursacht 3 Schadenspunkte am Vampir
        } else {
            System.out.println("Der Vampirjäger hat den Vampir verfehlt.");
        }
    }

    /**
     * Der Vampirjäger erleidet Schaden.
     * @param amount Der erlittene Schaden.
     */
    public void takeDamage(int amount) {
        this.energy -= amount;
        if (this.energy <= 0) {
            System.out.println("Der Vampirjäger " + name + " ist tot.");
        }
    }

    /**
     * Der Vampirjäger versucht zu fliehen (flee).
     */
    public void flee() {
        System.out.println("Ein Vampirjäger flieht nie!");
    }

    /**
     * Überprüft, ob der Vampirjäger noch lebt.
     * @return true, wenn der Vampirjäger noch lebt; false sonst.
     */
    public boolean alive() {
        return energy > 0;
    }
}
