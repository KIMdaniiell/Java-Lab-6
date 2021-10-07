package answer;

import answer.command.*;
import format.CommandAccomplishment;
import format.MusicBand;
import format.RequestWrapper;
import format.Response;

import java.util.HashMap;
import java.util.Stack;

public class AnswerHandler {
    private Response response;
    private RequestWrapper requestWrapper;
    private Stack<MusicBand> mystack;
    private CommandAccomplishment commandAccomplishment;
    private final HashMap<String, Command> commandHashMap;


    public AnswerHandler() {
        commandHashMap = new HashMap<>();
        initHashMap();
    }

    public void readAnswer(Response response, RequestWrapper requestWrapper) {
        this.response = response;
        this.requestWrapper = requestWrapper;
        String commandWord = requestWrapper.getCommand();
        mystack = response.getMystack();
        commandAccomplishment = response.getStatus();
        commandHashMap.get(commandWord).execute(mystack,commandAccomplishment);
    }

    public void initHashMap() {

        commandHashMap.put("info", new InfoCommand());
        commandHashMap.put("show", new ShowCommand());
        commandHashMap.put("clear", new ClearCommand());
        commandHashMap.put("reorder", new ReorderCommand());
        commandHashMap.put("sort", new SortCommand());
        commandHashMap.put("print_field_descending_description", new PrintDescriptionCommand());
        commandHashMap.put("filter_less_than_genre", new FilterCommand());
        commandHashMap.put("add", new AddCommand());
        commandHashMap.put("remove_greater", new RemoveGreaterCommand());
        commandHashMap.put("update", new UpdateCommand());
        commandHashMap.put("remove_any_by_description", new RemoveAnyByDesCommand());
        commandHashMap.put("remove_by_id", new RemoveByIdCommand());

        commandHashMap.put("exit", new ExitCommand());
        commandHashMap.put("help", new HelpCommand());
        //commandHashMap.put("execute_script", new ScriptCommand());
        //commandHashMap.put("save", new SaveCommand());

    }
}
