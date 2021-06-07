package ihm2;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    private static Stage stage;


    @Override
    public void start(Stage stage) throws Exception {
        Main.stage=stage;

        //Creation of all the scenes
        SceneAskFile.create();
        SceneNewGame.create();
        SceneTypeOfMap.create();

        SceneOptions.create();
        SceneRules.create();

        SceneMainMenu.create();

        //Stage Settings
        stage.setTitle("The Mill Game");
        stage.setScene(SceneMainMenu.getScene());
        stage.setResizable(true);
        stage.show();
    }

    public static void changeScene(Scene newScene){
        stage.setScene(newScene);
    }

    public static Stage getStage(){
        return stage;
    }

    public static void main(String[] args) {
        Application.launch(args);
    }

}
