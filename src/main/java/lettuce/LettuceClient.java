package lettuce;

/**
 * All rights Reserved, Designed By a.96bill.com
 *
 * @version V1.0
 * @Title: *.java
 * @Package com
 * @Description:
 * lettuce客户端
 *  参考
 * https://blog.csdn.net/aa1215018028/article/details/80777450
 * @author: wuzhicheng
 * @date: 14:43 2018/8/26/026
 * @company:北京今汇在线
 */
import java.util.concurrent.ExecutionException;

import com.lambdaworks.redis.RedisClient;
import com.lambdaworks.redis.RedisFuture;
import com.lambdaworks.redis.RedisURI;
import com.lambdaworks.redis.api.StatefulRedisConnection;
import com.lambdaworks.redis.api.async.RedisAsyncCommands;
import com.lambdaworks.redis.api.sync.RedisCommands;

public class LettuceClient {

    public static void main(String[] args) {
        RedisURI redisURI = RedisURI.create("redis://101.200.123.114:6379");
        redisURI.setPassword("redis_repay");
        RedisClient client = RedisClient.create(redisURI);
        StatefulRedisConnection<String, String> connect = client.connect();

        /* 同步执行的命令 */
        RedisCommands<String, String> commands1 = connect.sync();


        commands1.set("test1","hahhahaah");
        String str1 = commands1.get("test1");
        //String str2 = commands.get("test2");//MOVED 8899 192.168.37.128:7001;;;;client是单机版
        System.out.println(str1);

        /*  异步执行的命令  */
      RedisAsyncCommands<String, String> commands = connect.async();
      RedisFuture<String> future = commands.get("test1");
      try {
          String str = future.get();
          System.out.println(str);
      } catch (InterruptedException e) {
          e.printStackTrace();
      } catch (ExecutionException e) {
          e.printStackTrace();
      }
        System.out.println("222");

        connect.close();
        client.shutdown();
    }
}