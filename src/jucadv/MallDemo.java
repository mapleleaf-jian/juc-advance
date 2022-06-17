package jucadv;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

/**
 * @author zcl2806
 * @create 2022-06-17 3:08 PM
 */
public class MallDemo {
    public List<NetMall> list = Arrays.asList(
            new NetMall("jd"),
            new NetMall("tb"),
            new NetMall("pdd")
            );
    public static void main(String[] args) {

    }
}

@AllArgsConstructor
@NoArgsConstructor
class NetMall {
    @Getter
    private String name;

    public Double calPrice() {
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return ThreadLocalRandom.current().nextDouble(100);
    }
}