package com.lhl.boot.spider.processor;

import com.alibaba.fastjson.JSON;
import com.lhl.boot.spider.entity.AiQiYiListEntity;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created with IntelliJ IDEA
 *
 * @author liuhaolu01
 * @date 2020-08-24
 * @time 11:12
 * @describe: 爱奇艺播放列表获取
 */
@Component
@Slf4j
public class AiQiYiListProcessor implements PageProcessor {

    private Site site = Site.me()
            .setRetryTimes(3)
            .setSleepTime(100)
            .setTimeOut(50000)
            .addHeader("Accept-Encoding", "/")
            .setUserAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.59 Safari/537.36");

    @Override
    public void process(Page page) {
        List<String> number = page.getHtml().xpath("//ul[@class='qy-episode-num']/li[@class='select-item']/a/text()").all();
        if (CollectionUtils.isNotEmpty(number)) {
            List<String> numberList = number.stream().map(item -> "第" + item.trim() + "集").collect(Collectors.toList());
            page.putField("numberList", numberList);
        }
        List<String> urlList = page.getHtml().xpath("//ul[@class='qy-episode-num']/li[@class='select-item']").$("a", "href").all();
        if (CollectionUtils.isNotEmpty(urlList)) {
            List<String> collect = urlList.stream().map(url -> "https:" + url).collect(Collectors.toList());
            page.putField("urlList", collect);
        }
        List<String> titleList = page.getHtml().xpath("//ul[@class='qy-episode-num']/li[@class='select-item']").$("a", "title").all();
        page.putField("titleList", titleList);
    }

    @Override
    public Site getSite() {
        return site;
    }
}
