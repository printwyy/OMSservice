package com.omscloud.compilerworker.service;

import com.omscloud.compilerworker.model.*;

public interface IMoTransfer {
    public String generateMoFileContent(ModelJson modelJson);
    public String getParametersString(Component component);
    public String getModifiersString(Component component);
}
