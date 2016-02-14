package com.webapp;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;

import static org.hamcrest.CoreMatchers.instanceOf;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = SpringApp01Application.class)
@Transactional
public class PersistenceTests {


    @Autowired
    private JdbcSubscriptionRepository jdbcSubscriptionRepository;

    @Autowired
    private JpaSubscriptionRepository jpaSubscriptionRepository;

    @Test
    public void testJdbcSubscriptionRepositoryIsValid() {

        Assert.assertNotNull(jdbcSubscriptionRepository);
        Assert.assertThat(jdbcSubscriptionRepository.getJdbcDataSource(), instanceOf(javax.sql.DataSource.class));
    }

    @Test
    public void testJpaSubscriptionRepositoryIsValid() {
        Assert.assertNotNull(jpaSubscriptionRepository);
    }

    @Test
    public void testSusbcriptionServiceSaveSubscription() {
        jdbcSubscriptionRepository.saveSubscription(new Subscription("Libby Pauley", 000000));
    }


    @Test
    public void testJdbcRepositoryTotalNumberOfSubscriptions() {
        int noSubscriptions = 0;

        Assert.assertTrue(jdbcSubscriptionRepository.totalSubscriptionCount() != noSubscriptions);
    }

    @Test
    public void testSubscriptionServiceListAllSubscriptions() {
        Assert.assertTrue(jdbcSubscriptionRepository.getAllSubscriptions() != null);
        jdbcSubscriptionRepository.getAllSubscriptions().forEach((sub) -> System.out.println(sub));
    }

    @Test
    public void testRetrieveSubscriptionByServiceCode() {
        int serviceCode = 444444;

        Subscription subscriptionByServiceCode = jdbcSubscriptionRepository.getByServiceCode(serviceCode);

        Assert.assertNotNull(subscriptionByServiceCode);

        Assert.assertEquals("Derek", subscriptionByServiceCode.getUser());
    }

    @Test
    public void testRetrieveSubscriptionBySubscriptionId() {
        int subsciptionId = 1;

        Subscription subscriptionBySubscriptionId = jdbcSubscriptionRepository.getById(subsciptionId);

        Assert.assertNotNull(subscriptionBySubscriptionId);

        Assert.assertEquals("Derek", subscriptionBySubscriptionId.getUser());
    }


    @Test
    public void testJpaRepositoryTotalNumberOfSubscriptions() {
        int noSubscriptions = 0;

        Assert.assertTrue(jpaSubscriptionRepository.count() != noSubscriptions);
    }

    @Test
    public void testJpaRepositorySaveSubscription() {
        Subscription newSubscription = new Subscription();
        newSubscription.setUser("Molly");
        newSubscription.setServiceCode(111111);
        jpaSubscriptionRepository.save(newSubscription);
    }

    @Test
    public void testJpaRepositorySaveMultipleSubscriptions() {
        Subscription s1 = new Subscription("Mary", 555555);
        Subscription s2 = new Subscription("Derek", 666666);
        jpaSubscriptionRepository.save(Arrays.asList(s1, s2));
    }

    @Test
    public void testJpaRepositoryListAllSubscriptions() {
       Assert.assertTrue(jpaSubscriptionRepository.findAll() != null);
       jpaSubscriptionRepository.findAll().forEach((sub) -> System.out.println(sub));
    }

    @Test
    public void testJpaRepositoryFindBySubscriptionId() {
        int subscriptionId = 1;
        Subscription subscriptionToFindBySubscriptionId = jpaSubscriptionRepository.findBySubscriptionId(subscriptionId);
        Assert.assertEquals("Derek", subscriptionToFindBySubscriptionId.getUser());
    }

    @Test
    public void testJpaRepositoryFindBySubscriptionServiceCode() {
        int subscriptionServiceCode = 444444;
        Subscription subscriptionToFindBySubscriptionServiceCode = jpaSubscriptionRepository.findByServiceCode(subscriptionServiceCode);
        Assert.assertEquals("Derek", subscriptionToFindBySubscriptionServiceCode.getUser());
    }

    @Test
    public void testJpaRepositoryFindBySubscriberUserName() {
        String subscriberName = "Derek";
        Subscription subscriptionToFindBySubscriptionUserName = jpaSubscriptionRepository.findByUser(subscriberName);
        Assert.assertNotNull(subscriptionToFindBySubscriptionUserName);
        Assert.assertEquals(subscriberName, subscriptionToFindBySubscriptionUserName.getUser());
    }


}
