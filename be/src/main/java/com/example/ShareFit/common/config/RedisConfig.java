package com.example.ShareFit.common.config;

import io.lettuce.core.SocketOptions;
import io.lettuce.core.cluster.ClusterClientOptions;
import io.lettuce.core.cluster.ClusterTopologyRefreshOptions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisClusterConfiguration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceClientConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import java.time.Duration;
import java.util.List;

@Configuration
public class RedisConfig {
    @Value("${spring.redis.cluster.nodes}")
    private List<String> nodes;

    @Bean
    public RedisConnectionFactory redisConnectionFactory() {
        /*
        소켓 옵션 설정
        connectTimeout(Duration.ofMillis(100L)) : 애플리케이션과 Redis 사이에 LettuceConnection을 생성하는 시간 초과 값
        keepAlive(true) : TCP Keep Alive 기능을 설정
         */
        SocketOptions socketOptions = SocketOptions.builder()
                .connectTimeout(Duration.ofMillis(1000L))
                .keepAlive(true)
                .build();

        /*
        클러스터 토폴로지 갱신 옵션 설정
        dynamicRefreshSources(true) : 모든 Redis 노드로부터 topology 정보 획득. default = true
        enableAllAdaptiveRefreshTriggers() : Redis 클러스터에서 발생하는 모든 이벤트(MOVE, ACK)등에 대해서 topology 갱신
        enablePeriodicRefresh(Duration.ofSeconds(30)) : 주기적으로 토폴로지를 갱신하는 시간
         */
        ClusterTopologyRefreshOptions clusterTopologyRefreshOptions = ClusterTopologyRefreshOptions.builder()
                .dynamicRefreshSources(true)
                .enableAllAdaptiveRefreshTriggers()
                .enablePeriodicRefresh(Duration.ofSeconds(30))
                .build();

        ClusterClientOptions clusterClientOptions = ClusterClientOptions.builder()
                .pingBeforeActivateConnection(true)                        // 커넥션을 사용하기 위하여 PING 명령어를 사용하여 검증합니다.
                .autoReconnect(true)                                       // 자동 재접속 옵션을 사용합니다.
                .socketOptions(socketOptions)                              // 앞서 생성한 socketOptions 객체를 세팅합니다.
                .topologyRefreshOptions(clusterTopologyRefreshOptions)     // 앞서 생성한 clusterTopologyRefreshOptions 객체를 생성합니다.
                .maxRedirects(3).build();

        final LettuceClientConfiguration clientConfig = LettuceClientConfiguration.builder()
                .commandTimeout(Duration.ofMillis(150L))
                .clientOptions(clusterClientOptions)
                .build();



        RedisClusterConfiguration clusterConfig = new RedisClusterConfiguration(nodes);
        clusterConfig.setMaxRedirects(3);

        LettuceConnectionFactory factory = new LettuceConnectionFactory(clusterConfig, clientConfig);
        //----------------- (5) LettuceConnectionFactory 옵션
        factory.setValidateConnection(false);

        return factory;
    }

    @Bean
    public RedisTemplate<?, ?> redisTemplate() {
        RedisTemplate<?, ?> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisConnectionFactory());

        // Value를 사용할 경우 시리얼라이저
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new StringRedisSerializer());

        return redisTemplate;
    }
}
