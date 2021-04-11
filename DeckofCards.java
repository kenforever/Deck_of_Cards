import java.security.SecureRandom;
import java.util.Arrays;

public class DeckofCards {
	private Card[] deck; 
	private int currentCard; 
	private static final int NUMBER_OF_CARDS = 52; 
    String[] faces = { "Ace", "Deuce", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten", "Jack","Queen", "King" };
    String[] suits = { "Hearts", "Diamonds", "Clubs", "Spades" };
	private static final SecureRandom randomNumbers = new SecureRandom();


	public DeckofCards() {
		deck = new Card[NUMBER_OF_CARDS]; 
		currentCard = 0; 

		for (int count = 0; count < deck.length; count++)
			deck[count] = new Card(faces[count % 13], suits[count / 13]);
	}

    public void shuffle(){
        currentCard = 0;
        for (int first = 0; first<deck.length; first++){
            int second = randomNumbers.nextInt(NUMBER_OF_CARDS);

            Card temp = deck[first];
            deck[first]= deck[second];
            deck[second] = temp;
        }
    }

    // public Card dealCard(){
    //     if (currentCard <deck.length){
    //         return deck[currentCard++];
    //     }
    //     else{
    //         return null;
    //     }
    // }

    public String get_Face(){
        String face = deck[currentCard++].get_Face();
        return face;
    }
    public String get_Suit(){
        String suit = deck[currentCard++].get_Suit();
        return suit;
    }

    public String is_Pair_twoPairs(String[] face_temp){
        String[] face=Arrays.copyOf(face_temp, face_temp.length);
        for (int i = 0 ;i<face.length;i++){
            String Face_Temp = face[i];
            for (int j =0;j<face.length;j++){
                String Next_Face_Temp = face[j];
                if (i == j){
                    continue;
                }
                if (Face_Temp == Next_Face_Temp){
                    face[i] = "pair_1_A";
                    face[j] = "pair_1_B";
                    String two_Pair = is_two_Pairs(face);
                    if (two_Pair == "true"){
                        return "is_two_Pairs";
                    }
                    else{
                    return "isPair";
                    }
                }
            }
        }

        return "false";
    }
    public String is_two_Pairs(String[] face){
        for (int i = 0 ;i<face.length;i++){
            String Face_Temp = face[i];
            for (int j =0;j<face.length;j++){
                String Next_Face_Temp = face[j];
                if (i == j){
                    continue;
                }
                if (Face_Temp == Next_Face_Temp){
                    face[i] = "pair_2_A";
                    face[j] = "pair_2_B";
                    return "true";
                }
            }
        }
        return "false";
    }

    public String is_Flush_Four_Three(String[] suit){
        int[] Suit_Type = new int[4];
        int Hearts = 0;
        int Diamonds = 0;
        int Clubs = 0;
        int Spades = 0;

        for (String Suit_temp :suit){
            switch (Suit_temp){
                case "Hearts":
                    Hearts +=1;
                    break;
                case "Diamonds":
                    Diamonds +=1;
                    break;
                case "Clubs":
                    Clubs +=1;
                    break;
                case "Spades":
                    Spades +=1;
                    break;
                }
            }
        Suit_Type[0] = Hearts;
        Suit_Type[1] = Diamonds;
        Suit_Type[2] = Clubs;
        Suit_Type[3] = Spades;
        for (int i :Suit_Type){
            switch (i){
                case 5:
                    return "Flush";
                case 4:
                    return "Four";
                case 3:
                    return "Three";
                default:
                    return "false";
            }

        }
        return null;

    }
    public String is_Straight(String[] face){
        int[] Current_faces_Number = new int[5];
        for (int i =0;i<face.length;i++ ){
            int index = Arrays.asList(faces).indexOf(face[i])+1;
            Current_faces_Number[i]=index;
        }   
        Arrays.sort(Current_faces_Number);
        for (int i =0;i<Current_faces_Number.length-1;i++){
            int Current_Int = Current_faces_Number[i];
            int Next_Int = Current_faces_Number[i+1]-1;
            if (Current_Int != Next_Int){
                return "false";
            }
        }
        return "Straight";
    }

    public String is_Full(String[] face_temp){
        String[] face=Arrays.copyOf(face_temp, face_temp.length);
        Arrays.sort(face);
        if (face[0]==face[2]){
            if (face[3]==face[4]){
            return "Full";
            }
        }
        if (face[2]==face[4]){
            if(face[0]==face[1]){
                return "Full";
            }
        }
        return "false";
    }
    public String hands_detect(String[] Current_Face,String[] Current_Suit){

            String is_Pair = is_Pair_twoPairs(Current_Face);
            String is_Flush = is_Flush_Four_Three(Current_Suit);
            String is_Straight = is_Straight(Current_Face);
            String is_full = is_Full(Current_Face);

                
            switch (is_Flush){
                case "Four":
                    System.out.printf("%-20s","Four of a Kind");
                    break;
                case "Flush":
                    System.out.printf("%-20s","Flush");
                    break;
                case "Three":
                    System.out.printf("%-20s","Three of a Kind");
                    break;
                case "false":
                    System.out.printf("%-20s","High card");
                    break;
            }

            switch (is_Pair){
                case "false":
                    System.out.printf("%-20s","High card");
                    break;
                case "isPair":
                    System.out.printf("%-20s","Pair");
                    break;
                case "is_two_Pairs":
                    System.out.printf("%-20s","two pairs");
                    break;
            }

            
            switch (is_Straight){
                case "false":
                    System.out.printf("%-20s", "High card");
                    break;
                case "Straight":
                    System.out.printf("%-20s","Straight");
                    break;
            }
            switch (is_full){
                case "Full":
                    System.out.printf("%-20s\n","Full House");
                    return "Full";
                case "false":
                    System.out.printf("%-20s\n","High card");
                    break;
            }
            shuffle();
			return "false";
    }
}
