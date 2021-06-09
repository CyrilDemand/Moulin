package moulin;

import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Class used to represent the piece's colors
 */

public enum ColorEnum {
    NOIR('N',"\u001B[30m",new Color(0,0,0,1)),ROUGE('R',"\u001B[31m",new Color(1,0,0,1)),VERT('V',"\u001B[32m",new Color(0,1,0,1)),
    JAUNE('J',"\u001B[33m",new Color(1,1,0,1)), MAGENTA('M',"\u001B[35m",new Color(1,0,1,1)),BLEU('B',"\u001B[34m",new Color(0,0,1,1)),CYAN('C',"\u001B[36m",new Color(0,1,1,1));

    public static final String ANSI_RESET = "\u001B[0m";
    private char letter;
    private String string;
    private Color color;

    /**
     * Local constructor used to create a color
     * @param i first letter of the color
     */
    ColorEnum(char i){
        this.letter = i;
    }

    /**
     * Local constructor used to create a color
     * @param i first letter of the color
     * @param s string used by the terminal to set the color of the characters
     */
    ColorEnum(char i, String s, Color c){
        this(i);
        this.string=s;
        this.color=c;
    }

    /**
     * Gets the first letter of color
     * @return the first letter of color
     */

    public char getValue(){
        return this.letter;
    }

    /**
     * Gets the string used by the terminal to color the characters
     * @return the corresponding ANSI color code
     */

    public String getString() {
        if (!Config.useColorCodesInTerminal)return "";
        return string;
    }

    /**
     * return the equivalent javafx color
     * @return the color
     */

    public Color getColor(){
        return color;
    }

    /**
     * Gets the list of possible colors
     * @return list of possible colors
     */
    public static ArrayList<ColorEnum> List(){
        return new ArrayList<>(Arrays.asList(ColorEnum.values()));
    }

    /**
     * check if the string is corresponding to any color in the enum. This is used when players chose their color
     * @param string string used
     * @return true if the color exists, false otherwise
     */

    public static boolean isColor(String string){
        for (ColorEnum color : ColorEnum.values()){
            if (color.toString().equals(string.toUpperCase())){
                return true;
            }
        }
        return false;
    }

    public static boolean isColor(String string, ArrayList<ColorEnum> list){
        for (ColorEnum color : list){
            if (color.toString().equals(string.toUpperCase())){
                return true;
            }
        }
        return false;
    }

    /**
     * generates the text version of the color's name
     * @return the name of the color
     */
    public String toString(){
        return this.name();
    }

    /**
     * Display the color's name with the correct color using the corresponding ANSI color code
     */
    public static void diplayColor(){
        for (ColorEnum c: ColorEnum.List()) {
            System.out.print('['+c.getString()+c.toString()+ ColorEnum.ANSI_RESET+']');
        }
        System.out.println();
    }


    public static void diplayColor(ArrayList<ColorEnum> list){
        for (ColorEnum c: list) {
            System.out.print('['+c.getString()+c.toString()+ ColorEnum.ANSI_RESET+']');
        }
        System.out.println();
    }
}