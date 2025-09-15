package com.abm.apps.extra.framework.runner.models.result;

import com.abm.apps.extra.framework.runner.models.result.enums.OperationResultTypes;

public interface CompletionResult<T> extends OperationResult<T> {


    @Override
    default OperationResultTypes getResultType() {
        return OperationResultTypes.COMPLETION;
    }
}
