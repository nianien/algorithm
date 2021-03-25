package com.lining.math;


import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 校验并计算数学表达式的工具类<br>
 * 数字可以含正负号, 运算支持加减乘除,括号和幂运算
 *
 * @author skyfalling
 */
public class Calculator {

    private static AtomicInteger counter = new AtomicInteger();
    private static Map<String, Double> cache = new HashMap<>();
    /**
     * 无符号实数的正则表达式
     */
    private final static String unsignedRealNumber = "(0|[1-9]\\d*)(\\.\\d+)?";
    /**
     * 实数的正则表达式
     */
    private final static String realNumber = "(- |\\+)?(0|[1-9]\\d*)(\\.\\d+)?";

    /**
     * 计算表达式
     *
     * @param expression
     * @return
     */
    public double calculate(String expression) {
        // 先转换成无符号表达式
        String result = unSigned(expression);
        // 再存储数字变量
        result = initVariable(result);
        // 然后去掉表达式中的括号
        result = resolve(result);
        // 最后计算无括号的表达式
        result = doCalculate(result);
        return removeValue(result);
    }

    /**
     * 判断表达式是否合法, 如果为空返回false
     *
     * @param expression
     * @return
     */
    public boolean isValid(String expression) {
        if (Optional.ofNullable(expression).orElse("").isEmpty())
            return false;
        char tail = expression.charAt(expression.length() - 1);
        // 表达式非正常结束,尾字符不是数字且不是右括号
        if (!Character.isDigit(tail) && tail != ')')
            return false;
        // 表达式中的数字子串不合法
        if (!checkNumber(expression))
            return false;

        // 未匹配的左括号数
        int unmatched = 0;
        // 表达式第一个字符可以是左括号，正负号或数字
        EnumSet<Type> expected = EnumSet.noneOf(Type.class);
        expected.add(Type.LP);
        expected.add(Type.SGN);
        expected.add(Type.NUM);
        for (int i = 0; i < expression.length(); i++) {
            // 当前字符对应的类型
            Type type = Type.typeOf(expression.charAt(i));
            // 所期望的类型不包含当前类型
            if (!expected.contains(type))
                return false;
            switch (type) {
                case LP:
                    // 当前类型为左括号，则未匹配括号数加1
                    unmatched++;
                    break;
                case RP:
                    // 当前类型为右括号，则未匹配数减1
                    unmatched--;
                    break;
            }
            // 下一个期望的类型
            expected = nextType(type, unmatched);
        }// for

        return unmatched == 0;// 括号匹配成功
    }

    /**
     * 去括号并计算括号中的表达式,最后获取一个无括号的表达式
     *
     * @param expression
     * @return
     */
    private String resolve(String expression) {
        // 取最右左括号,则其内不会再嵌套括号
        int from = expression.lastIndexOf('(');
        // 如果不存在括号
        if (from == -1)
            return expression;
        // 否则,取与其相匹配的右括号
        int to = expression.indexOf(')', from + 1);

        // 取括号之前的部分
        String left = expression.substring(0, from);
        // 取括号之后的部分
        String right = expression.substring(to + 1);
        // 取括号中的部分,该部分为无括号表达式
        String subexp = expression.substring(from + 1, to);
        // 计算无括号表达式
        String key = doCalculate(subexp);
        // 递归调用,去掉全部括号
        return resolve(left + key + right);
    }

    /**
     * 将表达式转换成无符号表达式
     *
     * @param expression
     * @return
     */
    private static String unSigned(String expression) {
        expression = expression.replace("(+", "(").replace("(-", "(0-");
        if (expression.startsWith("-")) {
            expression = "0" + expression;
        } else if (expression.startsWith("+")) {
            expression = expression.substring(1);
        }
        return expression;
    }

    /**
     * 将无符号数字转换成变量存储
     *
     * @param expression
     * @return
     */
    private String initVariable(String expression) {
        Pattern p = Pattern.compile(unsignedRealNumber);
        Matcher m = p.matcher(expression);
        int i = 0;
        // 从位置i开始搜索匹配的正则表达式
        while (m.find(i)) {
            int start = m.start();
            int end = m.end();
            // 表达式中的实数字符串
            String str = expression.substring(start, end);
            // 将实数字符串转换成double类型存储
            double d = Double.parseDouble(str);
            String key = putValue(d);
            // 数字之前的部分
            String p1 = expression.substring(0, start);
            // 数字之后的部分
            String p2 = expression.substring(end);
            // 重新构造表达式
            expression = p1 + key + p2;
            // 从当前位置向后继续搜索实数字符串
            i = p1.length() + key.length();
            m = p.matcher(expression);
        }
        // 返回形如@1+@2*@3的变量表达式,其中@1,@2,@3为实数对应的变量名
        return expression;
    }

    /**
     * 无括号的表达式运算
     *
     * @param expression
     * @return
     */
    private String doCalculate(String expression) {
        // 按优先级运算, 从左到右进行运算
        expression = doCalculate(expression, new char[]{'^'}, true);// 幂运算
        expression = doCalculate(expression, new char[]{'*', '/'}, false);// 乘除运算
        expression = doCalculate(expression, new char[]{'+', '-'}, false);// 加减运算
        return expression;
    }

