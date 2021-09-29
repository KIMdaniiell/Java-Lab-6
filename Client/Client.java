package Client;

import java.io.IOException;
import java.io.OutputStream;
import java.net.*;

public class Client {
    /*
    IO-TCP-Client
     */
    private static int serverport;
    private static InetAddress serveraddress;
    private static InetSocketAddress inetSocketAddress;

    public static void main(String[] args) {
        System.out.println("Клиент запущен...");
        try (Socket socket = new Socket()) {  //Создаю сокет для взаимодействия с сервером
            socket.setSoTimeout(5000);  //Устанавливаю таймаут в 5 секунд
            setServerSocketAddress(args);
            socket.connect(inetSocketAddress);  //Подключение к серверному приложению
            System.out.println("Соединение установлено...");

            OutputStream outputStream = socket.getOutputStream();
            outputStream.write(new byte[]{123, 123});   //Запись байтового массива в байтовый поток вывода
            outputStream.flush();   //Очистка кэша
            System.out.println("Данные отправлены...");

            System.out.println("Завершение работы...");
        } catch (SocketTimeoutException e) {
            System.out.println("Error: Socket timeout.");
        } catch (UnknownHostException e) {
            System.out.println("Error: Unknown host.");
        } catch (IOException e) {
            System.out.println("Error: IO exception.");
            e.printStackTrace();
        }
    }

    private static void setServerSocketAddress(String[] args) throws UnknownHostException {
        if (args.length != 0) {
            serveraddress = InetAddress.getByName(args[0].split(":")[0]);
            serverport = Integer.parseInt(args[0].split(":")[1]);
        } else {
            serveraddress = InetAddress.getLocalHost();
            serverport = 40747;
        }
        inetSocketAddress = new InetSocketAddress(serveraddress, serverport);
        System.out.println("Выбран адрес: "+serveraddress.getHostAddress()+":"+serverport);
    }
}
