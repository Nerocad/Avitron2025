package com.nerocad.spring.service;

import com.nerocad.spring.entity.Product;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jvnet.hk2.annotations.Service;


import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class ConversationService {
    public static Logger logger = (Logger) LogManager.getLogger(ConversationService.class);
    private ProductService productService;
    private Map<Long, Product> productDraft = new ConcurrentHashMap<>();
    private Map<Long, Integer> steps = new ConcurrentHashMap<>();

    public String startConversation(Long chatId) {
        productDraft.put(chatId, new Product());
        steps.put(chatId, 1);
        return "Введите тип товара:";
    }

    public synchronized String saveAnswer(Long chatId, String answer) {
        Product draft = productDraft.get(chatId);
        int step = steps.getOrDefault(chatId, 1);

        switch (step) {
            case 1 -> {
                draft.setProductType(answer.trim());
                steps.put(chatId, 2);
                return "Введите модель товара:";
            }
            case 2 -> {
                draft.setProductName(answer.trim());
                steps.put(chatId, 3);
                return "Введите цену поставщика:";
            }
            case 3 -> {
                try {
                    draft.setSupplierPrice(Integer.valueOf(answer.trim()));
                    steps.put(chatId, 4);
                    return "Товар добавлен в черновик. Сохраняем? (да, нет)";
                } catch (NumberFormatException e) {
                    return "Некорректная сумма. Введите число";
                }
            }
            case 4 -> {
                if (answer.equalsIgnoreCase("да")) {
                    steps.remove(chatId);
                    productService.addProduct(draft);
                    Product finished = productDraft.remove(chatId);
                    return "Товар успешно добавле:\n" + finished;
                } else {
                    productDraft.remove(chatId);
                    steps.remove(chatId);
                }
            }
            default -> {
                productDraft.remove(chatId);
                steps.remove(chatId);
                return "Ошибка состояния. Попробуй снова с команды /add";
            }
        }
        return "Дефолтный ответ";
    }
}
