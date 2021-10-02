package server.recieve;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class RequestReciever {

    private static final int bufferSize = 4096;
    private ByteBuffer byteBuffer;
    private SocketChannel socketChannel;

    public RequestReciever(SocketChannel socketChannel) {
        this.socketChannel = socketChannel;
        this.byteBuffer = ByteBuffer.allocate(bufferSize);
    }

    public RequestWrapper getRequestWrapper() throws IOException, ClassNotFoundException {
        int available = 0;
        int wasavailable = 0;

        byteBuffer.clear();
        do {
            wasavailable = available;
            available = socketChannel.read(byteBuffer);
        } while (byteBuffer.hasRemaining() && (available > 0 || wasavailable == 0));
        byteBuffer.flip();

        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byteBuffer.slice().array());
        ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);

        RequestWrapper requestWrapper = (RequestWrapper) objectInputStream.readObject();
        return requestWrapper;
    }
}
