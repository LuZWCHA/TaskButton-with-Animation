package com.lu.indexpagedemo.view.simpleviews.fixtures;



import com.lu.indexpagedemo.base.Tools.MyIfo;
import com.lu.indexpagedemo.base.mvp.ChatBaseActivity.Model.Dialog;
import com.lu.indexpagedemo.base.mvp.ChatBaseActivity.Model.Message;
import com.lu.indexpagedemo.base.mvp.ChatBaseActivity.Model.User;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/*
 * Created by Anton Bevza on 07.09.16.
 */
public final class DialogsFixtures extends FixturesData {
    private DialogsFixtures() {
        throw new AssertionError();
    }

    public static ArrayList<Dialog> getDialogs() {
        ArrayList<Dialog> chats = new ArrayList<>();

        for (int i = 0; i < 20; i++) {
            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.DAY_OF_MONTH, -(i * i));
            calendar.add(Calendar.MINUTE, -(i * i));

            chats.add(getDialog(i, calendar.getTime()));
        }

        return chats;
    }

    public static ArrayList<Dialog> getDialogs(int num){
        ArrayList<Dialog> chats = new ArrayList<>();

        for (int i = 0; i < num; ++i){
            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.DAY_OF_MONTH, -(i * i));
            calendar.add(Calendar.MINUTE, -(i * i));

            chats.add(getDialog(i, calendar.getTime()));
        }

        return chats;
    }

    private static Dialog getDialog(int i, Date lastMessageCreatedAt) {
        ArrayList<User> users = getUsers();
        return new Dialog(
                getRandomId(),
                users.size() > 1 ? groupChatTitles.get(users.size() - 2) : users.get(0).getName(),
                users.size() > 1 ? groupChatImages.get(users.size() - 2) : getRandomAvatar(),
                users,
                getMessage(lastMessageCreatedAt),
                i < 3 ? 3 - i : 0);
    }

    private static ArrayList<User> getUsers() {
        ArrayList<User> users = new ArrayList<>();
        int usersCount = 1 + rnd.nextInt(4);

        for (int i = 0; i < usersCount; i++) {
            if(i>0)
                users.add(getUser());
            else
                users.add(MyIfo.getMyIfo().getSelf());
        }
        return users;
    }

    public static User getUser() {
        return new User(
                getRandomId(),
                getRandomName(),
                getRandomAvatar(),
                1,
                getRandomBoolean());
    }

    private static Message getMessage(final Date date) {

        Message message = new Message(
                getRandomId(),
                getRandomMessage(),
                getUser(),
                date,
                "");

        return message;
    }
}
