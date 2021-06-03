package moulin;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Properties;
import java.util.Scanner;

/**
 * Class used for loading and saving the properties of the game
 */

public class Config {

    public static int nodeSize;
    public static int margeSize;
    public static int numberOfTraps;
    public static int numberOfPieces;
    public static ArrayList<String> aiRandomNames;

    public static void main(String[] args) {
        Config.resetConfig();
    }

    /**
     * Loads the config
     */
    public static void loadConfig(){
        String path=System.getProperty("user.dir")+File.separator+"game.properties";
        try (InputStream input = new FileInputStream(path)) {

            Properties properties = new Properties();

            properties.load(input);

            try{Config.nodeSize=Integer.parseInt(properties.getProperty("nodeSize"));}catch(Exception ignored){};
            try{Config.margeSize=Integer.parseInt(properties.getProperty("margeSize"));}catch(Exception ignored){};
            try{Config.numberOfTraps=Integer.parseInt(properties.getProperty("numberOfTraps"));}catch(Exception ignored){};
            try{Config.numberOfPieces=Integer.parseInt(properties.getProperty("numberOfPieces"));}catch(Exception ignored){};

            String list=properties.getProperty("aiRandomNames");
            String[] tab=list.substring(1,list.length()-1).split(",");
            Config.aiRandomNames=new ArrayList<String>();
            Config.aiRandomNames.addAll(Arrays.asList(tab));


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Resets the config to the default values
     */
    public static void resetConfig(){
        Properties properties=new Properties();
        properties.put("numberOfTraps","3");
        properties.put("numberOfPieces","3");
        properties.put("nodeSize","3");
        properties.put("margeSize","1");
        properties.put("aiRandomNames","[Armstrong,Bandit,Beast,Boomer,Buzz,C-Block,Casper,Caveman,Centice,Chipper,Cougar,Dude,Foamer,Fury,Gerwin,Goose,Heater,Hollywood,Hound,Iceman,Imp,Jester,Junker,Khan,Marley,Maverick,Merlin,Middy,Mountain,Myrtle,Outlaw,Poncho,Rainmaker,Raja,Rex,Roundhouse,Sabretooth,Saltie,Samara,Scout,Shepard,Slider,Squall,Sticks,Stinger,Storm,Sultan,Sundown,Swabbie,Tex,Tusk,Viper,Wolfman,Yuri]");

        String path=System.getProperty("user.dir")+File.separator+"game.properties";
        System.out.println("Reset config at : "+path);
        FileOutputStream outputStream=null;
        try {
            outputStream = new FileOutputStream(path);
        }catch(FileNotFoundException e){
            e.printStackTrace();
        }
        try{
            properties.store(outputStream, "This file contains all the properties you can change to modify how the game looks and plays");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static int nextInt(int min,int max){
        int choix = min-2;
        while (!(choix>=min && choix<= max)){
            Scanner scanner = new Scanner(System.in);
            try {
                choix = scanner.nextInt();
            }catch (Exception e){
                System.out.println("On t'as demandé un nombre tu sais pas lire pd ?");
            }
            if (!(choix>=min && choix<= max)) System.out.println("ça t'arracherais les doigts de mettre un nombre entre "+ min + " et " + max +" ?");
        }
        return choix;
    }
}
