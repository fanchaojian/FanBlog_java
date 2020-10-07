package com.fanchaojian.domain;

import org.springframework.stereotype.Service;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author fanchaojian
 * @date 2020-9-21 - 15:33
 */
public class InvokeResult implements Serializable {
    private String code ;

    private String message ;

    public InvokeResult() {
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
