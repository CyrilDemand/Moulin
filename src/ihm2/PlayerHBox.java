package ihm2;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import moulin.Color;
import moulin.Config;

import java.util.Collections;


public class PlayerHBox extends HBox {
    private static final int CANVAS_WIDTH=100;

    public PlayerHBox(){
        Button bUp=new Button("\uD83E\uDC09");
        bUp.addEventHandler(ActionEvent.ACTION, e->{
            this.moveUp();
        });
        Button bDown=new Button("\uD83E\uDC0B");
        bDown.addEventHandler(ActionEvent.ACTION, e->{
            this.moveDown();
        });
        Button bDelete=new Button("\uD83D\uDDD1");
        bDelete.addEventHandler(ActionEvent.ACTION, e->{
            delete();
        });
        ComboBox<String> difficulties = new ComboBox<>();
        TextField playerName = new TextField("Player name");
        ComboBox<String> AIName = new ComboBox<>();
        AIName.getItems().addAll("Random","Normal");
        ComboBox<String> colors = new ComboBox<>();;
        for (Color c:Color.List()){
            colors.getItems().add(""+c);
        }
        difficulties.getItems().addAll(Config.aiRandomNames);
        ComboBox<String> player = new ComboBox<String>();
        player.getItems().addAll("Player","AI");
        player.setValue("Player");
        this.getChildren().addAll(bDown,bUp,bDelete,player,playerName,AIName,difficulties,colors);
        player.valueProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                if (t1.equals("Player")){
                    PlayerHBox.super.getChildren().add(playerName);
                    PlayerHBox.super.getChildren().remove(AIName);
                    PlayerHBox.super.getChildren().remove(difficulties);
                }else{
                    PlayerHBox.super.getChildren().remove(playerName);
                    PlayerHBox.super.getChildren().add(AIName);
                    PlayerHBox.super.getChildren().add(difficulties);
                }
            }
        });
    }
    public void moveUp(){
        try {
            Collections.swap(SceneChoixJoueurs.players.getItems(), getPlayerListIndex(), getPlayerListIndex() - 1);
            selectCorrespondingListViewItem();
        }catch (IndexOutOfBoundsException err){
            //System.out.println("already at the top");
        }
    }

    public void moveDown(){
        try {
            Collections.swap(SceneChoixJoueurs.players.getItems(), getPlayerListIndex(), getPlayerListIndex() + 1);
            selectCorrespondingListViewItem();
        }catch (IndexOutOfBoundsException err){
            //System.out.println("already at the bottom");
        }
    }
    public void delete(){
        System.out.println(this);
        SceneChoixJoueurs.players.getItems().remove(this);
    }

    public int getPlayerListIndex(){
        return SceneChoixJoueurs.players.getItems().indexOf(this);
    }

    public void selectCorrespondingListViewItem(){
        int i=SceneChoixJoueurs.players.getItems().indexOf(this);
        //System.out.println(i);
        SceneChoixJoueurs.players.getFocusModel().focus(i);
        SceneChoixJoueurs.players.getSelectionModel().select(i);
    }
}