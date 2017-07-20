import com.taskadapter.redmineapi.Include;
import com.taskadapter.redmineapi.RedmineException;
import com.taskadapter.redmineapi.RedmineManager;
import com.taskadapter.redmineapi.RedmineManagerFactory;
import com.taskadapter.redmineapi.bean.Issue;
import com.taskadapter.redmineapi.bean.Project;

import java.util.List;

/**
 * Created by smeleyka on 13.07.17.
 */
public class RedmineApi {
    private String uri;
    static RedmineManager mgr;
    static Integer queryId = null;

    public RedmineApi(String uri, String apiKey) {
        this.uri = uri;
        mgr = RedmineManagerFactory.createWithApiKey(uri, apiKey);
    }

    public RedmineApi(String uri, String login, String password) {
        this.uri = uri;
        mgr = RedmineManagerFactory.createWithUserAuth(uri, login, password);
    }


    public List<Issue> getIssues(String projectKey) throws RedmineException {
        List<Issue> issues = null;
        issues = mgr.getIssueManager().getIssues(projectKey, queryId);

        return issues;
    }

    public Issue getIssuesById(int id) throws RedmineException {

        return mgr.getIssueManager().getIssueById(id, Include.journals);
    }

    public List<Project> getProjects() throws RedmineException {
        List<Project> projects = null;
        projects = mgr.getProjectManager().getProjects();

        return projects;
    }

    public RedmineManager getMgr() {

        return mgr;
    }

}
