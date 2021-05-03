package moulin;

import java.util.ArrayList;
import java.util.Arrays;

public enum Color {
    NOIR('N'),ROUGE('R'),VERT('V'),JAUNE('J'), MAGENTA('M'),BLEU('B'),ORANGE('O');

    private char letter;

    /**
     * @param i
     */
    Color(char i){
        this.letter = i;
    }

    public char getValue(){
        return this.letter;
    }

    public static ArrayList<Color> List(){ //liste toutes les couleurs disponibles
        return new ArrayList<>(Arrays.asList(Color.values()));
    }

    public static boolean isColor(String string){
        for (Color color:Color.values()){
            if (color.toString().equals(string.toUpperCase())){
                return true;
            }
        }
        return false;
    }

    public String toString(){
        return this.name();
    }
}
