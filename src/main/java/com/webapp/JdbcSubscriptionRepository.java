package com.webapp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
public class JdbcSubscriptionRepository {

    private static Logger LOG = LoggerFactory.getLogger(JdbcSubscriptionRepository.class.getCanonicalName());

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public DataSource getJdbcDataSource() {
        return jdbcTemplate.getDataSource();
    }

    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }


    public int totalSubscriptionCount() {
        String sql = "SELECT COUNT(*) FROM subscription";

        int total = getJdbcTemplate().queryForObject(sql, Integer.class);

        return total;
    }

    public void saveSubscription(Subscription subscription) {

        String query = "insert into subscription(subscriber_name, service_code) values(?, ?)";
        Object[] args = new Object[] {subscription.getUser(), subscription.getServiceCode()};

        int out = getJdbcTemplate().update(query, args);

        if (out != 0) {
            LOG.debug("Subscription saved successfully with Service Code: " + subscription.getServiceCode());
        } else {
            LOG.error("Subscription failed to be saved with Service Code: " + subscription.getServiceCode());
        }
    }

    public Subscription getByServiceCode(int serviceCode) {
        String query = "select id, subscriber_name, service_code from subscription where service_code = ?";

        Subscription subscriptionByServiceCode = jdbcTemplate.queryForObject(query, new Object[]{serviceCode}, new RowMapper<Subscription>() {
            @Override
            public Subscription mapRow(ResultSet resultSet, int i) throws SQLException {
               Subscription subscription = new Subscription();
                subscription.setSubscriptionId(resultSet.getInt("id"));
                subscription.setUser(resultSet.getString("subscriber_name"));
                subscription.setServiceCode(resultSet.getInt("service_code"));
                return subscription;
            }
        });

        return subscriptionByServiceCode;
    }

    public Subscription getById(int id) {
        String query = "select id, subscriber_name, service_code from subscription where id = ?";

        Subscription subscriptionById = jdbcTemplate.queryForObject(query, new Object[]{id}, new RowMapper<Subscription>() {
            @Override
            public Subscription mapRow(ResultSet resultSet, int i) throws SQLException {
                Subscription subscription = new Subscription();
                subscription.setSubscriptionId(resultSet.getInt("id"));
                subscription.setUser(resultSet.getString("subscriber_name"));
                subscription.setServiceCode(resultSet.getInt("service_code"));
                return subscription;
            }
        });

        return subscriptionById;
    }

    public List<Subscription> getAllSubscriptions() {

        String sql = "SELECT * FROM subscription";

        java.util.List<Subscription> subscriptions = new ArrayList<>();

        List<Map<String, Object>> rows = getJdbcTemplate().queryForList(sql);
        for (Map<String, Object> row : rows) {
            Subscription subscription = new Subscription();
            subscription.setSubscriptionId((Integer)(row.get("id")));
            subscription.setUser((String) row.get("subscriber_name"));
            subscription.setServiceCode((Integer) row.get("service_code"));
            subscriptions.add(subscription);
        }

        LOG.debug("Returning all Subscriptions:\n");
        for (Subscription sub : subscriptions) {
            LOG.debug(sub.toString());
            LOG.debug("\n");
        }

        return subscriptions;
    }

}
