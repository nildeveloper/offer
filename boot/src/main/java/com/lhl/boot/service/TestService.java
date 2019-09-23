package com.lhl.boot.service;

import com.lhl.boot.entity.TestEntity;
import com.lhl.boot.utils.FreemarkerUtil;
import org.springframework.stereotype.Service;
import org.xhtmlrenderer.swing.Java2DRenderer;
import org.xhtmlrenderer.util.FSImageWriter;

import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: lhl
 * Date: 2019-07-21
 * Time: 10:44
 * Description:
 */
@Service
public class TestService {

    public void test() throws Exception {
        String TEMPLET_SRC = "/ftl/";
        String SEND_BIRTHDAY_EMAIL_TEMPLATE_NAME = "sendBirthdayEmailTemplate.ftl";
        Map<String, Object> map = new HashMap<>();
        map.put("name", "张三");
        map.put("url", "https://oan.bitmain.vip/new/file/group1/M00/00/18/wKhuLV1AKyGADiitABVHCPeB9Jg419.png");
        String html = FreemarkerUtil.processTemplate(TestEntity.class, TEMPLET_SRC,
                SEND_BIRTHDAY_EMAIL_TEMPLATE_NAME, map);
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("test.html"), "UTF-8"));
        writer.write(html);
        writer.newLine();
        writer.flush();
        writer.close();

        File file = new File("test.html");
        Java2DRenderer renderer = new Java2DRenderer(file, 920, 600);
        BufferedImage image = renderer.getImage();
        FSImageWriter imageWriter = new FSImageWriter();
        imageWriter.setWriteCompressionQuality(0.9f);
        imageWriter.write(image, "pic.jpg");
        File pic = new File("pic.jgp");
    }
}
