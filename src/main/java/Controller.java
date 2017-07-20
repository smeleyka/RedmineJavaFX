import com.taskadapter.redmineapi.Include;
import com.taskadapter.redmineapi.IssueManager;
import com.taskadapter.redmineapi.RedmineException;
import com.taskadapter.redmineapi.bean.Changeset;
import com.taskadapter.redmineapi.bean.Issue;
import com.taskadapter.redmineapi.bean.Journal;
import com.taskadapter.redmineapi.bean.Project;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.KeyEvent;
import sun.font.CompositeGlyphMapper;

import java.util.*;

public class Controller {

    public TextField uriField;
    public TextField loginField;
    public RedmineApi redmineInstance;
    public String projectKey = "zayavki";
    public TextArea textArea;
    public PasswordField passwordField;

    public void singButtonAction(ActionEvent actionEvent) {
        List<Project> projects = null;
        List<Issue> issues = null;
        List<Issue> issuesWithJournal=new ArrayList<Issue>();
        List<Journal> journal = null;
        HashMap<Integer,Journal> issueHash;
        int issueCount;
        int[] idArr;

        //redmineInstance = new RedmineApi("http://redmine.vbg.ru/redmine", loginField.getText(), passwordField.getText());

        textArea.clear();
        redmineInstance = new RedmineApi("http://redmine.vbg.ru/redmine", "01537d3bfbf9b5368eee0f2a6918bbb80b98e545");
        IssueManager issueMgr = redmineInstance.getMgr().getIssueManager();

        try {
            // создаем ArrayList из заявок
            issues = redmineInstance.getIssues(projectKey);
            projects = redmineInstance.getProjects();

            System.out.println(issueMgr.);


        //  создаем ArrayList из заявок c журналом
            int i = 0;
//            System.out.println(issues.size());
//            System.out.println(issues.toString());

//            for (int j = 0; j < issues.size(); j++)   {
//                System.out.println(issues.get(j));
//
////               Issue issue = issues.iterator().next();
////               int i = issue.getId();
////                issuesWithJournal.add(issueMgr.getIssueById(i , Include.journals));
////               System.out.println(issuesWithJournal.size());
////               //Issue issue = issues.iterator().next();
////               System.out.println(i++);
//            }


//        for (Issue issuej : issuesWithJournal){
//            if (issuej.getJournals().iterator().hasNext()){
//                System.out.println(issuej.getJournals().iterator().next().getCreatedOn());
//            }
//
//        }



        } catch (RedmineException e) {
            showAllert(e.getMessage());
            return;
        }



        //
       // Object[] journalArr = issue.getJournals().toArray();
       // System.out.println(((Journal) journalArr[0]).getId() + " " + ((Journal) journalArr[0]).getNotes() + " " + ((Journal) journalArr[0]).getCreatedOn());


        //textArea.appendText(issue.getJournals().);


//        for (Project project : projects) {
//            textArea.appendText(project.toString() + "\n");
//        }
//
//        for (Issue issue : issues) {
//            //textArea.appendText(issue.toString() + "\n");
//            //textArea.appendText(issue.getId() +" "+ issue.getCreatedOn().toString()+ " " +issue.getUpdatedOn().toString() +"######"+ issue.getChangesets().size()+"\n");
//            if (issue.getId()==11849) {
//                journal = (List<Journal>)issue.getJournals();
//
//                textArea.appendText(issue.getSubject()+"----"+journal.size()+ "------"+issue.getChangesets().size());
//            }
//        }
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
}
