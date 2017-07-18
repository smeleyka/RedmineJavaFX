import com.taskadapter.redmineapi.RedmineException;
import com.taskadapter.redmineapi.bean.Issue;
import com.taskadapter.redmineapi.bean.Project;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.KeyEvent;

import java.util.List;

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
        textArea.clear();
        redmineInstance = new RedmineApi(uriField.getText(), loginField.getText(), passwordField.getText());
        try {
            issues = redmineInstance.getIssues(projectKey);
            projects = redmineInstance.getProjects();
        } catch (RedmineException e) {
            // e.printStackTrace();
            showAllert(e.getMessage());
            return;
        }


        for (Project project : projects) {
            textArea.appendText(project.toString() + "\n");
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

    public void onInputMethodTextChanged(InputMethodEvent inputMethodEvent) {
        System.out.println("onInputMethodTextChanged");
    }

    public void onKeyTyped(KeyEvent keyEvent) {
        String text = uriField.getText();

        if (text.length()>4 && !text.matches("^http://.*")) {
            uriField.setText("http://"+text);
            uriField.end();
        }
    }
}
