/* BlackJack Final      Author:Kelli Stasiak        Due Date:  May 15, 2018 */

package blackjack2;

import java.util.ArrayList;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author Kelli
 */
public class BlackJack extends Application {
    
    //GLOBAL VARIABLES
    
    //The Deck
    public static ArrayList<Integer>cardDeck = Deck.CreateTheDeck();
    
    //Creating a HBox to hold player's cards
    public static HBox playerHBox = new HBox();
   
    //Creating a HBox to hold dealer's cards
    public static HBox dealerHBox = new HBox();

    //Player Total Amount
    public static Label playerHardTotalLabel = new Label();
    public static Label playerSoftTotalLabel = new Label();
    public static int playerGrandTotal;
    
    //Dealer Total Amount
    public static int dealerHardTotal;
    public static int dealerSoftTotal;
    
    //ArrayList holding the player's and dealer's hand
    public static ArrayList<Integer>playerHand = new ArrayList<>();
    public static ArrayList<Integer>dealerHand = new ArrayList<>();
    
    public static Button btnHit = new Button("Hit Me");
    
    public static Button btnStay = new Button("Stay");
        
    public static int cardCount = 5;    
    
    public static int wins;
    public static int loss;
    
    //Status Label
    public static Label statusLabel = new Label();
    //Credit Label
    public static Label creditLabel = new Label();
    //Win/Loss Stats
    public static Label getStats = new Label();
    
    //This is the dealer card that will be hidden half of the round
    public static ImageView iVDCard1 = new ImageView();
        
    @Override
    public void start(Stage primaryStage){
        //Creating a pane to hold the card table
        BorderPane borderPane = new BorderPane();
        borderPane.setPadding(new Insets (10, 10, 10, 10));
        borderPane.setStyle("-fx-background-color: rgba(115, 205, 50, 1)");
       
        HBox statusBar = new HBox();
        statusBar.setMaxHeight(5);
        statusLabel.setStyle("-fx-text-fill: yellow; -fx-font: 26px \"Serif\"; ");
        statusLabel.setAlignment(Pos.BASELINE_CENTER);  

        //LABELS
        //Player Label
        Label playerLabel = new Label("Player");
        
        //Dealer Label
        Label dealerLabel = new Label("Dealer"); 
        
        //Win/Loss Stats
        getStats.setText("Wins: " + wins + "\tLoss: " + loss);
        
        //BUTTONS
        //New Game Button
        Button newGame = new Button("New Game");
        
        //NextGame Button
        Button nextGame = new Button("Start New Game");
        nextGame.setVisible(false);
        
        //Stay Button
        btnStay.setDisable(true);
        
        //Hit Button is a global variable
        btnHit.setDisable(true);
   
        //Creating a vertical box to hold buttons, credits, Deck totals
        VBox vBox = new VBox();
                
        //Setting Style and alignment for creditLabel
        creditLabel.setStyle("-fx-text-fill: yellow; -fx-font: 26px \"Serif\"; ");
        creditLabel.setAlignment(Pos.TOP_CENTER);
        
        //EVENT HANDLERS
        
        //newGame Button Event Handler
        newGame.setOnAction((ActionEvent event) -> {
            btnHit.setDisable(false);
            btnStay.setDisable(false);
            newGame.setVisible(false);
            DealFirstFourCards();
            nextGame.setVisible(true);
        });
        
        //Hit Button Event Handler
        btnHit.setOnAction((ActionEvent event) -> {
            HitMe(cardCount, cardDeck);
            AddCardValue(playerHand);
            cardCount++;
        });        
        
        //Stay Button Event Handler
        btnStay.setOnAction((ActionEvent event) -> {
           btnHit.setDisable(true);//disables the hit button after user hits the stay button
           playerGrandTotal = CompareBestPlayerTotal(); 
           DealersTotal(dealerHand);
           DealersCards(dealerHand, cardCount);
           btnStay.setDisable(true);
        });
        
        //Restart Game Handler
        nextGame.setOnAction((ActionEvent event) ->{
           nextGame.setVisible(false);
           newGame.setVisible(true);
           playerHBox.getChildren().clear();
           dealerHBox.getChildren().clear();
           playerHardTotalLabel.setText(" ");
           playerSoftTotalLabel.setText(" ");
           statusLabel.setText(" ");
           dealerHardTotal = 0;
           dealerSoftTotal = 0;
           cardDeck = Deck.CreateTheDeck();
           playerHand.clear();
           dealerHand.clear();
           cardCount = 5;
        });
        
        
        //laying out the border pane
        borderPane.setTop(dealerHBox);
        borderPane.setCenter(statusBar);
        borderPane.setBottom(playerHBox);
        borderPane.setRight(vBox);
        
        //Adding elements to the hboxes and vbox
        vBox.getChildren().addAll(getStats, creditLabel, nextGame, newGame, btnHit, btnStay, playerHardTotalLabel, playerSoftTotalLabel);
        playerHBox.getChildren().add(playerLabel);
        dealerHBox.getChildren().add(dealerLabel);
        statusBar.getChildren().add(statusLabel);
        
        //Setting the scene
        Scene scene = new Scene(borderPane, 600, 500);
        primaryStage.setTitle("Kelli's Black Jack Table");
        primaryStage.setScene(scene);
        primaryStage.show();
   
    }//end of start
    
