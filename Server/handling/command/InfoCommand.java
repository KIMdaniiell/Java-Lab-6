package handling.command;

import format.CommandAccomplishment;
import format.MusicBand;
import format.Response;

import java.time.LocalDate;
import java.util.Stack;

public class InfoCommand implements Command {
    private final Stack<MusicBand> mystack;

    public InfoCommand(Stack<MusicBand> mystack) {
        this.mystack = mystack;
    }

    @Override
    public Response execute(String args, MusicBand musicBand) {
        String note = "Тип : " + mystack.getClass().getName();
        System.out.println(note);
        note = "Количество элементов : " + mystack.size();
        System.out.println(note);
        note = "Дата инициализации : " + LocalDate.now();
        System.out.println(note);
        for (MusicBand band : mystack) {
            System.out.println("\t-" + band.toString());
        }
        return new Response(CommandAccomplishment.SUCCESSFUL, mystack);
    }
}
