package org.wenxueliu;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class OrderService {

    static final Logger log = LoggerFactory.getLogger(OrderService.class);
    public void doPayment() {
        try {
            Thread.sleep(10000);// 10 seconds
        } catch (InterruptedException e) {
            log.error("interrupted by {}", e.getMessage());
            Thread.currentThread().interrupt();
        }
    }

    public void printShippingLabel() {
        try {
            Thread.sleep(20000);// 20 seconds
        } catch (InterruptedException e) {
            log.error("interrupted by {}", e.getMessage());
            Thread.currentThread().interrupt();
        }
    }
}
