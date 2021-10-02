package server.handling.command;

import server.handling.Response;
import server.handling.data.format.MusicBand;

import java.util.Stack;

public class PrintDescriptionCommand implements Command {
    private Stack<MusicBand> mystack;

    public PrintDescriptionCommand(Stack<MusicBand> mystack) {
        this.mystack = mystack;
    }

    @Override
    public void execute(String args, MusicBand musicBand, Response response) {
        for (MusicBand band : mystack) {
            System.out.println(band.getDescription());
        }
    }
}
