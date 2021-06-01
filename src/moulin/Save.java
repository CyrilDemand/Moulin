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
        save.add('{');
        save.addAll(this.SaveBoard(jeu.getBoard()));
        save.addAll(this.SavePlayers(jeu.getPlayers()));
        save.add('\n');;
        save.add('}');
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
        ArrayList<Character> res = new ArrayList<>(this.StringToList("\n\t\"nodes\":[\n"));
        for (Node n : board.getNodes()) {
            res.add('\t');res.add('\t');
            res.add('[');
            res.addAll(this.StringToList(Integer.toString(n.getX())));
            res.add(',');
            res.addAll(this.StringToList(Integer.toString(n.getY())));
            res.add(',');
            res.addAll(this.StringToList(Integer.toString(n.getId())));
            res.add(']');
            if (!n.equals(board.getNodes().get(board.getNodes().size()-1))){
                res.add(',');
            }
            res.add('\n');
        }
        res.add('\t');res.add(']');res.add(',');
        return res;
    }
    public ArrayList<Character> SaveEdges(Board board) {
        ArrayList<Character> res = new ArrayList<>(this.StringToList("\n\t\"edges\":[\n"));
        for (Edge e: board.getEdges()) {
            res.add('\t');res.add('\t');
            res.add('[');
            res.addAll(this.StringToList(Integer.toString(e.getStart().getId())));
            res.add(',');
            res.addAll(this.StringToList(Integer.toString(e.getEnd().getId())));
            res.add(']');
            if (!e.equals(board.getEdges().get(board.getEdges().size()-1))){
                res.add(',');
            }
            res.add('\n');
        }
        res.add('\t');res.add(']');
        res.add(',');
        return res;
    }

    public ArrayList<Character> SaveLines(Board board) {
        ArrayList<Character> res = new ArrayList<>(this.StringToList("\n\t\"lines\":[\n"));
        for (Line l: board.getLines()) {
            res.add('\t');res.add('\t');
            res.add('[');
            for (Node n :l.getNodes()) {
                res.addAll(this.StringToList(Integer.toString(n.getId())));
                if (!n.equals(l.getNodes().get(l.getNodes().size()-1))){
                    res.add(',');
                }
            }
            res.add(']');
            if (!l.equals(board.getLines().get(board.getLines().size()-1))){
                res.add(',');
            }
            res.add('\n');
        }
        res.add('\t');res.add(']');
        res.add(',');
        return res;
    }

    public ArrayList<Character> SavePlayers(ArrayList<Player> players) {
        ArrayList<Character> res = new ArrayList<>(this.StringToList("\n\t\"players\":[\n"));
        for (Player p: players) {
            res.add('\t');res.add('\t');
            res.add('[');
            res.addAll(this.StringToList('\"'+p.getName()+'\"'));
            res.add(',');
            res.addAll(this.StringToList('\"'+p.getColor().toString()+'\"'));
            res.add(',');
            res.addAll(this.SavePiece(p));
            res.add(',');
            res.addAll(this.StringToList(Integer.toString(p.getNbTrap())));
            res.add(']');
            if (!p.equals(players.get(players.size()-1))){
                res.add(',');
            }
            res.add('\n');
        }
        res.add('\t');
        res.add(']');
        return res;
    }

    public ArrayList<Character> SavePiece(Player player) {
        ArrayList<Character> res = new ArrayList<>();
        res.add('[');
        for (Piece p: player.getPieces()) {
            res.addAll(this.StringToList(Integer.toString(p.getNode().getId())));
            if (!p.equals(player.getPieces().get(player.getPieces().size()-1))){
                res.add(',');
            }
        }
        res.add(']');
        return res;
    }

    public ArrayList<Character> SaveTrap(Board board) {
        ArrayList<Character> res = new ArrayList<>(this.StringToList("\n\t\"traps\":[\n"));
        ArrayList<String> traps = new ArrayList<>();
        for (Edge e: board.getEdges()) {
            if(e.isTrapped()){
                String tmp = "";
                tmp+="\t\t";
                tmp+='[';
                tmp+="\"edge\"";
                tmp+=',';
                tmp+=Integer.toString(e.getTrap().getTurnsLeft());
                tmp+=',';
                tmp+=Integer.toString(e.getStart().getId());
                tmp+=',';
                tmp+=Integer.toString(e.getEnd().getId());
                tmp+=']';
                tmp+='\n';
                traps.add(tmp);
            }
        }
        for (Node n: board.getNodes()) {
            if(n.isTrapped()){
                String tmp = "";
                tmp+='\t';
                tmp+=res.add('[');
                tmp+="\"node\"";
                tmp+=',';
                tmp+=Integer.toString(n.getTrap().getTurnsLeft());
                tmp+=',';
                tmp+=Integer.toString(n.getId());
                tmp+=',';
                tmp+=Integer.toString(n.getTrap().getDestination().getId());
                tmp+=']';
                traps.add(tmp);
            }
        }
        for (String s:traps) {
            res.addAll(this.StringToList(s));
            if(!s.equals(traps.get(traps.size()-1))){
                res.add(',');
            }
        }
        res.add('\t');res.add(']');
        res.add(',');
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
