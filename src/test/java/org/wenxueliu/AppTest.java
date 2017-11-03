package org.wenxueliu;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Tag;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;


/**
 * Unit test for simple App.
 */
@Disabled
@RunWith(JUnitPlatform.class)
public class AppTest {
    @BeforeAll
    static void setup() {
        // Initialize connection to file.
        System.out.println("@BeforeAll - Execute once before all test methods in this class.");
    }

    @BeforeEach
    void init() {
        // Insert some sample data before each test
        System.out.println("@BeforeEach - Executed before each test method in this class.");
    }

    @Tag("develop")
    @Test
    void develop() {
        System.out.println("develop ...");
    }

    @Tag("run")
    @Test
    void run() {
        System.out.println("develop ...");
    }

    @DisplayName("Test add user successfully.")
    @Test
    void testAddUserSuccess() {
        System.out.println("Test add user successfully");
    }

    @DisplayName("Test add user with passed argument is null.")
    @Test
    void testAddUserNull() {
        System.out.println("Test add null user.");
    }

    @Test
    @Disabled("Not implemented yet.")
    void testDeleteUser() {}

    @AfterEach
    void tearDown() {
        // Reset the file content.
        System.out.println("@AfterEach - This method is called after each test method.");
    }

    @AfterAll
    static void done() {
        // Closes connection to the file
        System.out.println("@AfterAll - This method is called after all test methods.");
    }
}
