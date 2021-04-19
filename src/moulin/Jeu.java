package moulin;
import java.util.ArrayList;
import java.util.Scanner;

public class Jeu {
    private static int turn = 0;

    private Board board;

    private ArrayList<Player> players;

    public Jeu(Board board,ArrayList<Player> players){
        this.board=board;
        this.players=players;
    }

    public void addTurn(){
        Jeu.turn++;
    }

    public Board getBoard(){
        return this.board;
    }

    public void setBoard(Board board){
        this.board = board;
    }

    public void displayPlayers(){
        for (Player player:this.players){
            System.out.println(player);
        }
    }
}
