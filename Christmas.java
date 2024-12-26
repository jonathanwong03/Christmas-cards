import java.util.Scanner;

class Christmas {
    public static void main (String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean[] cards = {false, false, true, true};
        // let true = green
        // let false = red
        String winner = get_winner(cards, sc);
        System.out.println(winner);
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

    public static String get_winner(boolean[] xmas_cards, Scanner sc) {
        int length = xmas_cards.length;
        if (length <= 2) {
            return "Impossible to tell";
        }

        boolean is_all_same = all_same(xmas_cards);
        
        do {
            System.out.println("Select a card you will like to flip: ");
            int idx = sc.nextInt();
            idx -= 1;
            // tackle the edge cases first
            if (idx == 0) {
                xmas_cards[0] = !xmas_cards[0];
                xmas_cards[1] = !xmas_cards[1];
                xmas_cards[length-1] = !xmas_cards[length-1];
            }
            else if (idx == length-1) {
                xmas_cards[length-1] = !xmas_cards[length-1];
                xmas_cards[length-2] = !xmas_cards[length-2];
                xmas_cards[0] = !xmas_cards[0];
            }
            
            else if (idx > 0 && idx < length) {
                xmas_cards[idx] = !xmas_cards[idx];
                xmas_cards[idx-1] = !xmas_cards[idx-1];
                xmas_cards[idx+1] = !xmas_cards[idx+1];
            }
            else {
                System.out.println("Please enter a valid input");
            }
            is_all_same = all_same(xmas_cards);
        } while (!is_all_same);
       
        return xmas_cards[0] ? "Player 1 wins": "Player 2 wins";
    }
}

   
    
