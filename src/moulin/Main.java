package moulin;

import java.util.*;

public class Main {

    /**
     * Allows you to launch the game
     * @param args arguments
     * @throws Exception exception
     */
    public static void main(String[] args) throws Exception {
        Config.loadConfig();


        Scanner scanner = new Scanner(System.in);
        System.out.println("Bienvenue sur le jeu du moulin !\n");
        System.out.println("Que voulez-vous faire ?");

        System.out.println("[1] Nouvelle partie");
        System.out.println("[2] Charger une sauvergarde");
        System.out.println("[3] Quitter");

        int choix;
        do {
            System.out.print("Votre choix : ");
            choix = scanner.nextInt();
        }while (!(choix>=1 && choix<=3));


        Player resultOfTheGame=null;
        Jeu jeu=null;

        if (choix==1){
            //-------NOUVELLE PARTIE--------
            System.out.println("Sur quel map voulez-vous jouer ?");
            System.out.println("[1] Map pre-génerée");
            System.out.println("[2] Map customisée");
            do {
                System.out.print("Votre choix : ");
                choix = scanner.nextInt();
            }while (!(choix>=1 && choix<=2));

            Board board=null;
            if (choix==1){
                System.out.println("Choisissez le nombre de cotés de votre map (3 à 10)");
                do {
                    System.out.print("Votre choix : ");
                    choix = scanner.nextInt();
                }while (!(choix>=3 && choix<=10));

                board=Board.generateBoard(choix);
            }else if (choix==2){
                System.out.println("Charger une map depuis un fichier");
                return;
            }

            System.out.println("Combien de joueurs voulez-vous ? (2 à 4 joueurs)");
            do{
                System.out.print("Votre choix : ");
                choix=scanner.nextInt();
            }while (!(choix>=2 && choix<=4));

            ArrayList<Player> players = new ArrayList<>(Jeu.initPlayer(choix));

            jeu = new Jeu(board,players);
            System.out.println(jeu.getPlayers());


            System.out.println("Choisissez votre methode de placement des pions : ");
            System.out.println("[1] Placement par les joueurs");
            System.out.println("[2] Placement aléatoire");
            do {
                System.out.print("Votre choix : ");
                choix = scanner.nextInt();
            }while (!(choix>=1 && choix<=2));

            if (choix==1){
                resultOfTheGame=jeu.start();
            }else {
                jeu = Jeu.randomStart(jeu.getBoard(), jeu.getPlayers());
            }
        }else if (choix==2){
            //----------CHARGER UNE PARTIE EXISTANTE------------------
            Jeu jeu1 = Save.loadJeu("");
            System.out.println(Jeu.getTurn());
            System.out.println(jeu1.getPlayers().get(Jeu.getTurn()).getColor());
            jeu1.getBoard().render();
            jeu1.endGame();
            return;
        }else{
            //QUITTER
            return;
        }

        //-------------BOUCLE DE JEU-------------------
        if (resultOfTheGame==null){
            resultOfTheGame=jeu.endGame();
        }

        if (resultOfTheGame==null){

        }else{
            jeu.getBoard().render();
            System.out.println(resultOfTheGame+" a gagné !");
        }

    }


}
