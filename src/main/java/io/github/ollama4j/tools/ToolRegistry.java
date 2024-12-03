package io.github.ollama4j.tools;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class ToolRegistry {
    private final Map<String, ToolFunction> functionExecutions = new HashMap<>();
    private final Map<String, Tools.ToolSpecification> functionSpecs = new HashMap<>();

    public ToolFunction getFunction(String name) {
        return functionExecutions.get(name);
    }

    public void addFunction(String name, ToolFunction function, Tools.ToolSpecification spec) {
        functionExecutions.put(name, function);
        functionSpecs.put(name,spec);
    }

    public Collection<Tools.ToolSpecification> getRegisteredSpecs(){
        return functionSpecs.values();
    }
}
