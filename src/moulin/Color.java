package moulin;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;

public enum Color {
    BLANC(0),NOIR(1),ROUGE(2),VERT(3),JAUNE(4), VIOLET(5),BLEU(6),ORANGE(7);

    private int value;

    private Color(){}

    private Color(int i){
        this.value = i;
    }

    private int getValue(){
        return this.value;
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
