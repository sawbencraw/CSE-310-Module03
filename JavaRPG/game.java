import java.io.*;
import java.util.*;

public class Game {
    private Player player;
    private List<Enemy> enemies;

    public Game() {
        player = new Player("Hero", 100, 20, 3);
        enemies = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            enemies.add(Enemy.createRandomEnemy());
        }
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to the RPG game!");
        System.out.println("Your character: " + player.getName() + " with " + player.getHealth() + " health.");

        int enemyIndex = 0;
        while (player.isAlive() && enemyIndex < enemies.size()) {
            Enemy currentEnemy = enemies.get(enemyIndex);

            System.out.println("\nYou encounter a " + currentEnemy.getName() + "!");
            while (player.isAlive() && currentEnemy.isAlive()) {
                System.out.println("\nChoose an action: (1) Attack (2) Use Potion (3) Special Ability (4) Save Game (5) Load Game");
                int choice = scanner.nextInt();

                switch (choice) {
                    case 1:
                        player.attack(currentEnemy);
                        break;
                    case 2:
                        player.usePotion();
                        break;
                    case 3:
                        player.useSpecialAbility(currentEnemy);
                        break;
                    case 4:
                        saveGame();
                        break;
                    case 5:
                        loadGame();
                        break;
                    default:
                        System.out.println("Invalid choice.");
                }

                if (currentEnemy.isAlive()) {
                    if (Math.random() < 0.3) {
                        currentEnemy.useSpecialAbility(player);
                    } else {
                        currentEnemy.attack(player);
                    }
                }

                System.out.println("\n" + player.getName() + " health: " + player.getHealth());
                System.out.println(currentEnemy.getName() + " health: " + currentEnemy.getHealth());
            }

            if (player.isAlive()) {
                System.out.println("You defeated the " + currentEnemy.getName() + "!");
                enemyIndex++;
            } else {
                System.out.println("You were defeated by the " + currentEnemy.getName() + ".");
                break;
            }
        }

        if (player.isAlive()) {
            System.out.println("Congratulations! You have defeated all the enemies and completed your quest!");
        }

        scanner.close();
    }

    private void saveGame() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("game.sav"))) {
            oos.writeObject(player);
            oos.writeObject(enemies);
            System.out.println("Game saved successfully!");
        } catch (IOException e) {
            System.out.println("Failed to save game: " + e.getMessage());
        }
    }

    private void loadGame() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("game.sav"))) {
            player = (Player) ois.readObject();
            enemies = (List<Enemy>) ois.readObject();
            System.out.println("Game loaded successfully!");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Failed to load game: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        Game game = new Game();
        game.start();
    }
}
