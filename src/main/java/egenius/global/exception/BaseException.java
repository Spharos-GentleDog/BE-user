package egenius.global.exception;

import egenius.global.base.BaseResponseStatus;
import lombok.Getter;

@Getter
public class BaseException extends Exception {
    private BaseResponseStatus status;

    public BaseException(BaseResponseStatus status) {
        this.status = status;
    }

}
