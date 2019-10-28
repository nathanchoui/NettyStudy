package personal.nathan.niobase;

import java.nio.IntBuffer;

/**
 * @author nathan.z
 * @date 2019/10/24.
 */
public class BufferStudy {

    /**
     * 写模式下： limit就是capacity
     * 读模式下：limit是可以读取的最大容量
     *
     *
     * @param args
     */
    public static void main(String[] args) {
        // 创建缓存区
        IntBuffer intBuffer = IntBuffer.allocate(20);
        for (int i = 0; i < 10; i ++) {
            intBuffer.put(i * i);

        }
        intBuffer.mark();
        System.out.println(intBuffer);
        // 翻转，变成读模式
        intBuffer.flip();
        System.out.println(intBuffer);
    }
}
