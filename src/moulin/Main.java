package moulin;

import ihm.Render;
import org.json.JSONException;

import java.io.File;
import java.io.IOException;
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
        System.out.println("Board{edges=[Edge{start=1, end=2}, Edge{start=2, end=3}, Edge{start=3, end=4}, Edge{start=4, end=5}, Edge{start=5, end=6}, Edge{start=1, end=6}, Edge{start=7, end=8}, Edge{start=8, end=9}, Edge{start=9, end=10}, Edge{start=10, end=11}, Edge{start=11, end=12}, Edge{start=7, end=12}, Edge{start=13, end=14}, Edge{start=14, end=15}, Edge{start=15, end=16}, Edge{start=16, end=17}, Edge{start=17, end=18}, Edge{start=13, end=18}, Edge{start=1, end=7}, Edge{start=3, end=9}, Edge{start=5, end=11}, Edge{start=7, end=13}, Edge{start=9, end=15}, Edge{start=11, end=17}], nodes=[Node{x=5, y=5, id=1}, Node{x=4, y=6, id=2}, Node{x=2, y=7, id=3}, Node{x=2, y=5, id=4}, Node{x=2, y=3, id=5}, Node{x=3, y=4, id=6}, Node{x=7, y=5, id=7}, Node{x=4, y=7, id=8}, Node{x=1, y=8, id=9}, Node{x=1, y=5, id=10}, Node{x=1, y=2, id=11}, Node{x=4, y=3, id=12}, Node{x=9, y=5, id=13}, Node{x=5, y=8, id=14}, Node{x=0, y=10, id=15}, Node{x=0, y=5, id=16}, Node{x=0, y=0, id=17}, Node{x=4, y=2, id=18}], lines=[Line{nodes=[Node{x=5, y=5, id=1}, Node{x=4, y=6, id=2}, Node{x=2, y=7, id=3}]}, Line{nodes=[Node{x=2, y=7, id=3}, Node{x=2, y=5, id=4}, Node{x=2, y=3, id=5}]}, Line{nodes=[Node{x=2, y=3, id=5}, Node{x=3, y=4, id=6}, Node{x=5, y=5, id=1}]}, Line{nodes=[Node{x=7, y=5, id=7}, Node{x=4, y=7, id=8}, Node{x=1, y=8, id=9}]}, Line{nodes=[Node{x=1, y=8, id=9}, Node{x=1, y=5, id=10}, Node{x=1, y=2, id=11}]}, Line{nodes=[Node{x=1, y=2, id=11}, Node{x=4, y=3, id=12}, Node{x=7, y=5, id=7}]}, Line{nodes=[Node{x=9, y=5, id=13}, Node{x=5, y=8, id=14}, Node{x=0, y=10, id=15}]}, Line{nodes=[Node{x=0, y=10, id=15}, Node{x=0, y=5, id=16}, Node{x=0, y=0, id=17}]}, Line{nodes=[Node{x=0, y=0, id=17}, Node{x=4, y=2, id=18}, Node{x=9, y=5, id=13}]}, Line{nodes=[Node{x=5, y=5, id=1}, Node{x=7, y=5, id=7}, Node{x=9, y=5, id=13}]}, Line{nodes=[Node{x=2, y=7, id=3}, Node{x=1, y=8, id=9}, Node{x=0, y=10, id=15}]}, Line{nodes=[Node{x=2, y=3, id=5}, Node{x=1, y=2, id=11}, Node{x=0, y=0, id=17}]}]}".equals(
                "Board{edges=[Edge{start=1, end=2}, Edge{start=2, end=3}, Edge{start=3, end=4}, Edge{start=4, end=5}, Edge{start=5, end=6}, Edge{start=1, end=6}, Edge{start=7, end=8}, Edge{start=8, end=9}, Edge{start=9, end=10}, Edge{start=10, end=11}, Edge{start=11, end=12}, Edge{start=7, end=12}, Edge{start=13, end=14}, Edge{start=14, end=15}, Edge{start=15, end=16}, Edge{start=16, end=17}, Edge{start=17, end=18}, Edge{start=13, end=18}, Edge{start=1, end=7}, Edge{start=3, end=9}, Edge{start=5, end=11}, Edge{start=7, end=13}, Edge{start=9, end=15}, Edge{start=11, end=17}], nodes=[Node{x=5, y=5, id=1}, Node{x=4, y=6, id=2}, Node{x=2, y=7, id=3}, Node{x=2, y=5, id=4}, Node{x=2, y=3, id=5}, Node{x=3, y=4, id=6}, Node{x=7, y=5, id=7}, Node{x=4, y=7, id=8}, Node{x=1, y=8, id=9}, Node{x=1, y=5, id=10}, Node{x=1, y=2, id=11}, Node{x=4, y=3, id=12}, Node{x=9, y=5, id=13}, Node{x=5, y=8, id=14}, Node{x=0, y=10, id=15}, Node{x=0, y=5, id=16}, Node{x=0, y=0, id=17}, Node{x=4, y=2, id=18}], lines=[Line{nodes=[Node{x=5, y=5, id=1}, Node{x=4, y=6, id=2}, Node{x=2, y=7, id=3}]}, Line{nodes=[Node{x=2, y=7, id=3}, Node{x=2, y=5, id=4}, Node{x=2, y=3, id=5}]}, Line{nodes=[Node{x=2, y=3, id=5}, Node{x=3, y=4, id=6}, Node{x=5, y=5, id=1}]}, Line{nodes=[Node{x=7, y=5, id=7}, Node{x=4, y=7, id=8}, Node{x=1, y=8, id=9}]}, Line{nodes=[Node{x=1, y=8, id=9}, Node{x=1, y=5, id=10}, Node{x=1, y=2, id=11}]}, Line{nodes=[Node{x=1, y=2, id=11}, Node{x=4, y=3, id=12}, Node{x=7, y=5, id=7}]}, Line{nodes=[Node{x=9, y=5, id=13}, Node{x=5, y=8, id=14}, Node{x=0, y=10, id=15}]}, Line{nodes=[Node{x=0, y=10, id=15}, Node{x=0, y=5, id=16}, Node{x=0, y=0, id=17}]}, Line{nodes=[Node{x=0, y=0, id=17}, Node{x=4, y=2, id=18}, Node{x=9, y=5, id=13}]}, Line{nodes=[Node{x=5, y=5, id=1}, Node{x=7, y=5, id=7}, Node{x=9, y=5, id=13}]}, Line{nodes=[Node{x=2, y=7, id=3}, Node{x=1, y=8, id=9}, Node{x=0, y=10, id=15}]}, Line{nodes=[Node{x=2, y=3, id=5}, Node{x=1, y=2, id=11}, Node{x=0, y=0, id=17}]}]}")
        );
        Board.generateBoard(3).render();
        System.out.println("In which mode do you want to start the game ?\n");

        System.out.println("[1] Console Mode");
        System.out.println("[2] Graphic Mode");
        System.out.print("Your choice : ");

        int mode=Config.nextInt(1,2);
        if (mode==1){
            Main.mainConsole();
        }else{
            Render.main(new String[0]);
        }
    }

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

    public static Jeu loadJeu() throws JSONException, IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\nEnter the name of the save you want to load : ");
        String map="";
        File file;
        do{
            map=scanner.nextLine();
            file = new File(map);
            if (map.equals("IWANTTOGOBACK")){
                Main.mainConsole();
                break;
            }
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

    private static boolean isJson(String s){
        try{
            return s.substring(s.length()-5,s.length()).equals(".json");
        }catch (Exception ignored){
            return false;
        }
    }
}
