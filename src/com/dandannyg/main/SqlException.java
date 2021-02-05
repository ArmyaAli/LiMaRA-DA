package com.dandannyg.main;
//Daniel Guo
import javax.servlet.ServletException;

public class SqlException extends ServletException {
	
	private static final long serialVersionUID = 1L;
	
	public SqlException()
	{
	}

	public SqlException(String message)
	{
		super(message);
	}

	public SqlException(Throwable rootCause)
	{
		super(rootCause);
	}

	public SqlException(String message, Throwable rootCause)
	{
		super(message, rootCause);
	}
}
