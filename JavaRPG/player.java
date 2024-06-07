import java.io.Serializable;
import java.util.Random;

public class Player extends Character implements SpecialAbility, Serializable {
    private static final long serialVersionUID = 1L;
    private int potions;

    public Player(String name, int health, int attackPower, int potions) {
        super(name, health, attackPower);
        this.potions = potions;
    }

    public void usePotion() {
        if (potions > 0) {
            health += 20;
            potions--;
            System.out.println(name + " used a potion and restored health. Current health: " + health);
        } else {
            System.out.println("No potions left!");
        }
    }

    @Override
    public void attack(Character opponent) {
        Random rand = new Random();
        int damage = rand.nextInt(attackPower) + 1;
        System.out.println(name + " attacks " + opponent.getName() + " for " + damage + " damage.");
        opponent.takeDamage(damage);
    }

    @Override
    public void useSpecialAbility(Character opponent) {
        System.out.println(name + " uses a powerful slash!");
        opponent.takeDamage(30);
    }
}
