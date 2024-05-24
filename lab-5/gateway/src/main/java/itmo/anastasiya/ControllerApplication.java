package itmo.anastasiya;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class ControllerApplication {
    public static void main(String[] args) {
        ApplicationContext applicationContext = SpringApplication.run(ControllerApplication.class, args);
        //UserServiceImpl userService = applicationContext.getBean(UserServiceImpl.class);
        //userService.save("neaboba", "123", Role.ADMIN, 1L);
    }
}
