package server.handling.command;

import server.handling.Response;
import server.handling.data.format.MusicBand;

import java.util.Iterator;
import java.util.Stack;

public class RemoveByIdCommand implements Command {
    private Stack<MusicBand> mystack;

    public RemoveByIdCommand(Stack<MusicBand> mystack) {
        this.mystack = mystack;
    }

    @Override
    public void execute(String args, MusicBand musicBand, Response response) {
        Integer id = Integer.valueOf(args);
        Iterator<MusicBand> iterator = mystack.iterator();
        boolean not_deleted = true;

        while (iterator.hasNext()){
            MusicBand band = iterator.next();
            Integer bandid = Integer.valueOf(band.getId().toString());
            if (bandid.equals(id)){
                String note = "Удален элемент - "+band.toString();
                System.out.println(note);
                response.addNote(note);
                iterator.remove();
                not_deleted = false;
                note = "Элемент с данным описанием был удален.";
                System.out.println(note);
                response.addNote(note);
            }
        }
        if (not_deleted){
            String note = "Элемента с таким описанием не существует.";
            System.out.println(note);
            response.addNote(note);
        }
    }
}
