package org.selbowgreaser.employeeservice.builder;

import org.selbowgreaser.employeeservice.util.XMLGregorianCalendarUtil;
import org.selbowgreaser.soap.api.employee_service.Result;

import java.math.BigInteger;

public class SuccessResultBuilder {

    public static Result buildSuccessResult(Integer code) {
        Result result = new Result();

        result.setCode(BigInteger.valueOf(code));
        result.setTimestamp(XMLGregorianCalendarUtil.now());
        result.setMessage("SUCCESS");

        return result;
    }
}
