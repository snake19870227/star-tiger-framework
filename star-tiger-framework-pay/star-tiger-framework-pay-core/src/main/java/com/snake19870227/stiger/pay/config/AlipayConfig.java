package com.snake19870227.stiger.pay.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Configuration;
import com.alipay.easysdk.factory.Factory;
import com.alipay.easysdk.kernel.AlipayConstants;
import com.alipay.easysdk.kernel.Client;
import com.alipay.easysdk.kernel.Config;
import com.alipay.easysdk.kernel.Context;
import com.alipay.easysdk.kms.aliyun.AliyunKMSClient;
import com.alipay.easysdk.kms.aliyun.AliyunKMSSigner;
import com.aliyun.tea.TeaModel;
import com.snake19870227.stiger.pay.properties.AlipayProperties;
import com.snake19870227.stiger.pay.properties.StarTigerPayProperties;

/**
 * @author Bu HuaYang (buhuayang1987@foxmail.com)
 * 2020/08/25
 */
@Configuration
public class AlipayConfig implements ApplicationContextAware {

    private static final Logger logger = LoggerFactory.getLogger(AlipayConfig.class);

    public static final String BEAN_PREFIX = "Alipay_";

    public static final String COMMON_PREFIX = BEAN_PREFIX + "Common_";
    public static final String PAGE_PREFIX = BEAN_PREFIX + "Page_";
    public static final String WAP_PREFIX = BEAN_PREFIX + "Wap_";
    public static final String APP_PREFIX = BEAN_PREFIX + "App_";

    private final StarTigerPayProperties starTigerPayProperties;

    public AlipayConfig(StarTigerPayProperties starTigerPayProperties) {
        logger.info("实例化配置类：" + this.getClass().getName());
        this.starTigerPayProperties = starTigerPayProperties;
    }

    private Config alipayConfig(AlipayProperties alipayProperties) {
        Config config = new Config();
        config.protocol = alipayProperties.getProtocol();
        config.gatewayHost = alipayProperties.getGatewayHost();
        config.signType = alipayProperties.getSignType();

        config.appId = alipayProperties.getAppId();

        config.merchantPrivateKey = alipayProperties.getMerchantPrivateKey();

        config.merchantCertPath = alipayProperties.getMerchantCertPath();
        config.alipayCertPath = alipayProperties.getAlipayCertPath();
        config.alipayRootCertPath = alipayProperties.getAlipayRootCertPath();

        config.alipayPublicKey = alipayProperties.getAlipayPublicKey();

        config.notifyUrl = alipayProperties.getNotifyUrl();

        config.encryptKey = alipayProperties.getEncryptKey();

        return config;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        DefaultListableBeanFactory beanFactory = (DefaultListableBeanFactory) applicationContext.getAutowireCapableBeanFactory();
        starTigerPayProperties.getAlipayMerchants().forEach((s, alipayProperties) -> {
            Context context = null;
            try {
                Config options = alipayConfig(alipayProperties);

                context = new Context(options, Factory.SDK_VERSION);

                if (AlipayConstants.AliyunKMS.equals(context.getConfig(AlipayConstants.SIGN_PROVIDER_CONFIG_KEY))) {
                    context.setSigner(new AliyunKMSSigner(new AliyunKMSClient(TeaModel.buildMap(options))));
                }

                beanFactory.registerBeanDefinition(
                        COMMON_PREFIX + alipayProperties.getAppId(),
                        BeanDefinitionBuilder.genericBeanDefinition(com.alipay.easysdk.payment.common.Client.class)
                                .addConstructorArgValue(new Client(context))
                                .getBeanDefinition()
                );

                beanFactory.registerBeanDefinition(
                        APP_PREFIX + alipayProperties.getAppId(),
                        BeanDefinitionBuilder.genericBeanDefinition(com.alipay.easysdk.payment.app.Client.class)
                                .addConstructorArgValue(new Client(context))
                                .getBeanDefinition()
                );

                beanFactory.registerBeanDefinition(
                        PAGE_PREFIX + alipayProperties.getAppId(),
                        BeanDefinitionBuilder.genericBeanDefinition(com.alipay.easysdk.payment.page.Client.class)
                                .addConstructorArgValue(new Client(context))
                                .getBeanDefinition()
                );

            } catch (Exception e) {
                logger.error("加载支付宝应用支付配置失败.[{}]", alipayProperties.getAppId(), e);
            }
        });
    }
}
