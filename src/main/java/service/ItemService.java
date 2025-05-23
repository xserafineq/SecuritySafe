package service;

import model.SafeItem;
import model.User;

public abstract class ItemService {

    public abstract SafeItem getItem(User user,String title);

    public abstract void removeItem(int id);

    public abstract void addItem(String s1, String s2, String s3, User user);

    public abstract void addItem(String s1, String s2, User user);

    public abstract void updateItem(String s1, String s2,String s3,User user);

    public abstract void updateItem(String s1, String s2,User user);


    public String hideContent(String content) {
        String hidenContent = "";
        for(int i=0;i<content.toString().length();i++){
            hidenContent += "*";
        }
        return hidenContent;
    }

}
