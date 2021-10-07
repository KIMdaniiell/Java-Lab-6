package format;

import format.MusicBand;

import java.io.Serializable;

public class RequestWrapper implements Serializable {
    private String command;
    private String arg;
    private MusicBand musicBand;

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public String getArg() {
        return arg;
    }

    public void setArg(String arg) {
        this.arg = arg;
    }

    public MusicBand getMusicBand() {
        return musicBand;
    }

    public void setMusicBand(MusicBand musicBand) {
        this.musicBand = musicBand;
    }
}