    public static void main(String[] args) {
        //Testing
        //System.out.println(CreatingTheDeck());
        launch(args);
    }//end Main
    
    public static void DealFirstFourCards(){
        //This Method Deals the first 4 cards of the game
        //Player Card one
        Card playerCard1 = new Card(cardDeck.get(1));
        Image card1 = new Image(playerCard1.ImageName(cardDeck.get(1)));
        ImageView iVCard1 = new ImageView();
        iVCard1.setImage(card1);
        playerHand.add(playerCard1.GettingCardValue(cardDeck.get(1)));

                  
        //Player Card two
        Card playerCard2 = new Card(cardDeck.get(3));
        Image card2 = new Image(playerCard2.ImageName(cardDeck.get(3)));
        ImageView iVCard2 = new ImageView();
        iVCard2.setImage(card2);
        playerHand.add(playerCard2.GettingCardValue(cardDeck.get(3)));
        AddCardValue(playerHand);
        
        
        playerHBox.getChildren().addAll(iVCard1, iVCard2);
          
        //Dealer Card one
        Card dealerCard1 = new Card(cardDeck.get(cardDeck.get(2)));
        //Hidden Card for Dealer
        Image hiddenCard = new Image(dealerCard1.HiddenCard());
        iVDCard1.setImage(hiddenCard);
        dealerHand.add(dealerCard1.GettingCardValue(cardDeck.get(2)));
        
        //Dealer Card Two
        Card dealerCard2 = new Card(cardDeck.get(cardDeck.get(4)));
        Image dCard2 = new Image(dealerCard2.ImageName(cardDeck.get(4)));
        ImageView iVDCard2 = new ImageView();
        iVDCard2.setImage(dCard2);
        dealerHand.add(dealerCard2.GettingCardValue(cardDeck.get(4)));
        
        dealerHBox.getChildren().addAll(iVDCard1, iVDCard2);
    }//End DealFirstFourCards

    public static void DealersCards(ArrayList<Integer>dealerHand, int cardCount){
        //This Method deals the dealer's additional cards if needed
        int dealerCardCount = 3;
        
        //Showing the hidden dealer card
        Card dealerCard1 = new Card(cardDeck.get(cardDeck.get(2)));
        Image dCard1 = new Image(dealerCard1.ImageName(cardDeck.get(2)));
        iVDCard1.setImage(dCard1);
        
        while (dealerSoftTotal <= 16 && dealerHardTotal <= 16 && dealerCardCount < 6){
           //Dealer must hit
            if(dealerCardCount == 3){
                Card dealerCard3 = new Card(cardDeck.get(cardCount));
                Image dealerCard3Image = new Image(dealerCard3.ImageName(cardDeck.get(cardCount)));
                ImageView iVDCard3 = new ImageView();
                iVDCard3.setImage(dealerCard3Image);
                dealerHBox.getChildren().add(iVDCard3);
                dealerHand.add(dealerCard3.GettingCardValue(cardDeck.get(cardCount)));
                DealersTotal(dealerHand);
                dealerCardCount++;
                cardCount++;
            }//end if

            if(dealerCardCount == 4 && dealerSoftTotal <= 16 && dealerHardTotal <= 16){
                Card dealerCard4 = new Card(cardDeck.get(cardCount));
                Image dealerCard4Image = new Image(dealerCard4.ImageName(cardDeck.get(cardCount)));
                ImageView iVDCard4 = new ImageView();
                iVDCard4.setImage(dealerCard4Image);
                dealerHBox.getChildren().add(iVDCard4);
                dealerHand.add(dealerCard4.GettingCardValue(cardDeck.get(cardCount)));
                DealersTotal(dealerHand);
                dealerCardCount++;
                cardCount++;
            }//end if

            if(dealerCardCount == 5 && dealerSoftTotal <= 16 && dealerHardTotal <= 16){
                Card dealerCard5 = new Card(cardDeck.get(cardCount));
                Image dealerCard5Image = new Image(dealerCard5.ImageName(cardDeck.get(cardCount)));
                ImageView iVDCard5 = new ImageView();
                iVDCard5.setImage(dealerCard5Image);
                dealerHBox.getChildren().add(iVDCard5);
                dealerHand.add(dealerCard5.GettingCardValue(cardDeck.get(cardCount)));
                DealersTotal(dealerHand);
                dealerCardCount++;
                cardCount++;
            }//end if
        }//end while
        WhoWon();
    }//end dealer cards
    
