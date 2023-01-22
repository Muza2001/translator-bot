package org.example.bot;

import org.example.service.BotService;
import org.example.service.serviceImpl.BotServiceImpl;
import org.example.utils.BotMenu;
import org.example.utils.BotSettings;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.ParseMode;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class AppBot extends TelegramLongPollingBot {

    public static BotServiceImpl botService;

    public static int index = 1;

    // 🇺🇿
    // 🇺🇸
    // 🇷🇺
    public static List<String> arrays = new ArrayList<>();
     static {
        arrays.add("\uD83C\uDDFA\uD83C\uDDFF");
        arrays.add("\uD83C\uDDFA\uD83C\uDDF8");
        arrays.add("\uD83C\uDDF7\uD83C\uDDFA");
     }
    @Override
    public String getBotUsername() {
        return BotSettings.USERNAME;
    }

    @Override
    public String getBotToken() {
        return BotSettings.TOKEN;
    }

    @Override
    public void onUpdateReceived(Update update) {

        if(update.hasMessage()){
            Message message = update.getMessage();
            SendMessage sendMessage = new SendMessage();

            if(message.hasText()){
                String text = message.getText();
                switch (text){
                    case BotMenu.START -> {
                        SendMessage start = start(update);
                        try {
                            execute(start);
                        } catch (TelegramApiException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    case BotMenu.CHANGE_LANGUAGE -> {

                    }
                }
                }
            if (message.hasReplyMarkup()){
            }
            }

    }

    public SendMessage start(Update update) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setParseMode(ParseMode.MARKDOWN);
        sendMessage.setChatId(update.getMessage().getChatId());
        sendMessage.setReplyMarkup(inlineKeyboardMarkup());
        sendMessage.setText("Welcome to the bot \nChoose the language");
        return sendMessage;
    }

    public InlineKeyboardMarkup inlineKeyboardMarkup() {
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> list = new ArrayList<>();
        List<InlineKeyboardButton> buttons;

        Iterator<String> iterator = arrays.iterator();

        while (iterator.hasNext()){
            String next = iterator.next();
            buttons = new ArrayList<>();
            if(next != null) {
                InlineKeyboardButton inlineKeyboardButton = new InlineKeyboardButton(next);
                inlineKeyboardButton.setCallbackData("choose/" + index);
                buttons.add(inlineKeyboardButton);
                index++;
            }
            if(iterator.hasNext()) {
                if (next != null) {
                    next = iterator.next();
                    InlineKeyboardButton inlineKeyboardButton1 = new InlineKeyboardButton(next);
                    inlineKeyboardButton1.setCallbackData("choose/" + index);
                    buttons.add(inlineKeyboardButton1);
                    index++;
                }
            }
            list.add(buttons);
        }
        inlineKeyboardMarkup.setKeyboard(list);
        return inlineKeyboardMarkup;
    }

}

