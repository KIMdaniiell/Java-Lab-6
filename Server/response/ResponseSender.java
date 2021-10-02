package server.response;

import server.connection.Connection;
import server.handling.Response;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class ResponseSender {

    private static final int bufferSize = 4096;
    private Response response;
    private final SocketChannel socketChannel;

    public ResponseSender(Response response, Connection connection) throws IOException {
        this.response = response;
        this.socketChannel = connection.getSocketChannel();
    }

    public void send() throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
        objectOutputStream.writeObject(response);

        ByteBuffer outBuffer = ByteBuffer.wrap(byteArrayOutputStream.toByteArray());
        while (outBuffer.hasRemaining()) {
            socketChannel.write(outBuffer);
        }
        outBuffer.clear();
        response = new Response();
    }
}
