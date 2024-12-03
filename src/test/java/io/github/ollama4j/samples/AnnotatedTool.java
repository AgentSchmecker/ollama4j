package io.github.ollama4j.samples;

import io.github.ollama4j.OllamaAPI;
import io.github.ollama4j.exceptions.OllamaBaseException;
import io.github.ollama4j.exceptions.ToolInvocationException;
import io.github.ollama4j.tools.OllamaToolsResult;
import io.github.ollama4j.tools.annotations.OllamaToolService;
import io.github.ollama4j.tools.annotations.ToolProperty;
import io.github.ollama4j.tools.annotations.ToolSpec;
import io.github.ollama4j.types.OllamaModelType;
import io.github.ollama4j.utils.Options;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;

@OllamaToolService(providers = {AnnotatedTool.class})
public class AnnotatedTool {

    @ToolSpec(desc = "Computes the most important constant all around the globe!")
    public String computeMkeConstant(@ToolProperty(desc = "Number of digits that shall be returned") Integer noOfDigits ){
        return BigDecimal.valueOf((long)(Math.random()*1000000L),noOfDigits).toString();
    }

    public static void main(String[] args) throws ToolInvocationException, OllamaBaseException, IOException, ClassNotFoundException, InterruptedException {
        final AnnotatedTool annotatedTool = new AnnotatedTool();
        annotatedTool.test();
    }

    public void test() throws ClassNotFoundException, ToolInvocationException, OllamaBaseException, IOException, InterruptedException {
        OllamaAPI api = new OllamaAPI("http://localhost:11434");
        api.setRequestTimeoutSeconds(120);
        api.registerTools();
        final OllamaToolsResult ollamaToolsResult = api.generateWithTools("mixtral:8x22b", "Give me the most Important constant of all around the globe!", new Options(new HashMap<>()));
        System.err.println("Tool: " +ollamaToolsResult.getToolResults().get(0).getFunctionName());
        System.err.println("Params: " +ollamaToolsResult.getToolResults().get(0).getFunctionArguments());
        System.err.println("Result: " +ollamaToolsResult.getToolResults().get(0).getResult());

        System.err.println("Answer: " + ollamaToolsResult.getModelResult().getResponse());

    }


}
