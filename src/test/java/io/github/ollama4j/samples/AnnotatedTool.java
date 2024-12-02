package io.github.ollama4j.samples;

import io.github.ollama4j.OllamaAPI;
import io.github.ollama4j.tools.annotations.OllamaToolService;
import io.github.ollama4j.tools.annotations.ToolProperty;
import io.github.ollama4j.tools.annotations.ToolProvider;
import io.github.ollama4j.tools.annotations.ToolSpec;

import java.math.BigDecimal;

@OllamaToolService(providers = {AnnotatedTool.class})
public class AnnotatedTool {

    @ToolSpec(desc = "Computes the most important constant all around the globe!")
    public String computeMkeConstant(@ToolProperty(desc = "Number of digits that shall be returned") Integer noOfDigits ){
        return BigDecimal.valueOf((long)(Math.random()*1000000L),noOfDigits).toString();
    }

    public static void main(String[] args) throws ClassNotFoundException {
        OllamaAPI api = new OllamaAPI("http://localhost:11434");
        api.registerTools();
    }


}
