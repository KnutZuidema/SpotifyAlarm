package win.knutzuidema.spotifyalarm.ui;


import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.util.Duration;
import win.knutzuidema.spotifyalarm.api.BrowseAPI;
import win.knutzuidema.spotifyalarm.api.PlayerAPI;
import win.knutzuidema.spotifyalarm.api.PlaylistAPI;
import win.knutzuidema.spotifyalarm.datatypes.Device;
import win.knutzuidema.spotifyalarm.datatypes.Playlist;
import win.knutzuidema.spotifyalarm.datatypes.SpotifyObject;
import win.knutzuidema.spotifyalarm.datatypes.Track;

import java.awt.*;
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
    private Text deviceName;
    private Text songName;
    private GridPane grid;
    private GridPane gridButtons;
    private Pane listing;
    private ListView<SpotifyObject> context;
    private ListView<SpotifyObject> tracks;
    private Stack<Node> stack;
    private ProgressBar progressBar;
    private Task<Void> progress;
    private Task<String> songUpdate;
    private Stage stage;
    private double sceneWidth;
    private double textWidth;
    private Timeline timeline = new Timeline();
    private Duration startDuration;
    private Duration endDuration;
    private KeyValue startKeyValue;
    private KeyFrame startKeyFrame;
    private KeyValue endKeyValue;
    private KeyFrame endKeyFrame;
    private String name;
    private double duration;

    @Override
    public void start(Stage primaryStage){
        stage = primaryStage;
        player = new PlayerAPI();
        browse = new BrowseAPI();
        playlist = new PlaylistAPI();
        device = player.getPlayer().getActiveDevice();
        playpause = new Button();
        next = new Button(">>");
        previous = new Button("<<");
        back = new Button("Back");
        deviceName = new Text(device.getName());
        songName = new Text();
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
                    Thread.sleep(100);
                    if (player.getPlayer().isPlaying()) {
                        updateProgress(player.getCurrentlyPlaying().getProgress(), duration);
                    }
                }
            }
        };
        songUpdate = new Task<String>() {
            @Override
            protected String call() throws Exception {
                while(true) {
                    Thread.sleep(20);
                    name = player.getPlayer().getCurrentlyPlaying().getName();
                    if (!name.equals(songName.getText())) {
                        updateValue(name);
                        updateScroll();
                        duration = player.getPlayer().getCurrentlyPlaying().getDuration();
                    }
                    updateMessage(player.getPlayer().isPlaying() ? "| |" : ">");
                }
            }
        };
        primaryStage.setTitle("SpotifyAlarm");
        context.setItems(FXCollections.observableArrayList(browse.getFeaturedPlaylists().getItems()));
        context.setOnMouseClicked(this::contextAction);
        tracks.setOnMouseClicked(this::trackAction);
        playpause.setOnAction(this::playpauseAction);
        playpause.textProperty().bind(songUpdate.messageProperty());
        songName.textProperty().bind(songUpdate.valueProperty().asString());
        next.setOnAction(event -> player.next());
        previous.setOnAction(event -> player.previous());
        back.setOnAction(this::backAction);
        back.setVisible(false);
        progressBar.progressProperty().bind(progress.progressProperty());
        progressBar.setMaxWidth(Double.MAX_VALUE);
        Thread progressThread = new Thread(progress);
        progressThread.setDaemon(true);
        progressThread.start();
        Thread songThread = new Thread(songUpdate);
        songThread.setDaemon(true);
        songThread.start();

        GridPane.setHalignment(deviceName, HPos.CENTER);
        GridPane.setHgrow(progressBar, Priority.ALWAYS);
        gridButtons.setAlignment(Pos.CENTER);
        grid.add(deviceName, 0, 0);
        grid.add(songName, 0, 1);
        grid.add(gridButtons, 0, 2);
        gridButtons.add(previous, 0, 0);
        gridButtons.add(playpause, 1, 0);
        gridButtons.add(next, 2, 0);
        grid.add(listing, 0, 4);
        listing.getChildren().add(context);
        grid.add(back, 0, 5);
        grid.add(progressBar, 0, 3);

        primaryStage.setScene(new Scene(grid));
        primaryStage.setResizable(false);
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
        }
        else{
            player.play();
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

    private void updateScroll(){
        sceneWidth = stage.getWidth();
        textWidth = new Text(name).getLayoutBounds().getWidth();
        System.out.println(sceneWidth + " | " + textWidth);
        startDuration = Duration.ZERO;
        endDuration = Duration.seconds(7);
        startKeyValue = new KeyValue(songName.translateXProperty(), sceneWidth - 15 < textWidth ? sceneWidth : 0);
        startKeyFrame = new KeyFrame(startDuration, startKeyValue);
        endKeyValue = new KeyValue(songName.translateXProperty(), sceneWidth - 15 < textWidth ? -1.0 * textWidth : 0);
        endKeyFrame = new KeyFrame(endDuration, endKeyValue);
        timeline = new Timeline(startKeyFrame, endKeyFrame);
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }
}
