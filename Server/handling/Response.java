package server.handling;

import server.handling.data.format.MusicBand;

import java.util.Stack;

public class Response {
    String string;
    Stack<MusicBand> mystack;

    public Response(String message, Stack<MusicBand> list) {
        this.string = message;
        this.mystack = list;
    }

    public Response() {
        this.string = "";
        this.mystack = new Stack<MusicBand>();
    }

    public void addNote(String note) {
        if (string != null) {
            this.string += note + "\n";
        } else {
            this.string += "\n";
        }
    }

    public void addMusicBand(MusicBand band) {
        mystack.add(band);
    }

    public void addMusicBand(Stack<MusicBand> stack) {
        mystack.addAll(stack);
    }

    public String getString() {
        return string;
    }

    public void setString(String string) {
        this.string = string;
    }

    public Stack<MusicBand> getMystack() {
        return mystack;
    }

    public void setMystack(Stack<MusicBand> mystack) {
        this.mystack = mystack;
    }
}
