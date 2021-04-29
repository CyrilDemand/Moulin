package moulin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        //test discord
        Board board=Board.loadBoard("ressources\\map.json");
        Scanner scanner = new Scanner( System.in );

        Jeu jeu = new Jeu(board,initPlayer());
        jeu.displayPlayers();
        ArrayList<Vecteur> v = new ArrayList<Vecteur>();
        jeu.getPlayers().get(0).getPieces().get(0).put(jeu.getBoard().getNodeById(1));
        jeu.getPlayers().get(0).getPieces().get(1).put(jeu.getBoard().getNodeById(4));
        jeu.getPlayers().get(0).getPieces().get(2).put(jeu.getBoard().getNodeById(7));

        HashMap<Vecteur,Boolean> test = new HashMap<>();

        for (int i = 0; i<jeu.getPlayers().get(0).getPieces().size()-1;i++){
            test.put(new Vecteur(jeu.getPlayers().get(0).getPieces().get(i).getNode().getX(),
                    jeu.getPlayers().get(0).getPieces().get(i).getNode().getY(),
                    jeu.getPlayers().get(0).getPieces().get(i+1).getNode().getX(),
                    jeu.getPlayers().get(0).getPieces().get(i+1).getNode().getX()),
                    jeu.getBoard().isLinked(jeu.getPlayers().get(0).getPieces().get(i).getNode().getId(),
                            jeu.getPlayers().get(0).getPieces().get(i).getNode().getId()
                    )
            );
        }
        System.out.println(test.);
        //Main Noé !

        //System.out.println(board);
    }

    private static ArrayList<Player> initPlayer(){
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
