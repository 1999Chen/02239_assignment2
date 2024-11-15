package Server;

import Model.User;

import java.util.HashMap;
import java.io.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

public class AuthService {


    XMLReader xmlReader = new XMLReader();
    List<User> list = xmlReader.readingXML();

    // 存储读取的HashMap


    public AuthService() {

    }

    public boolean Authenticate(String username, String password) {
        if (list != null) {
            System.out.println("文件中读取成功：");
            return list.stream().anyMatch(user -> username.equals(user.getUsername())) && list.stream().anyMatch(user -> username.equals(user.getUsername()));

        } else {
            System.out.println("读取失败或文件不存在。");
        }
        return false;
    }



    // 读取用户数据


}
