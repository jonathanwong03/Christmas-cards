import java.util.Scanner;

class Christmas {
    public static boolean choose_colour(Scanner sc) {
        // let Player 1 choose colour first, Player 2 will pick the other colour
        System.out.println("Player 1, pick your colour: ");
        String colour = sc.next();
        while (!colour.equals("Green") && !colour.equals("green") && !colour.equals("Red") && !colour.equals("red")) {
            System.out.println("Please enter a valid colour: ");
            colour = sc.nextLine();
        }
        return colour.equals("Green") || colour.equals("green"); 
    }

    public static String get_current_player(int count) {
        return count % 2 != 0 ? "Player 1": "Player 2";
    }

    public static boolean all_same(boolean[] xmas_cards) {
        // this is to check if all are true or all are false
        boolean first_card = xmas_cards[0];
        for (int i = 1; i < xmas_cards.length; i++) {
            if (xmas_cards[i] != first_card) {
                return false;
            }
        }
        return true;
    }

    public static void print_winner(boolean var1, boolean var2) {
        if (var1 == var2) {
            System.out.println("Player 1 wins");
            return;
        }
        System.out.println("Player 2 wins");
    }

    public static void get_winner(boolean[] xmas_cards, Scanner sc) {
        // first get the number of elements in the array
        int length = xmas_cards.length;

        // check to see if there are only 2 cards or less
        if (length <= 3) {
            System.out.println("Impossible to tell");
            return;
        }
        
        // now, we start the game
        int count = 1;
        boolean p1_colour = choose_colour(sc);
        boolean is_all_same = all_same(xmas_cards);
        
        while (!is_all_same) {
            String current_player = get_current_player(count);
            System.out.println(current_player + ", select a card you will like to flip: ");
            int idx = sc.nextInt();
            idx -= 1;

            // tackle the edge cases first
            if (idx == 0) {
                xmas_cards[0] = !xmas_cards[0];
                xmas_cards[1] = !xmas_cards[1];
                xmas_cards[length-1] = !xmas_cards[length-1];
                count++;
            }
            else if (idx == length-1) {
                xmas_cards[length-1] = !xmas_cards[length-1];
                xmas_cards[length-2] = !xmas_cards[length-2];
                xmas_cards[0] = !xmas_cards[0];
                count++;
            }

            // then tackle the remaining cases
            else if (idx > 0 && idx < length) {
                xmas_cards[idx] = !xmas_cards[idx];
                xmas_cards[idx-1] = !xmas_cards[idx-1];
                xmas_cards[idx+1] = !xmas_cards[idx+1];
                count++;
            }

            // if it goes out of bounds
            else {
                System.out.println("Please enter a valid input. ");
            }

            // check to see if all the cards are the same colour
            is_all_same = all_same(xmas_cards);
        } 

        // print the winner of the game
        print_winner(p1_colour, xmas_cards[0]);
    }

    public static void main (String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean[] cards = {false, false, true, true};
        // let true = green
        // let false = red
        // players can flip any card
        // there is a possibility where one player helps the other win by accident
        get_winner(cards, sc);
    }
}
