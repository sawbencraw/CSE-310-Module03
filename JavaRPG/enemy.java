import java.io.Serializable;
import java.util.Random;

public class Enemy extends Character implements SpecialAbility, Serializable {
    private static final long serialVersionUID = 1L;

    public Enemy(String name, int health, int attackPower) {
        super(name, health, attackPower);
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
        System.out.println(name + " uses a deadly strike!");
        opponent.takeDamage(25);
    }

    public static Enemy createRandomEnemy() {
        Random rand = new Random();
        String[] names = {"Goblin", "Orc", "Troll", "Dragon"};
        int[] healths = {50, 70, 100, 200};
        int[] attackPowers = {15, 20, 25, 40};

        int index = rand.nextInt(names.length);
        return new Enemy(names[index], healths[index], attackPowers[index]);
    }
}
