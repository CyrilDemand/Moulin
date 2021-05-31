package moulin;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;

public class Save {

    String path;
    public Save(String path){
        this.path = "saves"+ File.separator+path;
    }

    public boolean generateSave(Jeu jeu) throws JSONException {
        ArrayList<Character> save = new ArrayList<>();
        save.addAll(this.SaveBoard(jeu.getBoard()));
        save.addAll(this.SavePlayers(jeu.getPlayers()));
        char[] b = new char[save.size()];
        File fichier =  new File(this.path+".json");
        for (int i = 0; i<save.size();i++) {
            b[i]=save.get(i);
        }
        try {
            // ouverture d'un flux de sortie sur un fichier
            // a pour effet de créer le fichier
            Writer writer =  new FileWriter(fichier) ;

            // écriture dans le fichier
            writer.write(b) ;

            // la méthode close de FileWriter appelle elle-même flush()
            writer.close() ;

        }  catch (IOException e) {

            // affichage du message d'erreur et de la pile d'appel
            System.out.println("Erreur " + e.getMessage()) ;
            e.printStackTrace() ;
        }

        return false;
    }

    public ArrayList<Character> SaveBoard(Board board){
        ArrayList<Character> res = new ArrayList<>();
        res.addAll(this.SaveNodes(board));
        res.addAll(this.SaveEdges(board));
        res.addAll(this.SaveLines(board));
        res.addAll(this.SaveTrap(board));
        return res;
    }
    public ArrayList<Character> SaveNodes(Board board) {
        ArrayList<Character> res = new ArrayList<>(this.StringToList("\"nodes\":["));
        for (Node n : board.getNodes()) {
            res.add('[');
            res.addAll(this.StringToList(Integer.toString(n.getX())));
            res.add(',');
            res.addAll(this.StringToList(Integer.toString(n.getY())));
            res.add(',');
            res.addAll(this.StringToList(Integer.toString(n.getId())));
            res.add(']');
            res.add(',');
        }
        res.add(']');
        return res;
    }
    public ArrayList<Character> SaveEdges(Board board) {
        ArrayList<Character> res = new ArrayList<>(this.StringToList("\"edges\":["));
        for (Edge e: board.getEdges()) {
            res.add('[');
            res.addAll(this.StringToList(Integer.toString(e.getStart().getId())));
            res.add(',');
            res.addAll(this.StringToList(Integer.toString(e.getEnd().getId())));
            res.add(']');
            res.add(',');
        }
        res.add(']');
        return res;
    }

    public ArrayList<Character> SaveLines(Board board) {
        ArrayList<Character> res = new ArrayList<>(this.StringToList("\"lines\":["));
        for (Line l: board.getLines()) {
            res.add('[');
            for (Node n :l.getNodes()) {
                res.addAll(this.StringToList(Integer.toString(n.getId())));
                res.add(',');
            }
            res.add(']');
            res.add(',');
        }
        res.add(']');
        return res;
    }

    public ArrayList<Character> SavePlayers(ArrayList<Player> players) {
        ArrayList<Character> res = new ArrayList<>(this.StringToList("\"players\":["));
        for (Player p: players) {
            res.add('[');
            res.addAll(this.StringToList(p.getName()));
            res.add(',');
            res.addAll(this.StringToList(p.getColor().toString()));
            res.add(',');
            res.addAll(this.SavePiece(p));
            res.add(']');
        }
        res.add(']');
        return res;
    }

    public ArrayList<Character> SavePiece(Player player) {
        ArrayList<Character> res = new ArrayList<>(this.StringToList("\"pieces\":["));
        for (Piece p: player.getPieces()) {
            res.addAll(this.StringToList(Integer.toString(p.getNode().getId())));
            res.add(',');
        }
        res.add(']');
        return res;
    }

    public ArrayList<Character> SaveTrap(Board board) {
        ArrayList<Character> res = new ArrayList<>(this.StringToList("\"traps\":["));
        for (Edge e: board.getEdges()) {
            if(e.isTrapped()){
                res.addAll(this.StringToList("edge"));
                res.add(',');
                res.addAll(this.StringToList(Integer.toString(e.getTrap().getTurnsLeft())));
                res.add(',');
                res.addAll(this.StringToList(Integer.toString(e.getStart().getId())));
                res.add(',');
                res.addAll(this.StringToList(Integer.toString(e.getStart().getId())));
            }
        }
        for (Node n: board.getNodes()) {
            if(n.isTrapped()){
                res.addAll(this.StringToList("node"));
                res.add(',');
                res.addAll(this.StringToList(Integer.toString(n.getTrap().getTurnsLeft())));
                res.add(',');
                res.addAll(this.StringToList(Integer.toString(n.getId())));
                res.add(',');
                res.addAll(this.StringToList(Integer.toString(n.getTrap().getDestination().getId())));
            }
        }
        res.add(']');
        return res;
    }

    public ArrayList<Character> StringToList(String s){
        ArrayList<Character> res = new ArrayList<>();
        for (Character c:s.toCharArray()) {
            res.add(c);
        }
        return res;
    }
}
