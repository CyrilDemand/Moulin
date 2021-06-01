package moulin;

import java.util.*;

public class Main {

    /**
     * Allows you to launch the game
     * @param args arguments
     * @throws Exception exception
     */
    public static void main(String[] args) throws Exception {
        Board board = Board.generateBoard(4);
        ArrayList<Player> players = new ArrayList<>();
        players.add(new Player("Patrick",Color.ROUGE));
        players.add(new RandomAI("Intelligence Artificielle",Color.BLEU));
        for (int i = 0;i<3;i++){
            for (Player p:players) {
                p.addPiece(new Piece(p.getColor(),i));
            }
        }
        Jeu jeu = new Jeu(board,players);
        System.out.println(jeu.getPlayers());
        jeu.start();
        Save save = new Save("test");
        Trap t = new Trap(3);
        board.getEdges().get(2).setTrap(t);
        save.generateSave(jeu);
        board.render(3,1);
        Jeu jeu1 = new Jeu(jeu);
        jeu1.getBoard().render(3,1);
        while (jeu.isFinished()==null){
            jeu.endGame();
        }
        board.render(3,1);
        System.out.println("fin");
    }

    /**
     * creates an ArrayList for the players
     * @return the player list
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
