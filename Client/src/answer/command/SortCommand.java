package answer.command;

import format.CommandAccomplishment;
import format.MusicBand;

import java.util.Stack;

public class SortCommand implements Command {
    @Override
    public void execute(Stack<MusicBand> mystack, CommandAccomplishment commandAccomplishment) {
        if (commandAccomplishment == CommandAccomplishment.SUCCESSFUL) {
            String note = "Коллекция отсортирована.";
            System.out.println(note);
        }
    }
}
