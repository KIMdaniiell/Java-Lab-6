package server.handling.command;

import server.Parser;
import server.handling.Response;
import server.handling.data.format.MusicBand;

import java.util.Stack;

public class SaveCommand implements Command {
    private final Stack<MusicBand> mystack;

    public SaveCommand(Stack<MusicBand> mystack) {
        this.mystack = mystack;
    }

    @Override
    public void execute(String args, MusicBand musicBand, Response response) {
        Parser.deserialize(args, mystack);
        String note = "Сохранение ...";
        System.out.println(note);
        response.addNote(note);
    }
}
