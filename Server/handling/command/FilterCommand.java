package server.handling.command;

import server.handling.Response;
import server.handling.data.format.MusicBand;
import server.handling.data.format.MusicGenre;

import java.util.Stack;

public class FilterCommand implements Command {
    private final Stack<MusicBand> mystack;

    public FilterCommand(Stack<MusicBand> mystack) {
        this.mystack = mystack;
    }

    @Override
    public void execute(String args, MusicBand musicBand, Response response) {
        String offer = args;

        for (MusicBand band : mystack) {
            if (band.getGenre().compareTo(MusicGenre.valueOf(offer)) < 0) {
                System.out.println(band.toString());
            }
        }
    }
}
