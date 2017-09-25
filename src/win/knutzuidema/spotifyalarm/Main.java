package win.knutzuidema.spotifyalarm;

import win.knutzuidema.spotifyalarm.api.PlayerAPI;
import win.knutzuidema.spotifyalarm.datatypes.Player;
import win.knutzuidema.spotifyalarm.ui.UI;


public class Main {
    public static void main(String[] args){
        //UI.main(args);

        Player player = new PlayerAPI().getPlayer();
        player.setRefreshRate(500);
        player.keepSynchronized(true);

        player.lastUpdatedProperty().addListener(((observable, oldValue, newValue) -> System.out.println(newValue + " | " +
                (int) (((double) player.getProgress()/(double) player.getCurrentlyPlaying().getDuration())*100) + "%")));

        while(true){}
    }
}
