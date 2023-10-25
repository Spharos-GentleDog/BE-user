package egenius.global.exception;

import egenius.global.base.BaseResponseStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class BaseException extends Exception {
    private BaseResponseStatus status;

    public void setStatus(BaseResponseStatus status) {
        this.status = status;
    }
}
