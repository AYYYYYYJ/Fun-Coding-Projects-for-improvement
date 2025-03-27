//*****************************************************
//**************** JAVA BLACKJACK *********************
//*****************************************************

//TO DO: recreate this using multiple files instead of 1
//TO DO: Also include a different gui than the terminal 
    import java.util.ArrayList;
    import java.util.Scanner;
    import java.util.Random;

    class Main {
    private static int acesUsed;
    private static int aceCount;
    private static int winCount;
    private static int lossCount;
    
    public static void main(String[] args) {
        winCount = 0;
        lossCount = 0;
        Scanner sus = new Scanner(System.in);
        Random rand = new Random();
        String[] deck = { "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A" };
        ArrayList<String> playerDeck = new ArrayList<String>();
        ArrayList<String> botDeck = new ArrayList<String>();
        boolean gameActive = true;

        while (gameActive) {

        playerDeck.clear();
        botDeck.clear();
        int pTotal = 0;
        int botTotal = 0;
        acesUsed = 0;
        aceCount = 0;
        String choice = "";
        String firstCards = "";

        System.out.println("Hello it's blackjack time. Here are your two cards\n");

        // make first two cards
        for (int i = 0; i < 2; i++) {
            firstCards = deck[rand.nextInt(deck.length)];
            playerDeck.add(firstCards);
            pTotal += addTotal(firstCards, pTotal);
            System.out.print(playerDeck.get(i));
            if (i != 1) {
            System.out.print(", ");
            }
            pTotal = checkAce(playerDeck, pTotal);
        }

        // make bot's first cards and display one of them
        System.out.print("\t\t\t Bot's Cards: ");
        for (int i = 0; i < 2; i++) {
            firstCards = deck[rand.nextInt(deck.length)];
            botDeck.add(firstCards);
            botTotal += addTotal(firstCards, botTotal);
            botTotal = checkAce(botDeck, botTotal);
        }
        System.out.print(botDeck.get(0) + ", _");
        System.out.println("\nTotal: " + pTotal + "\n");

        while (true) {

            if (pTotal <= 21) {
            // if total <= 21, keep running
            while (!(choice.equalsIgnoreCase("h") || choice.equalsIgnoreCase("s"))) {

                System.out.println("Hit (h) or stand (s)?");
                choice = sus.nextLine();
                if (!(choice.equalsIgnoreCase("h") || choice.equalsIgnoreCase("s"))) {
                System.out.println("\nPlease enter either \"h\" or \"s\"");
                }
            }

            if (choice.equalsIgnoreCase("s")) {
                break;
            }

            choice = "";
            String card = deck[rand.nextInt(deck.length)];
            displayCards(playerDeck, card);
            pTotal += addTotal(card, pTotal);

            // if over 21 and have an ace, make the ace go from 11 to 1
            // gets stuck in infinite loop if dont count aces already used

            pTotal = checkAce(playerDeck, pTotal);

            System.out.println("\nTotal: " + pTotal + "\n");
            } else {
            // if total > 21, stop game
            break;
            }
        }

        if (pTotal <= 21) {

            System.out.println("\n\nOk time for the bot to play.");
            // make BOT first two cards
            for (int i = 0; i < 2; i++) {
            System.out.print(botDeck.get(i));
            if (i != 1) {
                System.out.print(", ");
            }
            }
            System.out.println("\nBot's Total: " + botTotal + "\n");

            while (botTotal < pTotal) {
            String botCard = deck[rand.nextInt(deck.length)];
            displayCards(botDeck, botCard);
            botTotal += addTotal(botCard, botTotal);
            botTotal = checkAce(botDeck, botTotal);
            System.out.println("\nBot's Total: " + botTotal);
            }

        }

        winText(pTotal, botTotal, playerDeck, botDeck);

        System.out.println("\nWould you like to play again? (h/s)");
        String ans = sus.nextLine();
        if (ans.equals("s")) {
            gameActive = false;
        }
        System.out.println("\n");

        }

        sus.close();
    }


    public static void displayCards(ArrayList<String> playerDeck, String card) {
        playerDeck.add(card);

        for (int i = 0; i < playerDeck.size(); i++) {
        System.out.print(playerDeck.get(i));
        if (i != playerDeck.size() - 1) {
            System.out.print(", ");
        }
        }
        System.out.println();
    }


    
    public static int checkAce(ArrayList<String> deck, int total) {

        for (int i = 0; i < deck.size(); i++) {
        if (deck.get(i).equals("A") && total > 21 && acesUsed != aceCount) {
            total -= 10;
            acesUsed++;
        }
        }
        return total;
    }



    public static int addTotal(String card, int totalCount) {
        int addNum = 0;
        try {
        addNum += Integer.parseInt(card);
        } catch (Exception e) {
        if (card.equals("A")) {
            addNum += 11;
            aceCount++;
        } else {
            addNum += 10;
        }
        }
        return addNum;
    }

    
    public static void winText(int total, int botTotal, ArrayList<String> playerDeck, ArrayList<String> botDeck) {
        System.out.println();
        // if bust automatically lose
        if (total > 21) {
        for (int i = 0; i < 3; i++) {
            System.out.println("YOU BUSTED RIP");
        }
        lossCount++;
        System.out.println("\nwins : losses - " + winCount + " : " + lossCount);
        }
        // if more points or bot busts, win
        else if (total > botTotal || botTotal > 21) {
        for (int i = 0; i < 3; i++) {
            System.out.println("YOU WIN LETS GOOOOOOOO");
        }
        winCount++;
        System.out.println("\nwins : losses - " + winCount + " : " + lossCount);
        }
        // if less points, lose
        else if (total < botTotal) {
        for (int i = 0; i < 3; i++) {
            System.out.println("YOU LOST LLLLLLLLLLLLLLLL");
        }
        lossCount++;
        System.out.println("\nwins : losses - " + winCount + " : " + lossCount);
        }
        // win by blackjack vs 21
        else if (total == 21 && botTotal == 21 && playerDeck.size() == 2 && botDeck.size() != 2) {
        for (int i = 0; i < 3; i++) {
            System.out.println("YOU WIN BY BLACKJACK LETS GOOOOOOOO");
        }
        winCount++;
        System.out.println("\nwins : losses - " + winCount + " : " + lossCount);
        }
        // lose by blackjack vs 21
        else if (total == 21 && botTotal == 21 && playerDeck.size() != 2 && botDeck.size() == 2) {
        for (int i = 0; i < 3; i++) {
            System.out.println("YOU LOST BY BLACKJACK LLLLLLLLLLLLLLLL");
        }
        lossCount++;
        System.out.println("\nwins : losses - " + winCount + " : " + lossCount);
        }
        // nums are same
        else if (total == botTotal) {
        for (int i = 0; i < 3; i++) {
            System.out.println("YOU TIED WITH THE BOT LLLLLLLLLLLLLLLLLLLLL");
        }
        System.out.println("\nwins : losses - " + winCount + " : " + lossCount);
        }
        System.out.println();
    }

}