package win.knutzuidema.spotifyalarm.ui;


import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import win.knutzuidema.spotifyalarm.api.BrowseAPI;
import win.knutzuidema.spotifyalarm.api.PlayerAPI;
import win.knutzuidema.spotifyalarm.api.PlaylistAPI;
import win.knutzuidema.spotifyalarm.datatypes.Device;
import win.knutzuidema.spotifyalarm.datatypes.Playlist;
import win.knutzuidema.spotifyalarm.datatypes.SpotifyObject;

public class UI extends Application {
    public static void main(String[] args){
        launch(args);
    }

    @Override
    public void start(Stage primaryStage){
        primaryStage.setTitle("SpotifyAlarm");
        PlayerAPI player = new PlayerAPI();
        BrowseAPI browse = new BrowseAPI();
        PlaylistAPI playlist = new PlaylistAPI();
        Device device = player.getPlayer().getActiveDevice();
        Button playpause = new Button(player.isPlaying() ? "PAUSE" : "PLAY");
        Button next = new Button(">>");
        Button previous = new Button("<<");
        TextField text = new TextField(device.getName());
        text.setEditable(false);
        text.setAlignment(Pos.CENTER);
        ListView<SpotifyObject> list = new ListView<>();
        ObservableList<SpotifyObject> listContent = FXCollections.observableArrayList(browse.getFeaturedPlaylists().getItems());
        list.setItems(listContent);
        list.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (event.getButton().equals(MouseButton.PRIMARY)) {
                    if (event.getClickCount() > 1) {
                        Playlist selected = (Playlist) list.getSelectionModel().getSelectedItem();
                        listContent.retainAll();
                        listContent.addAll(playlist
                                .getPlaylistTracks(selected.getOwner().getId(), selected.getId())
                                .getTracks());
                    }
                }
            }
        });

        playpause.setOnAction(event -> {
            if(player.isPlaying()){
                player.pause();
                playpause.setText("PLAY");
            }
            else{
                player.play();
                playpause.setText("PAUSE");
            }
        });

        next.setOnAction(event -> player.next());
        previous.setOnAction(event -> player.previous());

        GridPane grid = new GridPane();
        GridPane gridButtons = new GridPane();
        gridButtons.setAlignment(Pos.CENTER);
        grid.add(text, 0, 0);
        grid.add(gridButtons, 0, 1);
        gridButtons.add(previous, 0, 0);
        gridButtons.add(playpause, 1, 0);
        gridButtons.add(next, 2, 0);
        grid.add(list, 0, 2);

        primaryStage.setScene(new Scene(grid));
        primaryStage.show();
    }
}
