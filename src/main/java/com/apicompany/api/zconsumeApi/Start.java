package com.apicompany.api.zconsumeApi;

import java.io.IOException;
import java.net.MalformedURLException;

public class Start {

	public static void main(String[] args) throws MalformedURLException, IOException {
		RequestService rc = new RequestService ();
		rc.captureData();
	}

}
