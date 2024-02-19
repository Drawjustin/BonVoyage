package ArtBridge.ArtBridgelogin.service.errorMessage;

import org.springframework.dao.DataAccessException;

public class MyDataAccessException extends DataAccessException {

    public MyDataAccessException(String msg) {
        super(msg);
    }

    public MyDataAccessException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
