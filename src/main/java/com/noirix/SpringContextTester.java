package com.noirix;

import com.noirix.aspect.LoggingAspect;
import com.noirix.domain.Gender;
import com.noirix.domain.User;
import com.noirix.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.log4j.Logger;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

//@Slf4j
public class SpringContextTester {
    private static final Logger log = Logger.getLogger(SpringContextTester.class);

    public static void main(String[] args) {
        AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext("com.noirix");

        UserService userService = annotationConfigApplicationContext.getBean(UserService.class);

        log.info(userService.findAll().stream().map(User::getName).collect(Collectors.joining(", ")));

        //log.info(userService.findById(11L).toString());

       // System.out.println(userService.findById(11L));

        System.out.println(userService.search("select * from m_users where name like '%e%'"));
        List<User> testCreate = userService.search("TestCreate");

        for (User user : testCreate) {
            log.info(user.toString());
        }

        User userForSave =
                User.builder()
                        .name("Viachaslau")
                        .surname("Kalevich")
                        .birthDate(new Date())
                        .created(new Timestamp(new Date().getTime()))
                        .changed(new Timestamp(new Date().getTime()))
                        .gender(Gender.MALE)
                        .weight(90f)
                        .build();

        log.info(userService.save(userForSave).toString());

        //Add search method to service
        //Realise search method with JDBCTemplate or NamedParamJDBCTemplate

        LoggingAspect.getStatistic();
    }
}
