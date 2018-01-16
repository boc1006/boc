package com.boc.common.utils;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 对于信息模板变量处理
 *
 * @author ranqibing
 * @date 2016/12/27
 */
public class SimpleTmpl {

    /**
     * 使用context中对应的值替换templet 变量名
     * @param templet 模板
     * @param context 用于替换模板中的变量
     * @return 短信模板
     */
    public static String render(String templet, Map<String, String> context) {
        Set<String> paramNames = getParamNames(templet);
        Object value = null;
        for (String name : paramNames) {
            value = context.get(name);
            value = value == null ? "" : value;
            String regex = "\\Q${" + name + "}\\E";
            templet = templet.replaceAll(regex, value.toString());
        }
        return templet;
    }

    /**
     * 根据分割符从模板中取得变量的名字
     * @param templet 模板
     * @return 模板中的变量名
     */
    public static Set<String> getParamNames(String templet) {
        Set<String> paramNames = new HashSet<String>();
        StringBuilder sb = new StringBuilder();
        char[] chars = templet.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            switch (chars[i]) {
                case '{':
                    if(chars[i-1]=='$'){
                        sb.setLength(0);
                    }
                    break;
                case '}':
                    paramNames.add(sb.toString());
                    sb.setLength(0);
                    break;
                default:
                    sb.append(chars[i]);
                    break;
            }
        }
        return paramNames;
    }


    /**
     * 判断用户变量替换后是否还纯在可变参数
     * @param exp 替换后的模板
     * @return true 模板可用   false 模板匹配没有成功
     */
    public static Boolean varExp(String exp){
        if (!(exp.startsWith("${")) || !exp.endsWith("}")) {
//            throw new IllegalArgumentException("表达式[" + exp + "]必须类似于${}");
            return false;
        }
        return true;
    }

    /**
     * 将短信表内容字段的值转化为map
     * @param content 短信表中的内容字段
     * @param templet 模板内容
     */
    public static Map<String, String> getMap(String content, String templet) {
        int i = 0;
        //1、短信内容-->参数数组
        String[] params = content.split(",");
        Map<String, String> map = new HashMap();

        //2、取出模板中的参数名
        Matcher m = Pattern.compile("\\$\\{[A-Za-z0-9]*}").matcher(templet);
        while (m.find()) {
            //System.out.println(m.group(0));
            Matcher m1 = Pattern.compile("[A-Za-z0-9]*").matcher(m.group(0));
            while (m1.find()) {
                if (m1.group(0) != null && !("".equals(m1.group(0)))) {
                    //3、模板参数为键，短信内容为值-->map
                    map.put(m1.group(0), params[i]);
                }
            }
            i++;
        }
        //System.out.println(map);
        return map;
    }

}
