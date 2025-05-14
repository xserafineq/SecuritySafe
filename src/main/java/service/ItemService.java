package service;

import model.SafeItem;
import model.User;

public abstract class ItemService {

    public abstract SafeItem getItem(User user,String title);

}
