package py.com.cs.xfxnumberfield.component;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.Timer;
import java.util.TimerTask;
import java.util.function.UnaryOperator;

import javafx.event.EventHandler;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.control.TextFormatter.Change;
import javafx.scene.input.KeyEvent;
import javafx.util.StringConverter;

public class FXNumberTextField extends TextField implements UnaryOperator<Change>, EventHandler<KeyEvent> {

	private DecimalFormat formatter = new DecimalFormat("#,###.##");
	private String decimalSeparator;
	private String groupingSeparator;

	public FXNumberTextField() {
		this.setTextFormatter(new TextFormatter<Double>(getStringConveter(), 0.0, this));
		this.setOnKeyReleased(this);
		this.groupingSeparator = formatter.getDecimalFormatSymbols().getGroupingSeparator() + "";
		this.decimalSeparator = formatter.getDecimalFormatSymbols().getDecimalSeparator() + "";
	}

	public void setValue(Double value) {
		if (value != null)
			this.setTextFormatter(new TextFormatter<Double>(getStringConveter(), value, this));
	}

	public Double getValue() {
		return Double.parseDouble(this.get(this.getText()));
	}
	

	private void error() {
		this.setStyle("-fx-text-inner-color: red;");
		new Timer().schedule(new TimerTask() {
			@Override
			public void run() {
				FXNumberTextField.this.setStyle("-fx-text-inner-color: black;");;
			}
		}, 300);
	}


	public String get(String text) {
		String str = text.replace(groupingSeparator, "");
		str = str.replace(decimalSeparator, ".");
		return str;
	}

	private StringConverter<Double> getStringConveter() {
		return new StringConverter<Double>() {
			@Override
			public String toString(Double object) {
				return object == null ? "" : formatter.format(object);
			}

			@Override
			public Double fromString(String string) {
				try {
					return string.isEmpty() ? 0.0 : formatter.parse(string).doubleValue();
				} catch (ParseException e) {
					return 0.0;
				}
			}
		};
	}

	@Override
	public Change apply(Change change) {
		for (char c : change.getText().toCharArray()) {
			if ((!Character.isDigit(c)) && c != decimalSeparator.charAt(0)) {
				error();
				return null;
			}
		}
		return change;
	}

	@Override
	public void handle(KeyEvent e) {
		if (e.getEventType().toString().equals("KEY_RELEASED")) {
			if (!this.getText().contains(decimalSeparator))
				this.commitValue();
		}
	}

}
