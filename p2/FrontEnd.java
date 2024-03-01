package p2;

import org.w3c.dom.*;
import javax.xml.parsers.*;
import java.io.*;
import javax.xml.xpath.*;
import java.util.*;

import p2.DataModel.*;
import p2.Sint21P2.*;

public class FrontEnd {

    /* Screen en modo browser para la Fase 0 */
    public static void Fase0(PrintWriter out, String fichero, String clienteIP, String userAgent, String ServerIP) {
        
        out.println("<html>");
        out.println("<head>");
        out.println("<meta charset='utf-8'>");
        out.println("<link rel='stylesheet' href='./p2/p2.css'>");
        out.println("<title>Servicio de consulta de libros</title>");
        out.println("</head>");
        out.println("<body>");
        out.println("<h1>Servicio de consulta de información de libros</h1>");
        out.println("<h1>Bienvenido a este servicio</h1>");

        String[] nombreaux = fichero.split("/");
        String NombreFichero = nombreaux[nombreaux.length - 1];
        
        out.println("<h2>El documento xml es:"+NombreFichero+"</h2>");

        out.println("<h2>La ip del cliente es:"+clienteIP+"</h2>");

        out.println("<h2>El navegador del cliente es:"+userAgent+"</h2>");

        out.println("<h2>La ip del server es:"+ServerIP+"</h2>");


        out.println("<br>");
        out.println("<br>");

        out.println("<form id=\"form\" action=\"/sint21/P2Lib\">");
        out.println("<input type=\"submit\" value=\"Siguiente\" method=\"get\"></input>");
        out.println("<input type=\"hidden\" value=1 name=\"fase\"></input>");
        out.println("</form>");
    

        out.println("</ul>");
        out.println("<br>");
        out.println("<hr>");
        out.println("<h4>Autor: Samuel Vila Camino</h4>");
        out.println("</body>");
        out.println("</html>");

    }

    public static void Fase1(PrintWriter out, ArrayList<Country> paises) {  //lista paises conocidos donde hay libro publicado


        out.println("<html>");
        out.println("<head>");
        out.println("<meta charset=\"utf-8\">");
        out.println("<link rel=\"stylesheet\" href=\"./p2/p2.css\">");
        out.println("<title>Servicio de consulta de libros</title>");
        out.println("</head>");
        out.println("<body>");
        out.println("<h1>Servicio de consulta de información de libros</h1>");
        out.println("<h2>Consulta: Fase 1:</h2>");
        out.println("<h2>Selecciona un país</h2>");

        out.println("<ol>");//inicio lista ordenada

        for (int i=0; i < paises.size(); i++){
            Country paisactual = paises.get(i);
            out.println("<li><a class='disponible' href=\"sint21/P2Lib?fase=2%pais=" + paisactual.getidentificador()+ "\">" + paisactual.getNombre() + "</a></li>");
        }

        out.println("</ol>"); //final lista ordenada
        
        out.println("<form id=\"form\" action=\"/sint21/P2Lib?fase=1\">");
        out.println("<input type=\"submit\" value=\"Atrás\" method=\"get\"></input>");
        out.println("<input type=\"hidden\" value=0 name=\"fase\"></input>");

        out.println("</form>");
        
        out.println("<br>");
        //out.println("<button type=\"button\" id=\"inicio\" onclick=\"location.href=\'?p="+"&fase=0\';\">Inicio</button>");
        out.println("<hr>");
        out.println("<h4>Autor: Samuel Vila Camino</h4>");
        out.println("</body>");
        out.println("</html>");


    }



     
    public static void Fase2(PrintWriter out, ArrayList<Author> autores, Country pais) {  //lista autores con libros en un pais


        out.println("<html>");
        out.println("<head>");
        out.println("<meta charset=\"utf-8\">");
        out.println("<link rel=\"stylesheet\" href=\"./p2/p2.css\">");
        out.println("<title>Servicio de consulta de libros</title>");
        out.println("</head>");
        out.println("<body>");
        out.println("<h1>Servicio de consulta de información de libros</h1>");
        out.println("<h2>Consulta: Fase 2 (Consultando informacion de país ="+pais.getNombre()+")</h2>"); 
        out.println("<h2>Selecciona un autor</h2>");

        out.println("<ol>"); //comienzo lista ordenada

        for (int i=0; i < autores.size(); i++){
            Author autoractual = autores.get(i);
            out.println("<li><a class='disponible' href=\"sint21/P2Lib?fase=3%autor=" + autoractual.getidentificador()+ "\">" + autoractual.getNombre() + "</a> Nacido en: "+ autoractual.getNacimiento()+ "</li>");
       
        }

        out.println("</ol>"); //final lista ordenada
        
        out.println("<form id=\"form\" action=\"/sint21/P2Lib?fase=1\">");
        out.println("<input type=\"submit\" value=\"Atrás\" method=\"get\"></input>");
        out.println("<input type=\"hidden\" value=1 name=\"fase\"></input>");

        out.println("</form>");

        out.println("<br>");
        out.println("<hr>");
        out.println("<h4>Autor: Samuel Vila Camino</h4>");
        out.println("</body>");
        out.println("</html>");


    }



    public static void Fase3(PrintWriter out,Country pais,Author autor, ArrayList<Book> libros) {  //lista libros de un autor


        out.println("<html>");
        out.println("<head>");
        out.println("<meta charset=\"utf-8\">");
        out.println("<link rel=\"stylesheet\" href=\"./p2/p2.css\">");
        out.println("<title>Servicio de consulta de libros</title>");
        out.println("</head>");
        out.println("<body>");
        out.println("<h1>Servicio de consulta de información de libros</h1>");
        out.println("<h2>Consulta: Fase 3 (Consultando informacion de país ="+pais.getNombre()+")</h2>");
         out.println("<h2>(Consultando informacion de autor ="+autor.getNombre()+")</h2>");

        out.println("<h3>Lista de libros</h3>");
        out.println("<ol>");  //comienzo lista ordenada

        for (int i=0; i<libros.size(); i++){
            Book libroactual = libros.get(i);
            if(libroactual.getdisponible().compareTo("no")!=0) { out.println("<li><a class='disponible'>"+libroactual.getNombre()+"</a> </li>"); }
            else {out.println("<li><a class='nodisponible'>"+libroactual.getNombre()+"</a> </li>");}
        }
        
        out.println("</ol>"); //final lista ordenada

        out.println("<form id=\"form\" action=\"/sint21/P2Lib\">");
        out.println("<input type=\"submit\" value=\"Atrás\" method=\"get\"></input>");
        out.println("<input type=\"hidden\" value=2 name=\"fase\"></input>");

        out.println("</form>");
        out.println("<br>");
        out.println("<hr>");
        out.println("<h4>Autor: Samuel Vila Camino</h4>");
        out.println("</body>");
        out.println("</html>");


    
    }

}