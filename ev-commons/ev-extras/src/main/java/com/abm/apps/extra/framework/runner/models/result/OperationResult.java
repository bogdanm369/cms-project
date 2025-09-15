package com.abm.apps.extra.framework.runner.models.result;

import com.abm.apps.extra.framework.runner.models.result.enums.OperationResultTypes;

public interface OperationResult<T> {

    OperationResultTypes getResultType();
    T getResult();
    Exception getExceptionResult();
}
