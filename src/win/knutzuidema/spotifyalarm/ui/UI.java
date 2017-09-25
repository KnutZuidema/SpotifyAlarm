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
import win.knutzuidema.spotifyalarm.api.MeAPI;
import win.knutzuidema.spotifyalarm.api.PlayerAPI;
import win.knutzuidema.spotifyalarm.api.PlaylistAPI;
import win.knutzuidema.spotifyalarm.datatypes.*;

import java.util.Stack;

public class UI extends Application {
    public static void main(String[] args){
        launch(args);
    }

    private Player player;
    private Button playpause;
    private Button next;
    private Button previous;
    private Button back;
    private Button mute;
    private Button featuredPlaylists;
    private Button newReleases;
    private Button library;
    private Button libraryTracks;
    private Button libraryAlbums;
    private Button libraryPlaylists;
    private Button libraryArtists;
    private Text deviceName;
    private Label songName;
    private GridPane grid;
    private GridPane gridControls;
    private GridPane gridContext;
    private GridPane gridLibrary;
    private Pane listing;
    private ListView<SpotifyObject> context;
    private Stack<Node> stack;
    private ProgressBar progressBar;
    private Task<Void> progressUpdate;
    private Task<Void> isPlayingUpdate;
    private Task<Void> scrollUpdate;
    private Stage stage;
    private double stageWidth;
    private double textWidth;
    private double duration;
    private Slider slider;
    private String currentlyPlaying;
    private boolean nameChanged = true;
    private int lastVolume;
    private Node nodeIn0_4;

