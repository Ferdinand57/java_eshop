package id.ac.ui.cs.advprog.eshop;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@SpringBootTest
class EshopApplicationTests {

    @Test
    void contextLoads() {
        // This test already verifies that the context loads, which is good.
        // We can keep it as a basic sanity check.
    }

    @Test
    void testMainMethod() {
        assertDoesNotThrow(() -> EshopApplication.main(new String[] {}));
    }
}