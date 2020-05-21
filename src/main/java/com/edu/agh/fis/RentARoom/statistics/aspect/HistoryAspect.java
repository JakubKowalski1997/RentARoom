package com.edu.agh.fis.RentARoom.statistics.aspect;

import com.edu.agh.fis.RentARoom.statistics.service.HistoryService;
import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import static com.edu.agh.fis.RentARoom.statistics.model.Action.SAVE;

@Aspect
@Configuration
public class HistoryAspect {
    private final static Logger log = Logger.getLogger(HistoryAspect.class);

    @Autowired
    private HistoryService historyService;

    @After("execution(* com.edu.agh.fis.RentARoom.room.service..*save(..))")
    public void afterSave(JoinPoint joinPoint) {
        historyService.save(joinPoint.getTarget().toString(), SAVE);
    }

//    @After("execution(* com.edu.agh.fis.RentARoom..*findAll(..))")
//    public void afterFindAll(JoinPoint joinPoint) {
//        historyService.save(joinPoint.getTarget().toString(), FIND_ALL);
//    }
//
//    @After("execution(* com.edu.agh.fis.RentARoom..*find(..))")
//    public void afterFindOne(JoinPoint joinPoint) {
//        historyService.save(joinPoint.getTarget().toString(), FIND_ONE);
//    }
}
