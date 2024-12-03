package io.github.ollama4j.tools;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.lang.reflect.Method;
import java.util.LinkedHashMap;
import java.util.Map;

@Setter
@Getter
@AllArgsConstructor
public class ReflectionalToolFunction implements ToolFunction{

    private Object functionHolder;
    private Method function;
    private LinkedHashMap<String,Object> arguments;

    @Override
    public Object apply(Map<String, Object> arguments) {
        LinkedHashMap<String, Object> argumentsCopy = new LinkedHashMap<>(arguments);
        for (String param : arguments.keySet()){
            argumentsCopy.replace(param,arguments.get(param));
        }
        try {
            return function.invoke(functionHolder, argumentsCopy.values());
        } catch (Exception e) {
            throw new RuntimeException("Failed to invoke tool: " + function.getName(), e);
        }
    }

}
