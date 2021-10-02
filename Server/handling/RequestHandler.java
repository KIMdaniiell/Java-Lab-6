package server.handling;

import server.handling.command.*;
import server.handling.data.format.MusicBand;

import java.util.HashMap;
import java.util.Stack;

public class RequestHandler {
    private final HashMap<String, Command> commandHashMap;
    private String data_path;
    private Stack<MusicBand> mystack;
    private Response response;

    public RequestHandler(String data_path, Stack<MusicBand> collection) {
        this.data_path = data_path;
        response = new Response();
        commandHashMap = new HashMap<>();
        mystack = collection;
        initHashMap();
    }

    public void execute(String name, String args, MusicBand band) {
        if (commandHashMap.containsKey(name)) {
            commandHashMap.get(name).execute(args, band, response);
        } else {
            System.out.println("no such command");
        }
    }

    public void initHashMap() {

        commandHashMap.put("help", new HelpCommand(mystack));
        commandHashMap.put("info", new InfoCommand(mystack));
        commandHashMap.put("show", new ShowCommand(mystack));
        commandHashMap.put("clear", new ClearCommand(mystack));
        //commandHashMap.put("save", new SaveCommand(mystack));
        commandHashMap.put("exit", new ExitCommand(mystack));
        commandHashMap.put("reorder", new ReorderCommand(mystack));
        commandHashMap.put("sort", new SortCommand(mystack));
        commandHashMap.put("print_field_descending_description", new PrintDescriptionCommand(mystack));
        commandHashMap.put("filter_less_than_genre", new FilterCommand(mystack));
        commandHashMap.put("add", new AddCommand(mystack));
        commandHashMap.put("remove_greater", new RemoveGreaterCommand(mystack));
        commandHashMap.put("update", new UpdateCommand(mystack));
        commandHashMap.put("remove_any_by_description", new RemoveAnyByDesCommand(mystack));
        commandHashMap.put("remove_by_id", new RemoveByIdCommand(mystack));
        //commandHashMap.put("execute_script", new ScriptCommand(mystack));

    }
}