    /**
     * 根据运算符计算表达式,并指定运算符运算方向<br>
     * 运算符之间具有相同的优先级
     *
     * @param expression
     * @param ops         具有相同优先级的运算符
     * @param rightToLeft 是否从右到左运算
     * @return
     */
    private String doCalculate(String expression, char[] ops,
                               boolean rightToLeft) {
        int index = rightToLeft ? lastIndexOfAny(expression, ops)
                : indexOfAny(expression, ops);
        if (index == -1)
            return expression;
        // 运算符
        char op = expression.charAt(index);

        // 求原子式左变量以及之前的表达式部分
        String left = expression.substring(0, index);
        int from = left.lastIndexOf("@");
        // 原子式左变量
        String p1 = left.substring(from);
        // 原子式之前的表达式
        left = left.substring(0, from);

        // 求原子式右变量以及之后的表达式部分
        String right = expression.substring(index + 1);
        int to = right.indexOf("@");
        int i = 0;
        // 计算右变量的截止索引位置
        for (; i < right.length(); i++) {
            char ch = right.charAt(i);
            // 不是数字字符且不是变量前缀符
            if (!Character.isDigit(ch) && ch != '@') {
                break;
            }
        }
        // 原子式右变量
        String p2 = right.substring(to, i);
        // 原子式之后的表达式
        right = right.substring(i);

        return doCalculate(left + atomCalculate(p1, p2, op) + right, ops,
                rightToLeft);
    }

    /**
     * 计算原子表达式
     *
     * @param p1
     * @param p2
     * @param op
     * @return
     */
    private String atomCalculate(String p1, String p2, char op) throws IllegalArgumentException {
        double x = removeValue(p1);
        double y = removeValue(p2);
        switch (op) {
            case '+':
                return putValue(x + y);
            case '-':
                return putValue(x - y);
            case '*':
                return putValue(x * y);
            case '/':
                return putValue(x / y);
            case '^':
                return putValue(Math.pow(x, y));
            default:
                throw new IllegalArgumentException("Unknown operator: " + op);
        }
    }

    /**
     * 检查表达式中的数字字符串是否合法
     *
     * @param expression
     * @return
     */
    private boolean checkNumber(String expression) {
        // 表达式中的数字字符串
        Pattern pattern = Pattern.compile("[\\d|\\.]+");
        Matcher matcher = pattern.matcher(expression);
        int i = 0;
        while (matcher.find(i)) {
            // 是否为有效的实数
            if (!Pattern.matches(realNumber, matcher.group()))
                return false;
            i = matcher.end();
        }
        return true;
    }

    /**
     * 判断当前类型后面可以出现的类型
     *
     * @param type      当前类型
     * @param unmatched 未匹配的左括号数
     * @return
     */
    private EnumSet<Type> nextType(Type type, int unmatched) {
        EnumSet<Type> expected = EnumSet.noneOf(Type.class);
        switch (type) {
            case NUM:// 数字之后可接数字、运算符（含负号）以及右括号
                expected.add(Type.NUM);
                expected.add(Type.SGN);
                expected.add(Type.OP);
                if (unmatched > 0)// 有尚未匹配的左括号
                    expected.add(Type.RP);
                break;
            case LP:// 左括号之后可接左括号、数字、正负号
                expected.add(Type.NUM);
                expected.add(Type.SGN);
                expected.add(Type.LP);
                break;
            case RP:// 右括号之后可接右括号、运算符（含正负号）
                expected.add(Type.SGN);
                expected.add(Type.OP);
                if (unmatched > 0)// 有尚未匹配的左括号
                    expected.add(Type.RP);
                break;

            case SGN:// 运算符（含正负号）之后可接数字、左括号
            case OP:
                expected.add(Type.NUM);
                expected.add(Type.LP);
                break;
        }
        return expected;
    }

    private boolean isEmpty(String str) {
        return str == null || str.isEmpty();
    }

    private String putValue(Double value) {
        String key = "@" + counter.incrementAndGet();
        cache.put(key, value);
        return key;
    }

    private Double removeValue(String key) {
        return cache.remove(key);
    }

    public static int lastIndexOfAny(String source, char[] array) {
        if (array.length == 0) {
            return -1;
        }
        for (int i = source.length() - 1; i >= 0; i--) {
            if (Arrays.binarySearch(array, source.charAt(i)) >= 0) {
                return i;
            }
        }
        return -1;
    }

    public static int indexOfAny(String source, char[] array) {
        if (array.length == 0) {
            return -1;
        }
        for (int i = 0; i < source.length(); i++) {
            if (Arrays.binarySearch(array, source.charAt(i)) >= 0) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 自定义表达式类型
     *
     * @author skyfalling
     */
    private enum Type {
        /**
         * 数字
         */
        NUM,
        /**
         * 符号
         */
        SGN,
        /**
         * 运算符
         */
        OP,
        /**
         * 左括号
         */
        LP,
        /**
         * 右括号
         */
        RP;

        /**
         * 根据字符判断类型
         */
        static Type typeOf(char ch) throws IllegalArgumentException {
            if (Character.isDigit(ch) || ch == '.')
                return Type.NUM;
            switch (ch) {
                case '*':
                case '/':
                case '^':
                    return Type.OP;
                case '+':
                case '-':
                    return Type.SGN;
                case '(':
                    return Type.LP;
                case ')':
                    return Type.RP;
                default:
                    throw new IllegalArgumentException("Unknown type:" + ch);
            }
        }
    }
}
