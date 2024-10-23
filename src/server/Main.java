package server;

import server.client.ClientController;
import server.client.ClientGUI;
import server.client.ClientView;
import server.server.*;

public class Main {
    public static void main(String[] args) {

        ServerView view = new SwingUI();

        Repository filStorage = new FileStorage();
        ServerController server = new ServerController(view, filStorage);
        view.setController(server);

        ClientController clientController1 = new ClientController();
        ClientController clientController2 = new ClientController();
        ClientView clientView1 = new ClientGUI();
        ClientView clientView2 = new ClientGUI();
        clientView1.setClient(clientController1);
        clientView2.setClient(clientController2);

        clientController1.setClientView(clientView1);
        clientController2.setClientView(clientView2);
        clientController1.setServer(server);
        clientController2.setServer(server);
    }
}
