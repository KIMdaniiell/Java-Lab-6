package response;

import connection.Connection;
import format.MusicBand;
import format.Response;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.Comparator;

public class ResponseSender {

    private static final int bufferSize = 4096;
    private Response response;
    private SocketChannel socketChannel;

    public ResponseSender() {
    }

    public void send(Response response, SocketChannel socketChannel) throws ClientClosedExeption,IOException {
        this.response = response;
        this.socketChannel = socketChannel;


        response.getMystack().sort(new Comparator<MusicBand>() {
            @Override
            public int compare(MusicBand o1, MusicBand o2) {
                try {
                    int o1size;
                    int o2size;
                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    ObjectOutputStream oos = new ObjectOutputStream(baos);
                    oos.writeObject(o1);
                    o1size = baos.size();
                    baos = new ByteArrayOutputStream();
                    oos = new ObjectOutputStream(baos);
                    oos.writeObject(o2);
                    o2size = baos.size();
                    return o1size - o2size;
                } catch (IOException e) {
                    System.out.println("Error: Unable to sort collection.");
                }
                return 0;
            }
        });




                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
        if (socketChannel.isConnected()) {
            objectOutputStream.writeObject(response);
        } else {
            throw new ClientClosedExeption("");
        }
        ByteBuffer outBuffer = ByteBuffer.wrap(byteArrayOutputStream.toByteArray());
        while (outBuffer.hasRemaining()) {
            socketChannel.write(outBuffer);
        }
        outBuffer.clear();
    }
}
