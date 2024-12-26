package com.tauseef.app.promotions;

import com.tauseef.app.models.Order;
import com.tauseef.app.services.PromotionService;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class NewYearPromotion extends PromotionService {

    @Override
    public void applyDiscount(Order order) {

        Map<String, LocalDateTime> dates = getPromotionSeason();

        if (today.isAfter(dates.get("start")) && today.isBefore(dates.get("end"))) {
            updateOrder(order);

        } else if (nextHandler != null) {
            nextHandler.applyDiscount(order);
        }
    }

    @Override
    public double getDiscountPercentage() {
        return 15;
    }

    @Override
    public String getPromotionName() {
        return "New Year Promotion";
    }

    public Map<String, LocalDateTime> getPromotionSeason() {
        return new HashMap<>(
                Map.of(
                        "start", LocalDate.parse("2025-01-01").atStartOfDay(),
                        "end", LocalDate.parse("2025-02-01").atStartOfDay()
                )
        );
    }
}
