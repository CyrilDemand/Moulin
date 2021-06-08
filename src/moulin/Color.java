package moulin;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Class used to represent the piece's colors
 */

public enum Color {
    NOIR('N',"\u001B[30m"),ROUGE('R',"\u001B[31m"),VERT('V',"\u001B[32m"),
    JAUNE('J',"\u001B[33m"), MAGENTA('M',"\u001B[35m"),BLEU('B',"\u001B[34m"),CYAN('C',"\u001B[36m");

    public static final String ANSI_RESET = "\u001B[0m";
    private char letter;
    private String string;

    /**
     * Local constructor used to create a color
     * @param i first letter of the color
     */
    Color(char i){
        this.letter = i;
    }

    /**
     * Local constructor used to create a color
     * @param i first letter of the color
     * @param s string used by the terminal to set the color of the characters
     */
    Color(char i,String s){
        this(i);
        this.string=s;
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
     * Gets the list of possible colors
     * @return list of possible colors
     */
    public static ArrayList<Color> List(){
        return new ArrayList<>(Arrays.asList(Color.values()));
    }

    /**
     * check if the string is corresponding to any color in the enum. This is used when players chose their color
     * @param string string used
     * @return true if the color exists, false otherwise
     */

    public static boolean isColor(String string){
        for (Color color : Color.values()){
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
        for (Color c: Color.List()) {
            System.out.print('['+c.getString()+c.toString()+Color.ANSI_RESET+']');
        }
        System.out.println();
    }
}