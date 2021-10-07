package handling.command;

import format.CommandAccomplishment;
import format.MusicBand;
import format.Response;

import java.util.Stack;

public class ExitCommand implements Command {
    private final Stack<MusicBand> mystack;

    public ExitCommand(Stack<MusicBand> mystack) {
        this.mystack = mystack;
    }

    @Override
    public Response execute(String args, MusicBand band) {
        System.out.println("Завершение работы программы ...");
        return new Response(CommandAccomplishment.SUCCESSFUL,mystack);
    }
}
