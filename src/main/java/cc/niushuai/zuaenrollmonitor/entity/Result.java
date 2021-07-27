package cc.niushuai.zuaenrollmonitor.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Objects;

/**
 * 统一返回
 *
 * @author niushuai
 * @date 2021/7/27 16:35:16
 */
@Data
@Accessors(chain = true)
public class Result {

    private String code;
    private String message;

    private Object datas;

    private Result() {}

    private static Result getResult() {return new Result();}

    public static Result success() {

        return success(null);
    }

    public static Result success(Object data) {
        return success("200", "success", data);
    }

    public static Result success(String code, String message, Object data) {
        return getResult().setCode(code).setMessage(message).setDatas(data);
    }


    public static Result error() {

        return error(null);
    }

    public static Result error(String message) {
        return success("500", message, null);
    }

    public static Result error(String message, Object data) {
        return success("500", message, data);
    }

    public static Result error(String code, String message, Object data) {
        return getResult().setCode(code).setMessage(message).setDatas(data);
    }

    public boolean isSuccess() {
        return null != this && Objects.equals("200", this.getCode());
    }
}
