package fr.iambluedev.logger;

public enum PrintColor {
	
	BLACK("\u001B[30m"),
	RED("\u001B[31m"),
	GREEN("\u001B[32m"),
	YELLOW("\u001B[33m"),
	BLUE("\u001B[34m"),
	MAGENTA("\u001B[35m"),
	CYAN("\u001B[36m"),
	WHITE("\u001B[37m"),
	DEFAULT("\u001B[39m"),
	RESET("\u001B[0m");

	private String ansiColor;

	private PrintColor(String ansiColor) {
		this.setAnsiColor(ansiColor);
	}

	public String getAnsiColor() {
		return this.ansiColor;
	}

	public void setAnsiColor(String ansiColor) {
		this.ansiColor = ansiColor;
	}
}
