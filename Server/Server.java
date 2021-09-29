package Server;

import java.io.IOException;
import java.net.BindException;
import java.net.InetSocketAddress;
import java.net.SocketTimeoutException;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

public class Server {
    private static int port;

    public static void main(String[] args) {
        System.out.println("Cервер запущен...");
        setPort(args);  //Выбираю порт

        try (ServerSocketChannel serverSocketChannel = ServerSocketChannel.open()) {
            InetSocketAddress inetSocketAddress = new InetSocketAddress(port);  //Привязал фабрику сокетов к порту
            serverSocketChannel.bind(inetSocketAddress);
            /*
            //Если нужен будет тайм аут на сервере:
            serverSocketChannel.socket().setSoTimeout(5000);    //Через класс ServerSocket задал таймаут
            SocketChannel socketChannel = serverSocketChannel.socket().accept().getChannel(); //Получиль SocketChannel для взаимодействия с клиентом
            */
            SocketChannel socketChannel = serverSocketChannel.accept();     //Получиль SocketChannel для взаимодействия с клиентом
            socketChannel.configureBlocking(false); //Перевод канала в неблокирующий режим
            System.out.println(socketChannel.isBlocking());
            System.out.println("Соединение установлено...");

            ByteBuffer byteBuffer = ByteBuffer.allocate(4096);
            socketChannel.read(byteBuffer); //Читаю байты из канала в буфер
            byteBuffer.flip();  //Подготавливаю буфер к чтению
            System.out.println("Данные получены...");

            readbytebuffer(byteBuffer);

            socketChannel.close();  //Освобождаю сокет
            System.out.println("Завершение работы сервера...");
        } catch (BindException e) {
            System.out.println("Error: The chosen port is already in use.");
        } catch (SocketTimeoutException e) {
            System.out.println("Error: Socket timeout.");
        } catch (IllegalArgumentException e) {
            System.out.println("Error: Port parameter is outside the specified range of valid port values.");
        } catch (IOException e) {
            System.out.println("Error: IO exception.");
        }
    }

    private static void setPort(String[] args) {
        if (args.length != 0) {
            port = Integer.parseInt(args[0]);
        } else {
            port = 40747;
        }
    }

    private static void readbytebuffer(ByteBuffer byteBuffer) {
        System.out.print("Полученные данные:\t");
        while (byteBuffer.hasRemaining()) {
            System.out.print(byteBuffer.get() + " ");
        }
        System.out.println();
    }
}
