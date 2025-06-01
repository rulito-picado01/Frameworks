package workflow.usuario;

import workflow.framework.Params;
import workflow.framework.Workflow;
import workflow.framework.nodos.Crendenciales;
import workflow.framework.nodos.EmailSender;
import workflow.framework.nodos.HttpRequest;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        var httpRequest = new HttpRequest("Retrieving Posts",
                "https://jsonplaceholder.typicode.com/posts",
                PostSummaryJsonParser.INPUT_JSON_KEY);
        var jsonParser = new PostSummaryJsonParser(EmailSender.BODY_KEY);
        var emailSender = new EmailSender("Send Summary",
                new Crendenciales("3bf1ab1307588e", "fad0caf3d7c56d"));
        var workflow = Workflow.createFrom(List.of(httpRequest, jsonParser, emailSender));
        workflow.run(Params.empty());
    }
}
