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
        System.out.println("Welcome to the Mill game !\n");
        System.out.println("What do you want to do ?");

        System.out.println("[1] New game");
        System.out.println("[2] Load a saved game");
        System.out.println("[3] Quit");

        int choix;
        do {
            System.out.print("Your choice : ");
            choix = Config.nextInt(1,3);
        }while (!(choix>=1 && choix<=3));


        Player resultOfTheGame=null;
        Jeu jeu=null;

        if (choix==1){
            //-------NOUVELLE PARTIE--------
            System.out.println("Which map do you want to play on ?");
            System.out.println("[1] pre-generated map");
            System.out.println("[2] Custom map");
            do {
                System.out.print("Your choice : ");
                choix = Config.nextInt(1,2);
            }while (!(choix>=1 && choix<=2));

            Board board=null;
            if (choix==1){
                System.out.println("Choose the amount of sides for your map (between 3 and 10)");
                do {
                    System.out.print("Your choice : ");
                    choix = Config.nextInt(3,10);
                }while (!(choix>=3 && choix<=10));

                board=Board.generateBoard(choix);
            }else if (choix==2){
                System.out.println("Here are the available maps");
                ArrayList<String> mapFiles=Jeu.getFiles("customMaps");

                for (int i=0;i<mapFiles.size();i++){
                    System.out.print(mapFiles.get(i));
                    if (i!=mapFiles.size()-1){
                        System.out.print(", ");
                    }
                }

                System.out.println("\nEnter the name of the map you want to play on : ");
                String map="";
                do{
                    map=scanner.nextLine();
                }while (!mapFiles.contains(map));

                board=Save.loadBoard(System.getProperty("user.dir")+ File.separator+"customMaps"+File.separator+map+".json");
            }

            System.out.println("How much players do you want ? (2 to 4 players)");
            do{
                System.out.print("Your choice : ");
                choix=Config.nextInt(2,4);
            }while (!(choix>=2 && choix<=4));

            ArrayList<Player> players = new ArrayList<>(Jeu.initPlayer(choix));

            jeu = new Jeu(board,players);
            System.out.println("Choose a way to place the pieces : ");
            System.out.println("[1] Placement by the players");
            System.out.println("[2] Random placement");
            do {
                System.out.print("Your choice : ");
                choix = Config.nextInt(1,2);
            }while (!(choix>=1 && choix<=2));

            if (choix==1){
                resultOfTheGame=jeu.start();
            }else {
                jeu = Jeu.randomStart(jeu.getBoard(), jeu.getPlayers());
            }
        }else if (choix==2){
            System.out.println("Here are the available saves :");
            ArrayList<String> savesFiles=Jeu.getFiles("saves");

            for (int i=0;i<savesFiles.size();i++){
                System.out.print(savesFiles.get(i));
                if (i!=savesFiles.size()-1){
                    System.out.print(", ");
                }
            }

            System.out.println("\nEnter the name of the save you want to load : ");
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
            System.out.println(resultOfTheGame+" won !");
        }
    }
}
