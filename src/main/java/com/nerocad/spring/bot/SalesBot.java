package com.nerocad.spring.bot;

import com.nerocad.spring.config.BotConfig;
import com.nerocad.spring.service.ConversationService;
import com.nerocad.spring.service.ProductService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Component
public class SalesBot extends TelegramLongPollingBot {
    private static final Logger logger = (Logger) LogManager.getLogger(SalesBot.class);
    private final ProductService productService;
    private final ConversationService conversationService;
    private final String GREETING = "Привет!\nЯ бот для учёта продаж на Авито. " +
            "Пока я еще не готов.\n" +
            "Готовы только заглушки на основные команды.\n" +
            "Бот в процессе обрастания функционалом";
    private final String HELP_MESSAGE = "Доступные команды:\n" +
            "/start - приветствие\n" +
            "/help - список команд\n" +
            "/available - показать непроданные товары с плановой ценой\n" +
            "/add - добавить новый товар\n" +
            "/mark - отметить товар как проданный\n" +
            "/edit - изменить товар\n" +
            "/delete - удалить товар\n" +
            "/profit - показать прибыль (фактическая продажа - закупка)\n" +
            "/list_all - показать все записи\n";
    private final String AVAILABLE_MESSAGE = "Здесь будут непроданные товары с ценой";
    private final String ADD_MESSAGE = "Добавление товара (пока не реализовано)";
    private final String MARK_MESSAGE = "Пометка товара как проданного (пока не реализовано)";
    private final String EDIT_MESSAGE = "Редактирование записи (пока не реализовано)";
    private final String DELETE_MESSAGE = "Удаление записи (пока не реализовано)";
    private final String PROFIT_MESSAGE = "Подсчет прибыли (пока не реализовано)";
    private final String LIST_ALL_MESSAGE = "Список всех товаров (пока не реализовано)";
    private final String UNKNOWN_MESSAGE = "Неизвестная команда. Напиши /help что бы увидеть список команд";
    private final BotConfig botConfig;

    @Autowired
    public SalesBot(BotConfig botConfig, ProductService productService,
                    ConversationService conversationService) {
        super(botConfig.gotBotToken());
        this.botConfig = botConfig;
        this.productService = productService;
        this.conversationService = conversationService;
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

//            String response = switch (messageText) {
//                case "/start" -> GREETING;
//                case "/help" -> HELP_MESSAGE;
//                case "/available" -> AVAILABLE_MESSAGE;
//                case "/add" -> ADD_MESSAGE;
//                case "/mark" -> MARK_MESSAGE;
//                case "/edit" -> EDIT_MESSAGE;
//                case "/delete" -> DELETE_MESSAGE;
//                case "/profit" -> PROFIT_MESSAGE;
//                case "/list_all" -> LIST_ALL_MESSAGE;
//                default -> UNKNOWN_MESSAGE;
            //sendMessage(chatId, response);
            if (messageText.equals("/add")) {
                String reply = conversationService.saveAnswer(chatId, messageText);
                sendMessage(chatId, reply);
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
            logger.info("Failed send message", e);
        }
    }

    private void addProduct() {

    }
}
