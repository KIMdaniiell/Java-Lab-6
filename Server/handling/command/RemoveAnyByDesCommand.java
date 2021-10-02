package server.handling.command;

import server.handling.Response;
import server.handling.data.format.MusicBand;

import java.util.Iterator;
import java.util.Stack;

public class RemoveAnyByDesCommand implements Command {
    private Stack<MusicBand> mystack;

    public RemoveAnyByDesCommand(Stack<MusicBand> mystack) {
        this.mystack = mystack;
    }

    @Override
    public void execute(String args, MusicBand musicBand, Response response) {

        String description = args;
        Iterator<MusicBand> iterator = mystack.iterator();
        boolean not_deleted = true;

        while (iterator.hasNext()){
            MusicBand band = iterator.next();
            if (not_deleted&&band.getDescription().equals(description)){
                iterator.remove();
                not_deleted = false;
                String note = "Элемент с данным описанием был удален.";
                System.out.println(note);
                response.addNote(note);
            }
        }
        if (not_deleted){
            String note =("Элемента с таким описанием не существует.");
            System.out.println(note);
            response.addNote(note);
        }
    }
}