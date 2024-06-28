package model;

import java.util.Random;

/**
 * Repräsentiert einen Menschen mit einer zufälligen Menge an Blut.
 */
public class Human {

    // Attribute
    private double amountOfBlood;

    /**
     * Konstruktor für die Klasse Human.
     */
    public Human() {
        Random random = new Random();
        this.amountOfBlood = 6 + (2 * random.nextDouble());
    }

    /**
     * Getter-Methoden 
     */
    public double getAmountOfBlood() {
        return amountOfBlood;
    }

    // Setter
    /**
     * Setter-Methoden für alle Attribute.
     */
    public void setAmountOfBlood(double amountOfBlood) {
        this.amountOfBlood = amountOfBlood;
    }

    /**
     * Der Mensch versucht zu fliehen.
     */
    public void flee() {
        Random random = new Random();
        if (random.nextInt(100) < 20) {
            System.out.println("Der Mensch ist erfolgreich geflohen.");
        } else {
            System.out.println("Der Mensch konnte nicht fliehen.");
        }
    }

    /**
     * Der Mensch verliert eine bestimmte Menge an Blut.
     * @param amount Die Menge an verlorenem Blut.
     */
    public void loseBlood(double amount) {
        this.amountOfBlood -= amount;
        System.out.println("Der Mensch hat " + amount + " Liter Blut verloren.");
        if (this.amountOfBlood < 5) {
            System.out.println("Der Mensch hat sich in einen Vampir verwandelt!");
            // Logik zur Verwandlung in einen Vampir hinzufügen
        }
    }
}