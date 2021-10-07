package console;

import connection.Connector;
import format.MusicBand;
import format.RequestWrapper;

import java.io.IOException;
import java.util.HashSet;
import java.util.Scanner;

public class ConsoleReader {
    private final HashSet<String> commandHashSet = new HashSet<String>();
    Scanner scanner;
    Connector connector;

    public ConsoleReader(Connector connector) {
        scanner = new Scanner(System.in);
        this.connector = connector;
        initHashSet();
        //read();
    }

    private void initHashSet() {
        commandHashSet.add("help");
        commandHashSet.add("info");
        commandHashSet.add("show");
        commandHashSet.add("clear");
        commandHashSet.add("exit");
        commandHashSet.add("reorder");
        commandHashSet.add("sort");
        commandHashSet.add("print_field_descending_description");
        commandHashSet.add("filter_less_than_genre");
        commandHashSet.add("add");
        commandHashSet.add("remove_greater");
        commandHashSet.add("update");
        commandHashSet.add("remove_any_by_description");
        commandHashSet.add("remove_by_id");
        commandHashSet.add("execute_script");
    }

    public RequestWrapper read() throws ClassNotFoundException, IOException {
        RequestWrapper requestWrapper = new RequestWrapper();
        String commandWord = null;
        String commandArgument = null;
        while (!commandHashSet.contains(commandWord)) {
            if (commandWord != null) {
                System.out.println("Error: Unknown command.");
            }
            String commandLine = scanner.nextLine();
            commandWord = commandLine.toLowerCase().strip().split(" ")[0];
            commandArgument = commandLine.toLowerCase().strip().substring(commandWord.length()).strip();
        }
        requestWrapper.setCommand(commandWord);
        requestWrapper.setArg(commandArgument);
        if ((commandWord.equals("add")) || (commandWord.equals("update")) || (commandWord.equals("remove_greater"))) {
            MusicBand musicBand = new BandBuilder().readNewMusicBand(scanner);
            requestWrapper.setMusicBand(musicBand);
        }
        return requestWrapper;
    }
}
