package com.lhl.boot.mr;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * Created with IntelliJ IDEA
 *
 * @author liuhaolu01
 * @date 2020-09-30
 * @time 10:33
 * @describe: Mapper<KEYIN, VALUEIN, KEYOUT, VALUEOUT>
 *     hadoop实现了自己的一套序列化机制
 */
public class WCMapper extends Mapper<LongWritable, Text, Text, IntWritable> {

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String line = value.toString();
        String[] split = line.split(" ");
        for (String str : split) {
            context.write(new Text(str), new IntWritable(1));
        }
    }
}
