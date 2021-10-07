package handling.command;

import format.*;

import java.util.Stack;

public class AddCommand implements Command {
    private final Stack<MusicBand> mystack;

    public AddCommand(Stack<MusicBand> mystack) {
        this.mystack = mystack;
    }

    @Override
    public Response execute(String args, MusicBand musicBand) {
        MusicBand myband = musicBand;
        myband.setId(mystack);
        mystack.push(myband);
        System.out.println("Был добавлен новый объект - " + myband.toString());
        return new Response(CommandAccomplishment.SUCCESSFUL,mystack);
    }
}
