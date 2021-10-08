package handling.command;

import format.CommandAccomplishment;
import format.MusicBand;
import format.Response;
import parser.Parser;

import java.util.Stack;

public class SaveCommand implements Command {
    private final Stack<MusicBand> mystack;

    public SaveCommand(Stack<MusicBand> mystack) {
        this.mystack = mystack;
    }

    @Override
    public Response execute(String args, MusicBand musicBand) {
        Parser.deserialize(args, mystack);
        String note = "Сохранение ...";
        System.out.println(note);
        return new Response(CommandAccomplishment.SUCCESSFUL, mystack);
    }
}
