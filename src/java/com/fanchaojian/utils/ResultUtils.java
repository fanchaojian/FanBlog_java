package com.fanchaojian.utils;

/**
 * 统一返回结果工具类
 * @author fanchaojian
 * @date 2020-9-29 - 15:48
 */
public class ResultUtils {
    public static JsonResult success(Object object) {
        JsonResult result = new JsonResult();
        result.setCode(0);
        result.setMsg("成功");
        result.setData(object);
        return result;
    }

    public static JsonResult success() {
        return success(null);
    }

    public static JsonResult error(Integer code, String msg) {
        JsonResult result = new JsonResult();
        result.setCode(code);
        result.setMsg(msg);
        return result;
    }
}
