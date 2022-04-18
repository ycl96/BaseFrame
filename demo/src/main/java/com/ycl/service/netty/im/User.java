package com.ycl.service.netty.im;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author : YangChunLong
 * @date : Created in 2021/9/30 17:06
 * @description:
 * @modified By:
 * @version: :
 */
public interface User {
    List<ByteBuffer> msgList = new ArrayList<>();
    Scanner scan = new Scanner(System.in);

    void input();

}
