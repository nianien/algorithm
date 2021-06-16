package com.lining.spiralmatrix;


import java.util.stream.IntStream;

/**
 * 螺旋矩阵
 *
 * @author NiaNien
 */
public class SpiralMatrix {

    public interface CoordinateHandler {

        void doHandle(int i, int j);
    }

    public static class ArrayCoordinateHandler implements CoordinateHandler {

        private int[][] array;

        private int n = 0;

        public ArrayCoordinateHandler(int[][] array) {
            this.array = array;
        }

        @Override
        public void doHandle(int i, int j) {
            this.array[i][j] = ++n;

        }
    }

    public static int[][] clockwise(int row, int column) {
        int[][] arr = new int[row][column];
        CoordinateHandler handler = new ArrayCoordinateHandler(arr);
        Point p1 = new Point();
        Point p2 = new Point(row - 1, column - 1);
        clockwise(p1, p2, handler);
        return arr;
    }

    public static int[][] counterClockwise(int row, int column) {
        int[][] arr = new int[row][column];
        CoordinateHandler handler = new ArrayCoordinateHandler(arr);
        Point p1 = new Point();
        Point p2 = new Point(row - 1, column - 1);
        counterClockwise(p1, p2, handler);
        return arr;
    }

    /**
     * 顺时针遍历由点p1和p2围成矩形的边
     *
     * @param p1
     * @param p2
     * @param handler
     */
    public static void clockwise(Point p1, Point p2, CoordinateHandler handler) {
        if (!p1.le(p2))
            return;
        //向右, 行不变, 列增加
        IntStream.rangeClosed(p1.y, p2.y).forEach(i -> handler.doHandle(p1.x, i));
        //向下, 列不变,行增加
        IntStream.rangeClosed(p1.x + 1, p2.x).forEach(i -> handler.doHandle(i, p2.y));
        //向左, 行不变,列递减
        IntStream.rangeClosed(p1.y, p2.y - 1).map(i -> p1.y + p2.y - 1 - i).filter(i -> p1.x < p2.x).forEach(i -> handler.doHandle(p2.x, i));
        //向上, 列不变,行递减
        IntStream.rangeClosed(p1.x + 1, p2.x - 1).map(i -> p2.x + p1.x - i).filter(i -> p1.y < p2.y).forEach(i -> handler.doHandle(i, p1.y));

        p1.increase(1, 1);
        p2.decrease(1, 1);
        clockwise(p1, p2, handler);
    }

    /**
     * 逆时针遍历由点p1和p2围成矩形的边
     *
     * @param p1
     * @param p2
     * @param handler
     */
    public static void counterClockwise(Point p1, Point p2,
                                        CoordinateHandler handler) {
        if (!p1.le(p2))
            return;
        //向下, 列不变,行增加
        IntStream.rangeClosed(p1.x, p2.x).forEach(i -> handler.doHandle(i, p1.y));
        //向右, 行不变, 列增加
        IntStream.rangeClosed(p1.y + 1, p2.y).forEach(i -> handler.doHandle(p2.x, i));
        //向上, 列不变,行递减
        IntStream.rangeClosed(p1.x, p2.x - 1).map(i -> p2.x + p1.x - 1 - i).filter(i -> p1.y < p2.y).forEach(i -> handler.doHandle(i, p2.y));
        //向左, 行不变,列递减
        IntStream.rangeClosed(p1.y + 1, p2.y - 1).map(i -> p1.y + p2.y - i).filter(i -> p1.x < p2.x).forEach(i -> handler.doHandle(p1.x, i));
        // 此时P1,p2坐标已复位
        p1.increase(1, 1);
        p2.decrease(1, 1);

        counterClockwise(p1, p2, handler);
    }

    /**
     * 坐标对象
     *
     * @author NiaNien
     */
    public static class Point {
        int x;
        int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public Point() {
            this(0, 0);
        }

        public void increase(int x, int y) {
            this.x += x;
            this.y += y;
        }

        public void decrease(int x, int y) {
            this.x -= x;
            this.y -= y;
        }

        /**
         * 坐标相等
         *
         * @param p
         * @return
         */
        public boolean eq(Point p) {
            return this.x == p.x && this.y == p.y;
        }

        /**
         * 坐标不等
         *
         * @param p
         * @return
         */
        public boolean ne(Point p) {
            return this.x != p.x && this.y != p.y;
        }

        /**
         * 小于指定坐标
         *
         * @param p
         * @return
         */
        public boolean lt(Point p) {
            return this.x < p.x && this.y < p.y;
        }

        /**
         * 小于等于指定坐标
         *
         * @param p
         * @return
         */
        public boolean le(Point p) {
            return this.x <= p.x && this.y <= p.y;
        }

        /**
         * 大于指定坐标
         *
         * @param p
         * @return
         */
        public boolean gt(Point p) {
            return this.x > p.x && this.y > p.y;
        }

        /**
         * 大于等于指定坐标
         *
         * @param p
         * @return
         */
        public boolean ge(Point p) {
            return this.x >= p.x && this.y >= p.y;
        }

    }

}
