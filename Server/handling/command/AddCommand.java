package server.handling.command;

import server.handling.Response;
import server.handling.data.format.MusicBand;

import java.util.Stack;

public class AddCommand implements Command {
    private final Stack<MusicBand> mystack;

    public AddCommand(Stack<MusicBand> mystack) {
        this.mystack = mystack;
    }

    @Override
    public void execute(String args, MusicBand musicBand, Response response) {
        MusicBand myband = musicBand;
        mystack.push(myband);
        System.out.println("Был добавлен новый объект - " + myband.toString());
        response.addNote("Был добавлен новый объект - " + myband.toString());
    }
}
