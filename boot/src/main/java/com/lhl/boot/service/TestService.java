package com.lhl.boot.service;

import com.lhl.boot.cache.AbstractCache;
import com.lhl.boot.dao.TestDao;
import com.lhl.boot.entity.TestEntity;
import com.lhl.boot.utils.FreemarkerUtil;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private TestDao testDao;

    public String testLog(String param) {
        System.out.println(param);
        return "log finished!";
    }

    private TestEntity getById(int id) {
        return testDao.get(id);
    }

    private TestEntity getFromCache(int id) {
        return new TestCache(id).get();
    }

    /**
     * 本地缓存
     */
    public static class TestCache extends AbstractCache<TestEntity> {

        @Autowired
        TestDao testDao;

        private static final String CACHE_KEY = "entityById_%s";

        private int id;

        public TestCache(int id) {
            this.id = id;
            this.key = String.format(CACHE_KEY, id);
        }

        @Override
        public TestEntity loadCache() {
            return testDao.get(id);
        }
    }
}