    public static void CheckForTwentyOne(){
        //This function check for a 21 before the dealer has a chance to get their cards
        
        int playerSoftTotal = Integer.parseInt(playerSoftTotalLabel.getText());
        int playerHardTotal = Integer.parseInt(playerHardTotalLabel.getText());
        
        if(playerHand.size() == 2){
            if (playerSoftTotal == 21 || playerHardTotal == 21){
                btnHit.setDisable(true);
                btnStay.setDisable(true);
                wins++;
                getStats.setText("Wins: " + wins + "\tLoss: " + loss);
                WhoWon("You" , 21);
            }//end if
        }//end if
                
        //Checking for a 5 card Charlie
        if(playerHand.size() == 5){
            if(playerSoftTotal <= 21 && playerHardTotal <= 21){
                btnHit.setDisable(true);
                btnStay.setDisable(true);
                statusLabel.setText("Five Card Charlie!! You Win!!");
                wins++;
                getStats.setText("Wins: " + wins + "\tLoss: " + loss);
            } //end if
           
            if(playerSoftTotal <= 21){
                btnHit.setDisable(true);
                btnStay.setDisable(true); 
                statusLabel.setText("Five Card Charlie!! You Win!!");
                wins++;
                getStats.setText("Wins: " + wins + "\tLoss: " + loss);
            }//end if
        }//end if

        if(playerSoftTotal > 21 && playerHardTotal > 21){
            btnHit.setDisable(true);
            btnStay.setDisable(true);
            statusLabel.setText("You Busted, Dealer Wins");
            loss++;
            getStats.setText("Wins: " + wins + "\tLoss: " + loss);
        }//end if
    }//end CheckForTwentyOne  
    
    public static void WhoWon (){
        //This Function checks for who has the better hand between the dealer and the player
        
        int dealerBestHand = CompareBestDealerTotal();
        int playerBestHand = CompareBestPlayerTotal();
        
        if (playerBestHand > 21){
           statusLabel.setText("Busted, Dealer Wins");
           loss++;
           getStats.setText("Wins: " + wins + "\tLoss: " + loss);
        }//end if
        
        if (dealerBestHand > 21){
            statusLabel.setText("Dealer Busted, You Win");
            wins++;
            getStats.setText("Wins: " + wins + "\tLoss: " + loss);
        }//end if
        
        else{
            if(dealerBestHand > playerBestHand){
                statusLabel.setText("Dealer Wins");
                loss++;
                getStats.setText("Wins: " + wins + "\tLoss: " + loss);
            }//end if
        
            else{
                statusLabel.setText("You Win!!!");
                wins++;
                getStats.setText("Wins: " + wins + "\tLoss: " + loss);
            }//end else            
        }//end else
    }//end WhoWon
    
    public static void WhoWon (String whoWon, int blackJack){
        //This Method is only used if the player or dealer hits a Black Jack
        statusLabel.setText(whoWon + " Hit A Black Jack");
    }//End WhoWon
    
