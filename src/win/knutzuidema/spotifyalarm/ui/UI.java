package win.knutzuidema.spotifyalarm.ui;


import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.geometry.HPos;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.text.Text;
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

    private PlayerAPI player;
    private BrowseAPI browse;
    private PlaylistAPI playlist;
    private Device device;
    private Button playpause;
    private Button next;
    private Button previous;
    private Button back;
    private Button mute;
    private Text deviceName;
    private Label songName;
    private GridPane grid;
    private GridPane gridButtons;
    private Pane listing;
    private ListView<SpotifyObject> context;
    private ListView<SpotifyObject> tracks;
    private Stack<Node> stack;
    private ProgressBar progressBar;
    private Task<Void> progress;
    private Task<Double> songUpdate;
    private Task<Void> songScroll;
    private Stage stage;
    private double stageWidth;
    private double textWidth;
    private double duration;
    private Slider slider;
    private Thread progressThread;
    private Thread songThread;
    private Thread scrollThread;
    private Track currentlyPlaying;
    private int lastVolume;

    @Override
    public void start(Stage primaryStage){
        stage = primaryStage;
        player = new PlayerAPI();
        browse = new BrowseAPI();
        playlist = new PlaylistAPI();
        device = player.getPlayer().getActiveDevice();
        if(!device.isActive()){
            Dialog<Void> dialog = new Dialog<>();
            dialog.setTitle("SpotifyAlarm - Error");
            dialog.setContentText("There is no active device.\nPlease log into Spotify on one of your devices.");
            dialog.getDialogPane().getButtonTypes().add(new ButtonType("OK"));
            dialog.setOnCloseRequest(event -> System.exit(0));
            dialog.show();
            return;
        }
        playpause = new Button();
        next = new Button(">>");
        previous = new Button("<<");
        back = new Button("Back");
        mute = new Button("x");
        deviceName = new Text(device.getName());
        songName = new Label();
        grid = new GridPane();
        gridButtons = new GridPane();
        listing = new Pane();
        context = new ListView<>();
        tracks = new ListView<>();
        stack = new Stack<>();
        progressBar = new ProgressBar();
        slider = new Slider();
        currentlyPlaying = player.getPlayer().getCurrentlyPlaying();

        progress = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                while(true) {
                    if (player.getPlayer().isPlaying()) {
                        updateProgress(player.getCurrentlyPlaying().getProgress(), duration);
                    }
                    Thread.sleep(300);
                }
            }
        };
        songUpdate = new Task<Double>() {
            @Override
            protected Double call() throws Exception {
                duration = player.getPlayer().getCurrentlyPlaying().getDuration();
                while(true) {
                    Track tmp = player.getPlayer().getCurrentlyPlaying();
                    if (!tmp.getId().equals(currentlyPlaying.getId())) {
                        duration = player.getPlayer().getCurrentlyPlaying().getDuration();
                        currentlyPlaying = tmp;
                    }
                    updateMessage(player.getPlayer().isPlaying() ? "| |" : ">");
                    Thread.sleep(500);
                }
            }
        };

        songScroll = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                while (true) {
                    stageWidth = stage.getWidth();
                    textWidth = new Text(currentlyPlaying.getName()).getLayoutBounds().getWidth();
                    char[] chars = currentlyPlaying.getName().toCharArray();
                    List<Character> list = new LinkedList<>();
                    for (char c : chars) {
                        list.add(c);
                    }
                    list.add(' ');
                    list.add(' ');
                    list.add(' ');
                    list.add(' ');
                    list.add(' ');
                    list.add(' ');
                    updateMessage(new String(chars));
                    GridPane.setHalignment(songName, HPos.CENTER);
                    while (stageWidth < textWidth) {
                        GridPane.setHalignment(songName, HPos.LEFT);
                        stageWidth = stage.getWidth();
                        textWidth = new Text(currentlyPlaying.getName()).getLayoutBounds().getWidth();
                        list.add(list.get(0));
                        list.remove(0);
                        StringBuilder string = new StringBuilder();
                        for (Character c : list) {
                            string.append(c);
                        }
                        updateMessage(string.toString());
                        Thread.sleep(180);
                    }
                    Thread.sleep(100);
                }
            }
        };

        primaryStage.setTitle("SpotifyAlarm");
        context.setItems(FXCollections.observableArrayList(browse.getFeaturedPlaylists().getItems()));
        context.setOnMouseClicked(this::contextAction);
        tracks.setOnMouseClicked(this::trackAction);
        playpause.setOnAction(this::playpauseAction);
        playpause.textProperty().bind(songUpdate.messageProperty());
        mute.setOnAction(event -> {
            System.out.println(slider.getValue());
            if(slider.getValue() == 0.0) {
                slider.setValue(lastVolume);
            }else{
                slider.setValue(0);
            }
        });
        songName.textProperty().bind(songScroll.messageProperty());
        next.setOnAction(event -> player.next());
        previous.setOnAction(event -> player.previous());
        back.setOnAction(this::backAction);
        back.setVisible(false);
        progressBar.progressProperty().bind(progress.progressProperty());
        progressBar.setMaxWidth(Double.MAX_VALUE);
        progressThread = new Thread(progress);
        progressThread.setDaemon(true);
        progressThread.start();
        songThread = new Thread(songUpdate);
        songThread.setDaemon(true);
        songThread.start();
        scrollThread = new Thread(songScroll);
        scrollThread.setDaemon(true);
        scrollThread.start();
        slider.setOrientation(Orientation.VERTICAL);
        slider.setMax(100);
        slider.setMin(0);
        slider.setMajorTickUnit(100);
        slider.setMinorTickCount(99);
        slider.setSnapToTicks(true);
        slider.setPrefHeight(1);
        slider.setValue(device.getVolume());
        lastVolume = device.getVolume();
        slider.valueProperty().addListener((observable, oldValue, newValue) -> {
            if (!slider.isValueChanging()) {
                player.setVolume(newValue.intValue());
                if(newValue.intValue() > 0) {
                    lastVolume = newValue.intValue();
                }
            }
        });

        GridPane.setHgrow(progressBar, Priority.ALWAYS);
        GridPane.setVgrow(slider, Priority.ALWAYS);
        gridButtons.setAlignment(Pos.CENTER);
        GridPane.setHalignment(deviceName, HPos.CENTER);
        grid.add(deviceName, 0, 0);
        grid.add(songName, 0, 1);
        gridButtons.add(previous, 0, 0);
        gridButtons.add(playpause, 1, 0);
        gridButtons.add(next, 2, 0);
        grid.add(gridButtons, 0, 2);
        grid.add(listing, 0, 4, 2, 1);
        listing.getChildren().add(context);
        grid.add(back, 0, 5);
        grid.add(progressBar, 0, 3);
        grid.add(slider, 1, 0, 1, 3);
        grid.add(mute, 1, 3);

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
}
