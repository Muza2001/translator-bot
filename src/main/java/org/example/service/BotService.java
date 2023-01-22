package org.example.service;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;

import java.io.IOException;

public interface BotService {

    String translate(String langFrom, String langTo, String text) throws IOException;

    SendMessage start(Update update);

}
