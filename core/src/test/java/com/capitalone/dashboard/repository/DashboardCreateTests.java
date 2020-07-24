package com.capitalone.dashboard.repository;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;

import com.capitalone.dashboard.model.Application;
import com.capitalone.dashboard.model.Component;
import com.capitalone.dashboard.model.Dashboard;
import com.capitalone.dashboard.model.DashboardType;
import com.capitalone.dashboard.model.Widget;

public class DashboardCreateTests extends FongoBaseRepositoryTest {

    @Autowired
    private DashboardRepository dashboardRepository;

    @Autowired
    private ComponentRepository componentRepository;

    @Test
    public void createTeamDashboardTest() {
        Component component = new Component("Jay's component");
        component.setOwner("Jay");

        component = componentRepository.save(component);
        System.out.println(component.getId());

        Application application = new Application("Jay's App", component);

        Dashboard dashboard = new Dashboard("Topo", "Jays's Dashboard", application,"amit", DashboardType.Team);

        Widget build = new Widget();
        build.setName("build");
        build.getOptions().put("color", "red");
        build.getOptions().put("items", new String[] { "item 1", "item 2"});
        dashboard.getWidgets().add(build);

        Widget scm = new Widget();
        scm.setName("scm");
        scm.getOptions().put("enabled", true);
        scm.getOptions().put("foo", "bar");
        scm.getOptions().put("threshold", 10);
        dashboard.getWidgets().add(scm);


        dashboardRepository.save(dashboard);



        for (Dashboard d : dashboardRepository.findAll(new Sort(Sort.Direction.ASC, "title"))) {
            System.out.println(d.getTitle());
        }

    }
}
