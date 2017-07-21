import com.taskadapter.redmineapi.Include;
import com.taskadapter.redmineapi.IssueManager;
import com.taskadapter.redmineapi.RedmineException;
import com.taskadapter.redmineapi.bean.Changeset;
import com.taskadapter.redmineapi.bean.Issue;
import com.taskadapter.redmineapi.bean.Journal;
import com.taskadapter.redmineapi.bean.Project;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.KeyEvent;
import sun.font.CompositeGlyphMapper;

import java.net.URL;
import java.util.*;

public class Controller implements Initializable{

    public TextField uriField;
    public TextField loginField;
    public RedmineApi redmineInstance;
    public String projectKey = "zayavki";
    public TextArea textArea;
    public PasswordField passwordField;
    public BarChart barChart;

    public void singButtonAction(ActionEvent actionEvent) {
        List<Project> projects = null;
        List<Issue> issues = null;
        List<Issue> issuesWithJournal = new ArrayList<Issue>();
        List<Journal> journal = null;
        HashMap<Integer, Journal> issueHash;
        int issueCount;
        int[] idArr;

        //redmineInstance = new RedmineApi("http://redmine.vbg.ru/redmine", loginField.getText(), passwordField.getText());

        textArea.clear();
        redmineInstance = new RedmineApi("http://redmine.vbg.ru/redmine", "01537d3bfbf9b5368eee0f2a6918bbb80b98e545");
        IssueManager issueMgr = redmineInstance.getMgr().getIssueManager();

        try {
            // создаем ArrayList из заявок
            //issues = redmineInstance.getIssues(projectKey);

//&set_filter=1&f[]=status_id&op[status_id]=c&f[]=created_on&op[created_on]=%3E%3C&v[created_on][]=2017-07-01&v[created_on][]=2017-07-16
            Map<String,String> params = new HashMap<>();
                 params.put("set_filter", "1");
                 params.put("f[]", "status_id");
                 params.put("op[status_id]", "c");

            issues = issueMgr.getIssues(params);

            projects = redmineInstance.getProjects();


            //  создаем ArrayList из заявок c журналом
            int issueId = 0;
            System.out.println(issues.size());
            for (int i = 0; i < issues.size(); i++) {
                issueId = issues.get(i).getId();
                issuesWithJournal.add(issueMgr.getIssueById(issueId, Include.journals));
            }
            Date dateOpen = new Date();
            Date dateClose = new Date();
                        // обрабатываем массив заявок с журналом
        for (Issue issuej : issuesWithJournal){
            System.out.print (issuej.getId()+"--OPENED--");
            System.out.print (issuej.getCreatedOn()+"");


            textArea.appendText (issuej.getId()+"--OPENED--");
            textArea.appendText (issuej.getCreatedOn()+"");
            if (issuej.getJournals().iterator().hasNext()){
                System.out.print("--FIRST ANSWER--"+issuej.getJournals().iterator().next().getCreatedOn());
                textArea.appendText ("--FIRST ANSWER--"+issuej.getJournals().iterator().next().getCreatedOn());
            }
            System.out.println();
            textArea.appendText ("\n");

        }


        } catch (RedmineException e) {
            showAllert(e.getMessage());
            return;
        }





























    }

    public void showAllert(String info) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText(null);
        alert.setContentText(info);
        alert.showAndWait();
    }

    public void onAction(ActionEvent actionEvent) {
        System.out.println("OnAction");
    }

    public void onKeyTyped(KeyEvent keyEvent) {
        String text = uriField.getText();

        if (text.length() > 4 && !text.matches("^http://.*")) {
            uriField.setText("http://" + text);
            uriField.end();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
