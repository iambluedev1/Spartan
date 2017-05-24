package fr.iambluedev.spartan.node.logger;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.logging.Formatter;
import java.util.logging.LogRecord;

import fr.iambluedev.logger.StringFormatter;

public class SpartanFileFormatter extends Formatter{
	
	private final DateFormat date = new SimpleDateFormat("HH:mm:ss");
	
	@Override
	public String format(LogRecord record) {
		StringBuilder formatted = new StringBuilder();
	    
	    formatted.append(this.date.format(Long.valueOf(record.getMillis())));
	    formatted.append(" [");
	    formatted.append(record.getLevel().getLocalizedName());
	    formatted.append("] ");
	    formatted.append(formatMessage(record));
	    formatted.append(System.getProperty("line.separator"));
	    if (record.getThrown() != null)
	    {
	      StringWriter writer = new StringWriter();
	      record.getThrown().printStackTrace(new PrintWriter(writer, true));
	      formatted.append(writer);
	    }
	    return new StringFormatter(formatted.toString()).removeColors().removeSymbol().getMsg();
	}

}
