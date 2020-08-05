package bg.neshev.atlas.vm;

import bg.neshev.atlas.dto.BaseDTO;
import bg.neshev.atlas.executor.JavascriptExecutor;
import bg.neshev.atlas.pojo.PointPOJO;
import bg.neshev.atlas.service.BaseService;
import bg.neshev.atlas.service.PathService;
import lombok.Getter;
import lombok.Setter;
import org.zkoss.bind.BindUtils;
import org.zkoss.bind.annotation.*;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zkplus.spring.DelegatingVariableResolver;
import org.zkoss.zul.ListModelList;

import java.util.ArrayList;
import java.util.List;

@VariableResolver(DelegatingVariableResolver.class)
public class BaseManagementVM {
    @WireVariable private JavascriptExecutor javascriptExecutor;
    @WireVariable private BaseService baseService;
    @WireVariable private PathService pathService;

    @Getter @Setter private ListModelList basesList = new ListModelList<BaseDTO>();

    private List<BaseDTO> bases;

    @Init
    public void init() {
    }

    @GlobalCommand
    @NotifyChange("bases")
    public void sendClickPoint(@BindingParam("point") PointPOJO point) {
        basesList.add(BaseDTO.builder()
                         .lat(point.getLat())
                         .lng(point.getLng())
                         .build());
    }

    @Command
    public void showBases() {
        javascriptExecutor.clearMap();
        javascriptExecutor.addBasesToMap(baseService.test());
    }

    @Command
    public void showPaths() {
        javascriptExecutor.clearMap();
        javascriptExecutor.addPathsToMap(pathService.test());
    }

    @Command
    public void clearMap() {
        //javascriptExecutor.clearMap();
        System.out.println(basesList.getInnerList().ge.getLat());
    }

    @Command
    public void zoomOnMarker(@BindingParam("base") BaseDTO base) {
        javascriptExecutor.zoomOnMaker(PointPOJO.builder()
                                                .lat(base.getLat())
                                                .lng(base.getLng())
                                                .build());
    }

    public List<BaseDTO> getBases() {
        return bases;
    }

    public void setBases(List<BaseDTO> bases) {
        this.bases = bases;
    }
}
