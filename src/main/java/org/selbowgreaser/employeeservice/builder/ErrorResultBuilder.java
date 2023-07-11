package org.selbowgreaser.employeeservice.builder;

import org.selbowgreaser.employeeservice.util.XMLGregorianCalendarUtil;
import org.selbowgreaser.soap.api.employee_service.Result;

import java.math.BigInteger;
import java.util.List;

public class ErrorResultBuilder {

    public static final String DELIMITER = ";";

    public static Result buildErrorResult(Integer code, List<String> errors) {
        Result result = new Result();

        result.setCode(BigInteger.valueOf(code));
        result.setTimestamp(XMLGregorianCalendarUtil.now());
        result.setMessage(String.join(DELIMITER, errors));

        return result;
    }
}
