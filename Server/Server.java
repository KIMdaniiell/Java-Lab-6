package server;

import java.io.IOException;
import java.net.BindException;
import java.net.InetSocketAddress;
import java.net.SocketTimeoutException;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;


public class Server {
    private static final int bufferSize = 4096;
    /*
    NIO-TCP-Server
     */
    private static int port = 40747;

    public static void main(String[] args) {
        System.out.println("Cервер запущен...");
        setPort(args);  //Выбираю порт

        try (ServerSocketChannel serverSocketChannel = ServerSocketChannel.open()) {
            InetSocketAddress inetSocketAddress = new InetSocketAddress(port);
            serverSocketChannel.bind(inetSocketAddress);   //Привязал фабрику сокетов к порту

            while (true) {
                System.out.println("Ожидание подключения...");
                /*
                //Если нужен будет тайм аут на сервере:
                serverSocketChannel.socket().setSoTimeout(5000);    //Через класс ServerSocket задал таймаут
                SocketChannel socketChannel = serverSocketChannel.socket().accept().getChannel(); //Получиль SocketChannel для взаимодействия с клиентом
                */
                ByteBuffer byteBuffer = ByteBuffer.allocate(bufferSize);
                try (SocketChannel socketChannel = serverSocketChannel.accept()) {
                    //Получиль SocketChannel для взаимодействия с клиентским приложением
                    socketChannel.configureBlocking(false); //Перевод канала в неблокирующий режим
                    System.out.println("Соединение установлено...");

                    byteBuffer.clear();
                    int c = socketChannel.read(byteBuffer);
                    System.out.print(c + " ");
                    while (c < 1) {
                        //Читаю байты из канала в буфер
                        c = socketChannel.read(byteBuffer);
                        System.out.print(c + " ");
                        if (c == -1) {
                            System.exit(-1);
                        }
                    }
                    System.out.println("\n" + byteBuffer.position() + "\t" + byteBuffer.limit());
                    byteBuffer.flip();  //Подготавливаю буфер к чтению
                    System.out.println("Данные получены...");

                    readbytebuffer(byteBuffer);

                    socketChannel.write(generateByteBuffer(byteBuffer));
                    System.out.println("Ответ отправлен...");
                    System.out.println("Завершение подключения...");
                }
            }
            //System.out.println("Завершение работы сервера...");
        } catch (BindException e) {
            System.out.println("Error: The chosen port is already in use.");
        } catch (SocketTimeoutException e) {
            System.out.println("Error: Socket timeout.");
        } catch (IllegalArgumentException e) {
            System.out.println("Error: Port parameter is outside the specified range of valid port values.");
        } catch (IOException e) {
            System.out.println("Error: IO exception.");
            e.printStackTrace();
        }
    }

    private static void setPort(String[] args) {
        if (args.length != 0) {
            try {
                port = Integer.parseInt(args[0]);
            } catch (NumberFormatException e) {
                System.out.println("Error: Invalid format of input arguments. Port setting failed. Default value is chosen (40747) .");
            }
        }
        System.out.println("Выбран порт: " + port);
    }

    private static void readbytebuffer(ByteBuffer byteBuffer) {
        System.out.print("Полученные данные:\t");
        while (byteBuffer.hasRemaining()) {
            System.out.print(byteBuffer.get() + " ");
        }
        System.out.println();
        byteBuffer.rewind();
    }

    private static ByteBuffer generateByteBuffer(ByteBuffer inputByteBuffer) {
        ByteBuffer outputByteBuffer = ByteBuffer.allocate(4096);
        byte b;
        while (inputByteBuffer.hasRemaining()) {
            b = (byte) (inputByteBuffer.get() + 1);
            outputByteBuffer.put(b);
        }
        inputByteBuffer.rewind();
        outputByteBuffer.flip();
        return outputByteBuffer;
    }
}
