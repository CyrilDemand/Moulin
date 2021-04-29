package moulin;

import java.util.*;

public class Main {


    public static ArrayList<Player> initPlayer(){
        ArrayList<Player> players = new ArrayList<>();
        for (int i = 0; i<2;i++){
            players.add(new Player(chooseName(),chooseColor()));
            for (int idx = 0; idx<3;idx++){
                players.get(i).addPiece(new Piece(players.get(i).getColor()));
            }
        }
        return players;
    }

    private static String chooseName(){
        Scanner scan = new Scanner(System.in);
        System.out.println("Quel est votre nom ?");
        String user = "";
        user = scan.nextLine();
        while (user.equals("") || user.length()<3){
            System.out.println("Choisissez un vrai nom");
            user = scan.nextLine();
        }
        return user;
    }

    private static Color chooseColor(){
        Scanner scan = new Scanner(System.in);
        System.out.println("Voici les couleurs que l'on a :\n"+Color.List());
        String user = "";
        user = scan.nextLine();
        while (!Color.isColor(user.toLowerCase())){
            System.out.println("Choisissez une vraie couleur");
            user = scan.nextLine();
        }
        return Color.valueOf(user.toUpperCase());
    }
}
