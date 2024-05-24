package itmo.anastasiya;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class Main {
    public static void main(String[] args) {
        ApplicationContext applicationContext = SpringApplication.run(Main.class, args);
        //UserServiceImpl userService = applicationContext.getBean(UserServiceImpl.class);
        //userService.save("neaboba", "123", Role.ADMIN, 2L);
    }
}