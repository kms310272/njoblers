package kingdom.njoblers.user.batch.controller;

import java.time.LocalDate;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

@EnableScheduling
@Component
public class UserBatchController {
  
//	@Autowired
//    private UserMemberService memberService;
//
//    @Scheduled(cron = "0 0 0 * * ?") // 매일 자정마다 실행
//    public void updateMemberStatus(@RequestBody Map param) {
//        LocalDate ninetyDaysAgo = LocalDate.now().minusDays(90);
//        
//        param.put("ninety_days_ago", ninetyDaysAgo);
//        memberService.updateMemberStatus(param);
//    }
}
