package win.knutzuidema.spotifyalarm.ui;


import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.stage.Stage;
import win.knutzuidema.spotifyalarm.api.BrowseAPI;
import win.knutzuidema.spotifyalarm.api.PlayerAPI;
import win.knutzuidema.spotifyalarm.api.PlaylistAPI;
import win.knutzuidema.spotifyalarm.datatypes.Device;
import win.knutzuidema.spotifyalarm.datatypes.Playlist;
import win.knutzuidema.spotifyalarm.datatypes.SpotifyObject;
import win.knutzuidema.spotifyalarm.datatypes.Track;

import java.util.Stack;

public class UI extends Application {
    public static void main(String[] args){
        launch(args);
    }

    private PlayerAPI player;
    private BrowseAPI browse;
    private PlaylistAPI playlist;
    private Device device;
    private Button playpause;
    private Button next;
    private Button previous;
    private Button back;
    private TextField text;
    private GridPane grid;
    private GridPane gridButtons;
    private Pane listing;
    private ListView<SpotifyObject> context;
    private ListView<SpotifyObject> tracks;
    private Stack<Node> stack;
    private ProgressBar progressBar;
    private Task<Void> progress;

    @Override
    public void start(Stage primaryStage){
        player = new PlayerAPI();
        browse = new BrowseAPI();
        playlist = new PlaylistAPI();
        device = player.getPlayer().getActiveDevice();
        playpause = new Button(player.getPlayer().isPlaying() ? "PAUSE" : "PLAY");
        next = new Button(">>");
        previous = new Button("<<");
        back = new Button("Back");
        text = new TextField(device.getName());
        grid = new GridPane();
        gridButtons = new GridPane();
        listing = new Pane();
        context = new ListView<>();
        tracks = new ListView<>();
        stack = new Stack<>();
        progressBar = new ProgressBar();
        progress = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                while(true) {
                    try {
                        Thread.sleep(100);
                    } catch (Exception ignored) {}
                    if (player.getPlayer().isPlaying()) {
                        updateProgress(player.getCurrentlyPlaying().getProgress(), player.getPlayer().getCurrentlyPlaying().getDuration());
                    }
                }
            }
        };

        primaryStage.setTitle("SpotifyAlarm");
        context.setItems(FXCollections.observableArrayList(browse.getFeaturedPlaylists().getItems()));
        context.setOnMouseClicked(this::contextAction);
        tracks.setOnMouseClicked(this::trackAction);
        playpause.setOnAction(this::playpauseAction);
        next.setOnAction(event -> player.next());
        previous.setOnAction(event -> player.previous());
        back.setOnAction(this::backAction);
        back.setVisible(false);
        text.setEditable(false);
        text.setAlignment(Pos.CENTER);
        progressBar.progressProperty().bind(progress.progressProperty());
        progressBar.setMaxWidth(Double.MAX_VALUE);
        GridPane.setHgrow(progressBar, Priority.ALWAYS);
        Thread thread = new Thread(progress);
        thread.setDaemon(true);
        thread.start();

        gridButtons.setAlignment(Pos.CENTER);
        grid.add(text, 0, 0);
        grid.add(gridButtons, 0, 1);
        gridButtons.add(previous, 0, 0);
        gridButtons.add(playpause, 1, 0);
        gridButtons.add(next, 2, 0);
        grid.add(listing, 0, 3);
        listing.getChildren().add(context);
        grid.add(back, 0, 4);
        grid.add(progressBar, 0, 2);

        primaryStage.setScene(new Scene(grid));
        primaryStage.show();
    }

    private void contextAction(MouseEvent event){
        if (event.getButton().equals(MouseButton.PRIMARY)) {
            if (event.getClickCount() > 1) {
                stack.push(context);
                back.setVisible(true);
                Playlist selected = (Playlist) context.getSelectionModel().getSelectedItem();
                tracks.setItems(FXCollections.observableArrayList(playlist
                                .getPlaylistTracks(selected.getOwner().getId(), selected.getId())
                                .getTracks()));
                listing.getChildren().setAll(tracks);
            }
        }else if(event.getButton().equals(MouseButton.SECONDARY)){
            Playlist selected = (Playlist) context.getSelectionModel().getSelectedItem();
            player.play(selected);
        }
    }

    private void trackAction(MouseEvent event){
        if (event.getButton().equals(MouseButton.PRIMARY)) {
            if (event.getClickCount() > 1) {
                Track selected = (Track) tracks.getSelectionModel().getSelectedItem();
                player.play(selected);
            }
        }
    }

    private void playpauseAction(ActionEvent event){
        if(player.getPlayer().isPlaying()){
            player.pause();
            playpause.setText("PLAY");
        }
        else{
            player.play();
            playpause.setText("PAUSE");
        }
    }

    private void backAction(ActionEvent event){
        if(!stack.empty()){
            listing.getChildren().setAll(stack.pop());
            if(stack.empty()){
                back.setVisible(false);
            }
        }
    }
}
