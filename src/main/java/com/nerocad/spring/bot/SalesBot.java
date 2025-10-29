package com.nerocad.spring.bot;

import com.nerocad.spring.config.BotConfig;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Component
public class SalesBot extends TelegramLongPollingBot {

    private final String GREETING = "Привет!\nЯ бот для учёта продаж на Авито." +
            "\nПока я умею только здороваться, но скоро смогу помогать с учетом товаров";
    private final BotConfig botConfig;

    public SalesBot(BotConfig botConfig) {
        super(botConfig.gotBotToken());
        this.botConfig = botConfig;
    }

    @Override
    public String getBotUsername() {
        return botConfig.getBotName();
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            String messageText = update.getMessage().getText();
            long chatId = update.getMessage().getChatId();

            case messageText:
            if (messageText.equals("/start")) {
                sendMessage(chatId, GREETING);
            }
        }
    }

    private void sendMessage(long chatId, String textToSend) {
        SendMessage message = new SendMessage();
        message.setChatId(String.valueOf(chatId));
        message.setText(textToSend);

        try {
            execute(message);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
