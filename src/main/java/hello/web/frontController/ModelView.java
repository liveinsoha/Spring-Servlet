package hello.web.frontController;

import java.util.HashMap;
import java.util.Map;

public class ModelView {

    String viewPath;
    Map<String, Object> model = new HashMap<>();

    public ModelView(String viewPath) {
        this.viewPath = viewPath;
    }

    public void addParam(String param, Object object) {
        model.put(param, object);
    }

    public String getViewPath() {
        return viewPath;
    }

    public Map<String, Object> getModel() {
        return model;
    }
}
