import connection.Connection;
import format.MusicBand;
import format.RequestWrapper;
import format.Response;
import handling.RequestHandler;
import parser.Parser;
import recieve.RequestReciever;
import response.ClientClosedExeption;
import response.ResponseSender;

import java.io.IOException;
import java.net.BindException;
import java.nio.channels.SocketChannel;
import java.util.Stack;

public class ServerMain {
    /*
     * This server is based on TCP protocol.
     * It receives RequestWrapper instances and
     * sends Response as a result.
     */
    public static void main(String[] args) {
        final int port = 40748;
        String datapath = "inputdata.xml";

        Stack<MusicBand> collection = Parser.serialize(datapath);

        RequestHandler requestHandler = new RequestHandler(datapath, collection);
        RequestReciever requestReciever;
        ResponseSender responseSender = new ResponseSender();

        try (Connection connection = new Connection(port)) {
            while (true) {
                try (SocketChannel socketChannel = connection.getSocketChannel()) {

                    requestReciever = new RequestReciever(socketChannel);

                    RequestWrapper requestWrapper = requestReciever.getRequestWrapper();
                    Response response = requestHandler
                            .execute(requestWrapper.getCommand(), requestWrapper.getArg(), requestWrapper.getMusicBand());
                    responseSender.send(response, socketChannel);

                } catch (ClientClosedExeption e) {
                    System.out.println("Error: Client is not available.");
                    //e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    System.out.println("Error: Deserialization failed.");
                    //e.printStackTrace();
                } catch (IOException e) {
                    System.out.println("Error: Connection interrupted.");
                    e.printStackTrace();
                }
            }

        } catch (BindException e) {
            System.out.println("Error: The chosen port is already in use.");
            //e.printStackTrace();
        } catch (IllegalArgumentException e) {
            System.out.println("Error: Port parameter is outside the specified range of valid port values.");
            //e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Error: IO exception. Connection failed.");
            e.printStackTrace();
        }
    }
}
