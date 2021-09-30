package Client;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.*;


public class Client {
    /*
    IO-TCP-Client
     */
    private static int serverPort;
    private static InetAddress serverAddress;
    private static InetSocketAddress inetSocketAddress;
    //private static InputStream inputStream;
    //private static OutputStream outputStream;

    public static void main(String[] args) {
        System.out.println("Клиент запущен...");
        try (Socket socket = new Socket()) {  //Создаю сокет для взаимодействия с сервером
            socket.setSoTimeout(1000);  //Устанавливаю тай  маут в 5 секунд
            setServerSocketAddress(args);
            socket.connect(inetSocketAddress);  //Подключение к серверному приложению
            System.out.println("Соединение установлено...");

            try (InputStream inputStream = socket.getInputStream(); OutputStream outputStream = socket.getOutputStream()){
                outputStream.write(new byte[]{123, 123});   //Запись байтового массива в байтовый поток вывода
                outputStream.flush();   //Очистка кэша
                System.out.println("Данные отправлены...");

                readInputStream(inputStream);
            }

            System.out.println("Завершение работы...");
        } catch (ConnectException e) {
            System.out.println("Error: Server Application is is not responding. Connection failed.");
        } catch (SocketTimeoutException e) {
            System.out.println("Error: Socket timeout.");
            e.printStackTrace();
        } catch (UnknownHostException e) {
            System.out.println("Error: Unknown host.");
        } catch (IOException e) {
            System.out.println("Error: IO exception.");
            e.printStackTrace();
        }
    }

    private static void setServerSocketAddress(String[] args) throws UnknownHostException {
        if (args.length != 0) {
            serverAddress = InetAddress.getByName(args[0].split(":")[0]);
            serverPort = Integer.parseInt(args[0].split(":")[1]);
        } else {
            serverAddress = InetAddress.getLocalHost();
            serverPort = 40747;
        }
        inetSocketAddress = new InetSocketAddress(serverAddress, serverPort);
        System.out.println("Подключение к серверу: "+serverAddress.getHostAddress()+":"+serverPort);
    }
    private static void readInputStream(InputStream inputStream) throws IOException{
        System.out.print("Полученные данные:\t");
        int c;
        while ((c=inputStream.read())!=-1){
            System.out.print(c+" ");
        }
        System.out.println();
    }
}
