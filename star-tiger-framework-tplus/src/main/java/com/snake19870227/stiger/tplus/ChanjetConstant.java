package com.snake19870227.stiger.tplus;

/**
 * @author Bu HuaYang (buhuayang1987@foxmail.com)
 * 2021/05/08
 */
public class ChanjetConstant {

    public static final String RESPONSE_SUCCESS_CODE = "200";

    public static final String SERVER_URL = "https://openapi.chanjet.com";

    public static final String HEADER_APP_KEY = "appKey";

    public static final String HEADER_APP_SECRET = "appSecret";

    public static final String HEADER_OPEN_TOKEN = "openToken";

    public static final String CFG_KEY_TEMP_AUTH_CODE = "chanjet_temp_auth_code";

    public static final String CFG_KEY_APP_TICKET = "chanjet_app_ticket";

    public static final String CFG_KEY_APP_ACCESS_TOKEN = "chanjet_app_access_token";

    public static final String CFG_KEY_PERMANENT_AUTH_CODE = "chanjet_permanent_auth_code";

    public static final String CFG_KEY_ORG_ACCESS_TOKEN = "chanjet_org_access_token";

    public static final String FLOW_ACCESS_TOKEN = "00000000000000000000000000000001";

    public static class OpenApi {

        public static final String AUTH_RESEND_APP_TICKET = SERVER_URL + "/auth/appTicket/resend";

        public static final String AUTH_GET_APP_ACCESS_TOKEN = SERVER_URL + "/auth/appAuth/getAppAccessToken";

        public static final String AUTH_GET_PERMANENT_AUTH_CODE = SERVER_URL + "/auth/orgAuth/getPermanentAuthCode";

        public static final String AUTH_GET_ORG_ACCESS_TOKEN = SERVER_URL + "/auth/orgAuth/getOrgAccessToken";

        public static final String AUTH_GET_ACCESS_TOKEN = SERVER_URL + "/auth/getToken";

        public static final String AUTH_GET_ACCESS_TOKEN_BY_PERMANENT_CODE = SERVER_URL + "/auth/token/getTokenByPermanentCode";

        public static final String AUTH_REFRESH_TOKEN = SERVER_URL + "/auth/refreshToken";

    }

    /**
     * 00--普通发票，01--专用发票，02–收据；为空时，默认按收据处理
     */
    public static class InvoiceType {
        public static final String CODE_00 = "00";
        public static final String CODE_01 = "01";
        public static final String CODE_02 = "02";
    }

    /**
     * 业务类型编码，15–普通销售；16–销售退货
     */
    public static class BusinessType {
        public static final String CODE_15 = "15";
        public static final String CODE_16 = "16";
    }

    /**
     * 201,销售出库
     * 202,材料出库
     * 203,赠品出库
     * 204,盘亏出库
     * 205,转换出库
     * 206,组装出库
     * 207,拆卸出库
     * 208,其他出库
     * 209,线上销售
     * 210,样品
     */
    public static class RdStyle {
        public static final String CODE_201 = "201";
        public static final String CODE_202 = "202";
        public static final String CODE_203 = "203";
        public static final String CODE_204 = "204";
        public static final String CODE_205 = "205";
        public static final String CODE_206 = "206";
        public static final String CODE_207 = "207";
        public static final String CODE_208 = "208";
        public static final String CODE_209 = "209";
        public static final String CODE_210 = "210";
    }
}
