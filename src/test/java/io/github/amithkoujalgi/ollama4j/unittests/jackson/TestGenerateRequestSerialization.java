package io.github.amithkoujalgi.ollama4j.unittests.jackson;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import io.github.amithkoujalgi.ollama4j.core.models.generate.OllamaGenerateRequestBuilder;
import io.github.amithkoujalgi.ollama4j.core.models.generate.OllamaGenerateRequestModel;
import io.github.amithkoujalgi.ollama4j.core.utils.OptionsBuilder;

public class TestGenerateRequestSerialization extends AbstractSerializationTest<OllamaGenerateRequestModel> {

    private OllamaGenerateRequestBuilder builder;

    @BeforeEach
    public void init() {
        builder = OllamaGenerateRequestBuilder.getInstance("DummyModel");
    }

    @Test
    public void testRequestOnlyMandatoryFields() {
        OllamaGenerateRequestModel req = builder.withPrompt("Some prompt").build();

        String jsonRequest = serialize(req);
        assertEqualsAfterUnmarshalling(deserialize(jsonRequest, OllamaGenerateRequestModel.class), req);
    }

    @Test
    public void testRequestWithOptions() {
        OptionsBuilder b = new OptionsBuilder();
        OllamaGenerateRequestModel req =
                builder.withPrompt("Some prompt").withOptions(b.setMirostat(1).build()).build();

        String jsonRequest = serialize(req);
        OllamaGenerateRequestModel deserializeRequest = deserialize(jsonRequest, OllamaGenerateRequestModel.class);
        assertEqualsAfterUnmarshalling(deserializeRequest, req);
        assertEquals(1, deserializeRequest.getOptions().get("mirostat"));
    }

    @Test
    public void testWithJsonFormat() {
        OllamaGenerateRequestModel req =
                builder.withPrompt("Some prompt").withGetJsonResponse().build();

        String jsonRequest = serialize(req);
        // no jackson deserialization as format property is not boolean ==> omit as deserialization
        // of request is never used in real code anyways
        JSONObject jsonObject = new JSONObject(jsonRequest);
        String requestFormatProperty = jsonObject.getString("format");
        assertEquals("json", requestFormatProperty);
    }

}
