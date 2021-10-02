package server.handling.command;

import server.handling.Response;
import server.handling.data.format.MusicBand;

import java.util.Collections;
import java.util.Stack;

public class SortCommand implements Command {
    private final Stack<MusicBand> mystack;

    public SortCommand(Stack<MusicBand> mystack) {
        this.mystack = mystack;
    }

    @Override
    public void execute(String args, MusicBand musicBand, Response response) {
        Collections.sort(mystack);
        String note = "Коллекция отсортирована.";
        System.out.println(note);
        response.addNote(note);
    }
}
