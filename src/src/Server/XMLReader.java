package Server;
import Model.User;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class XMLReader {

    List<User> list = new ArrayList<>();

        public List<User> readingXML() {
            String filePath = "src/src/users.txt";
            try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    System.out.println(line);
                    String[] parts = line.split("/");
                    User user = new User(parts[0],parts[1]);
                    list.add(user);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return list;
        }

}