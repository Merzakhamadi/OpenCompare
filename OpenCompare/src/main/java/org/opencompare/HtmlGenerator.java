package org.opencompare;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class HtmlGenerator {

	private boolean nvd;
	private boolean plotly;
	private PrintWriter pw;

	public HtmlGenerator(boolean nvd, boolean plotly) {
		super();
		this.nvd = nvd;
		this.plotly = plotly;
	}

	public boolean isNvd() {
		return nvd;
	}

	public void setNvd(boolean nvd) {
		this.nvd = nvd;
	}

	public boolean isPlotly() {
		return plotly;
	}

	public void setPlotly(boolean plotly) {
		this.plotly = plotly;
	}

	public void writeAll()
	{
		File f = new File("www/index.html");

		try {
			this.pw = new PrintWriter(new BufferedWriter(new FileWriter(f)));
			this.writeHeader();
			this.writeBody();
			this.pw.close();
		} catch (IOException exception) {
			System.out.println("Erreur lors de la lecture : " + exception.getMessage());
		}
		UnZip unziper = new UnZip();
		unziper.unzipFunction("www","ressources.zip");
	}

	public void writeHeader() {
		this.pw.println("<!DOCTYPE html>");
		this.pw.println("<html lang=\"fr\">");
		this.pw.println("<head>");
		this.pw.println("\t<meta charset=\"utf-8\">");
		this.pw.println("\t<meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">");
		this.pw.println("\t<meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">");
		this.pw.println("\t<title>OpenCompare</title>");
		this.pw.println("\t<script src=\"js/jquery/jquery-1.12.3.min.js\"></script>");
		this.pw.println("\t<script src=\"js/bootstrap/bootstrap.min.js\"></script>");
		this.pw.println("\t<link href=\"css/bootstrap.min.css\" rel=\"stylesheet\">");
		this.pw.println("<link href=\"css/style.css\" rel=\"stylesheet\">");

		if(this.isNvd()){
			this.pw.println("\t<link href=\"js/nvd3/build/nv.d3.css\" rel=\"stylesheet\" type=\"text/css\">");
			this.pw.println("\t<script src=\"js/nvd3/build/d3.min.js\" charset=\"utf-8\"></script>");
			this.pw.println("\t<script src=\"js/nvd3/build/nv.d3.js\"></script>");
		}
		else if(this.isPlotly()){
			this.pw.println("<script src=\"js/plotly/build/plotly-latest.min.js\"></script>");
			this.pw.println("<script src=\"js/plotly/build/plotly-1.2.0.min.js\"></script>");
		}
		this.pw.println("</head>");

	}

	public void writeBody() {
		this.pw.println("<body>");
		this.pw.println("\t<nav class=\"navbar navbar-inverse navbar-fixed-top\" role=\"navigation\">");
		this.pw.println("\t<div class=\"container\">");
		this.pw.println("\t\t<div class=\"navbar-header\">");
		this.pw.println("\t\t\t<button type=\"button\" class=\"navbar-toggle\" data-toggle=\"collapse\" data-target=\"#bs-example-navbar-collapse-1\">");
		this.pw.println("\t\t\t\t<span class=\"sr-only\">Toggle navigation</span>");
		this.pw.println("\t\t\t\t<span class=\"icon-bar\"></span>");
		this.pw.println("\t\t\t\t<span class=\"icon-bar\"></span>");
		this.pw.println("\t\t\t\t<span class=\"icon-bar\"></span>");
		this.pw.println("\t\t\t</button>");
		this.pw.println("\t\t\t<a class=\"navbar-brand\" href=\"index.html\">OpenCompare</a>");
		this.pw.println("\t\t</div>");
		this.pw.println("\t\t<div class=\"collapse navbar-collapse\" id=\"bs-example-navbar-collapse-1\">");
		this.pw.println("\t\t\t<ul class=\"nav navbar-nav\">");
		this.pw.println("\t\t\t\t<li>");
		this.pw.println("\t\t\t\t\t<a href=\"about.html\">About</a>");
		this.pw.println("\t\t\t\t</li>");
		this.pw.println("\t\t\t</ul>");
		this.pw.println("\t\t</div>");
		this.pw.println("\t</div>");
		this.pw.println("\t</nav>");
		this.pw.println("\t<br>");
		this.pw.println("\t<br>");
		
		if(this.isNvd()){
			this.pw.println("\t<div class=\"row\">");
			this.pw.println("\t<div class=\"col-lg-3 col-centered\">");
			this.pw.println("\t<div id='graphtitle'></div>");
			this.pw.println("\t</div>");
			this.pw.println("\t</div>");
	    
			this.pw.println("<div id=\"graph\" class='with-3d-shadow with-transitions'>");
			this.pw.println("<svg></svg>");
			this.pw.println("</div>");
			this.pw.println("<script src=\"js/graph_nvd.js\"></script>");
		}
		else if(this.isPlotly()){
			
			this.pw.println("<div id=\"graph\" class='with-3d-shadow with-transitions'>");
			this.pw.println("</div>");
			this.pw.println("<script src=\"js/graph_plotly.js\"></script>");
		}
	    
		this.pw.println("</body>");
		this.pw.println("</html>");
	}

}
