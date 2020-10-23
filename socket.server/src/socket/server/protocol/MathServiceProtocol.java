package socket.server.protocol;


public class MathServiceProtocol implements Protocol {
	
	
	private static final String ADD = "+";
	private static final String MUL = "*";
	private static final String SUB = "-";
	

	@Override
	public String process(final String input) {

		if(input.startsWith(ADD))
		{
			return processAdd(input);
			
		}
		else if(input.startsWith(MUL)) {
			
			return processMul(input);
		}
		else if(input.startsWith(SUB))
		{
			return processSub(input);
		}
		else {
			
			return null;
		}

	}
	
	private String[] extractNumbers(String input) 
	{
		final String inputWithoutOperand = input.substring(1); // Operator wird entfernt
		final String trimInput = inputWithoutOperand.trim(); //Leerzeichen vorne und hinten entfernen
		final String[] numbersAsStrings = trimInput.split(" "); // einzelne Zahlen extrahieren
		return numbersAsStrings;
	}
	
	
	private String processAdd(String input){
		
		final String[] numbersAsStrings = extractNumbers(input);
		
		double result = 0;
		
		for(String numberAsString : numbersAsStrings)
		{
			final double number = Double.valueOf(numberAsString);
			result = result + number;
			
		}
		
		return "Das Ergebnis ist: " + result;
		
	}

	
	
	private String processSub(String input) {
		
		final String[] numbersAsStrings = extractNumbers(input);
		
		double result = 0;
		
		for(String numberAsString : numbersAsStrings) {
			
			final double number = Double.valueOf(numberAsString);
			result = result - number;
		}
 		return "Das Ergebnis ist: " + result;
	}
	
	private String processMul(String input) {
		
		final String[] numbersAsStrings = extractNumbers(input);
		
		double result = 1;
		
		for(String numberAsString : numbersAsStrings) {
			
			final double number = Double.valueOf(numberAsString);
			result = result * number;
		}
		return "Das Ergebnis ist: " + result;
	}
	
	
	
}
