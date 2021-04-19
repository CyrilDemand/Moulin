package moulin;

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

    public static void List(){ //liste toutes les couleurs disponibles
        System.out.println("Voici toutes les couleurs disponibles :");
        for (Color color:Color.values()){
            System.out.println(color.toString());
        }
    }

    private String toString(Color color){
        return color.name()+" ("+color.getValue()+")";
    }
}
