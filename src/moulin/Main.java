package moulin;

import java.util.*;

public class Main {

    /**
     * Allows you to launch the game
     * @param args arguments
     * @throws Exception exception
     */
    public static void main(String[] args) throws Exception {

        Player resultOfTheGame=null;

        Board board = Board.generateBoard(4);
        ArrayList<Player> players = new ArrayList<>();
        players.add(new Player("Patrick",Color.ROUGE));
        players.add(new RandomAI("Intelligence Artificielle",Color.BLEU));

        for (int i=0;i<3;i++){
            for (Player p:players) {
                p.addPiece(new Piece(p.getColor(),i));
            }
        }

        Jeu jeu = new Jeu(board,players);
        System.out.println(jeu.getPlayers());



        Scanner scanner = new Scanner(System.in);
        System.out.println("Choisissez votre methode de placement des pions : ");
        System.out.println("[1] Placement par les joueurs");
        System.out.println("[2] Placement aléatoire");
        int choix;
        do {
            System.out.print("Votre choix : ");
            choix = scanner.nextInt();
        }while (!(choix>=1 && choix<=2));

        if (choix==1){
            resultOfTheGame=jeu.start();
        }else if (choix==2){
            resultOfTheGame=jeu.randomStart();
        }

        if (resultOfTheGame==null){
            resultOfTheGame=jeu.endGame();
        }

        if (resultOfTheGame==null){

        }else{
            board.render(3,1);
            System.out.println(resultOfTheGame+" a gagné !");
        }


    }


}
