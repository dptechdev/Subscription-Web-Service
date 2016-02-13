package com.webapp;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

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
    public void testJpaRepositoryListAllSubscriptions() {
       Assert.assertTrue(jpaSubscriptionRepository.findAll() != null);
       jpaSubscriptionRepository.findAll().forEach((sub) -> System.out.println(sub));
    }

    @Test
    public void testJpaRepositoryFindBySubscriptionId() {
        Subscription subscriptionToFindBySubscriptionId = jpaSubscriptionRepository.findBySubscriptionId(1);
        Assert.assertEquals("Derek", subscriptionToFindBySubscriptionId.getUser());
    }


}
