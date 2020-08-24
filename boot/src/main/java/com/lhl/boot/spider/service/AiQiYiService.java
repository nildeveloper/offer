package com.lhl.boot.spider.service;

import com.lhl.boot.spider.pipeline.AiQiYiListPipeline;
import com.lhl.boot.spider.processor.AiQiYiListProcessor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.downloader.selenium.SeleniumDownloader;
import us.codecraft.webmagic.pipeline.ConsolePipeline;

/**
 * Created with IntelliJ IDEA
 *
 * @author liuhaolu01
 * @date 2020-08-24
 * @time 11:14
 * @describe:
 */
@Service
@Slf4j
public class AiQiYiService {

    public void getAiQiYiList(String url) {
        SeleniumDownloader seleniumDownloader = new SeleniumDownloader("D:\\Download\\chromedriver.exe");
        seleniumDownloader.setSleepTime(2000);
        log.info("get list schedule start...");
        Spider spider = Spider.create(new AiQiYiListProcessor());
//        spider.addPipeline(new ConsolePipeline());
        spider.addPipeline(new AiQiYiListPipeline());
        spider.thread(1);
        spider.addUrl(url);
        spider.setDownloader(seleniumDownloader);
        spider.start();
        spider.setExitWhenComplete(true);
        log.info("get lists schedule finish");
    }

}
