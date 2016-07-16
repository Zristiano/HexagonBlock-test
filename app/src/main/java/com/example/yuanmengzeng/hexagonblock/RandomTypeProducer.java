package com.example.yuanmengzeng.hexagonblock;

import java.security.KeyRep;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.TreeMap;

/**
 * 产生模块随机类型 Created by yuanmengzeng on 2016/7/16.
 */
public class RandomTypeProducer
{
    /**
     * 各类型出现的频数
     */
    private Integer typeCount[] = {39, 12, 13, 12, 13, 13, 9, 7, 7, 6, 8, 7, 0, 9, 7, 8, 0, 6, 6, 9, 8, 6, 7, 7, 1};

    private Integer sum = 0;

    TreeMap<Integer, Integer> randomContainer = new TreeMap<>();

    private SecureRandom secureRandom; // 产生随机数类

    public RandomTypeProducer()
    {
        try
        {
            secureRandom = SecureRandom.getInstance("SHA1PRNG");
            initData();
        }
        catch (Exception e)
        {
            secureRandom = new SecureRandom();
            ZYMLog.error("ZYM  no such SecureRandom ");
        }
    }

    private void initData()
    {
        List<Integer> typeCountArrays = Arrays.asList(typeCount);
        for (int i = 0; i < typeCount.length; i++)
        {
            sum += typeCount[i]; // type频数总和
        }

        LinkedList<Integer> numList = new LinkedList<>(); // 计数器列表
        for (int i = 0; i < sum; i++)
        {
            numList.add(i);
        }

        while (numList.size() > 0)
        {
            int countOrder = secureRandom.nextInt(numList.size());
            int count = numList.get(countOrder); // 从计数器列表中取出key
            do
            {
                Integer random = secureRandom.nextInt(typeCountArrays.size());
                if (typeCount[random] > 0)
                {
                    typeCount[random]--; // 减去该类型的频数
                    randomContainer.put(count, random);
                    numList.remove(countOrder); // 删除使用过的key
                    break;
                }
            }
            while (true);
        }
//        numList = null;
//        typeCount = null;
//        typeCountArrays = null;
        ZYMLog.info("all");
    }

    public int getSum()
    {
        return sum;
    }

    public Integer getType()
    {
        Integer key = secureRandom.nextInt(sum);
        return randomContainer.get(key);
    }

}
