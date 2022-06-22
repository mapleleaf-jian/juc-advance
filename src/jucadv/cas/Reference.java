package jucadv.cas;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.concurrent.atomic.AtomicReference;

/**
 * @author zcl2806
 * @create 2022-06-22 4:50 PM
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
class User {
    private String name;
    private int age;
}
public class Reference {
    public static void main(String[] args) {
        AtomicReference<User> ar = new AtomicReference<>();
        User u1 = new User("john", 15);
        User u2 = new User("bob", 34);
        ar.set(u1);
        System.out.println(ar.compareAndSet(u1, u2) + "\t" + ar.get());
        System.out.println(ar.compareAndSet(u1, u2) + "\t" + ar.get());
    }
}
