package server.client;

/**
 * Интерфейс описывающий абстракцию GUI
 */
public interface ClientView {
    void setClient(ClientController clientController);
    /**
     * Метод для отображения сообщения в GUI
     * @param message текст сообщения
     */
    void showMessage(String message);

    /**
     * Метод отключения от сервера со стороны сервера
     */
    void disconnectedFromServer();
}
