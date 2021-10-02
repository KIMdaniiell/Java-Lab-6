package server.handling.command;

import server.handling.Response;
import server.handling.data.format.MusicBand;

import java.util.Collections;
import java.util.Stack;

public class ReorderCommand implements Command {
    private Stack<MusicBand> mystack;

    public ReorderCommand(Stack<MusicBand> mystack) {
        this.mystack = mystack;
    }

    @Override
    public void execute(String args, MusicBand musicBand, Response response) {
        Collections.reverse(mystack);
        String note = "Коллекция отсортирована в обратном порядке.";
        System.out.println(note);
        response.addNote(note);
    }
}
