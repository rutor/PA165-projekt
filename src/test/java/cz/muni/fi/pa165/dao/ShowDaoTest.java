package cz.muni.fi.pa165.dao;

import cz.muni.fi.pa165.ApplicationContext;
import org.junit.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.*;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.util.Assert;
import javax.persistence.*;

import static org.junit.Assert.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = ApplicationContext.class)
public class ShowDaoTest extends AbstractTestNGSpringContextTests {

    @PersistenceUnit
    private EntityManagerFactory emf;

    @Test
    public void failingTest() {
        //fail("This should run and fail!");
    }
}
