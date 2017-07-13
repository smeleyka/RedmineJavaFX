import com.taskadapter.redmineapi.RedmineException;
import com.taskadapter.redmineapi.RedmineManager;
import com.taskadapter.redmineapi.RedmineManagerFactory;
import com.taskadapter.redmineapi.bean.Issue;
import com.taskadapter.redmineapi.bean.Project;

import javax.naming.ldap.Rdn;
import java.util.List;

/**
 * Created by smeleyka on 13.07.17.
 */
public class RedmineApi {
    private String uri;
    private String login;
    private String password;
    static RedmineManager mgr;
    static Integer queryId = null;

    public RedmineApi(String uri, String login, String password) {
        this.uri = uri;
        this.login = login;
        this.password = password;
        mgr = RedmineManagerFactory.createWithUserAuth(uri, login, password);
    }

    public List<Issue> getIssues(String projectKey) throws RedmineException {
        List<Issue> issues = null;
        issues = mgr.getIssueManager().getIssues(projectKey, queryId);

        return issues;
    }

    public List<Project> getProjects() {
        List<Project> projects = null;
        try {
            projects = mgr.getProjectManager().getProjects();
        } catch (RedmineException e) {
            e.printStackTrace();
        }

        return projects;
    }

}
