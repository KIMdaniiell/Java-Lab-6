package format;

import java.io.Serializable;
import java.util.Stack;

public class Response implements Serializable {
    CommandAccomplishment status;
    Stack<MusicBand> mystack;

    public Response(CommandAccomplishment status,Stack<MusicBand> stack){
        this.status = status;
        mystack = stack;

    }

    public Stack<MusicBand> getMystack() {
        return mystack;
    }

    public void setMystack(Stack<MusicBand> mystack) {
        this.mystack = mystack;
    }

    public CommandAccomplishment getStatus() {
        return status;
    }

    public void setStatus(CommandAccomplishment status) {
        this.status = status;
    }
}


