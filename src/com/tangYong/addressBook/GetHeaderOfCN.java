package com.tangYong.addressBook;

import java.io.UnsupportedEncodingException;

/**
 * 将获取中文字符串首字母串
 * 只支持GB2312字符集中的汉字
 */
public class GetHeaderOfCN {
    //GB2312字符集中每个英文字母的首个汉字区位号，i、u、v除外，最后一个是END
    private final int[] secPosValue = { 1601, 1637, 1833, 2078, 2274,
            2302, 2433, 2594, 2787, 3106, 3212, 3472, 3635, 3722, 3730, 3858,
            4027, 4086, 4390, 4558, 4684, 4925, 5249, 5590 };

    //对应的英文字符
    private final String[] lc_FirstLetter = { "a", "b", "c", "d", "e",
            "f", "g", "h", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s",
            "t", "w", "x", "y", "z" };

    //将给定的汉字字符串转换为首字母串
    public String getAllFirstLetter(String str) {
        String result = "";
        for (int i = 0; i < str.length(); i++) {
            result = result.concat(getFirstLetter(String.valueOf(str.charAt(i))));
        }

        return result;
    }

    //取得汉字首字母
    public String getFirstLetter(String chinese) {
        //将GB2312编码转换为ISO8859-1
        String str = transform(chinese, "GB2312", "ISO8859-1");

        // 判断是不是汉字
        if (str.length() > 1){
            //汉字高两位为区号，低两位为位号（ISO8859-1编码改为GB2312）
            int sectorCode = (int) str.charAt(0) - 160; // 汉字区号
            int positionCode = (int) str.charAt(1) - 160; // 汉字位号

            // 汉字区位号
            int secPosCode = sectorCode * 100 + positionCode;
            if (secPosCode > 1600 && secPosCode < 5590) {
                for (int i = 0; i < 23; i++) {
                    //判断该汉字属于哪一个区位号
                    if (secPosCode >= secPosValue[i] && secPosCode < secPosValue[i + 1]) {
                        str = lc_FirstLetter[i];
                        break;
                    }
                }
            }
            chinese = str;
        }else{
            //如果是大写
            if( chinese.charAt(0) < 'a' ){
                chinese = String.valueOf( (char)(chinese.charAt(0) + 32) );
            }
        }

//        System.out.println(chinese);
        return chinese;
    }

    // 字符串编码转换
    private String transform(String str, String charsetName,String toCharsetName) {
        try {
            str = new String(str.getBytes(charsetName), toCharsetName);
        } catch (UnsupportedEncodingException e) {
            System.out.println(e.getMessage());
        }
        return str;
    }
}
