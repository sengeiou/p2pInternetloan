import com.p2pInternetloan.base.utils.BigDecimalUtil;

import java.math.BigDecimal;

/**
 * @Description:
 * @Author: cpc
 * @Date: 2019-10-19 17:23
 * @Version: V1.0
 */
public class Test/* extends BaseTestCase*/ {

    @org.junit.Test
    public void test(){
        BigDecimal bj = new BigDecimal("120000");
        BigDecimal annualInterestRate = new BigDecimal("0.06");
        System.out.println(BigDecimalUtil.equalAmountPrincipal(bj, annualInterestRate, 12));
    }
}