    public static void DealersTotal(ArrayList<Integer>dealerHand){
    //This Method adds the card for the dealers hand    
        for(int i = 0; i < dealerHand.size(); i++){
            //Hard Total with an Ace
            if (dealerHand.get(i) == 1){
                dealerHardTotal += 10;
            }//end if
           //Soft Total 
           dealerSoftTotal += dealerHand.get(i); 
           dealerHardTotal += dealerHand.get(i);
        }//end for
        
        if(dealerHand.size() == 2){
            if(dealerSoftTotal == 21 || dealerHardTotal == 21){
                WhoWon("Dealer" , 21);
            }//end if
        }//end if    
        
        if(dealerSoftTotal > 21 && dealerHardTotal > 21){
            statusLabel.setText("Dealer Busted, You Win!");
            getStats.setText("Wins: " + wins + "\tLoss: " + loss);
        }//end if
    }//end DealerTotal
    
    public static int CompareBestDealerTotal(){
        //This method compares the hard and soft totals for the dealers and returns the best hand
        int grandTotal = 0;
        
        if(dealerSoftTotal <= 21 && dealerHardTotal <= 21){
            if(dealerSoftTotal > dealerHardTotal){
                grandTotal = dealerSoftTotal;
                return grandTotal;
            }//end if
            else{
                grandTotal = dealerHardTotal;
                return grandTotal;
            } //end else   
        }//end if
        else{
            if (dealerSoftTotal <= 21){
                grandTotal = dealerSoftTotal;
                return grandTotal;
            }//end if 
            if (dealerHardTotal <= 21){
                grandTotal = dealerHardTotal;
                return grandTotal;
            }//end if
        }//end else
        return grandTotal;
    }//End CompareBestDealerTotal
    
    public static int CompareBestPlayerTotal(){
        //This Method compares the players hard and soft total and returns the best hand
        
        int playerSoftTotal = Integer.parseInt(playerSoftTotalLabel.getText());
        int playerHardTotal = Integer.parseInt(playerHardTotalLabel.getText());
        int grandTotal = 0;
        
        if(playerSoftTotal <= 21 && playerHardTotal <= 21){
            if(playerSoftTotal > playerHardTotal){
                grandTotal = playerSoftTotal;
                return grandTotal;
            }//end if
                   
            else{
                grandTotal = playerHardTotal;
                return grandTotal;
            } //end else   
        }//end if
        else{
            if (playerSoftTotal <= 21){
                grandTotal = playerSoftTotal;
                return grandTotal;
            }//end if 
            if (playerHardTotal <= 21){
                grandTotal = playerHardTotal;
                return grandTotal;
            }//end if
        }//end else
        return grandTotal;
    }//end CompareBestPlayerTotal
    
    public static void AddCardValue(ArrayList<Integer>playerHand){
        //This gets the total for the players hand
        
        int playerSoftTotal = 0;
        int playerHardTotal = 0;
        for(int i = 0; i < playerHand.size(); i++){
            //Hard Total with an Ace
            if (playerHand.get(i) == 1){
                playerHardTotal += 10;
            }//end if
           //Soft Total 
           playerSoftTotal += playerHand.get(i); 
           playerHardTotal += playerHand.get(i);
           playerHardTotalLabel.setText(String.valueOf(playerHardTotal));
           playerSoftTotalLabel.setText(String.valueOf(playerSoftTotal));
           CheckForTwentyOne();
        }//end for
    }//end AddCardValue   
    
