package server.handling.command;

import server.handling.Response;
import server.handling.data.format.MusicBand;

import java.util.Iterator;
import java.util.Stack;

public class RemoveGreaterCommand implements Command {
    private final Stack<MusicBand> mystack;

    public RemoveGreaterCommand(Stack<MusicBand> mystack) {
        this.mystack = mystack;
    }

    @Override
    public void execute(String args, MusicBand musicBand, Response response) {
        Stack<MusicBand> stackofgreater = new Stack<>();
        MusicBand someband = musicBand;

        Iterator<MusicBand> iterator = mystack.iterator();
        while (iterator.hasNext()) {
            if (iterator.next().compareTo(someband) > 0) {
                iterator.remove();
            }
        }
    }
}
