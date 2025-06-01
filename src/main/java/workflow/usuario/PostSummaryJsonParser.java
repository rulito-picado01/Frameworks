package workflow.usuario;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import workflow.framework.CodeNode;
import workflow.framework.Params;

import java.util.List;
import java.util.stream.Collectors;

public class PostSummaryJsonParser implements CodeNode {
    public final static String INPUT_JSON_KEY = "INPUT_JSON_KEY";
    private final String outputKey;

    public PostSummaryJsonParser(String outputKey) {
        this.outputKey = outputKey;
    }

    @Override
    public String name() {
        return "Parseando los Posts y generando un resumen";
    }

    @Override
    public void execute(Params params) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            List<Post> posts = mapper.readValue((String) params.get(INPUT_JSON_KEY), new TypeReference<List<Post>>() {
            });
            String summary = posts.stream()
                    .limit(5)
                    .map(post -> "• (" + post.id() + ") " + post.title())
                    .collect(Collectors.joining("\n"));

            params.set(outputKey, summary);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}

record Post(int userId, String title, int id, String body) {
}