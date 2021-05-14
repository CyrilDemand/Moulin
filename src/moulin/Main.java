package moulin;

import java.util.*;

public class Main {

    public static void main(String[] args){
        Board board = Board.loadBoard("ressources\\mapSquare.json");
        Jeu jeu = new Jeu(board,initPlayer());
        for (Piece p : jeu.getPlayers().get(0).getPieces()) {
            System.out.println("Le pion est de couleur : "+p.getColor().getString()+p.getColor().toString()+Color.ANSI_RESET);
        }
        jeu.start();
        board.render(3,3);
        while (!jeu.isFinished()){
            jeu.endGame();
        }
        System.out.println("fin");
    }

    public static ArrayList<Player> initPlayer(){
        ArrayList<Player> players = new ArrayList<>();
        for (int i = 0; i<2;i++){
            players.add(new Player(Player.chooseName(),Player.chooseColor()));
            for (int idx = 0; idx<3;idx++){
                players.get(i).addPiece(new Piece(players.get(i).getColor(),players.get(i).getPieces().size()));
            }
        }
        return players;
    }
}