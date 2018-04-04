package assignment4;

class InvalidInputException extends Exception {
	String[] offending_cmd;
	
	public InvalidInputException(String[] cmd) {
		offending_cmd = cmd;
	}
	
	public String toString() {
		return "Invalid Command cmd: " + offending_cmd;
	}

}

