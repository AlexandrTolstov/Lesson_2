package server.server;

public interface ServerView {
    void showMessage(String text);
    boolean disconnect();
    boolean connect();
    void setController(ServerController serverController);

}
