import answer.AnswerHandler;
import answer.command.ExitCommand;
import connection.Connector;
import console.ConsoleReader;
import format.CommandAccomplishment;
import format.MusicBand;
import format.RequestWrapper;
import format.Response;

import java.io.IOException;
import java.util.Stack;


public class ClientMain {
    public static void main(String[] args) {
        final int serverPort = 40748;
        Connector connector = new Connector(serverPort);
        ConsoleReader consoleReader = new ConsoleReader(connector);
        AnswerHandler answerHandler = new AnswerHandler();
        while (true) {
            try {
                RequestWrapper requestWrapper = consoleReader.read();
                Response response;
                if (requestWrapper.getCommand().equals("help"))  {
                    response = new Response(CommandAccomplishment.SUCCESSFUL, new Stack<MusicBand>());
                } else if (requestWrapper.getCommand().equals("exit")){
                    new ExitCommand().execute(new Stack<MusicBand>(),CommandAccomplishment.SUCCESSFUL);
                    break;
                } else {
                    response = connector.sendRequestWrapper(requestWrapper);
                }
                answerHandler.readAnswer(response, requestWrapper);

            } catch (ClassNotFoundException e) {
                System.out.println("Error: Deserialization failed. Try again.");
                //e.printStackTrace();
            } catch (IOException e) {
                System.out.println("Error: IO exception. Connection failed. Try again.");
                //e.printStackTrace();
            }
        }
    }
}
