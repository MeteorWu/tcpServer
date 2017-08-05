package com.vparking.cloud.common.http.response;

/**
 * Created by TalenZhang on 2015/11/27.
 */
public class ReturnConstant {

    public static class SUCCESS {
        public static final int value = 200;
        public static final String desc = "操作成功完成";
    }

    public static class NOT_FOUND {
        public static final int value = 404;
        public static final String desc = "请求的服务不存在";
    }

    public static class INTERNAL_SERVER_ERROR {
        public static final int value = 500;
        public static final String desc = "请求的服务产生异常";
    }

    public static class FAULT_ACCESS_TOKEN {
        public static final int value = 900;
        public static final String desc = "您没有权限访问该接口";
    }

    public static class NO_ACCESS_TOKEN {
        public static final int value = 901;
        public static final String desc = "缺少访问授权参数";
    }

    public static class EXPIRE_ACCESS_TOKEN {
        public static final int value = 902;
        public static final String desc = "您授权已到期";
    }

    public static class KEY_IN_USED {
        public static final int value = 903;
        public static final String desc = "该字典项已经被使用，不允许删除";
    }

    public static class BUSINESS_ERROR {
        public static final int value = 10001;
        public static final String desc = "业务逻辑异常";
    }

    public static class RONGYUN_ERROR {
        public static final int value = 10002;
    }

    // Other
    public static class UPLOAD_ERROR {
        public static final int value = 1002;
        public static final String desc = "文件上传异常";
    }

    public static class NULL_POINT_ERROR {
        public static final int value = 2001;
        public static final String desc = "空指针异常";
    }

    public static class SQL_ERROR {
        public static final int value = 2002;
        public static final String desc = "SQL执行异常";
    }

    public static class FILE_NOT_FOUND_ERROR {
        public static final int value = 2003;
        public static final String desc = "文件不存在";
    }

    public static class OTHER_ERROR {
        public static final int value = 9001;
        public static final String desc = "其他异常";
    }

    // common : 10000
    public static class PARAMETER_EMPTY {
        public static final int value = 10003;
        public static final String desc = "参数不能为空";
    }

    public static class PARAMETER_INCORRECT {
        public static final int value = 10004;
        public static final String desc = "参数错误";
    }

    public static class CONFIG_KEY_REPEAT {
        public static final int value = 10005;
        public static final String desc = "字典项键重复";
    }

    public static class CONFIG_DETAIL_REPEAT {
        public static final int value = 10006;
        public static final String desc = "字典项值重复";
    }

    public static class BUSINESS_DISTRICTID_NOT_EXIST {
        public static final int value = 1000401;
        public static final String desc = "districtId不能为空";
    }
    public static class GLOBAL_CONFIG_FANGLE_ERROR {
        public static final int value = 1000402;
        public static final String desc = "校园新鲜事有效编辑时间设置过大，长度最大为9位";
    }
    public static class GLOBAL_CONFIG_USER_BEHAVIOR_VALID_ERROR {
        public static final int value = 1000403;
        public static final String desc = "用户有效操作记录时间不能大于60分钟";
    }
    public static class GLOBAL_CONFIG_USER_BEHAVIOR_INVALID_ERROR {
        public static final int value = 1000404;
        public static final String desc = "用户无效操作记录时间不能小于24小时";
    }
    // lock operation : 10100
}
