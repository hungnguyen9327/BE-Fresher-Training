package com.example.rcluster.config;

import io.lettuce.core.ReadFrom;
import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisClusterConfiguration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceClientConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
@EnableCaching
public class RedisConfiguration {

  @Value("${redis.nodes}")
  private String[] redisNodes;

  @Bean
  LettuceConnectionFactory redisConnectionFactory(RedisClusterConfiguration redisConfiguration) {
    LettuceClientConfiguration clientConfig = LettuceClientConfiguration.builder()
        .readFrom(ReadFrom.REPLICA_PREFERRED).commandTimeout(Duration.ofSeconds(120)).build();
    return new LettuceConnectionFactory(redisConfiguration, clientConfig);
  }

  @Bean
  RedisClusterConfiguration redisConfiguration() {
    List<String> clusterNodes = Arrays.asList(redisNodes);
    RedisClusterConfiguration redisClusterConfiguration = new RedisClusterConfiguration(
        clusterNodes);
    redisClusterConfiguration.setMaxRedirects(5);
    return redisClusterConfiguration;
  }

  @Bean
  public RedisTemplate<String, Object> template(RedisConnectionFactory connectionFactory) {
    RedisTemplate<String, Object> template = new RedisTemplate<>();
    template.setConnectionFactory(connectionFactory);
    template.setKeySerializer(new StringRedisSerializer());
    template.setHashKeySerializer(new StringRedisSerializer());
    template.setHashKeySerializer(new JdkSerializationRedisSerializer());
    template.setValueSerializer(new JdkSerializationRedisSerializer());
    template.setEnableTransactionSupport(true);
    template.afterPropertiesSet();
    return template;
  }
}
