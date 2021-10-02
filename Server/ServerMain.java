package server;

import server.connection.Connection;
import server.handling.RequestHandler;
import server.handling.data.format.MusicBand;
import server.recieve.RequestReciever;

import java.io.IOException;
import java.util.Scanner;
import java.util.Stack;

public class ServerMain {
    public static void main (String[] args) throws IOException , ClassNotFoundException{
        String datapath = "inputdata.xml";

        Stack<MusicBand> collection = Parser.serialize(datapath);


        Connection connection = new Connection();
        RequestReciever requestReciever = new RequestReciever(connection.getSocketChannel());
        RequestHandler requestHandler = new RequestHandler(datapath,collection);
        requestHandler.execute(requestReciever.getRequestWrapper().getCommand(),requestReciever.getRequestWrapper().getArg(),requestReciever.getRequestWrapper().getMusicBand());

    }
}
