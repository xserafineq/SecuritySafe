package service;

import model.Safe;
import model.SafeItem;
import model.User;

public abstract class ItemService {

    public abstract SafeItem getItem(User user,String title);

    public abstract void removeItem(int id);


    public String hideContent(String content) {
        String hidenContent = "";
        for(int i=0;i<content.toString().length();i++){
            hidenContent += "*";
        }
        return hidenContent;
    }

}
