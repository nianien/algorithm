package com.lining;

import com.lining.climbstair.StairClimber;
import org.junit.Test;

/**
 * Created with IntelliJ IDEA.
 * User: skyfalling
 * Date: 12-10-23
 * Time: 下午10:08
 * To change this template use File | Settings | File Templates.
 */
public class TestStairClimber {
    private StairClimber stairClimber = new StairClimber();

    @Test
    public void test() {
        assert stairClimber.climb(2, 1) == StairClimber.decompose(2, 1);
//        System.out.println(StairClimber.decompose(2, 1));
        System.out.println("===================================");
        assert stairClimber.climb(10, 7) == StairClimber.decompose(10, 7);
    }


}
