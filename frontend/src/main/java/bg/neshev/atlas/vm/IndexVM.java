package bg.neshev.atlas.vm;

import bg.neshev.atlas.dto.BaseDTO;
import bg.neshev.atlas.service.BaseService;
import bg.neshev.atlas.service.PathService;
import com.google.gson.Gson;
import lombok.Getter;
import org.zkoss.bind.annotation.Init;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zkplus.spring.DelegatingVariableResolver;

import java.util.List;

@VariableResolver(DelegatingVariableResolver.class)
public class IndexVM {
    @WireVariable private BaseService baseService;
    @WireVariable private PathService pathService;

    private Gson gson;

    @Init
    public void init() { }
}
