package bg.neshev.atlas.vm;

import bg.neshev.atlas.pojo.PointPOJO;
import com.google.gson.Gson;
import org.zkoss.bind.BindUtils;
import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.Init;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zkplus.spring.DelegatingVariableResolver;

import java.util.HashMap;
import java.util.Map;

@VariableResolver(DelegatingVariableResolver.class)
public class MapVM {
    private final Gson gson = new Gson();

    @Init
    public void init() {

    }

    @AfterCompose
    public void doAfterCompose(@ContextParam(ContextType.VIEW)Component view) {
        Selectors.wireEventListeners(view, this);
    }

    @Listen("onMapClick=#dataTransfer")
    public void onMapClick(Event event) {
        Map<String, Object> args = new HashMap<>();
        args.put("point", gson.fromJson(event.getData().toString(), PointPOJO.class));

        BindUtils.postGlobalCommand(null, null, "sendClickPoint", args);
    }
}
