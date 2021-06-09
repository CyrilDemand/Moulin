package moulin;

import org.json.JSONException;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.*;

public class Main {

    /**
     * prints the main menu
     * @throws JSONException JSONException
     * @throws IOException IOException
     */
    public static void mainConsole() throws JSONException, IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to the Mill game !\n");
        System.out.println("What do you want to do ?");

        System.out.println("[1] New game");
        System.out.println("[2] Load a saved game");
        System.out.println("[3] Quit");

        int choix;
        System.out.print("Your choice : ");
        choix = Config.nextInt(1,3);


        Player resultOfTheGame=null;
        Jeu jeu = null;

        if (choix==1){
            //-------NOUVELLE PARTIE--------
            System.out.println("Which map do you want to play on ?");
            System.out.println("[1] pre-generated map");
            System.out.println("[2] Custom map");
            System.out.print("Your choice : ");
            choix = Config.nextInt(1,2);

            Board board=null;
            if (choix==1){
                System.out.println("Choose the amount of sides for your map (between 3 and "+Config.boardMaxSides+")");
                System.out.print("Your choice : ");
                choix = Config.nextInt(3,Config.boardMaxSides);

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

            System.out.println("How many players do you want ? (2 to "+Config.playerMax+" players)");
            System.out.print("Your choice : ");
            choix=Config.nextInt(2,Config.playerMax);

            ArrayList<Player> players = new ArrayList<>(Jeu.initPlayer(choix));

            jeu = new Jeu(board,players);
            System.out.println("Choose a way to place the pieces : ");
            System.out.println("[1] Placement by the players");
            System.out.println("[2] Random placement");
            System.out.print("Your choice : ");
            choix = Config.nextInt(1,2);

            if (choix==1){
                resultOfTheGame=jeu.start();
            }else {
                jeu = Jeu.randomStart(jeu.getBoard(), jeu.getPlayers());
            }
        }else if (choix==2){
            jeu = loadJeu();
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

    /**
     * loads a save
     * @return the loaded game
     * @throws JSONException JSONException
     * @throws IOException IOException
     */
    public static Jeu loadJeu() throws JSONException, IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\nEnter the name of the save you want to load : ");
        String map="";
        File file;
        do{
            map=scanner.nextLine();
            if (map.equals("IWANTTOGOBACK")){
                Main.mainConsole();
                break;
            }
            file = new File(map);
            if(!file.exists()) System.out.println("wrong file");
            try {
                final String substring = map.substring(map.length() - 5);
                if (!substring.equals(".json")) System.out.println("What you have entered is not in json format");
            }catch (Exception ignored){

            }
        }while (!file.exists() || !isJson(map));

        try {
            return Save.loadJeu(map);
        }catch (IOException | JSONException e){
            System.out.println("Your file is not compatible with our backup method");
            return loadJeu();
        }
    }

    /**
     * checks if the file is an existing json
     * @param s the file name
     * @return true if it exists, false otherwise
     */
    private static boolean isJson(String s){
        try{
            return s.substring(s.length()-5,s.length()).equals(".json");
        }catch (Exception ignored){
            return false;
        }
    }
}
