package server.handling.command;

import server.handling.Response;
import server.handling.data.format.MusicBand;
import server.handling.exceptions.InvalidCommandArgumentExeption;

import java.util.Collections;
import java.util.Stack;

public class UpdateCommand implements Command {
    private Stack<MusicBand> mystack;

    public UpdateCommand(Stack<MusicBand> mystack) {
        this.mystack = mystack;
    }

    @Override
    public void execute(String args, MusicBand musicBand, Response response) {
        Integer curentid = Integer.valueOf(args);
        boolean has_this_id = false;
        for (MusicBand band: mystack){
            if (band.getId().equals(curentid)){
                has_this_id = true;
            }
        }
        if (has_this_id==false){
            String note = "Некорректный ввод параметра ID. Элемента с таким ID не существует.";
            System.out.println(note);
            response.addNote(note);
        } else {
            MusicBand oldband = new MusicBand();
            MusicBand newband = musicBand;
            Integer id = curentid;
            newband.saveID(id);
            for (MusicBand band : mystack) {
                if (band.getId().equals(id)) {
                    oldband = band;
                }
            }
            Collections.replaceAll(mystack, oldband, newband);
            String note = "Элемент с данным ID был обновлен.";
            System.out.println(note);
            response.addNote(note);
        }

    }
}
