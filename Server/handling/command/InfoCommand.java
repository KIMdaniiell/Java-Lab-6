package server.handling.command;

import server.handling.Response;
import server.handling.data.format.MusicBand;

import java.time.LocalDate;
import java.util.Stack;

public class InfoCommand implements Command {
    private final Stack<MusicBand> mystack;

    public InfoCommand(Stack<MusicBand> mystack) {
        this.mystack = mystack;
    }

    @Override
    public void execute(String args, MusicBand musicBand, Response response) {
        String note = "Тип : " + mystack.getClass().getName();
        System.out.println(note);
        response.addNote(note);
        note = "Количество элементов : " + mystack.size();
        System.out.println(note);
        response.addNote(note);
        note = "Дата инициализации : " + LocalDate.now();
        System.out.println(note);
        response.addNote(note);
        for (MusicBand band : mystack) {
            System.out.println("\t-" + band.toString());
        }
    }
}