    @Override
    public void start(Stage primaryStage){
        stage = primaryStage;
        player = PlayerAPI.getPlayer();
        if(!player.getActiveDevice().isActive()){
            Dialog<Void> dialog = new Dialog<>();
            dialog.setTitle("SpotifyAlarm - Error");
            dialog.setContentText("There is no active device.\nPlease log into Spotify on one of your devices.");
            dialog.getDialogPane().getButtonTypes().add(new ButtonType("OK"));
            dialog.setOnCloseRequest(event -> System.exit(0));
            dialog.show();
            return;
        }
        currentlyPlaying = player.getCurrentlyPlaying().getName();
        player.setRefreshRate(100);
        player.keepSynchronized(true);
        player.getActiveDevice().volumeProperty().addListener(((observable, oldValue, newValue) -> System.out.println("volume: " + newValue)));
        player.currentlyPlayingProperty().addListener(((observable, oldValue, newValue) -> {
            if(currentlyPlaying == null || !(currentlyPlaying.equals(player.getCurrentlyPlaying().getName()))){
                nameChanged = true;
                currentlyPlaying = player.getCurrentlyPlaying().getName();
                stageWidth = stage.getWidth();
                textWidth = new Text(currentlyPlaying).getLayoutBounds().getWidth() + 15;
                System.out.println("name changed");
                if(stageWidth < textWidth){
                    System.out.println("scrolling...");
                }
            }
        }));
        player.getActiveDevice().idProperty().addListener(((observable, oldValue, newValue) -> duration = player.getCurrentlyPlaying().getDuration()));
        duration = player.getCurrentlyPlaying().getDuration();
        playpause = new Button();
        next = new Button(">>");
        previous = new Button("<<");
        back = new Button("Back");
        mute = new Button("x");
        featuredPlaylists = new Button("Featured");
        featuredPlaylists.setPrefWidth(100);
        featuredPlaylists.setOnAction(this::contextButtonAction);
        newReleases = new Button("New Releases");
        newReleases.setPrefWidth(100);
        newReleases.setOnAction(this::contextButtonAction);
        library = new Button("Library");
        library.setPrefWidth(100);
        library.setOnAction(this::contextButtonAction);
        libraryAlbums = new Button("Albums");
        libraryAlbums.setPrefWidth(100);
        libraryAlbums.setOnAction(this::libraryButtonAction);
        libraryArtists = new Button("Artists");
        libraryArtists.setPrefWidth(100);
        libraryArtists.setOnAction(this::libraryButtonAction);
        libraryPlaylists = new Button("Playlists");
        libraryPlaylists.setPrefWidth(100);
        libraryPlaylists.setOnAction(this::libraryButtonAction);
        libraryTracks = new Button("Songs");
        libraryTracks.setPrefWidth(100);
        libraryTracks.setOnAction(this::libraryButtonAction);
        deviceName = new Text(player.getActiveDevice().getName());
        songName = new Label();
        grid = new GridPane();
        gridControls = new GridPane();
        gridContext = new GridPane();
        gridContext.setAlignment(Pos.CENTER);
        gridContext.setVgap(35);
        gridContext.setPrefSize(260, 400);
        gridLibrary = new GridPane();
        gridLibrary.setAlignment(Pos.CENTER);
        gridLibrary.setVgap(35);
        gridContext.setPrefSize(260, 400);
        stack = new Stack<>();
        progressBar = new ProgressBar();
        slider = new Slider();

        progressUpdate = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                while(player.getActiveDevice().isActive()) {
                    if (player.isPlaying()) {
                        updateProgress(player.getProgress(), duration);
                    }
                    Thread.sleep(100);
                }
                return Void.TYPE.newInstance();
            }
        };
        isPlayingUpdate = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                while (player.getActiveDevice().isActive()) {
                    updateMessage(player.isPlaying() ? "| |" : ">");
                    Thread.sleep(100);
                }
                return Void.TYPE.newInstance();
            }
        };

        scrollUpdate = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                String tmp = currentlyPlaying + "      ";
                while (player.getActiveDevice().isActive()) {
                    if(nameChanged) {
                        updateMessage(currentlyPlaying);
                        System.out.println("now playing " + currentlyPlaying + " by " + player.getCurrentlyPlaying().getArtists()[0].getName());
                        GridPane.setHalignment(songName, HPos.CENTER);
                        nameChanged = false;
                        tmp = currentlyPlaying + "      ";
                    }
                    while (stageWidth < textWidth) {
                        if(nameChanged){
                            nameChanged = false;
                            tmp = currentlyPlaying + "      ";
                            System.out.println("now playing " + currentlyPlaying + " by " + player.getCurrentlyPlaying().getArtists()[0].getName());
                        }
                        GridPane.setHalignment(songName, HPos.LEFT);
                        updateMessage(tmp);
                        tmp = tmp.substring(1, tmp.length()) + tmp.substring(0, 1);
                        Thread.sleep(100);
                    }
                    Thread.sleep(100);
                }
                return Void.TYPE.newInstance();
            }
        };

        Thread isPlayingThread = new Thread(isPlayingUpdate);
        isPlayingThread.setDaemon(true);
        isPlayingThread.start();
        Thread scrollUpdateThread = new Thread(scrollUpdate);
        scrollUpdateThread.setDaemon(true);
        scrollUpdateThread.start();
        Thread progressUpdateThread = new Thread(progressUpdate);
        progressUpdateThread.setDaemon(true);
        progressUpdateThread.start();

        primaryStage.setTitle("SpotifyAlarm");
        playpause.setOnAction(this::playpauseAction);
        playpause.textProperty().bind(isPlayingUpdate.messageProperty());
        mute.setOnAction(event -> {
            if(slider.getValue() == 0.0) {
                slider.setValue(lastVolume);
            }else{
                slider.setValue(0);
            }
        });
        songName.textProperty().bind(scrollUpdate.messageProperty());
        next.setOnAction(event -> PlayerAPI.next());
        previous.setOnAction(event -> PlayerAPI.previous());
        back.setOnAction(this::backAction);
        back.setVisible(false);
        progressBar.progressProperty().bind(progressUpdate.progressProperty());
        progressBar.setMaxWidth(Double.MAX_VALUE);
        slider.setOrientation(Orientation.VERTICAL);
        slider.setMax(100);
        slider.setMin(0);
        slider.setMajorTickUnit(100);
        slider.setMinorTickCount(99);
        slider.setSnapToTicks(true);
        slider.setPrefHeight(1);
        slider.setValue(player.getActiveDevice().getVolume());
        lastVolume = player.getActiveDevice().getVolume();
        slider.valueProperty().addListener((observable, oldValue, newValue) -> {
            if (!slider.isValueChanging()) {
                PlayerAPI.setVolume(newValue.intValue());
                if(newValue.intValue() > 0) {
                    lastVolume = newValue.intValue();
                }
            }
        });

        GridPane.setHgrow(progressBar, Priority.ALWAYS);
        GridPane.setVgrow(slider, Priority.ALWAYS);
        GridPane.setHalignment(deviceName, HPos.CENTER);
        gridControls.setAlignment(Pos.CENTER);
        grid.add(deviceName, 0, 0);
        grid.add(songName, 0, 1);
        gridContext.add(featuredPlaylists, 0, 0);
        gridContext.add(newReleases, 0, 1);
        gridContext.add(library, 0, 2);
        gridControls.add(previous, 0, 0);
        gridControls.add(playpause, 1, 0);
        gridControls.add(next, 2, 0);
        gridLibrary.add(libraryAlbums, 0, 0);
        gridLibrary.add(libraryTracks, 0, 1);
        gridLibrary.add(libraryPlaylists, 0, 2);
        grid.add(gridControls, 0, 2);
        grid.add(gridContext, 0, 4, 2, 1);
        nodeIn0_4 = gridContext;
        grid.add(back, 0, 5);
        grid.add(progressBar, 0, 3);
        grid.add(slider, 1, 0, 1, 3);
        grid.add(mute, 1, 3);

        primaryStage.setScene(new Scene(grid));
        primaryStage.setResizable(false);
        primaryStage.show();
        stageWidth = stage.getWidth();
        textWidth = new Text(currentlyPlaying).getLayoutBounds().getWidth() + 15;
    }

    private void contextAction(MouseEvent event){
        if (event.getButton().equals(MouseButton.PRIMARY)) {
            if (event.getClickCount() > 1) {
                back.setVisible(true);
                Node node = (Node) event.getSource();
                if(node instanceof ListView){
                    ListView<?> items = (ListView<?>) node;
                    Object item = items.getSelectionModel().getSelectedItem();
                    if(item instanceof Playlist) {
                        stack.push(node);
                        Playlist selected = (Playlist) item;
                        ListView<Track> context = new ListView<>(FXCollections
                                .observableArrayList(PlaylistAPI
                                        .getPlaylistTracks(selected.getOwner().getId(), selected.getId())
                                        .getTracks()));
                        context.setOnMouseClicked(this::contextAction);
                        this.update0_4(context);
                    }else if(item instanceof Album){
                        stack.push(node);
                        Album selected = ((Album) item).completeObject();
                        ListView<Track> context = new ListView<>(FXCollections
                                .observableArrayList(selected.getTracks().getItems()));
                        context.setOnMouseClicked(this::contextAction);
                        this.update0_4(context);
                    }else if(item instanceof Track){
                        Track selected = (Track) item;
                        PlayerAPI.play(selected);
                    }else{
                        System.out.println("no matching class found");
                    }
                }else{
                    System.out.println("not a list");
                }
            }
        }else if(event.getButton().equals(MouseButton.SECONDARY)){
            if(event.getSource() instanceof ListView){
                if(((ListView) event.getSource()).getSelectionModel().getSelectedItem() instanceof SpotifyObject){
                    SpotifyObject item = (SpotifyObject) ((ListView) event.getSource()).getSelectionModel().getSelectedItem();
                    PlayerAPI.play(item);
                }
            }
        }
    }

    private void contextButtonAction(ActionEvent event){
        stack.push(gridContext);
        back.setVisible(true);
        if(featuredPlaylists.equals(event.getSource())){
            ListView<Playlist> playlists = new ListView<>(FXCollections
                    .observableArrayList(BrowseAPI.getFeaturedPlaylists().getItems()));
            playlists.setOnMouseClicked(this::contextAction);
            this.update0_4(playlists);
        }else if(newReleases.equals(event.getSource())){
            ListView<Album> albums = new ListView<>(FXCollections
                    .observableArrayList(BrowseAPI.getNewReleases().getItems()));
            albums.setOnMouseClicked(this::contextAction);
            this.update0_4(albums);
        }else if(library.equals(event.getSource())){
            this.update0_4(gridLibrary);
        }
    }

    private void libraryButtonAction(ActionEvent event){
        stack.push(gridLibrary);
        if(libraryAlbums.equals(event.getSource())){
            ListView<Album> albums = new ListView<>(FXCollections
                    .observableArrayList(MeAPI.getSavedAlbums().getAlbums()));
            albums.setOnMouseClicked(this::contextAction);
            this.update0_4(albums);
        }else if(libraryPlaylists.equals(event.getSource())){
            ListView<Playlist> playlists = new ListView<>(FXCollections
                    .observableArrayList(MeAPI.getPlaylists().getItems()));
            playlists.setOnMouseClicked(this::contextAction);
            this.update0_4(playlists);
        }
    }

    private void playpauseAction(ActionEvent event){
        if(PlayerAPI.getPlayer().isPlaying()){
            PlayerAPI.pause();
        }
        else{
            PlayerAPI.play();
        }
    }

    private void backAction(ActionEvent event){
        if(!stack.empty()){
            this.update0_4(stack.pop());
            if(stack.empty()){
                back.setVisible(false);
            }
        }
    }

    private void update0_4(Node newNode){
        grid.getChildren().remove(nodeIn0_4);
        nodeIn0_4 = newNode;
        grid.add(nodeIn0_4, 0, 4, 2, 1);
    }
}
