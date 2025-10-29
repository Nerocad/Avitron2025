package com.nerocad.spring;

import com.nerocad.spring.bot.SalesBot;
import com.nerocad.spring.config.AppConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

public class Main {
    public static void main(String[] args) {
        try (AnnotationConfigApplicationContext context =
                     new AnnotationConfigApplicationContext(AppConfig.class)) {
            SalesBot bot = context.getBean(SalesBot.class);
            TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
            botsApi.registerBot(bot);

            System.out.println("Бот успешно запущен");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}