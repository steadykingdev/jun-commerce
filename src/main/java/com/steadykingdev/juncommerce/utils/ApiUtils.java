package com.steadykingdev.juncommerce.utils;

import com.steadykingdev.juncommerce.dto.ApiError;
import com.steadykingdev.juncommerce.dto.ApiResult;

public class ApiUtils {

    public static <T> ApiResult<T> success(T response) {
        return new ApiResult<>(true, response, null);
    }

    public static ApiResult<?> error(String message, int status) {
        return new ApiResult<>(false, null, new ApiError(message, status));
    }
}
