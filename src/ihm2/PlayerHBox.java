package ihm2;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import moulin.*;

import java.util.Collections;


public class PlayerHBox extends HBox {
    private static final int CANVAS_WIDTH=100;
    private final ComboBox<String> colors;
    private final ComboBox<String> player;
    private final ComboBox<String> difficulties;
    private final TextField playerName;
    private final ComboBox<String> AIName;
    private final Button bDelete=new Button("\uD83D\uDDD1");
    public PlayerHBox(){
        Button bUp=new Button("\uD83E\uDC09");
        bUp.addEventHandler(ActionEvent.ACTION, e->{
            this.moveUp();
        });
        Button bDown=new Button("\uD83E\uDC0B");
        bDown.addEventHandler(ActionEvent.ACTION, e->{
            this.moveDown();
        });

        bDelete.addEventHandler(ActionEvent.ACTION, e->{
            delete();
            SceneChoixJoueurs.switchTo();
        });
        difficulties = new ComboBox<>();
        playerName = new TextField("Player name");
        AIName = new ComboBox<>();
        AIName.getItems().addAll("Random","Normal");
        colors = new ComboBox<>();;
        for (ColorEnum c: ColorEnum.List()){
            colors.getItems().add(""+c);
        }
        colors.setValue(colors.getItems().get(0));
        difficulties.getItems().addAll(Config.aiRandomNames);
        player = new ComboBox<String>();
        player.getItems().addAll("Player","AI");
        player.setValue("Player");
        this.getChildren().addAll(bDown,bUp,bDelete,colors,player,playerName);
        colors.valueProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                SceneChoixJoueurs.switchTo();
            }
        });
        player.valueProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                if (t1.equals("Player")){
                    System.out.println(colors.getValue());
                    PlayerHBox.super.getChildren().add(playerName);
                    PlayerHBox.super.getChildren().remove(AIName);
                    PlayerHBox.super.getChildren().remove(difficulties);
                }else{
                    System.out.println(colors.getValue());
                    PlayerHBox.super.getChildren().remove(playerName);
                    PlayerHBox.super.getChildren().add(AIName);
                    AIName.setValue(AIName.getItems().get(0));
                    PlayerHBox.super.getChildren().add(difficulties);
                    difficulties.setValue(difficulties.getItems().get(0));
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
        SceneChoixJoueurs.delButton();
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

    public ComboBox<String> getColors() {
        return this.colors;
    }

    public Button getbDelete() {
        return bDelete;
    }
    public boolean isHuman(){
        return player.getValue().equals("Player");
    }
    public ColorEnum getColor(){
        return ColorEnum.valueOf(colors.getValue());
    }
    public Player getPlayer(){
        return new Player(this.getName(),this.getColor());
    }
    public String getName(){
        return playerName.getText();
    }
    public AI getAI(){
        System.out.println(difficulties.getValue());
        if (difficulties.getValue().equals("Random")){
            return new RandomAI(this.getAIName(),this.getColor());
        }else{
            return new NormalAI(this.getAIName(),this.getColor());
        }
    }

    public String getAIName(){
        return AIName.getValue();
    }
}
