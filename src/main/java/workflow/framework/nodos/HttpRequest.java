package workflow.framework.nodos;

import workflow.framework.Params;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpResponse;

public class HttpRequest extends AbstractNode {
    private String url;
    private String outputKey;

    public HttpRequest(String name, String url, String outputKey) {
        super(name, "Http Request");
        this.url = url;
        this.outputKey = outputKey;
    }

    @Override
    public void execute(Params params) {
        try {
            var client = HttpClient.newHttpClient();
            var request = java.net.http.HttpRequest.newBuilder().uri(URI.create(url))
                    .build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            params.set(outputKey, response.body());
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException("HTTP request failed: " + e.getMessage(), e);
        }
    }
}