    public static void HitMe(int cardCount, ArrayList<Integer>cardDeck){
        //This method is activated when the user hits the hit me button for more cards
        
        if(cardCount == 5){
            Card playerCard3 = new Card(cardDeck.get(cardCount));
            Image playerCard3Image = new Image(playerCard3.ImageName(cardDeck.get(cardCount)));
            ImageView iVCard3 = new ImageView();
            iVCard3.setImage(playerCard3Image);
            playerHBox.getChildren().add(iVCard3);
            playerHand.add(playerCard3.GettingCardValue(cardDeck.get(cardCount)));
        }//end if
        
        if(cardCount == 6){
            Card playerCard4 = new Card(cardDeck.get(cardCount));
            Image playerCard4Image = new Image(playerCard4.ImageName(cardDeck.get(cardCount)));
            ImageView iVCard4 = new ImageView();
            iVCard4.setImage(playerCard4Image);
            playerHBox.getChildren().add(iVCard4);
            playerHand.add(playerCard4.GettingCardValue(cardDeck.get(cardCount)));
        }//end if
        
        if(cardCount == 7){
            //FIVE CARD CHARLIE - AUTOMATIC WIN IF UNDER 21
            Card playerCard5 = new Card(cardDeck.get(cardCount));
            Image playerCard5Image = new Image(playerCard5.ImageName(cardDeck.get(cardCount)));
            ImageView iVCard5 = new ImageView();
            iVCard5.setImage(playerCard5Image);
            playerHBox.getChildren().add(iVCard5);
            playerHand.add(playerCard5.GettingCardValue(cardDeck.get(cardCount)));
        }//end if       
    }//end hit me
    
    
    
/*THIS IS HOW I ORGINALLY PROGRAMMED THE CARDS USING A 2D ARRRAY I JUST LEFT IT ON HERE TO SHOW YOU*/   
    
//    public static String [] [] CreatingTheDeck(){
//        //Mutli Dimensional array
//        //["ArrayImage.png", "Numeric Value in a string", "Queen of hearts"]
//        //To get numeric Value = int.Value[i];
//        
//        //Create MultiDimentional array
//        String value;
//        String cardType;
//        String cardSuite;
//        String cardImage;
//        String cardName;
//        int cardIdNumber;
//        //Get user input for this number
//        int numOfDecks = 1;
//        int rows = (numOfDecks * 52);
//        int columns = 4;
//
//        //this gives me a multi dimentional array that is:
//        //Rows that is how many cards there will be
//        //Then columns which will hold Card [cardImage],[cardValue],[cardName]
//        String [] [] cardDeck = new String [rows] [columns];
//        
//        
//        //Filling in Card Deck with Values
//        
//        for(int i = 0; i < rows; i++){
//            //filling in the image name
//            cardIdNumber = (i + 1);
//            //Testing
//            //System.out.println("Card ID Number " + cardIdNumber);
//            for (int j = 0; j < columns; j++){
//                if (j == 0){
//                    cardImage = String.valueOf("file:\\C:\\Users\\Kelli\\Documents\\NetBeansProjects\\BlackJack\\images\\" + cardIdNumber + ".png");
//                    cardDeck[i][j] = cardImage;
//                    //System.out.println(cardDeck[i][j]);
//                }//end adding image to array
//                
//                //filling in the value of the card
//                if (j == 1){
//                    cardDeck[i][j] = GettingCardValue(cardIdNumber);
//                }//end if
//                
//                //Getting the name of the card "King, Queen, Two, etc"
//                if (j == 2){
//                    cardDeck[i][j] = GettingCardName(cardIdNumber);
//                }//end of if for cardName
//                
//                //Getting the card suite
//                if (j == 3){
//                    cardDeck [i][j] = GettingCardSuite(cardIdNumber);
//                }//end of if for card suite
//                
//            }//end of for loop for columns
//            
//        }//end of for loop for rows        
//        
//        //Testing
////        for(int i = 0; i < rows; i++){
////            for(int j = 0; j < columns; j++){
////                System.out.println(cardDeck[i][j]);
////            }//end for loop
////            System.out.println("");
////        }//end for loop
//        
//        return cardDeck;
//    }
//    
//    public static String GettingCardValue(int cardIdNumber){
//        if (cardIdNumber == 1 || cardIdNumber == 14 ||cardIdNumber == 27 || cardIdNumber == 40){
//            return "1";
//        }//end if
//        
//        if (cardIdNumber == 2 || cardIdNumber == 15 ||cardIdNumber == 28 || cardIdNumber == 41){
//            return "2";
//        }//end if
//        
//        if (cardIdNumber == 3 || cardIdNumber == 16 ||cardIdNumber == 29 || cardIdNumber == 42){
//            return "3";
//        }//end if
//        
//        if (cardIdNumber == 4 || cardIdNumber == 17 ||cardIdNumber == 30 || cardIdNumber == 43){
//            return "4";
//        }//end if
//        
//        if (cardIdNumber == 5 || cardIdNumber == 18 ||cardIdNumber == 31 || cardIdNumber == 44){
//            return "5";
//        }//end if
//        
//        if (cardIdNumber == 6 || cardIdNumber == 19 ||cardIdNumber == 32 || cardIdNumber == 45){
//            return "6";
//        }//end if
//        
//        if (cardIdNumber == 7 || cardIdNumber == 20 ||cardIdNumber == 33 || cardIdNumber == 46){
//            return "7";
//        }//end if
//        
//        if (cardIdNumber == 8 || cardIdNumber == 21 ||cardIdNumber == 34 || cardIdNumber == 47){
//            return "8";
//        }//end if
//        
//        if (cardIdNumber == 9 || cardIdNumber == 22 ||cardIdNumber == 35 || cardIdNumber == 48){
//            return "9";
//        }//end 
//        
//        if (cardIdNumber == 10 || cardIdNumber == 23 ||cardIdNumber == 36 || cardIdNumber == 49){
//            return "10";
//        }//end if
//        
//        if (cardIdNumber == 11 || cardIdNumber == 24 ||cardIdNumber == 37 || cardIdNumber == 50){
//            return "10";
//        }//end if
//        
//        if (cardIdNumber == 12 || cardIdNumber == 25 ||cardIdNumber == 38 || cardIdNumber == 51){
//            return "10";
//        }//end if
//        
//        if (cardIdNumber == 13 || cardIdNumber == 26 ||cardIdNumber == 39 || cardIdNumber == 52){
//            return "10";
//        }//end if
//        
//        else {
//            return null;
//        }
//    }
//    
//    public static String GettingCardName(int cardIdNumber){
//        if (cardIdNumber == 1 || cardIdNumber == 14 ||cardIdNumber == 27 || cardIdNumber == 40){
//            return "Ace";
//        }//end if
//        
//        if (cardIdNumber == 2 || cardIdNumber == 15 ||cardIdNumber == 28 || cardIdNumber == 41){
//            return "Two";
//        }//end if
//        
//        if (cardIdNumber == 3 || cardIdNumber == 16 ||cardIdNumber == 29 || cardIdNumber == 42){
//            return "Three";
//        }//end if
//        
//        if (cardIdNumber == 4 || cardIdNumber == 17 ||cardIdNumber == 30 || cardIdNumber == 43){
//            return "Four";
//        }//end if
//        
//        if (cardIdNumber == 5 || cardIdNumber == 18 ||cardIdNumber == 31 || cardIdNumber == 44){
//            return "Five";
//        }//end if
//        
//        if (cardIdNumber == 6 || cardIdNumber == 19 ||cardIdNumber == 32 || cardIdNumber == 45){
//            return "Six";
//        }//end if
//        
//        if (cardIdNumber == 7 || cardIdNumber == 20 ||cardIdNumber == 33 || cardIdNumber == 46){
//            return "Seven";
//        }//end if
//        
//        if (cardIdNumber == 8 || cardIdNumber == 21 ||cardIdNumber == 34 || cardIdNumber == 47){
//            return "Eight";
//        }//end if
//        
//        if (cardIdNumber == 9 || cardIdNumber == 22 ||cardIdNumber == 35 || cardIdNumber == 48){
//            return "Nine";
//        }//end 
//        
//        if (cardIdNumber == 10 || cardIdNumber == 23 ||cardIdNumber == 36 || cardIdNumber == 49){
//            return "Ten";
//        }//end if
//        
//        if (cardIdNumber == 11 || cardIdNumber == 24 ||cardIdNumber == 37 || cardIdNumber == 50){
//            return "Jack";
//        }//end if
//        
//        if (cardIdNumber == 12 || cardIdNumber == 25 ||cardIdNumber == 38 || cardIdNumber == 51){
//            return "Queen";
//        }//end if
//        
//        if (cardIdNumber == 13 || cardIdNumber == 26 ||cardIdNumber == 39 || cardIdNumber == 52){
//            return "King";
//        }//end if
//        
//        else {
//            return null;
//        }
//              
//    } //End of GettingCardName Method
//     
//    public static String GettingCardSuite(int cardIdNumber){
//        //CardSuite
//        
//        if(cardIdNumber <= 13){
//          return " of Clubs";  
//        }
//        if(cardIdNumber > 13 && cardIdNumber <= 26){
//          return " of Diamonds";  
//        }
//        if(cardIdNumber > 26 && cardIdNumber <= 39){
//          return" of Hearts";  
//        }
//        if (cardIdNumber > 39 && cardIdNumber <= 52){
//          return " of Spades";  
//        }
//        else{
//            return null;
//        }
//    }//end GettingCardSuite Method 
//    
//    public static Integer [] CreateAndShuffleDeck(){
//        int [] deck = new int [52];
//        
//        for (int i = 0; i <= deck.length; i++){
//            deck[i] = i;
//            String cardName = String.valueOf("Card" + i);
//            Card new cardName =  
//        }
//        
//        
//        
//        
//        return shuffleDeck;
//    }
}    

