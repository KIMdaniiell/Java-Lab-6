package server.handling.command;

import server.handling.Response;
import server.handling.data.format.MusicBand;

import java.util.Stack;

public class ExitCommand implements Command {
    private final Stack<MusicBand> mystack;

    public ExitCommand(Stack<MusicBand> mystack) {
        this.mystack = mystack;
    }

    @Override
    public void execute(String args, MusicBand band, Response response) {
        System.out.println("Завершение работы программы ...");
        response.addNote("Завершение работы программы ...");
    }
}
