package org.opencompare;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class HtmlGenerator {

	public HtmlGenerator() {
		super();
	}

	public static void main(String[] args) {

		HtmlGenerator htmlGenerator = new HtmlGenerator();
		File f = new File("www/index.html");

		try {
			PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(
					f)));
			htmlGenerator.writeHeader(pw);
			htmlGenerator.writeBody(pw);

			pw.close();
		} catch (IOException exception) {
			System.out.println("Erreur lors de la lecture : "
					+ exception.getMessage());
		}

	}

	public void writeHeader(PrintWriter pw) {
		pw.println("<!DOCTYPE html>");
		pw.println("<html lang=\"fr\">");
		pw.println("<head>");
		pw.println("\t<meta charset=\"utf-8\">");
		pw.println("\t<meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">");
		pw.println("\t<meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">");
		pw.println("\t<title>OpenCompare</title>");
		pw.println("\t<link href=\"css/bootstrap.min.css\" rel=\"stylesheet\">");
		pw.println("\t<script src=\"https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js\"></script>");
		pw.println("\t<script src=\"https://oss.maxcdn.com/respond/1.4.2/respond.min.js\"></script>");
		pw.println("</head>");
	}

	public void writeBody(PrintWriter pw) {
		pw.println("<body>");
		pw.println("\t<nav class=\"navbar navbar-inverse navbar-fixed-top\" role=\"navigation\">");
		pw.println("\t<div class=\"container\">");
		pw.println("\t\t<div class=\"navbar-header\">");
		pw.println("\t\t\t<button type=\"button\" class=\"navbar-toggle\" data-toggle=\"collapse\" data-target=\"#bs-example-navbar-collapse-1\">");
		pw.println("\t\t\t\t<span class=\"sr-only\">Toggle navigation</span>");
		pw.println("\t\t\t\t<span class=\"icon-bar\"></span>");
		pw.println("\t\t\t\t<span class=\"icon-bar\"></span>");
		pw.println("\t\t\t\t<span class=\"icon-bar\"></span>");
		pw.println("\t\t\t</button>");
		pw.println("\t\t\t<a class=\"navbar-brand\" href=\"#\">OpenCompare</a>");
		pw.println("\t\t</div>");
		pw.println("\t\t<div class=\"collapse navbar-collapse\" id=\"bs-example-navbar-collapse-1\">");
		pw.println("\t\t\t<ul class=\"nav navbar-nav\">");
		pw.println("\t\t\t\t<li>");
		pw.println("\t\t\t\t\t<a href=\"#\">About</a>");
		pw.println("\t\t\t\t</li>");
		pw.println("\t\t\t</ul>");
		pw.println("\t\t</div>");
		pw.println("\t</div>");
		pw.println("\t</nav>");
		pw.println("\t<script src=\"https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js\"></script>");
		pw.println("\t<script src=\"js/bootstrap.min.js\"></script>");
		pw.println("</body>");
	}

}
