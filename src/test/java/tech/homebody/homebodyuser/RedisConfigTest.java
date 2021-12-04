package tech.homebody.homebodyuser;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Set;

@RunWith(SpringRunner.class)
@Slf4j
public class RedisConfigTest extends HomeBodyUserApplicationTests {
    @Autowired
    private StringRedisTemplate redisTemplate;

    @Test
    public void StringTest() {
        final String key = "testString";
        final ValueOperations<String, String> stringStringValueOperations = redisTemplate.opsForValue();
        stringStringValueOperations.set(key, "1");
        final String result_1 = stringStringValueOperations.get(key);

        log.info("result_1 = " + result_1);

        stringStringValueOperations.increment(key);
        final String result_2 = stringStringValueOperations.get((key));

        log.info("result_2 = "+ result_2);
    }

    @Test
    public void SortedSetDeleteTest() {
        final String key = "SortedSetTest";
        //ZSetOperations<String, String> stringStringZSetOperations = redisTemplate.opsForZSet();
        redisTemplate.delete(key);
//        stringStringZSetOperations.remove(key, "H");
//        stringStringZSetOperations.remove(key, "E");
//        stringStringZSetOperations.remove(key, "L");
//        stringStringZSetOperations.remove(key, "O");

    }

    @Test
    public void SortedSetTest() {
        final String key = "SortedSetTest";
        ZSetOperations<String, String> stringStringZSetOperations = redisTemplate.opsForZSet();
        stringStringZSetOperations.incrementScore(key, "H", 1);
        stringStringZSetOperations.incrementScore(key, "E", 5);
        stringStringZSetOperations.incrementScore(key, "L", 10);
        stringStringZSetOperations.incrementScore(key, "L", 15);
        stringStringZSetOperations.incrementScore(key, "O", 20);

        stringStringZSetOperations.incrementScore(key, "ALPHA", 30);

//        stringStringZSetOperations.add(key, "H", 1);
//        stringStringZSetOperations.add(key, "E", 5);
//        stringStringZSetOperations.add(key, "L", 10);
//        stringStringZSetOperations.add(key, "L", 15);
//        stringStringZSetOperations.add(key, "O", 20);

        Set<String> range = stringStringZSetOperations.range(key, 0 , 5);

        assert range != null;
        log.info("range = "+ Arrays.toString(range.toArray()));

        final Long size = stringStringZSetOperations.size(key);
        log.info("info = " + size);


        Set<ZSetOperations.TypedTuple<String>> scoreRange = stringStringZSetOperations.reverseRangeWithScores(key, 0, -1);
        assert scoreRange != null;
        log.info("size : "+ scoreRange.size());

        Iterator<ZSetOperations.TypedTuple<String>> rankByScore = scoreRange.iterator();

        while(rankByScore.hasNext()){
            ZSetOperations.TypedTuple<String> temp = rankByScore.next();
            log.info("ranking!!!!!!!!! Value : "+ temp.getValue());
            log.info("ranking!!!!!!!!! Score : "+ temp.getScore());

        }
    }
}
