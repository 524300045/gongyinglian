/**
 * Copyright 2018-2020 stylefeng & fengshuonan (https://gitee.com/stylefeng)
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package cn.stylefeng.guns.sys.core.exception.aop;

import cn.stylefeng.guns.base.auth.context.LoginContextHolder;
import cn.stylefeng.guns.base.auth.exception.AuthException;
import cn.stylefeng.guns.base.auth.exception.PermissionException;
import cn.stylefeng.guns.base.auth.exception.enums.AuthExceptionEnum;
import cn.stylefeng.guns.sys.core.exception.InvalidKaptchaException;
import cn.stylefeng.guns.sys.core.exception.enums.BizExceptionEnum;
import cn.stylefeng.guns.sys.core.log.LogManager;
import cn.stylefeng.guns.sys.core.log.factory.LogTaskFactory;
import cn.stylefeng.roses.kernel.model.exception.ServiceException;
import cn.stylefeng.roses.kernel.model.response.ErrorResponseData;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.internal.engine.path.PathImpl;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.Set;

import static cn.stylefeng.roses.core.util.HttpContext.getIp;
import static cn.stylefeng.roses.core.util.HttpContext.getRequest;

/**
 * ??????????????????????????????????????????????????????????????????@RequestMapping?????????????????????????????????
 *
 * @author fengshuonan
 * @date 2016???11???12??? ??????3:19:56
 */
@ControllerAdvice
@Order(-100)
@Slf4j
public class GlobalExceptionHandler {

    /**
     * ??????????????????
     *
     * @author fengshuonan
     * @Date 2020/2/5 11:50 ??????
     */
    @ExceptionHandler(MissingServletRequestParameterException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorResponseData handleError(MissingServletRequestParameterException e) {
        log.warn("Missing Request Parameter", e);
        String message = String.format("Missing Request Parameter: %s", e.getParameterName());
        return new ErrorResponseData(400, message);
    }

    /**
     * ??????????????????
     *
     * @author fengshuonan
     * @Date 2020/2/6 10:18 ??????
     */
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorResponseData handleError(MethodArgumentTypeMismatchException e) {
        log.warn("Method Argument Type Mismatch", e);
        String message = String.format("Method Argument Type Mismatch: %s", e.getName());
        return new ErrorResponseData(400, message);
    }

    /**
     * ??????????????????
     *
     * @author fengshuonan
     * @Date 2020/2/6 10:19 ??????
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorResponseData handleError(MethodArgumentNotValidException e) {
        log.warn("Method Argument Not Valid", e);
        BindingResult result = e.getBindingResult();
        FieldError error = result.getFieldError();
        String message = String.format("%s:%s", error.getField(), error.getDefaultMessage());
        return new ErrorResponseData(400, message);
    }

    /**
     * ????????????????????????
     *
     * @author fengshuonan
     * @Date 2020/2/6 9:49 ??????
     */
    @ExceptionHandler(BindException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorResponseData handleError(BindException e) {
        log.warn("Bind Exception", e);
        FieldError error = e.getFieldError();
        String message = String.format("%s:%s", error.getField(), error.getDefaultMessage());
        return new ErrorResponseData(400, message);
    }

    /**
     * ????????????????????????
     *
     * @author fengshuonan
     * @Date 2020/2/8 12:20
     */
    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorResponseData handleError(ConstraintViolationException e) {
        log.warn("Constraint Violation", e);
        Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
        ConstraintViolation<?> violation = violations.iterator().next();
        String path = ((PathImpl) violation.getPropertyPath()).getLeafNode().getName();
        String message = String.format("%s:%s", path, violation.getMessage());
        return new ErrorResponseData(400, message);
    }

    /**
     * ????????????????????????
     *
     * @author fengshuonan
     * @Date 2020/2/6 9:49 ??????
     */
    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorResponseData handleError(HttpMessageNotReadableException e) {
        log.warn("HttpMessageNotReadableException ", e);
        String message = String.format("HttpMessageNotReadableException:%s", e.getMessage());
        return new ErrorResponseData(400, message);
    }

    /**
     * ????????????--??????????????????????????????????????????????????????token????????????
     *
     * @author fengshuonan
     * @Date 2020/2/6 11:14 ??????
     */
    @ExceptionHandler(AuthException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ResponseBody
    public ErrorResponseData unAuth(AuthException e) {
        return new ErrorResponseData(e.getCode(), e.getMessage());
    }

    /**
     * ????????????--??????????????????
     *
     * @author fengshuonan
     * @Date 2020/2/6 11:14 ??????
     */
    @ExceptionHandler(PermissionException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ResponseBody
    public ErrorResponseData permissionExpection(PermissionException e) {
        return new ErrorResponseData(e.getCode(), e.getMessage());
    }

    /**
     * ?????????????????????
     *
     * @author fengshuonan
     * @Date 2020/2/6 11:14 ??????
     */
    @ExceptionHandler(InvalidKaptchaException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorResponseData credentials(InvalidKaptchaException e) {
        String username = getRequest().getParameter("username");
        LogManager.me().executeLog(LogTaskFactory.loginLog(username, "???????????????", getIp()));
        return new ErrorResponseData(AuthExceptionEnum.VALID_CODE_ERROR.getCode(), AuthExceptionEnum.VALID_CODE_ERROR.getMessage());
    }

    /**
     * ??????????????????
     *
     * @author fengshuonan
     * @Date 2020/2/6 11:14 ??????
     */
    @ExceptionHandler(ServiceException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ErrorResponseData bussiness(ServiceException e) {
        log.error("????????????:", e);
        if (LoginContextHolder.getContext().hasLogin()) {
            LogManager.me().executeLog(LogTaskFactory.exceptionLog(LoginContextHolder.getContext().getUserId(), e));
        }
        getRequest().setAttribute("tip", e.getMessage());
        return new ErrorResponseData(e.getCode(), e.getMessage());
    }

    /**
     * ??????????????????????????????
     *
     * @author fengshuonan
     * @Date 2020/2/6 11:15 ??????
     */
    @ExceptionHandler(Throwable.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ErrorResponseData notFount(Throwable e) {
        log.error("???????????????:", e);
        if (LoginContextHolder.getContext().hasLogin()) {
            LogManager.me().executeLog(LogTaskFactory.exceptionLog(LoginContextHolder.getContext().getUserId(), e));
        }
        String message = String.format("??????????????????????????????: %s", e.getMessage());
        getRequest().setAttribute("tip", message);
        return new ErrorResponseData(BizExceptionEnum.SERVER_ERROR.getCode(), message);
    }
}
