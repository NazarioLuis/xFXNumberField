package py.com.cs.xfxnumberfield.test;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import py.com.cs.xfxnumberfield.component.FXNumberTextField;

/**
 * A sample that demonstrates various key events and their usage. Type in the
 * text box to view the triggered events: key pressed, key typed and key 
 * released. Pressing the Shift, Ctrl, and Alt keys also trigger events.
 *
 * @see javafx.scene.input.KeyCode
 * @see javafx.scene.input.KeyEvent
 * @see javafx.event.EventHandler
 */
public class MyTestView extends Application {


    private FXNumberTextField textField;
	private Button button;
	private TextArea result;
	private Label label;

	@Override
    public void start(Stage primaryStage) {

        textField = new FXNumberTextField();
        textField.setValue(10000.5);
        
        button = new Button("Agregar valor"); 
        button.setOnAction((event) -> {
        	result.appendText("Valor double: "+textField.getValue()+"\n");
        });
        
        label = new Label("Resultado");
        result = new TextArea();

        VBox root = new VBox(10, textField,button,label,result);
        root.setAlignment(Pos.CENTER);
        primaryStage.setScene(new Scene(root, 400, 400));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}