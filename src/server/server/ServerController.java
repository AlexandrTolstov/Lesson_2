package server.server;

import server.client.ClientController;
import server.client.ClientGUI;

import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

public class ServerController {
    private static Repository repository;
    private static ServerView ui;
    public static final String LOG_PATH = "src/server/log.txt";
    boolean work;

    //Клиенты
    List<ClientController> clients;

    public ServerController(ServerView ui, Repository repository){
        clients = new ArrayList<>();
        this.ui = ui;
        this.repository = repository;
        //ui.showMessage(getHistory());
    }

    public boolean start(){
        if (work){
            ui.showMessage("Сервер уже был запущен");
        } else {
            work = true;
            ui.showMessage("Сервер запущен!");
        }
        return work;
    }

    public boolean stop(){
        if (!work){
            ui.showMessage("Сервер уже был остановлен");
        } else {
            work = false;
            while (!clients.isEmpty()){
                disconnectUser(clients.get(clients.size()-1));
            }
            ui.showMessage("Сервер остановлен!");
        }
        return work;
    }

    /**
     * Отключение клиента
     * @param client
     */
    public void disconnectUser(ClientController client){

        clients.remove(client);
        if (client != null){
            client.disconnectedFromServer();
        }
    }

    /**
     * Подключение клиента
     * @param client
     * @return
     */
    public boolean connectUser(ClientController client){
        if (!work){
            return false;
        }
        clients.add(client);
        return true;
    }

    /**
     * Отправка сообщения всем клиентам
     * @param text
     */
    public void message(String text){
        if (!work){
            return;
        }
        repository.save(text);
        answerAll(text);
    }

    /**
     * Ответ всем клиентам
     * @param text
     */
    private void answerAll(String text){
        for (ClientController clientController: clients){
            clientController.answerFromServer(text);
        }
    }

    /**
     * Получение всей истории
     * @return
     */
    public String getHistory(){
        return repository.read();
    }
}
