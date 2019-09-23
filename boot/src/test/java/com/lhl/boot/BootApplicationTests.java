package com.lhl.boot;

import com.lhl.boot.entity.TestEntity;
import com.lhl.boot.utils.FreemarkerUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.xhtmlrenderer.swing.Java2DRenderer;
import org.xhtmlrenderer.util.FSImageWriter;

import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Map;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class BootApplicationTests {

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

}
