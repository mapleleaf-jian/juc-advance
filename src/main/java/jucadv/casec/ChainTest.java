package jucadv.casec;

import jdk.jfr.DataAmount;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.concurrent.CompletableFuture;

/**
 * @author maple
 * @create 2022-06-16 22:49
 */
public class ChainTest {
    public static void main(String[] args) {
        Student student = new Student();
        student.setAge(10).setName("da").setMajor("java");

        CompletableFuture<Integer> completableFuture = CompletableFuture.supplyAsync(() -> {
            return 3;
        });
        System.out.println(completableFuture.join());
    }
}

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
class Student {
    private int age;
    private String name;
    private String major;
}