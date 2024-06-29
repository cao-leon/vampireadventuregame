package model;

import java.util.Random;

/**
 * Repräsentiert einen Vampirjäger mit verschiedenen Attributen und Fähigkeiten.
 */
public class VampireHunter {

    private String name;
    private int energy;
    private boolean alive;

    // Konstruktor
    public VampireHunter(String name) {
        this.name = name;
        this.energy = 30; // Standard-Energie eines Vampirjägers
        this.alive = true;
    }

    // Überladener Konstruktor für benutzerdefinierte Energie
    public VampireHunter(String name, int energy) {
        this.name = name;
        this.energy = energy;
        this.alive = true;
    }

    // Getter und Setter
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getEnergy() {
        return energy;
    }

    public void setEnergy(int energy) {
        this.energy = energy;
        if (this.energy <= 0) {
            this.alive = false;
        }
    }

    public boolean isAlive() {
        return alive;
    }

    // Methoden
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

    public void takeDamage(int amount) {
        this.energy -= amount;
        if (this.energy <= 0) {
            this.alive = false;
            System.out.println("Der Vampirjäger ist gestorben.");
        }
    }
}