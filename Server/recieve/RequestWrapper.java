package server.recieve;

import server.handling.data.format.MusicBand;

import java.io.Serializable;

public class RequestWrapper implements Serializable {
    private String command;
    private String arg;
    private MusicBand musicBand;

    public String getCommand() {
        return command;
    }

    public String getArg() {
        return arg;
    }

    public MusicBand getMusicBand() {
        return musicBand;
    }
}
