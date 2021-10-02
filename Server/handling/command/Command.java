package server.handling.command;

import server.handling.Response;
import server.handling.data.format.MusicBand;

public interface Command {
    public void execute(String args, MusicBand band, Response response);
}
