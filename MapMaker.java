package Games;

import java.util.Random;
import java.util.Scanner;

public class MapMaker {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        // Constants for tile ranges
        final int STONE_MIN = 0, STONE_MAX = 6;
        final int GRASS_MIN = 7, GRASS_MAX = 11;
        final int FLOWER_MIN = 12, FLOWER_MAX = 16;

        // Dimensions of the map
        final int ROWS = 12, COLUMNS = 16;

        // 2D array to store the tile map
        int[][] map = new int[ROWS][COLUMNS];

        // Prompt user for tile type
        System.out.println("Choose a tile type for the map: stone [1], grass [2], or flower [3].");

        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLUMNS; col++) {
                System.out.print("Enter tile type for position (" + row + ", " + col + "): ");
                int tileType = scanner.nextInt();
                int value;

                // Determine the random range based on the tile type
                switch (tileType) {
                    case 1:
                        value = random.nextInt(STONE_MAX - STONE_MIN + 1) + STONE_MIN;
                        break;
                    case 2:
                        value = random.nextInt(GRASS_MAX - GRASS_MIN + 1) + GRASS_MIN;
                        break;
                    case 3:
                        value = random.nextInt(FLOWER_MAX - FLOWER_MIN + 1) + FLOWER_MIN;
                        break;
                    default:
                        System.out.println("Invalid tile type. Defaulting to grass.");
                        value = random.nextInt(GRASS_MAX - GRASS_MIN + 1) + GRASS_MIN;
                }

                map[row][col] = value;
            }
        }

        // Print the generated tile map
        System.out.println("\nGenerated Tile Map:");
        for (int[] row : map) {
            for (int tile : row) {
                System.out.print(tile + " ");
            }
            System.out.println();
        }

        scanner.close();
    }
}

