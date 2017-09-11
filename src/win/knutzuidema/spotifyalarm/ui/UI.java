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
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import win.knutzuidema.spotifyalarm.api.BrowseAPI;
import win.knutzuidema.spotifyalarm.api.PlayerAPI;
import win.knutzuidema.spotifyalarm.api.PlaylistAPI;
import win.knutzuidema.spotifyalarm.datatypes.Device;
import win.knutzuidema.spotifyalarm.datatypes.Playlist;
import win.knutzuidema.spotifyalarm.datatypes.SpotifyObject;
import win.knutzuidema.spotifyalarm.datatypes.Track;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

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
        Button back = new Button("Back");
        TextField text = new TextField(device.getName());
        text.setEditable(false);
        text.setAlignment(Pos.CENTER);

        GridPane grid = new GridPane();
        GridPane gridButtons = new GridPane();
        Pane stack = new Pane();

        ListView<SpotifyObject> playlists = new ListView<>();
        ListView<SpotifyObject> tracks = new ListView<>();
        ObservableList<SpotifyObject> playlistContent = FXCollections.observableArrayList(browse.getFeaturedPlaylists().getItems());
        playlists.setItems(playlistContent);
        playlists.setOnMouseClicked(event -> {
                if (event.getButton().equals(MouseButton.PRIMARY)) {
                    if (event.getClickCount() > 1) {
                        Playlist selected = (Playlist) playlists.getSelectionModel().getSelectedItem();
                        tracks.setItems(FXCollections
                                .observableArrayList(playlist
                                .getPlaylistTracks(selected.getOwner().getId(), selected.getId())
                                .getTracks()));
                        stack.getChildren().setAll(tracks);
                    }
                }else if(event.getButton().equals(MouseButton.SECONDARY)){
                    Playlist selected = (Playlist) playlists.getSelectionModel().getSelectedItem();
                    player.play(selected);
                }
        });
        tracks.setOnMouseClicked(event -> {
            if (event.getButton().equals(MouseButton.PRIMARY)) {
                if (event.getClickCount() > 1) {
                    Track selected = (Track) tracks.getSelectionModel().getSelectedItem();
                    player.play(selected);
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

        back.setOnAction(event -> {
            stack.getChildren().setAll(playlists);
        });

        gridButtons.setAlignment(Pos.CENTER);
        grid.add(text, 0, 0);
        grid.add(gridButtons, 0, 1);
        gridButtons.add(previous, 0, 0);
        gridButtons.add(playpause, 1, 0);
        gridButtons.add(next, 2, 0);
        grid.add(stack, 0, 2);
        stack.getChildren().add(playlists);
        grid.add(back, 0, 3);

        primaryStage.setScene(new Scene(grid));
        primaryStage.show();
    }
}
