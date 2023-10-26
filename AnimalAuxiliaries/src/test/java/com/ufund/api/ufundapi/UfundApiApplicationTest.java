package com.ufund.api.ufundapi;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT, classes = UfundApiApplication.class)
public class UfundApiApplicationTest {

    @Test
    public void testMainApplication() {
        UfundApiApplication.main(new String[] {});
        
    }
}
