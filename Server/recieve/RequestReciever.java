package recieve;

import format.RequestWrapper;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class RequestReciever {

    private static final int bufferSize = 4096;
    private final ByteBuffer byteBuffer;
    private final SocketChannel socketChannel;

    public RequestReciever(SocketChannel socketChannel) {
        this.socketChannel = socketChannel;
        this.byteBuffer = ByteBuffer.allocate(bufferSize);
    }

    public RequestWrapper getRequestWrapper() throws IOException, ClassNotFoundException {

        byteBuffer.clear();

        int c = socketChannel.read(byteBuffer);
        while (c < 1){
            c = socketChannel.read(byteBuffer);
            if (c== -1) {
                System.out.println("\nError: Client socket was closed");
                break;
            }
        }
        byteBuffer.flip();
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byteBuffer.array());
        ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
        return (RequestWrapper) objectInputStream.readObject();


    }
}
