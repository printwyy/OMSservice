package com.omscloud.compilerworker.service.Impl;

import com.omscloud.compilerworker.model.*;
import com.omscloud.compilerworker.service.IMoTransfer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.stream.Collectors;
import java.util.List;

@Slf4j
@Service
public class MoTransfer implements IMoTransfer {
    @Override
    public String generateMoFileContent(ModelJson modelJson) {
        StringBuilder moFile = new StringBuilder("model " + modelJson.getName());

        // 11) Using the modelJson data that we built up we extract the data for the modelica
        //     code and build up the code.

        List<Component> sortedComponents = modelJson.getComponents()
                .stream()
                .sorted(Comparator.comparing(Component::getUri).thenComparing(Component::getName))
                .collect(Collectors.toList());

        sortedComponents.forEach(data -> {
            String parametersString = getParametersString(data);
            String modifiersString = getModifiersString(data);
            moFile.append("\n  ")
                    .append(data.getUri())
                    .append(' ')
                    .append(data.getName())
                    .append(parametersString)
                    .append(modifiersString)
                    .append(';');
        });

        moFile.append("\nequation");

        List<Connection> sortedConnections = modelJson.getConnections()
                .stream()
                .sorted(Comparator.comparing(Connection::getSrc).thenComparing(Connection::getDst))
                .collect(Collectors.toList());

        sortedConnections.forEach(data -> {
            moFile.append("\n  connect(")
                    .append(data.getSrc())
                    .append(", ")
                    .append(data.getDst())
                    .append(");");
        });

        moFile.append("\nend ").append(modelJson.getName()).append(';');

        return moFile.toString();
    }

    @Override
    public String getParametersString(Component component) {
        if (component.getParameters().isEmpty()) {
            return "";
        }

        StringBuilder paramsStringBuilder = new StringBuilder("(");
        component.getParameters().forEach((p, value) -> {
            paramsStringBuilder.append(p)
                    .append(" = ")
                    .append(value)
                    .append(", ");
        });

        if (!component.getParameters().isEmpty()) {
            paramsStringBuilder.deleteCharAt(paramsStringBuilder.length() - 2);
        }

        return paramsStringBuilder.append(")").toString();
    }
    @Override
    public String getModifiersString(Component component) {
        return component.getModifiers() != null ? "(" + component.getModifiers() + ")" : "";
    }
}
