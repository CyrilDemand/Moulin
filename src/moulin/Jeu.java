package moulin;
import java.util.ArrayList;
import java.util.Scanner;

public class Jeu {
    private static int turn = 0;

    private Board board;

    private final ArrayList<Player> players;

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

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public void displayPlayers(){
        for (Player player:this.players){
            System.out.println(player);
        }
    }

    public boolean isFinished(){
        //test discord
        ArrayList<Vecteur> v = new ArrayList<Vecteur>();
        ArrayList<Boolean> b = new ArrayList<Boolean>();


        for (int i = 0; i<this.getPlayers().get(0).getPieces().size()-1;i++){
            v.add(new Vecteur(this.getPlayers().get(0).getPieces().get(i).getNode().getX(),
                    this.getPlayers().get(0).getPieces().get(i+1).getNode().getX(),
                    this.getPlayers().get(0).getPieces().get(i).getNode().getY(),
                    this.getPlayers().get(0).getPieces().get(i+1).getNode().getY())
            );
        }

        for (int i = 0; i<this.getPlayers().get(0).getPieces().size()-1;i++){
            b.add(this.getBoard().isLinked(this.getPlayers().get(0).getPieces().get(i).getNode().getId(),
                    this.getPlayers().get(0).getPieces().get(i+1).getNode().getId()));
        }
        return (v.get(0).isCollinear(v.get(1)) && (b.get(0)&&b.get(1)));
    }
}
