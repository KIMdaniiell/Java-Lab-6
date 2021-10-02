package server.handling.command;

import server.handling.Response;
import server.handling.data.format.MusicBand;

import java.util.Stack;

public class ClearCommand implements Command{
    private Stack<MusicBand> mystack;

    public ClearCommand(Stack<MusicBand> mystack) {
        this.mystack = mystack;
    }

    @Override
    public void execute(String args, MusicBand band, Response response) {
        mystack.clear();
        System.out.println("Коллекция очищена.");
        response.addNote("Коллекция очищена.");
    }
}
