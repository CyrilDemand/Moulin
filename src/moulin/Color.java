package moulin;

import java.util.ArrayList;
import java.util.Arrays;

public enum Color {
    NOIR('N',"\u001B[30m"),ROUGE('R',"\u001B[31m"),VERT('V',"\u001B[32m"),
    JAUNE('J',"\u001B[33m"), MAGENTA('M',"\u001B[35m"),BLEU('B',"\u001B[34m"),CYAN('C',"\u001B[36m");

    public static final String ANSI_RESET = "\u001B[0m";
    private char letter;
    private String string;//ansi color

    Color(char i){
        this.letter = i;
    }
    Color(char i,String s){//On l'utilise pas, mais si il existe pas, c'est la merde
        this(i);
        this.string=s;
    }

    public char getValue(){
        return this.letter;
    }//getLetter

    public String getString() {
        return string;
    }//get ansi color

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

    public String toString(){
        return this.name();
    }

    public static void diplayColor(){
        for (Color c: Color.List()) {
            System.out.print('['+c.getString()+c.toString()+Color.ANSI_RESET+']');
        }
        System.out.println();
    }
}