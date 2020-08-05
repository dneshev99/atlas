package bg.neshev.atlas.executor;

import bg.neshev.atlas.dto.BaseDTO;
import bg.neshev.atlas.dto.PathDTO;
import bg.neshev.atlas.pojo.PointPOJO;
import com.google.gson.Gson;
import org.springframework.stereotype.Service;
import org.zkoss.zk.ui.util.Clients;

import java.util.List;

@Service
public class JavascriptExecutor {
    private final Gson gson = new Gson();

    public void addBasesToMap(List<BaseDTO> bases) {
        Clients.evalJavaScript("addBasesToMap(" + gson.toJson(bases) + ")");
    }

    public void addPathsToMap(List<PathDTO> paths) {
        Clients.evalJavaScript("addPathsToMap(" + gson.toJson(paths) + ")");
    }

    public void clearBasesLayer() {
        Clients.evalJavaScript("clearBasesLayer()");
    }

    public void clearPathsLayer() {
        Clients.evalJavaScript("clearPathsLayer()");
    }

    public void clearMap() {
        Clients.evalJavaScript("clearMap()");
    }

    public void zoomOnMaker(PointPOJO point) {
        Clients.evalJavaScript("zoomOnMarker(" + gson.toJson(point) + ")");
    }
}
