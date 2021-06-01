package moulin;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class ReturnButton extends Button{
    protected Scene prevScene;
    protected Stage s;
    static boolean fsHint;


    ReturnButton(Scene prevScene, Stage s, boolean fsHint){
        super("Retour");
        this.prevScene=prevScene;
        this.s = s;
        ReturnButton.fsHint=fsHint;
        this.setOnAction(e->{
            if(prevScene==null)s.close();
            s.setScene(prevScene);
            System.out.println(fsHint);
            if(ReturnButton.fsHint==true){
                s.setFullScreen(true);
            }
        });
    }

    public static void setFsHint(boolean b){
        ReturnButton.fsHint=b;
    }
}