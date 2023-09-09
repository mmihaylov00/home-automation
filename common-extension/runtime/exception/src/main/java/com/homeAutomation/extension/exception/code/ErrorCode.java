package com.homeAutomation.extension.exception.code;

import javax.ws.rs.core.Response;
import java.io.Serializable;

public interface ErrorCode extends Serializable {
    String name();

    Response.Status httpStatus();
}
