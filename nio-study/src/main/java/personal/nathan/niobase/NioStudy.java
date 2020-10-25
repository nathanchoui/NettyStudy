package personal.nathan.niobase;

import org.junit.jupiter.api.Test;

import java.nio.IntBuffer;

/**
 * @author nathan.z
 * @date 2020/10/25.
 */
public class NioStudy {

    @Test
    public void testPutGet() {
        IntBuffer intBuffer = IntBuffer.allocate(20);
        for (int i = 0; i < 10; i ++) {
            intBuffer.put(i);
        }
        System.out.println("put 10 elements");
        System.out.println(intBuffer);
        intBuffer.flip();
        System.out.println("flip");
        System.out.println(intBuffer);
        for (int j = 0; j < 5; j ++) {
            int value = intBuffer.get();
        }
        System.out.println("after got 5 elements");
        System.out.println(intBuffer);
    }
}
