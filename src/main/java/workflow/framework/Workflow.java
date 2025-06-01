package workflow.framework;

import workflow.framework.nodos.Node;

import java.util.List;
import java.util.logging.*;

public class Workflow {
    private List<Node> nodes;
    private String nodeName = "";
    private Logger logger = Logger.getLogger(Workflow.class.getName());

    private Workflow(List<Node> nodes) {
        this.nodes = nodes;
        setUpLogger();
    }

    public static Workflow createFrom(List<Node> nodes) {
        return new Workflow(nodes);
    }

    public void run(Params params) {
        var start = System.currentTimeMillis();
        logger.info("Iniciando la ejecución del workflow con " + nodes.size() + " nodos.");
        try {
            for (Node node : nodes) {
                var startNode = System.currentTimeMillis();
                nodeName = node.name();
                logger.info("Empezando a ejecutar el nodo: " + nodeName);
                node.execute(params);
                var endNode = System.currentTimeMillis();
                logger.info("Nodo " + nodeName + " ejecutado en " + toSeconds(endNode, startNode) + " segundos.");
            }
            var end = System.currentTimeMillis();
            logger.info("Workflow ejecutado con éxito en " + toSeconds(end, start) + " segundos.");
        } catch (Exception e) {
            logger.severe("Error en nodo: " + nodeName);
            logger.severe("Error durante la ejecución del workflow: " + e.getMessage());
        }
    }

    private long toSeconds(long endNode, long startNode) {
        return (endNode - startNode) / 1000;
    }

    private void setUpLogger() {
        try {
            // Crear FileHandler que escribe en "app.log", con modo append (true)
            FileHandler fileHandler = new FileHandler("app.log", true);
            fileHandler.setFormatter(new Formatter() {
                @Override
                public String format(LogRecord record) {
                    return String.format("[%s] %s%n", record.getLevel(), record.getMessage());
                }
            });
            // Agregar el handler al logger
            logger.addHandler(fileHandler);
            // Opcional: desactivar logs en consola (System.err)
            logger.setUseParentHandlers(false);
            // Opcional: establecer nivel de log
            logger.setLevel(Level.ALL);
            fileHandler.setLevel(Level.ALL);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}