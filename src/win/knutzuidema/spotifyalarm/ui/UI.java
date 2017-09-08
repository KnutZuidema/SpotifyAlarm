package win.knutzuidema.spotifyalarm.ui;


import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import win.knutzuidema.spotifyalarm.Player;

public class UI extends Application {
    public static void main(String[] args){
        launch(args);
    }

    @Override
    public void start(Stage primaryStage){
        primaryStage.setTitle("SpotifyAlarm");
        Button playpause = new Button("PLAY/PAUSE");
        Button next = new Button(">>");
        Button previous = new Button("<<");
        Player player = new Player();

        playpause.setOnAction(event -> {
            if(player.isPlaying()) player.pause();
            else player.play();
        });

        next.setOnAction(event -> player.next());
        previous.setOnAction(event -> player.previous());

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.add(previous, 0, 0);
        grid.add(playpause, 1, 0);
        grid.add(next, 3, 0);

        primaryStage.setScene(new Scene(grid));
        primaryStage.show();
    }
}
