package com.lhl.boot.mr;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created with IntelliJ IDEA
 *
 * @author liuhaolu01
 * @date 2020-09-30
 * @time 10:41
 * @describe:
 */
public class WCReducer extends Reducer<Text, IntWritable, Text, Text> {

    @Override
    protected void reduce(Text key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {
        AtomicInteger count = new AtomicInteger();
        values.forEach(value -> count.addAndGet(value.get()));
        context.write(key, new Text(String.valueOf(count.get())));
    }
}
