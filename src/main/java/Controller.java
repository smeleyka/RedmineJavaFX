import com.taskadapter.redmineapi.bean.Issue;
import javafx.event.ActionEvent;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.util.List;

public class Controller {

    //public Text actiontarget;
    public TextField uriField;
    public TextField loginField;
   // public TextField passField;
    public RedmineApi redmineInstance;
    public String projectKey = "zayavki";
    public TextArea textArea;
    public PasswordField passwordField;


    public void singButtonAction(ActionEvent actionEvent) {
        textArea.setText("Button Pressed");
        redmineInstance = new RedmineApi(uriField.getText(),loginField.getText(),passwordField.getText());
        List<Issue> issues = redmineInstance.getIssues(projectKey);

        for (Issue issue:issues){
            textArea.appendText(issue.toString()+"\n");
        }
    }
}
