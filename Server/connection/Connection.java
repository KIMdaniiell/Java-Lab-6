package server.connection;

import java.io.IOException;
import java.net.BindException;
import java.net.InetSocketAddress;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

public class Connection {

    private static int port = 40747;
    private ServerSocketChannel serverSocketChannel;

    public Connection () {
        try{
            serverSocketChannel = openServerSocketChannel();
        } catch (BindException e) {
            System.out.println("Error: The chosen port is already in use.");
        } catch (IllegalArgumentException e) {
            System.out.println("Error: Port parameter is outside the specified range of valid port values.");
        } catch (IOException e){
            System.out.println("Error: IO exception. Connection failed.");
            e.printStackTrace();
        }
    }

    private ServerSocketChannel openServerSocketChannel() throws IOException {
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.configureBlocking(false);
        InetSocketAddress inetSocketAddress = new InetSocketAddress(port);
        serverSocketChannel.bind(inetSocketAddress);
        return serverSocketChannel;
    }

    public SocketChannel getSocketChannel (ServerSocketChannel serverSocketChannel) throws IOException {
        SocketChannel socketChannel;
        do {
            socketChannel = serverSocketChannel.accept();
            if (socketChannel != null) {
                socketChannel.configureBlocking(false);
            }
        } while (socketChannel == null);
        return socketChannel;
    }
}
