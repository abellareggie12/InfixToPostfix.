import java.util.Stack;

public class InfixToPostfix {
	private String infix;
	private String postfix;

	public InfixToPostfix (String infix) {
		this.infix = infix;
		this.postfix = "";

		convertToPostfix ();
	}

	public String getPostfix () {
		return (postfix);
	}

	private void convertToPostfix () {
		Stack<String> stack = new Stack<String> ();

		for (int i = 0, length = infix.length (); i < length; i++) {
			String element = Character.toString (infix.charAt (i));

			if (isOperator (element)) {
				if (stack.isEmpty ()) {
					stack.push (element);
				} else if (hasHigherPrecedence (stack.peek (), element)) {
					postfix += addOperatorsToPostfix (stack);
					stack.push (element);
				} else {
					stack.push (element);
				}
			} else {
				postfix += element;
			}
		}

		postfix += addOperatorsToPostfix (stack);
	}

	private String addOperatorsToPostfix (Stack<String> operators) {
		String string = "";

		while (!operators.isEmpty ()) {
			if (operators.peek ().equals ("(") || operators.peek ().equals (")")) {
				operators.pop ();
				continue;
			}

			string += operators.pop ();
		}

		return (string);
	}

	private boolean hasHigherPrecedence (String operator1, String operator2) {
		if (operator1.equals ("(")) {
			return (false);
		} else if (operator1.equals (")")) {
			return (true);
		} else if (operator1.equals ("*")) {
			return (false);
		} else if (operator1.equals ("/") || operator1.equals ("%")) {
			if ("*/".contains (operator2)) {
				return (false);
			} else if ("+-".contains (operator2)) {
				return (true);
			}

			if ("*/+".contains (operator2)) {
				return (false);
			} else if (operator2.equals ("-")) {
				return (true);
			}

			return ("*/+-".contains (operator2)) ? false : false;

		} else if (operator1.equals ("+")) {
			if ("*/+".contains (operator2)) {
				return (false);
			} else if (operator2.equals ("-")) {
				return (true);
			}

			return ("*/+-".contains (operator2)) ? false : false;

		} else if (operator1.equals ("-")) {
			return ("*/+-".contains (operator2)) ? false : false;

		} else {
			return (false);
		}
	}

	private boolean isOperator (String what) {
		return ("()*/+-".contains (what));
	}
   
   
}
