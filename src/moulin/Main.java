package moulin;

import java.util.*;

public class Main {

    public static void main(String[] args){
        Board board = Board.generateBoard(3);
        ArrayList<Player> players = new ArrayList<>();
        players.add(new Player("Patrick",Color.ROUGE));
        players.add(new RandomAI("Intelligence Artificielle",Color.BLEU));
        TrapEdge t = new TrapEdge(2,board.getEdges().get(1));
        for (int i = 0;i<3;i++){
            for (Player p:players) {
                p.addPiece(new Piece(p.getColor(),i));
            }
        }
        players.get(0).getPieces().get(0).put(board.getNodeById(1));
        TrapNode t1 = new TrapNode(2, board.getNodeById(1), board.getNodeById(10),board);
        Jeu jeu = new Jeu(board,players);
        System.out.println(jeu.getPlayers());
        jeu.start();
        System.out.println("zeezzeez");
        board.render(3,3);
        Jeu jeu1 = new Jeu(jeu);
        jeu1.getBoard().render(3,3);
        while (!jeu.isFinished()){
            jeu.endGame();
            jeu1.getBoard().render(3,3);
        }
        board.render(3,3);
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