package com.spring.app.api.service;

import org.springframework.stereotype.Service;

@Service
public class ServiceConversionLogic {

	private static final String allowedString = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
	private char[] allowedCharacters = allowedString.toCharArray();
	private int allowedCharactersLength = allowedCharacters.length;

	public String encode(long input) {
		StringBuilder encodedString = new StringBuilder();

		if (input == 0) {
			return String.valueOf(allowedCharacters[0]);
		}

		while (input > 0) {
			encoding(input, encodedString);
			input = input / allowedCharactersLength;
		}

		return encodedString.reverse().toString();
	}

	private void encoding(long input, StringBuilder encodedString) {
		encodedString.append(allowedCharacters[(int) (input % allowedCharactersLength)]);
	}

	public long decode(String input) {
		char[] characters = input.toCharArray();
		return decodedValue(characters, characters.length, 0, 1);
	}

	private int decodedValue(char[] characters, int length, int decoded, int counter) {
		for (int i = 0; i < length; i++) {
			decoded += allowedString.indexOf(characters[i]) * Math.pow(allowedCharactersLength, length - counter);
			counter++;
		}
		return decoded;
	}
}
