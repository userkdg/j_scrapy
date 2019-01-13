package cn.kindg.jscrapy.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

/**
 * 使用读写锁
 */
public class ShareData {
    private static final List<Character> container = new ArrayList<Character>(){{
        IntStream.range(0,1000).forEach(i->add(i, 'C'));
    }};


}
