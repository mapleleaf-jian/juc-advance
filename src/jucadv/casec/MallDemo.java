package jucadv.casec;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * @author zcl2806
 * @create 2022-06-17 3:08 PM
 */
public class MallDemo {
    public static List<NetMall> list = Arrays.asList(
            new NetMall("jd"),
            new NetMall("tb"),
            new NetMall("pdd")
            );

    public static List<String> getPrice(List<NetMall> list, String name) {
        return list.stream()
                .map(netMall -> String.format(name + " in %s price is %.2f", netMall.getName(), netMall.calPrice()))
                .collect(Collectors.toList());
    }

    public static List<String> getPriceByCompletableFuture(List<NetMall> list, String name) {
        return list.stream()
                .map(netMall -> CompletableFuture.supplyAsync(()
                        -> String.format(name + " in %s price is %.2f", netMall.getName(), netMall.calPrice())))
                .map(cf -> cf.join()).collect(Collectors.toList());
    }

    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        List<String> price = getPriceByCompletableFuture(list, "mysql");
        price.forEach(System.out::println);
        long endTime = System.currentTimeMillis();
        System.out.println("花费的时间为: " + (endTime - startTime) + "ms");
    }

    private static void extracted() {
        long startTime = System.currentTimeMillis();
        List<String> price = getPrice(list, "mysql");
        price.forEach(System.out::println);
        long endTime = System.currentTimeMillis();
        System.out.println("花费的时间为: " + (endTime - startTime) + "ms");
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