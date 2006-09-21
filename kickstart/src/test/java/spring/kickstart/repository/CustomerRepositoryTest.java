package spring.kickstart.repository;

import org.springframework.test.jpa.AbstractJpaTests;

/**
 * @author trisberg
 */
public class CustomerRepositoryTest extends AbstractJpaTests {

    protected String[] getConfigLocations() {
        return new String[] {"repository-test-config.xml"};
    }

    public void testSomething() {
        assertTrue(true);
    }

}
