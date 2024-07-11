package model;

import java.util.Random;

/**
 * Repräsentiert einen Vampir mit verschiedenen Attributen und Fähigkeiten.
 */
public class Vampire {

    // Attribute
    private String name;
    private int age;
    private int grandness;
    private int hunger;
    private int energy;
    private boolean finallyDead;

    /**
     * Konstruktor für die Klasse Vampire.
     * @param name Der Name des Vampirs.
     * @param age Das Alter des Vampirs.
     */
    public Vampire(String name, int age) {
        this.name = name;
        this.age = age;
        this.grandness = 0;
        this.hunger = 0;
        this.energy = 10;
        this.finallyDead = false;
    }

    /**
     * Gibt den Namen des Vampirs zurück.
     * @return Der Name des Vampirs
     */
    public String getName() {
        return name;
    }

    /**
     * Gibt das Alter des Vampirs zurück.
     * @return Das Alter des Vampirs
     */
    public int getAge() {
        return age;
    }

    /**
     * Gibt die Großartigkeit des Vampirs zurück.
     * @return Die Großartigkeit des Vampirs
     */
    public int getGrandness() {
        return grandness;
    }

    /**
     * Gibt den Hunger des Vampirs zurück.
     * @return Der Hunger des Vampirs
     */
    public int getHunger() {
        return hunger;
    }

    /**
     * Gibt die Energie des Vampirs zurück.
     * @return Die Energie des Vampirs
     */
    public int getEnergy() {
        return energy;
    }

    /**
     * Gibt zurück, ob der Vampir endgültig tot ist.
     * @return true, wenn der Vampir endgültig tot ist, sonst false
     */
    public boolean isFinallyDead() {
        return finallyDead;
    }

    /**
     * Setzt den Namen des Vampirs.
     * @param name Der neue Name des Vampirs
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Setzt das Alter des Vampirs.
     * @param age Das neue Alter des Vampirs
     */
    public void setAge(int age) {
        this.age = age;
    }

    /**
     * Setzt die Großartigkeit des Vampirs.
     * @param grandness Die neue Großartigkeit des Vampirs
     */
    public void setGrandness(int grandness) {
        this.grandness = grandness;
    }

    /**
     * Setzt den Hunger des Vampirs.
     * @param hunger Der neue Hunger des Vampirs
     */
    public void setHunger(int hunger) {
        this.hunger = hunger;
    }

    /**
     * Setzt die Energie des Vampirs.
     * @param energy Die neue Energie des Vampirs
     */
    public void setEnergy(int energy) {
        this.energy = energy;
    }

    /**
     * Setzt den Status, ob der Vampir endgültig tot ist.
     * @param finallyDead Der neue Status, ob der Vampir endgültig tot ist
     */
    public void setFinallyDead(boolean finallyDead) {
        this.finallyDead = finallyDead;
    }

    /**
     * Der Vampir greift einen Menschen an.
     * @param human Der Mensch, der angegriffen wird.
     */
    public void attackHuman(Human human) {
        Random random = new Random();
        if (random.nextInt(100) < 60) {
            System.out.println("Der Mensch ist ruhiggestellt.");
        } else {
            System.out.println("Der Vampir konnte den Menschen nicht ruhigstellen.");
        }
    }

    /**
     * Der Vampir trinkt Blut.
     * @param amount Die Menge an Blut, die getrunken wird.
     */
    public void drinkBlood(double amount) {
        if (amount <= 0) {
            System.out.println("Du kannst nicht weniger als 0 Liter Blut trinken!");
            return;
        }
    
        if (amount > 8) {
            System.out.println("Du trinkst zu viel Blut und stirbst!");
            this.setFinallyDead(true);
            return;
        }
    
        System.out.println("Du trinkst " + amount + " Liter Blut.");
    
        this.hunger -= amount / 2;
        this.energy += amount * 2;
    
        if (this.hunger < 0) {
            this.hunger = 0;
        }
    
        if (this.energy > 100) {
            this.energy = 100;
        }
    }    

    /**
     * Der Vampir nimmt Schaden.
     * @param damage Der erlittene Schaden.
     */
    public void takeDamage(int damage) {
        Random random = new Random();
        if (random.nextInt(100) < 50) {
            System.out.println("Der Vampir hat aufgrund seiner Transparenz keinen Schaden genommen.");
        } else {
            this.energy -= damage;
            System.out.println("Der Vampir hat " + damage + " Schaden genommen.");
            if (this.energy <= 0) {
                this.finallyDead = true;
                System.out.println("Der Vampir ist endgültig tot.");
            }
        }
    }

    /**
     * Der Vampir greift einen Vampirjäger an.
     * @param vh Der Vampirjäger, der angegriffen wird.
     */
    public void attack(VampireHunter vh) {
        Random random = new Random();
        int damage = random.nextInt(6);
        if (random.nextInt(100) < 50) {
            damage *= 2;
            System.out.println("Doppelte Kraft aktiviert!");
        }
        System.out.println("Der Vampir verursacht " + damage + " Schaden am Vampirjäger.");
        vh.takeDamage(damage);
    }

    /**
     * Der Vampir versucht zu fliehen.
     */
    public void flee() {
        Random random = new Random();
        if (random.nextInt(100) < 60) {
            System.out.println("Der Vampir ist erfolgreich geflohen.");
        } else {
            System.out.println("Der Vampir konnte nicht fliehen.");
        }
    }

    /**
     * Aktualisiert den Hunger des Vampirs nach jeder Runde.
     * Wenn der Hunger 5 erreicht oder überschreitet, stirbt der Vampir.
     */
    public void updateHunger() {
        this.hunger += 1;
        if (this.hunger >= 5) {
            this.finallyDead = true;
            System.out.println("Der Vampir ist verhungert und wird aus dem Spiel entfernt.");
        }
    }
}