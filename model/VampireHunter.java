package model;

import java.util.Random;

/**
 * Repräsentiert einen Vampirjäger mit verschiedenen Attributen und Fähigkeiten.
 */
public class VampireHunter {

    private String name;
    private int energy;
    private boolean alive;

    /**
     * Konstruktor, um einen Vampirjäger mit einem Namen und Standard-Energie zu erstellen.
     * @param name Der Name des Vampirjägers
     */
    public VampireHunter(String name) {
        this.name = name;
        this.energy = 30; // Standard-Energie eines Vampirjägers
        this.alive = true;
    }

    /**
     * Überladener Konstruktor, um einen Vampirjäger mit einem Namen und benutzerdefinierter Energie zu erstellen.
     * @param name Der Name des Vampirjägers
     * @param energy Die Energie des Vampirjägers
     */
    public VampireHunter(String name, int energy) {
        this.name = name;
        this.energy = energy;
        this.alive = true;
    }

    /**
     * Gibt den Namen des Vampirjägers zurück.
     * @return Der Name des Vampirjägers
     */
    public String getName() {
        return name;
    }

    /**
     * Setzt den Namen des Vampirjägers.
     * @param name Der neue Name des Vampirjägers
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gibt die Energie des Vampirjägers zurück.
     * @return Die Energie des Vampirjägers
     */
    public int getEnergy() {
        return energy;
    }

    /**
     * Setzt die Energie des Vampirjägers.
     * Setzt den Status auf "nicht lebendig", wenn die Energie auf 0 oder weniger fällt.
     * @param energy Die neue Energie des Vampirjägers
     */
    public void setEnergy(int energy) {
        this.energy = energy;
        if (this.energy <= 0) {
            this.alive = false;
        }
    }

    /**
     * Gibt zurück, ob der Vampirjäger noch lebt.
     * @return true, wenn der Vampirjäger noch lebt, sonst false
     */
    public boolean isAlive() {
        return alive;
    }

    /**
     * Greift einen Vampir an und verursacht mit einer 50%-Wahrscheinlichkeit 3 Schadenspunkte.
     * @param vampire Der Vampir, der angegriffen wird
     */
    public void attack(Vampire vampire) {
        // 50% Wahrscheinlichkeit zu treffen und 3 Schaden zu verursachen
        Random random = new Random();
        if (random.nextInt(100) < 50) {
            System.out.println("Der Vampirjäger hat den Vampir getroffen und 3 Schaden verursacht.");
            vampire.takeDamage(3);
        } else {
            System.out.println("Der Vampirjäger hat verfehlt.");
        }
    }

    /**
     * Verursacht dem Vampirjäger Schaden und setzt den Status auf "nicht lebendig", wenn die Energie auf 0 oder weniger fällt.
     * @param amount Die Höhe des Schadens, der verursacht wird
     */
    public void takeDamage(int amount) {
        this.energy -= amount;
        if (this.energy <= 0) {
            this.alive = false;
            System.out.println("Der Vampirjäger ist gestorben.");
        }
    }
}