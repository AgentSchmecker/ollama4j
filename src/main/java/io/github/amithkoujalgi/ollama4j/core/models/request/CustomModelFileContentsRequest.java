package io.github.amithkoujalgi.ollama4j.core.models.request;

import static io.github.amithkoujalgi.ollama4j.core.utils.Utils.getObjectMapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CustomModelFileContentsRequest {
  private String name;
  private String modelfile;

  @Override
  public String toString() {
    try {
      return getObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(this);
    } catch (JsonProcessingException e) {
      throw new RuntimeException(e);
    }
  }
}
