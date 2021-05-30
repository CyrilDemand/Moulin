package moulin;

import java.util.*;

public class Main {

    /**
     * Allows you to launch the game
     *
     * @param args
     * @throws Exception
     */

    public static void main(String[] args) throws Exception {
        Board board = Board.generateBoard(4);
        ArrayList<Player> players = new ArrayList<>();
        players.add(new Player("Patrick",Color.ROUGE));
        players.add(new NormalAI("Intelligence Artificielle",Color.BLEU));
        for (int i = 0;i<3;i++){
            for (Player p:players) {
                p.addPiece(new Piece(p.getColor(),i));
            }
        }
        Jeu jeu = new Jeu(board,players);
        System.out.println(jeu.getPlayers());
        jeu.start();
        board.render(3,1);
        Jeu jeu1 = new Jeu(jeu);
        jeu1.getBoard().render(3,1);
        while (!jeu.isFinished()){
            jeu.endGame();
        }
        board.render(3,1);
        System.out.println("fin");
    }

    /**
     *Creates an ArrayList for the Players
     *
     * @return ArrayList<Player>
     *
     */

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
