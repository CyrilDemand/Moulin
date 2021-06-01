package moulin;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class MainMenuButton extends Button {

    private final String name;
    private Scene sc;
    protected Stage s;
    static boolean fsHint=false;

    MainMenuButton(String name,Scene sc,Stage s){
        super(name);
        this.name = name;
        this.sc = sc;
        this.s=s;
        this.setOnAction(e->{
            if(sc==null)s.close();
            s.setScene(sc);
            if (MainMenuButton.fsHint==true){
                System.out.println(name);
                s.setFullScreen(true);
            }
        });

        s.widthProperty().addListener( e->{
           this.setPrefWidth((s.getWidth()*0.65));
        });
        s.heightProperty().addListener( e->{
            this.setPrefHeight(s.getHeight()*0.1);
        });

    }

    public static void setFsHint(boolean b) {
        MainMenuButton.fsHint = b;
    }
}