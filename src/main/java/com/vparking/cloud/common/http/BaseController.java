package com.vparking.cloud.common.http;

import com.vparking.cloud.common.http.exception.BusinessException;
import com.vparking.cloud.common.http.response.Response;
import com.vparking.cloud.common.http.response.ReturnStatus;
import io.swagger.annotations.Api;
import org.apache.http.HttpException;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import java.io.PrintWriter;
import java.io.StringWriter;

@RestController
@Api(value = "/base", hidden = true)
public class BaseController {

    private final Logger logger = Logger.getLogger(BaseController.class);

//    @Autowired
//    public HttpServletRequest request;

//    @InitBinder
//    protected void init(HttpServletRequest request, ServletRequestDataBinder binder) throws Exception {
//        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//        dateFormat.setLenient(false);
//        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
//    }

    @ExceptionHandler
    public Response<?> exp(Exception ex) {

        // 根据不同错误转向不同页面
        if (ex instanceof BusinessException) {
            BusinessException be = (BusinessException) ex;
            logger.error("[" + be.getCode() + ":" + be.getDescription() + "]" + ex.getMessage());
            System.err.println("[" + be.getCode() + ":" + be.getDescription() + "]" + ex.getMessage());
            return Response.fail(be.getReturnStatus(), ex);
        } else if (ex instanceof NullPointerException) {
            handleLog(ex);
            System.err.println("[" + ReturnStatus.NULL_POINT_ERROR.getValue() + ":"
                    + ReturnStatus.NULL_POINT_ERROR.getDescription() + "]" + ex.getMessage());
            return Response.fail(ReturnStatus.NULL_POINT_ERROR, ex);
        } else if (ex instanceof HttpException) {
            handleLog(ex);
            System.err.println("[" + ReturnStatus.NOT_FOUND.getValue() + ":" + ReturnStatus.NOT_FOUND.getDescription()
                    + "]" + ex.getMessage());
            return Response.fail(ReturnStatus.NOT_FOUND, ex);
        } else {
            handleLog(ex);
            System.err.println("[" + ReturnStatus.OTHER_ERROR.getValue() + ":"
                    + ReturnStatus.OTHER_ERROR.getDescription() + "]" + ex.getMessage());
            return Response.fail(ReturnStatus.OTHER_ERROR, ex);
        }
        // return Response.fail();
    }

    private void handleLog(Exception ex) {
        StringBuffer logBuffer = new StringBuffer();
//        if (request != null) {
//            logBuffer.append("  request method=" + request.getMethod());
//            logBuffer.append("  url=" + request.getRequestURL());
//        }
        if (ex instanceof Exception) {
            logBuffer.append("  moreInfo=" + ex.getMessage());
        }
        if (ex != null) {
            logBuffer.append("  exception:" + exceptonToString(ex));
        }

        // System.out.println("#####handleLog = " + logBuffer.toString());
        logger.error(logBuffer.toString());
    }

    private String exceptonToString(Throwable e) {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw, true);
        e.printStackTrace(pw);
        pw.flush();
        sw.flush();
        return sw.toString();
    }

}
