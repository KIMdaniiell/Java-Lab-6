package handling.command;

import format.CommandAccomplishment;
import format.MusicBand;
import format.Response;

import java.util.Collections;
import java.util.Stack;

public class SortCommand implements Command {
    private final Stack<MusicBand> mystack;

    public SortCommand(Stack<MusicBand> mystack) {
        this.mystack = mystack;
    }

    @Override
    public Response execute(String args, MusicBand musicBand) {
        Collections.sort(mystack);
        String note = "Коллекция отсортирована.";
        System.out.println(note);
        return new Response(CommandAccomplishment.SUCCESSFUL,mystack);
    }
}
