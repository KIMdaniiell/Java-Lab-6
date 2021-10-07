package handling.command;

import format.CommandAccomplishment;
import format.MusicBand;
import format.Response;

import java.util.Iterator;
import java.util.Stack;

public class RemoveGreaterCommand implements Command {
    private final Stack<MusicBand> mystack;

    public RemoveGreaterCommand(Stack<MusicBand> mystack) {
        this.mystack = mystack;
    }

    @Override
    public Response execute(String args, MusicBand musicBand) {
        Stack<MusicBand> stackofgreater = new Stack<>();
        MusicBand someband = musicBand;

        Iterator<MusicBand> iterator = mystack.iterator();
        while (iterator.hasNext()) {
            if (iterator.next().compareTo(someband) > 0) {
                iterator.remove();
            }
        }
        return new Response(CommandAccomplishment.SUCCESSFUL,mystack);
    }
}
