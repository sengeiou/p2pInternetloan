import com.p2pInternetloan.base.utils.Query;
import com.p2pInternetloan.members.entity.Members;
import com.p2pInternetloan.members.service.MembersService;
import com.p2pInternetloan.setting.controller.SettingController;
import com.p2pInternetloan.setting.entity.Setting;
import com.p2pInternetloan.setting.service.SettingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestParam;

import javax.naming.Name;
import java.util.Map;

/**
 * @Description:
 * @Author: cpc
 * @Date: 2019-10-19 17:23
 * @Version: V1.0
 */
public class Test extends BaseTestCase {

    @Autowired
    SettingService settingService;

    @Autowired
    MembersService membersService;

    @org.junit.Test
    public void test(){
//        int useable_min_limit = settingService.setMinLimit("useable_testmin_limit", "23");
//        int bid_max_limit = settingService.update("withdraw_precedure_limit","54");
//        Setting bid_max_limit = settingService.queryById("request_precedure_limit");

        for (int i = 2;i<100;i++){
            Members members = new Members();
            int insert = membersService.insert(members);
            System.out.println(insert);

        }




//        System.out.println(bid_max_limit);
    }


}
