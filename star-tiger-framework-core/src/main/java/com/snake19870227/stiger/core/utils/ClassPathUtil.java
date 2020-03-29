package com.snake19870227.stiger.core.utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.core.type.classreading.CachingMetadataReaderFactory;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.util.ClassUtils;
import org.springframework.util.SystemPropertyUtils;
import com.snake19870227.stiger.core.StarTigerConstant;

/**
 * @author Bu HuaYang
 */
public class ClassPathUtil {

    private static final Logger logger = LoggerFactory.getLogger(ClassPathUtil.class);

    private static ResourcePatternResolver resourcePatternResolver = new PathMatchingResourcePatternResolver();
    private static MetadataReaderFactory metadataReaderFactory = new CachingMetadataReaderFactory(resourcePatternResolver);

    public static List<Resource> getResourceFolderFiles(String patternName, boolean includeJarFiles) {
        List<Resource> fileList = new ArrayList<>();
        String prefix = includeJarFiles ? ResourcePatternResolver.CLASSPATH_ALL_URL_PREFIX : ResourcePatternResolver.CLASSPATH_URL_PREFIX;
        try {
            for (Resource resource : resourcePatternResolver.getResources(prefix + patternName)) {
                if (!resource.isReadable()) {
                    logger.warn("无法读取 [{}], 跳过", resource.getURI());
                    continue;
                }
                fileList.add(resource);
            }
        } catch (IOException e) {
            logger.error("无法获取 'resources' 下 [{}] 文件", patternName, e);
        }
        return fileList;
    }

    public static List<Class<?>> scanPackageClass(String basePackage) {
        List<Class<?>> classList = new ArrayList<>();
        String basePackagePath = ClassUtils.convertClassNameToResourcePath(SystemPropertyUtils.resolvePlaceholders(basePackage));
        String packageSearchPath = ResourcePatternResolver.CLASSPATH_URL_PREFIX + basePackagePath + '/' + StarTigerConstant.PACKAGE_CLASSES_PATTERN;
        try {
            Resource[] resources = resourcePatternResolver.getResources(packageSearchPath);
            for (Resource resource : resources) {
                if (resource.isReadable()) {
                    MetadataReader metadataReader = metadataReaderFactory.getMetadataReader(resource);
                    Class<?> clazz = Class.forName(metadataReader.getClassMetadata().getClassName());
                    classList.add(clazz);
                }
            }
        } catch (Exception e) {
            logger.error("无法获取 '{}' 下 .Class 文件", basePackage, e);
        }
        return classList;
    }
}
