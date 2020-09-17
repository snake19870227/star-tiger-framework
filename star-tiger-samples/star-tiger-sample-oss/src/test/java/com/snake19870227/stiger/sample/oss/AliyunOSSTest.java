package com.snake19870227.stiger.sample.oss;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.MimeTypeUtils;
import com.snake19870227.stiger.oss.StarTigerOssStorage;

/**
 * @author BuHuaYang
 * 2020/9/6
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@AutoConfigureMockMvc
public class AliyunOSSTest {

    @Autowired
    @Qualifier("aliyunStorage")
    private StarTigerOssStorage ossStorage;

    @Test
    public void testOss() throws Exception {
        File file = new File("D:\\Administrator\\Pictures\\图\\IMG_1845.JPG");

        InputStream inputStream = new FileInputStream(file);

        ossStorage.putObject("720yc", "IMG_1845.JPG", inputStream, MimeTypeUtils.IMAGE_JPEG_VALUE);

        ossStorage.removeObject("720yc", "IMG_1845.JPG");
    }
}
