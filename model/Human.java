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
     * Initialisiert den Menschen mit einer zufälligen Menge an Blut zwischen 6 und 8 Litern.
     */
    public Human() {
        Random random = new Random();
        this.amountOfBlood = 6 + (2 * random.nextDouble());
    }

    /**
     * Gibt die Menge an Blut des Menschen zurück.
     * @return Die Menge an Blut des Menschen
     */
    public double getAmountOfBlood() {
        return amountOfBlood;
    }

    /**
     * Setzt die Menge an Blut des Menschen.
     * @param amountOfBlood Die neue Menge an Blut des Menschen
     */
    public void setAmountOfBlood(double amountOfBlood) {
        this.amountOfBlood = amountOfBlood;
    }

    /**
     * Der Mensch versucht zu fliehen.
     * Mit einer 20%igen Wahrscheinlichkeit ist der Fluchtversuch erfolgreich.
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
     * Wenn die Blutmenge unter 5 Liter fällt, verwandelt sich der Mensch in einen Vampir.
     * @param amount Die Menge an verlorenem Blut
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