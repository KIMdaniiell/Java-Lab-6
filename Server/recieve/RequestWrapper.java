package server.recieve;

import server.handling.Command;

import java.io.Serializable;

public class RequestWrapper implements Serializable {
    private Command command;
    private String arg;
    //private MusicBand musicBand;
}
