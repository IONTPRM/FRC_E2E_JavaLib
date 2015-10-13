package com.iontrading.frc_e2e.exception;

public class ResultNotMatchesException extends Exception {

	private static final long serialVersionUID = 1L;

	public ResultNotMatchesException() {}

	public ResultNotMatchesException(String message) {
		super(message);
	}
}
