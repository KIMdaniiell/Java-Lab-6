package connection;

import format.RequestWrapper;
import format.Response;

import java.io.*;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;

public class Connector {
    private Socket socket;
    private final int port;

    public Connector(int port) {
        this.port = port;
        //this.socket = connect(port);
    }

    private Socket connect(int port) throws IOException {
        Socket socket = new Socket();
        //socket.setSoTimeout(15000);
        InetAddress serverAddress = InetAddress.getLocalHost();
        InetSocketAddress serverSocketAddress = new InetSocketAddress(serverAddress, port);
        socket.connect(serverSocketAddress);
        return socket;
    }


    public Response sendRequestWrapper(RequestWrapper requestWrapper) throws ClassNotFoundException, IOException {
        this.socket = connect(port);
        OutputStream outputStream = socket.getOutputStream();
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream objOut = new ObjectOutputStream(byteArrayOutputStream);

        objOut.writeObject(requestWrapper);
        objOut.flush();
        byteArrayOutputStream.writeTo(outputStream);
        byteArrayOutputStream.flush();
        outputStream.flush();

        return receiveAnswer();
    }

    public Response receiveAnswer() throws ClassNotFoundException, IOException {
        ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
        return (Response) objectInputStream.readObject();
    }
}
