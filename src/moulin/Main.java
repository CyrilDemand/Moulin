package moulin;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        //test discord
        Scanner scanner = new Scanner( System.in );
        Board board = new Board();
        Jeu jeu = new Jeu(board,initPlayer());
        jeu.displayPlayers();
    }

    private static ArrayList<Player> initPlayer(){
        ArrayList<String> names =initName();
        ArrayList<Color> colors =initColor();
        ArrayList<Player> players = new ArrayList<>();
        for (int i = 0; i<2;i++){
            players.add(new Player(names.get(i),colors.get(i)));
            for (int idx = 0; idx<3;idx++){
                players.get(i).addPiece(new Piece(Color.ROUGE));
            }
        }
        return players;
    }

    private static ArrayList<String> initName(){
        ArrayList<String> names = new ArrayList<>();
        Scanner scan = new Scanner(System.in);
        for (int idx = 0; idx<2;idx++){
            System.out.println("veuillez entrer le nom du joueur n°"+idx);
            names.add("");
            do{
                names.set(idx,scan.nextLine());
            }while (names.get(idx).equals(""));
        }
        return names;
    }

    private static ArrayList<Color> initColor(){
        ArrayList<Color> colors = new ArrayList<>();
        Scanner scan = new Scanner(System.in);
        System.out.println("Voici les couleurs que l'on a :\n"+Color.List());
        for (int idx = 0; idx<2;idx++){
            System.out.println("veuillez entrer la couleur du joueur n°"+idx);
            do{
                colors.add(Color.valueOf(scan.nextLine()));
            }while (!Color.List().contains(colors.get(idx)));
        }
        return colors;
    }
}
