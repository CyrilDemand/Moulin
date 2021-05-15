package moulin;

import java.util.ArrayList;
import java.util.Arrays;

public enum Color {
    NOIR('N',"\u001B[30m"),ROUGE('R',"\u001B[31m"),VERT('V',"\u001B[32m"),
    JAUNE('J',"\u001B[33m"), MAGENTA('M',"\u001B[35m"),BLEU('B',"\u001B[34m"),CYAN('C',"\u001B[36m");

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";

    private char letter;
    private String string;

    /**
     * @param i
     */
    Color(char i){
        this.letter = i;
    }
    Color(char i,String s){
        this(i);
        this.string=s;
    }

    public char getValue(){
        return this.letter;
    }

    public String getString() {
        return string;
    }

    public static ArrayList<Color> List(){ //liste toutes les couleurs disponibles
        return new ArrayList<>(Arrays.asList(Color.values()));
    }

    public static boolean isColor(String string){
        for (Color color : Color.values()){
            if (color.toString().equals(string.toUpperCase())){
                return true;
            }
        }
        return false;
    }

    public static void diplayColor(){
        for (Color c: Color.List()) {
            System.out.print('['+c.getString()+c.toString()+Color.ANSI_RESET+']');
        }
        System.out.println();
    }

    public String toString(){
        return this.name();
    }
}