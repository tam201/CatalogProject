package memcached;

import infrastructure.entity._Catalog;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;


@SpringBootApplication
@EnableCaching
public class MemcachedApplication implements CommandLineRunner {
    private static final Logger logger = LoggerFactory.getLogger(MemcachedApplication.class);



    @Autowired
    private final MemcachedService userService;

    public MemcachedApplication() {
        this.userService = new MemcachedService();
    }

    @Override
    public void run(String... args) {
        logger.info("------------------ demo @Cacheable --------------------");
        logger.info("find user with id = 1: {}", userService.findById(Long.valueOf("1")));
        logger.info("find user with id = 1: {}", userService.findById(1L));
        logger.info("find user with id = 2: {}", userService.findById(Long.valueOf("2")));
        logger.info("find user with id = 2: {}", userService.findById(Long.valueOf("2")));
        logger.info("find user with id = 1: {}", userService.findById(Long.valueOf("1")));
        logger.info("------------------ demo @CacheEvict --------------------");
        userService.clearCache();
        logger.info("find user with id = 1: {}", userService.findById(Long.valueOf("1")));
        logger.info("find user with id = 2: {}", userService.findById(Long.valueOf("2")));
        logger.info("------------------ demo @CachePut --------------------");
        logger.info("reload and find user with id = 1: {}", userService.update(new _Catalog(Long.valueOf("1"), "tinh/tp", "QN")));
        logger.info("find user with id = 1: {}", userService.findById(Long.valueOf("1")));
        logger.info("find user with id = 2: {}", userService.findById(Long.valueOf("2")));
    }

    public static void main(String[] args) {
        SpringApplication.run(MemcachedApplication.class, args);
    }

}
