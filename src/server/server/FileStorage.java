package server.server;

import java.io.FileReader;
import java.io.FileWriter;

public class FileStorage implements Repository{
    public static final String LOG_PATH = "src/server/log.txt";

    String logText;

    public FileStorage(){
        logText = read();

    }

    @Override
    public String read() {
        StringBuilder stringBuilder = new StringBuilder();
        try (FileReader reader = new FileReader(LOG_PATH)){
            int c;
            while ((c = reader.read()) != -1){
                stringBuilder.append((char) c);
            }
            stringBuilder.delete(stringBuilder.length()-1, stringBuilder.length());
            return stringBuilder.toString();
        } catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void save(String text){
        logText += text + "\n";
        saveInLog();
    }

    private void saveInLog(){
        try (FileWriter writer = new FileWriter(LOG_PATH, true)){
            writer.write(logText);
            writer.write("\n");
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public String getLog() {
        return read();
    }
}
