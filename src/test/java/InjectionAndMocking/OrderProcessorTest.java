package InjectionAndMocking;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

public class OrderProcessorTest {

    @Test
    void processOrder_whenOutOfStock_returnsFalse() {
        // Arrange
        OrderRepository dummyRepo = mock(OrderRepository.class);
        PaymentGateway dummyPayment = mock(PaymentGateway.class);
        InventoryService inventoryStub = mock(InventoryService.class);
        NotificationService dummyNotification = mock(NotificationService.class); // DUMMY

        // Configure only the inventory check
        when(inventoryStub.isInStock("PROD-1", 2)).thenReturn(false);

        OrderProcessor processor = new OrderProcessor(
                dummyRepo, dummyPayment, inventoryStub, dummyNotification
        );

        // Act
        boolean result = processor.processOrder(new Order("ORD-1", "PROD-1", 2, 50.0), new Customer("c1"));

        // Assert
        assertFalse(result);
        // Notice: dummyNotification is never referenced or verified.
    }


    @Test
    void processOrder_whenPaymentSucceeds_returnsTrue() {
        // Arrange
        InventoryService inventoryStub = mock(InventoryService.class);   // STUB
        PaymentGateway paymentStub = mock(PaymentGateway.class);         // STUB
        OrderRepository dummyRepo = mock(OrderRepository.class);
        NotificationService dummyNotification = mock(NotificationService.class);

        // Programming indirect inputs (canned responses)
        when(inventoryStub.isInStock("PROD-1", 1)).thenReturn(true);
        when(paymentStub.charge(any(), eq(100.0))).thenReturn(true);

        OrderProcessor processor = new OrderProcessor(
                dummyRepo, paymentStub, inventoryStub, dummyNotification
        );

        // Act
        boolean success = processor.processOrder(
                new Order("ORD-100", "PROD-1", 1, 100.0),
                new Customer("cust@test.com")
        );

        // Assert (State Verification)
        assertTrue(success);
    }

    @Test
    void spyExample_recordsInteractionsWithRealObject() {
        // Wrap a real, functional ArrayList with a Spy
        //List<String> realList = new ArrayList<>();
        List<String> spyList = spy(new ArrayList<>()); // SPY

        // Act on the SUT
        spyList.add("Item 1");
        spyList.add("Item 2");

        // Assert State (Uses real object behavior)
        assertEquals(2, spyList.size());
        assertEquals("Item 1", spyList.get(0));

        // Assert Interaction (Spy observation)
        verify(spyList).add("Item 1");
        verify(spyList).add("Item 2");
    }

    @Test
    void processOrder_whenSuccessful_sendsReceiptToCustomer() {
        // Arrange
        InventoryService inventoryStub = mock(InventoryService.class);
        PaymentGateway paymentStub = mock(PaymentGateway.class);
        OrderRepository dummyRepo = mock(OrderRepository.class);
        NotificationService notificationMock = mock(NotificationService.class); // MOCK

        when(inventoryStub.isInStock(anyString(), anyInt())).thenReturn(true);
        when(paymentStub.charge(any(), anyDouble())).thenReturn(true);

        OrderProcessor processor = new OrderProcessor(
                dummyRepo, paymentStub, inventoryStub, notificationMock
        );

        // Act
        processor.processOrder(
                new Order("ORD-200", "PROD-1", 1, 150.0),
                new Customer("alice@example.com")
        );

        // Assert (Interaction Verification on the Mock)
        verify(notificationMock, times(1)).sendReceipt("alice@example.com", "ORD-200");
    }

    @Test
    void processOrder_whenSuccessful_persistsOrderInDatabase() {
        // Arrange
        InMemoryOrderRepository fakeRepo = new InMemoryOrderRepository(); // FAKE
        InventoryService inventoryStub = mock(InventoryService.class);
        PaymentGateway paymentStub = mock(PaymentGateway.class);
        NotificationService dummyNotification = mock(NotificationService.class);

        when(inventoryStub.isInStock(anyString(), anyInt())).thenReturn(true);
        when(paymentStub.charge(any(), anyDouble())).thenReturn(true);

        OrderProcessor processor = new OrderProcessor(
                fakeRepo, paymentStub, inventoryStub, dummyNotification
        );

        Order order = new Order("ORD-999", "PROD-1", 1, 45.0);

        // Act
        processor.processOrder(order, new Customer("bob@example.com"));

        // Assert (State verification directly against the Fake)
        assertEquals(1, fakeRepo.getCount());
        assertEquals("PROD-1", fakeRepo.findById("ORD-999").getProductId());
    }

}
