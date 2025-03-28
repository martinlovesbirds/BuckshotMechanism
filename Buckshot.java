import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Buckshot {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    Random random = new Random();

    System.out.println("What is player 1's name?");
    String player1 = scanner.nextLine();

    System.out.println("What is player 2's name?");
    String player2 = scanner.nextLine();

    System.out.printf("Welcome %s and %s to Buckshot Roulette%n", player1, player2);

    int p1Lives = 3;
    int p2Lives = 3;

    while (p1Lives > 0 && p2Lives > 0) {
      int totalRounds = random.nextInt(10) + 1;
      int liveRounds = random.nextInt(totalRounds + 1);
      int blankRounds = totalRounds - liveRounds;

      List<Boolean> rounds = new ArrayList<>();
      for (int i = 0; i < liveRounds; i++) {
        rounds.add(true); // true represents a live round
      }
      for (int i = 0; i < blankRounds; i++) {
        rounds.add(false); // false represents a blank round
      }

      // Shuffle the list to randomize the order of rounds
      Collections.shuffle(rounds);

      System.out.printf("There are %d total rounds, %d are live, %d are blanks%n", totalRounds, liveRounds, blankRounds);

      boolean player1Turn = random.nextBoolean();
      if (player1Turn) {
        System.out.printf("%s is the first player to shoot%n", player1);
      } else {
        System.out.printf("%s is the first player to shoot%n", player2);
      }

      int roundIndex = 0;

      while (p1Lives > 0 && p2Lives > 0 && roundIndex < rounds.size()) {
        boolean round = rounds.get(roundIndex);
        String currentPlayer = player1Turn ? player1 : player2;
        String otherPlayer = player1Turn ? player2 : player1;

        System.out.printf("%s, would you like to shoot the system or the player?%n", currentPlayer);
        String shoot = scanner.nextLine();

        if (shoot.equalsIgnoreCase("system")) {
          if (round) {
            System.out.printf("%s shot a live round and loses a life!%n", currentPlayer);
            if (player1Turn) {
              p1Lives--;
              System.out.printf("%s has %d lives left%n", player1, p1Lives);
            } else {
              p2Lives--;
              System.out.printf("%s has %d lives left%n", player2, p2Lives);
            }
          } else {
            System.out.println("It was a blank round. No lives lost.");
          }
        } else { // shooting the other player
          if (round) {
            System.out.printf("%s shot a live round and %s loses a life!%n", currentPlayer, otherPlayer);
            if (player1Turn) {
              p2Lives--;
              System.out.printf("%s has %d lives left%n", player2, p2Lives);
            } else {
              p1Lives--;
              System.out.printf("%s has %d lives left%n", player1, p1Lives);
            }
          } else {
            System.out.println("It was a blank round. No lives lost.");
          }
        }

        player1Turn = !player1Turn; // Switch turns
        roundIndex++;

        // Add 1 life to both players if rounds are exhausted
        if (roundIndex >= rounds.size() && p1Lives > 0 && p2Lives > 0) {
          p1Lives++;
          p2Lives++;
          System.out.printf("Both players have survived the rounds. %s and %s each gain an extra life!%n", player1, player2);
          System.out.printf("%s has %d lives, and %s has %d lives%n", player1, p1Lives, player2, p2Lives);
        }
      }
    }

    if (p1Lives == 0) {
      System.out.printf("%s wins! %s is out of lives.%n", player2, player1);
    } else {
      System.out.printf("%s wins! %s is out of lives.%n", player1, player2);
    }

    scanner.close();
  }
}