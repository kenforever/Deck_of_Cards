
public class Deck_of_Cards_Test {
    public static void main(String[] args){
        DeckofCards My_Deck_of_Cards = new DeckofCards();
        My_Deck_of_Cards.shuffle();
        // for (int i = 1;i<=52;i++){
        //     System.out.printf("%-19s",My_Deck_of_Cards.dealCard());

        //     if (i%5 == 0){
        //         System.out.println();
        //     }
        // }
        boolean end = false;
        while (end != true){
        String[] Current_Face = new String[5];
        String[] Current_Suit = new String[5];
        for (int j = 0;j<=4;j++){
            Current_Face[j] = My_Deck_of_Cards.get_Face();
            Current_Suit[j] = My_Deck_of_Cards.get_Suit();
            String Current_Card = Current_Face[j] +" of "+ Current_Suit[j];
            System.out.printf("%-20s", Current_Card);
        }
        System.out.println();
        String Finish = My_Deck_of_Cards.hands_detect(Current_Face, Current_Suit);

        if (Finish == "Full"){
            end = true;
        }
    }
        
}
}
