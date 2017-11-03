package org.wenxueliu;

import static java.time.Duration.ofSeconds;
import static org.junit.jupiter.api.Assertions.assertTimeout;
import static org.junit.jupiter.api.Assertions.assertTimeoutPreemptively;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.api.TestReporter;
import org.junit.jupiter.api.DisplayName;

class TestOrderService {

    @Test
    public void doPaymentNotExceed15Seconds() {
        OrderService orderService = new OrderService();
        assertTimeout(ofSeconds(15), () -> {
            // This method runs in 10 seconds
            orderService.doPayment();
        });
    }

    @Test
    public void doPaymentExceed5Seconds() {
        OrderService orderService = new OrderService();
        assertTimeout(ofSeconds(5), () -> {
            // This method runs in 10 seconds
            orderService.doPayment();
        } , "The doPayment method take more than 5 seconds");
    }

    @Test
    public void printShippingLabelExceeded15SecondsWithPreemptiveTermination() {
        OrderService orderService = new OrderService();
        assertTimeoutPreemptively(ofSeconds(15), () -> {
            // This method takes 20 seconds to run
            orderService.printShippingLabel();
        } , () -> "The printShippingLabel method took more than 15 seconds and was aborted.");
    }

	@Test
	@Tag("slow")
	public void placeOrderTest() {
	}

	@Test
	@Tag("fast")
	public void checkout() {
	}

	@Test
	@Tag("slow")
	public void doPayment() {
	}

	@Test
	@Tag("fast")
	public void validateOrder() {
    }

	@DisplayName("Test placeOrder method")
	@Test
	public void placeOrderOK(TestInfo testInfo, TestReporter testReporter) {

		assertEquals("Test placeOrder method", testInfo.getDisplayName());
		testReporter.publishEntry("key", "value");

		assertEquals(1, 1);
	}

	@Test
	public void placeOrderNoItem(AuditService auditService) {
		auditService.audit();
		assertEquals(1, 1);
	}

	@Test
	public void placeOrderNG(@Report ReportService reportService) {
		reportService.report();
		assertEquals(1, 1);
    }

	@Test
	public void placeOrderNotLogin(EnvironmentInfo env) {
		assertEquals("Windows 10", env.getOS());
		assertEquals(1, 1);
	}
}
