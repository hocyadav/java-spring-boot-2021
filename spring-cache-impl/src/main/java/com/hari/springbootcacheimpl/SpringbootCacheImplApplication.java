package com.hari.springbootcacheimpl;

import java.util.HashMap;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import com.hari.springbootcacheimpl.entity.Person;
import com.hazelcast.config.Config;
import com.hazelcast.config.ManagementCenterConfig;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IMap;

@SpringBootApplication
public class SpringbootCacheImplApplication {

	@Value("${hazel.mylocal-url}")//access value from application property
    private String hazelCastUrl_prop;

    public static void main(String[] args) {
        SpringApplication.run(SpringbootCacheImplApplication.class, args);
    }

    @Bean
    public HashMap<Integer, Person> personHashMap() {
        return new HashMap<Integer, Person>();
    }

    //1. Create config object
    @Bean
    public Config hazelCastConfig() {
        final ManagementCenterConfig managementCenterConfig = new ManagementCenterConfig().setEnabled(true).setUrl(hazelCastUrl_prop);
        return new Config().setManagementCenterConfig(managementCenterConfig);
    }

    //2. hazel instance - by using config object
    @Bean
    public HazelcastInstance hazelCastInstance(Config config) {
	    final HazelcastInstance hazelcastInstance = Hazelcast.newHazelcastInstance(config);
	    return hazelcastInstance;
    }

    @Bean
    public IMap<Object, Object> personHashMap2(HazelcastInstance hazelcastInstance) {
	    final IMap<Object, Object> personHashMap = hazelcastInstance.getMap("personHashMap");
	    return personHashMap;
    }

}
