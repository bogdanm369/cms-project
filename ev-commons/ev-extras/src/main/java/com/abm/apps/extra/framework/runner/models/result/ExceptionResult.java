package com.abm.apps.extra.framework.runner.models.result;

import com.abm.apps.extra.framework.runner.models.result.enums.OperationResultTypes;

public interface ExceptionResult extends OperationResult {


    @Override
    default OperationResultTypes getResultType() {
        return OperationResultTypes.EXCEPTION;
    }
}
