import java.util.Scanner;
import java.util.Map;
import java.util.HashMap;

class TwoDimensions {

     public static int choose_colour(Scanner sc) {
        // let Player 1 choose colour first, Player 2 will pick the other colour
        System.out.println("Player 1, pick your colour: ");
        String colour = sc.next();
        while (!colour.equals("Green") && !colour.equals("green") && !colour.equals("Red") && !colour.equals("red")) {
            System.out.println("Please enter a valid colour: ");
            colour = sc.nextLine();
        }
        return colour.equals("Green") || colour.equals("green") ? 1 : 2; 
    }

    public static int get_colour(int[][] values) {
       // create an array to keep track of the 1s and 2s
       int[] numbers = {0, 0};
       
       // first one represents number of 1s, second one represents number of 2s
       for (int i = 0; i < values.length; i++) {
            for (int j = 0; j < values.length; j++) {
                if (values[i][j] == 1) {
                    numbers[0]++;
                }
                else if (values[i][j] == 2) {
                    numbers[1]++;
                }
            }
       }

       if (numbers[0] != 0 && numbers[1] == 0) {
            return 1;
       }
       else if (numbers[0] == 0 && numbers[1] != 0) {
            return 2;
       }
       return -1;
    }

    public static String get_current_player(int turn) {
        return turn % 2 != 0 ? "Player 1" : "Player 2";
    }

    public static void modify_array(int[][] values, int row_coordinate, int column_coordinate, int length) {
        for (int r = -1; r < 2; r++) {
            for (int c = -1; c < 2; c++) {
                // tackle the edge cases first to make sure index is not out of bounds
                if (row_coordinate + r < 0 || row_coordinate + r > length - 1 ) {
                    continue;
                }
                else if (column_coordinate + c < 0 || column_coordinate + c > length - 1) {
                    continue;
                }
                else if (values[row_coordinate + r][column_coordinate + c] == 1) {
                    values[row_coordinate + r][column_coordinate + c] = 2;
                }
                else if (values[row_coordinate + r][column_coordinate + c] == 2) {
                    values[row_coordinate + r][column_coordinate + c] = 1;
                }
            }
        }
    }

    public static void print_winner(int p1_colour, int winning_colour) {
        if (p1_colour == winning_colour) {
            System.out.println("Player 1 wins");
            return;
        }
        System.out.println("Player 2 wins");
    }

    public static void get_winner(int[][] values, Scanner sc) {
        // we determine the length of the array
        int length = values.length;
        if (length <= 2) {
            System.out.println("Impossible to tell");
            return;
        }

        // we ask Player 1 to choose a colour
        int p1_colour = choose_colour(sc);

        // determine the number of turns 
        int turn = 1;

        // we can create a hashmap to link each card to each position in the array
        Map m = new HashMap();
        int arr_position = 0;
        int count = 0;
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length; j++) {
                arr_position++;
                if (values[i][j] == 1 || values[i][j] == 2) {
                    m.put(++count, arr_position);
                }
            }
        }

        // now, we determine if all the values in the array only contain 1 or 2 
        // -1: mix of 1s and 2s (there is no winning colours)
        // 1: only contain 1s
        // 2: only contain 2s
        int winning_colour = get_colour(values);
        
        // now, we start the game
        while (winning_colour == -1) {
            String current_player = get_current_player(turn);
            System.out.println(current_player + ", please select a card to flip: "); 
            int card = sc.nextInt();

            if (card < 1 || card > length) {
                System.out.println("Please enter a valid card: ");
            }

            else {
                // now, we determine the position of the card based on the key
                int coordinate = (int) m.get(card);
                int row_coordinate = coordinate / length;
                int column_coordinate = (coordinate % length) - 1;

                // check the surrounding 8 squares, and tackle edge cases
                modify_array(values, row_coordinate, column_coordinate, length);
                turn++;
            }

            winning_colour = get_colour(values);
        }

        print_winner(p1_colour, winning_colour);

    }
    public static void main(String[] args) {
        // 0 = empty position
        // 1 = green card
        // 2 = red card
        int[][] values = {{0, 1, 0, 0}, {0, 1, 0, 0}, {0, 2, 2, 0}, {0, 0, 0, 0}};
        Scanner sc = new Scanner(System.in);
        get_winner(values, sc);
    }

}
