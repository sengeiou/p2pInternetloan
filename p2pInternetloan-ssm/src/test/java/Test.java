import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * @Description:
 * @Author: cpc
 * @Date: 2019-10-19 17:23
 * @Version: V1.0
 */
public class Test extends BaseTestCase {
    @Autowired
    RedisTemplate redisTemplate;

    @org.junit.Test
    public void test(){
        redisTemplate.delete("d");
    }
}
