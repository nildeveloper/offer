package com.lhl.boot;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.lhl.boot.entity.TestEntity;
import com.lhl.boot.spider.service.AiQiYiService;
import com.lhl.boot.utils.FreemarkerUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.xhtmlrenderer.swing.Java2DRenderer;
import org.xhtmlrenderer.util.FSImageWriter;
import reactor.core.publisher.Mono;

import java.awt.image.BufferedImage;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class BootApplicationTests {

    @Autowired
    private AiQiYiService aiQiYiService;

//    @Test
//    public void contextLoads() {
//
//    }


    public static String TEMPLET_SRC = "/ftl/";

    public static final String SEND_BIRTHDAY_EMAIL_TEMPLATE_NAME = "sendBirthdayEmailTemplate.ftl";

    @Test
    public void test() throws Exception {

    }

    public static void main(String[] args) throws Exception {
        String TEMPLET_SRC = "/ftl/";
        String SEND_BIRTHDAY_EMAIL_TEMPLATE_NAME = "sendBirthdayEmailTemplate.ftl";
        Map<String, Object> map = new HashMap<>();
        map.put("name", "张三");
        map.put("url", "https://oan.bitmain.vip/new/file/group1/M00/00/18/wKhuLV1AKyGADiitABVHCPeB9Jg419.png");
        String html = FreemarkerUtil.processTemplate(TestEntity.class, TEMPLET_SRC,
                SEND_BIRTHDAY_EMAIL_TEMPLATE_NAME, map);
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("test"), "UTF-8"));
        writer.write(html);
        writer.newLine();
        writer.flush();
        writer.close();

        File file = new File("test");
        Java2DRenderer renderer = new Java2DRenderer(file, 100, 100);
        BufferedImage image = renderer.getImage();
        FSImageWriter imageWriter = new FSImageWriter();
        imageWriter.setWriteCompressionQuality(0.9f);
        imageWriter.write(image, "pic");
        File pic = new File("pic");
    }

    @Test
    public void testGetAiQiYiList() throws InterruptedException {
        System.setProperty("selenuim_config", "D:\\workspace\\offer\\boot\\src\\main\\resources\\config.ini");
        String url = "https://www.iqiyi.com/v_2ffkwycne88.html?vfrm=pcw_dianshiju&vfrmblk=F&vfrmrst=711219_dianshiju_tbrb_image1";
        aiQiYiService.getAiQiYiList(url);
        Thread.currentThread().join();
    }

    @Test
    public void testWebClient() throws IOException {
        String url = "https://www.iqiyi.com/v_2ffkwycne88.html?vfrm=pcw_dianshiju&vfrmblk=F&vfrmrst=711219_dianshiju_tbrb_image1";
        WebClient webClient = new WebClient();
        // 1 启动JS
        webClient.getOptions().setJavaScriptEnabled(true);
        // 2 禁用Css，可避免自动二次请求CSS进行渲染
        webClient.getOptions().setCssEnabled(false);
        // 3 启动客户端重定向
        webClient.getOptions().setRedirectEnabled(true);
        // 4 js运行错误时，是否抛出异常
        webClient.getOptions().setThrowExceptionOnScriptError(false);
        // 5 设置超时
        webClient.getOptions().setTimeout(50000);
        webClient.getOptions().setUseInsecureSSL(true);
        HtmlPage htmlPage = webClient.getPage(url);
        // 等待JS驱动dom完成获得还原后的网页
        webClient.waitForBackgroundJavaScript(10000);
        // 网页内容
        log.info(htmlPage.asText());
        webClient.close();
    }

}
