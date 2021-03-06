package com.example.yuanmengzeng.hexagonblock.Encipher;

import java.net.URLEncoder;
import java.security.InvalidParameterException;
import java.security.Key;
import java.util.Random;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;
import javax.crypto.spec.IvParameterSpec;

/**
 * 3DES加密工具类
 */
public class ThirdDESUtils
{

    public static final String THIRD_DES_SPLIT = "-=-=-=-=-";

    /**
     * key列表
     */
    public static final String KEY[] = {"70706C6976656F6B",

            "15B9FDAEDA40F86BF71C73292516924A294FC8BA31B6E9EA",

            "29028A7698EF4C6D3D252F02F4F79D5815389DF18525D326",

            "D046E6B6A4A85EB6C44C73372A0D5DF1AE76405173B3D5EC",

            "435229C8F79831131923F18C5DE32F253E2AF2AD348C4615",

            "9B2915A72F8329A2FE6B681C8AAE1F97ABA8D9D58576AB20",

            "B3B0CD830D92CB3720A13EF4D93B1A133DA4497667F75191",

            "AD327AFB5E19D0" +
                    "23150E382F6D3B3EB5B6319120649D31F8",

            "C42F31B008BF257067ABF115E0346E292313C746B3581FB0",

            "529B75BAE0CE2038466704A86D985E1C2557230DDF311ABC",

            "8A529D5DCE91FEE39E9EE9545DF42C3D9DEC2F767C89CEAB"};

    /**
     * 加密算法
     */
    private static final String CRYPTALGORITHM = "DESede/CBC/PKCS5Padding";

    /**
     * Encode类型
     */
    private static final String CODINGTYPE = "UTF-8";

    /**
     *
     */
    private static final byte DEFAULTIV[] = {1, 2, 3, 4, 5, 6, 7, 8};

    /**
     * key算法
     */
    private static final String KEYALGORITHM = "DESede";

    /**
     *
     */
    private static final char HEX_DIGITS[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E',
            'F'};

    /**
     * 加密
     *
     * @param param 待加密的字符串
     * @param keyIndex keyIndex
     * @return 加密后的字符串
     * @throws Exception 异常
     */
    public static String encode(String param, int keyIndex) throws Exception
    {
        if (keyIndex <= 0 || keyIndex >= KEY.length)
        {
            throw new InvalidParameterException();
        }

        String key = KEY[keyIndex];
        byte[] byteIV = hex2byte(KEY[0]);
        byte input[] = Hex.decode(key);
        Cipher cipher = Cipher.getInstance(CRYPTALGORITHM);
        DESedeKeySpec desKeySpec = new DESedeKeySpec(input);
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(KEYALGORITHM);
        SecretKey secretKey = keyFactory.generateSecret(desKeySpec);
        cipher.init(Cipher.ENCRYPT_MODE, secretKey, ivGenerator(byteIV));
        byte[] output = cipher.doFinal(param.getBytes(CODINGTYPE));
        return new String(Base64.encode(output), CODINGTYPE);
    }

    /**
     * 解密
     *
     * @param param 待解密的字符串
     * @param keyIndex keyIndex
     * @return 加密后的字符串
     * @throws Exception 异常
     */
    public static String decode(String param, int keyIndex) throws Exception
    {
        if (keyIndex <= 0 || keyIndex >= KEY.length)
        {
            throw new InvalidParameterException();
        }
        String key = KEY[keyIndex];
        byte[] byteIV = hex2byte(KEY[0]);
        return decrypt(param, key, byteIV);
    }

    /**
     * 字节数组转Hex字符串
     *
     * @param b 字节数组
     * @return Hex字符串
     */
    public static String toHexString(byte[] b)
    {
        StringBuilder sb = new StringBuilder(b.length * 2);
        for (byte aB : b)
        {
            sb.append(HEX_DIGITS[(aB & 0xf0) >>> 4]);
            sb.append(HEX_DIGITS[aB & 0x0f]);
        }
        return sb.toString();
    }

    /**
     * 生成key
     *
     * @param keyStr key字符串
     * @return Key
     * @throws Exception
     */
    private static Key keyGenerator(String keyStr) throws Exception
    {
        byte input[] = Hex.decode(keyStr);
        DESedeKeySpec keySpec = new DESedeKeySpec(input);
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(KEYALGORITHM);
        return keyFactory.generateSecret(keySpec);
    }

    private static IvParameterSpec ivGenerator(byte b[]) throws Exception
    {
        return new IvParameterSpec(b);
    }

    private static byte[] base64Decode(String s) throws Exception
    {
        return Base64.decode(s);
    }

    private static String decrypt(String strTobeDeCrypted, String strKey, byte byteIV[]) throws Exception
    {
        byte input[] = base64Decode(strTobeDeCrypted);
        Key k = keyGenerator(strKey);
        IvParameterSpec ivSpec = byteIV.length != 0 ? ivGenerator(byteIV) : ivGenerator(DEFAULTIV);
        Cipher c = Cipher.getInstance(CRYPTALGORITHM);
        c.init(2, k, ivSpec);
        byte output[] = c.doFinal(input);
        return new String(output, CODINGTYPE);
    }

    private static byte[] hex2byte(String hex) throws IllegalArgumentException
    {
        if (hex.length() % 2 != 0)
        {
            throw new IllegalArgumentException();
        }
        char[] arr = hex.toCharArray();
        byte[] b = new byte[hex.length() / 2];
        for (int i = 0, j = 0, l = hex.length(); i < l; i++, j++)
        {
            String swap = "" + arr[i++] + arr[i];
            int byteint = Integer.parseInt(swap, 16) & 0xFF;
            b[j] = Integer.valueOf(byteint).byteValue();
        }
        return b;
    }

    // public static void main(String[] args)
    // {
    // int index = new Random().nextInt(10) + 1;
    // String text = "李文涛" + THIRD_DES_SPLIT + "4255599" + THIRD_DES_SPLIT +
    // "fsfdsdf";
    // try
    // {
    // System.out.println(index + ", " + URLEncoder.encode(encode(text,
    // index), "UTF-8"));
    // System.out.println(decode("ZZB/moGmu3M4O5azVuu03dhCWOgz65WC0/L6suKjNQCxE6le2Jcse5UMCFGA+MMJ",
    // 8));
    // }
    // catch (Exception e)
    // {
    // e.printStackTrace();
    // }
    // }

}
