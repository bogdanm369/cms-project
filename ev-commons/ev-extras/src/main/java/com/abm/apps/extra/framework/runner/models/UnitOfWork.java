package com.abm.apps.extra.framework.runner.models;

import com.abm.apps.extra.framework.runner.models.result.OperationResult;

public interface UnitOfWork<T> {


    OperationResult<T> getOperationResult();

}
