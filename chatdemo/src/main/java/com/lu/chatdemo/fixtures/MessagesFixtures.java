package com.lu.chatdemo.fixtures;

import com.lu.chatdemo.Model.Message;
import com.lu.chatdemo.Model.User;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/*
 * Created by troy379 on 12.12.16.
 */
public final class MessagesFixtures extends FixturesData {
    private MessagesFixtures() {
        throw new AssertionError();
    }

    public static Message getImageMessage() {
        Calendar calendar = Calendar.getInstance();

        Message message = new Message();
        message.setCreatedAt(calendar.getTime());
        message.setId(getRandomId());
        message.setImageUrl(getRandomImage());
        message.setUser(getUser());
        return message;
    }


    public static Message getTextMessage() {
        return getTextMessage(getRandomMessage());
    }

    public static Message getTextMessage(String text) {
        Calendar calendar = Calendar.getInstance();

        Message message = new Message();
        message.setCreatedAt(calendar.getTime());
        message.setUser(getUser());
        message.setId(getRandomId());
        message.setText(text);
        return message;
    }

    public static ArrayList<Message> getMessages(Date startDate) {
        ArrayList<Message> messages = new ArrayList<>();
        for (int i = 0; i < 10/*days count*/; i++) {
            int countPerDay = rnd.nextInt(5) + 1;

            for (int j = 0; j < countPerDay; j++) {
                Message message;
                if (i % 2 == 0 && j % 3 == 0) {
                    message = getImageMessage();
                } else {
                    message = getTextMessage();
                }

                Calendar calendar = Calendar.getInstance();
                if (startDate != null) calendar.setTime(startDate);
                calendar.add(Calendar.DAY_OF_MONTH, -(i * i + 1));

                message.setCreatedAt(calendar.getTime());
                messages.add(message);
            }
        }
        return messages;
    }

    private static User getUser() {
        boolean even = rnd.nextBoolean();
        return new User(
                even ? 0L : 1L,
                even ? names.get(0) : names.get(1),
                even ? avatars.get(0) : avatars.get(1),
                1,
                true);
    }
}
