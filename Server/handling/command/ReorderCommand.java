package handling.command;

import format.CommandAccomplishment;
import format.MusicBand;
import format.Response;

import java.util.Collections;
import java.util.Stack;

public class ReorderCommand implements Command {
    private final Stack<MusicBand> mystack;

    public ReorderCommand(Stack<MusicBand> mystack) {
        this.mystack = mystack;
    }

    @Override
    public Response execute(String args, MusicBand musicBand) {
        Collections.reverse(mystack);
        String note = "Коллекция отсортирована в обратном порядке.";
        System.out.println(note);
        return new Response(CommandAccomplishment.SUCCESSFUL,mystack);
    }
}
