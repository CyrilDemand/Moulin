package moulin;

import java.io.File;
import java.lang.reflect.Array;
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
            System.out.println("Sur quellle map voulez-vous jouer ?");
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
                System.out.println("Voici les maps disponibles");
                ArrayList<String> mapFiles=Jeu.getFiles("customMaps");

                for (int i=0;i<mapFiles.size();i++){
                    System.out.print(mapFiles.get(i));
                    if (i!=mapFiles.size()-1){
                        System.out.print(", ");
                    }
                }

                System.out.println("\nEntrez le nom de la map sur laquelle vous voulez jouer : ");
                String map="";
                do{
                    map=scanner.nextLine();
                }while (!mapFiles.contains(map));

                board=Save.loadBoard(System.getProperty("user.dir")+ File.separator+"customMaps"+File.separator+map+".json");
            }

            System.out.println("Combien de joueurs voulez-vous ? (2 à 4 joueurs)");
            do{
                System.out.print("Votre choix : ");
                choix=scanner.nextInt();
            }while (!(choix>=2 && choix<=4));

            ArrayList<Player> players = new ArrayList<>(Jeu.initPlayer(choix));

            jeu = new Jeu(board,players);
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
            System.out.println("Voici les sauvegardes disponibles");
            ArrayList<String> savesFiles=Jeu.getFiles("saves");

            for (int i=0;i<savesFiles.size();i++){
                System.out.print(savesFiles.get(i));
                if (i!=savesFiles.size()-1){
                    System.out.print(", ");
                }
            }

            System.out.println("\nEntrez le nom de la sauvegarde que vous voulez charger : ");
            String map="";
            do{
                map=scanner.nextLine();
            }while (!savesFiles.contains(map));

            jeu=Save.loadJeu(map);
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
