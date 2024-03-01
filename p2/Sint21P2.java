package p2;  //lo pondremos en un pck comun todo luego
import p2.*;

import java.util.*;
import java.io.*;
import java.net.URL;


import jakarta.servlet.*;
import jakarta.servlet.http.*;

import org.json.*;



public class Sint21P2 extends HttpServlet {


    //private final static String documento_inicial = "https://manolo.webs.uvigo.gal/SINT/libreria.xml";


    public void init(){

        try{
        DataModel.parser();
        
        }catch(Exception e1){
            e1.printStackTrace();
            }

    }

    public void doGet(HttpServletRequest request, HttpServletResponse response){ 

        try{

        
            response.setContentType("text/html");
            response.setCharacterEncoding("utf-8");

            PrintWriter out = response.getWriter();

            String url = request.getRequestURI();

            int APIrest = url.indexOf("/v1/"); //si tiene "v1", hay que hacer api rest. Devuelve -1 si no hay esa cadena.

            if (APIrest==-1){

                int fase;
                try{
                    fase = Integer.parseInt(request.getParameter("fase"));
                }catch(Exception error){ System.out.println("no hay fase asi que empiezo en la 0");}

                switch(fase){
                    case 0:
                        String fichero = DataModel.sacarfichero();
                        String IPserver = request.getLocalAddr();
                        String IPcliente = request.getRemoteAddr();
                        String browser = request.getHeader("User-Agent");
                        FrontEnd.Fase0(out,fichero,IPcliente,browser,IPserver);
                    break;
                    case 1:
                        ArrayList<Country> paises=DataModel.getCountries();
                        FrontEnd.Fase1(out, paises);
                    break;
                    case 2:
                        String identificador = request.getParameter("pais");
                        Country pais = DataModel.getCountry(identificador);
                        ArrayList<Author> autores = DataModel.getAuthors(identificador);
                        FrontEnd.Fase2(out,autores,pais);
                    break;
                    case 3:
                        String identificador2 = request.getParameter("autor");
                        Author autor = DataModel.getAuthor(identificador2);
                        ArrayList<Book> libros = DataModel.getBooks(identificador2);
                        Country pais2 = DataModel.getCountry(autor.getPais());
                        FrontEnd.Fase3(out,pais,autor,libros);
                    break;

                }

            } 

            else if (APIrest != -1) {

                JSONArray arrayJSON = new JSONArray();

                String[] cadenaAPI = url.split("/v1/"); //separo por v1
                String[] segmentacion = cadenaAPI[1].split("/"); //separo por barras
                int numeroAPI = segmentacion.length;

                if (numeroAPI == 1){

                    if (segmentacion[0].equals("libros")){
                        
                        ArrayList<Book> libros = DataModel.getLibrosSinArgumento();

                        for (int i = 0; i < libros.size(); i++) {

                            Book libro = libros.get(i);

                            JSONObject objetoJSON = new JSONObject();

                            objetoJSON.put("identificador",libro.getidentificador());
                            objetoJSON.put("ISBN",libro.getISBN());
                            objetoJSON.put("autor",libro.getAutor());
                            objetoJSON.put("disponible",libro.getdisponible());
                            objetoJSON.put("titulo",libro.getNombre());

                            arrayJSON.put(objetoJSON);

                        }
                    }

                    if (segmentacion[0].equals("paises")){
                        
                        ArrayList<Country> paises = DataModel.getCountries();

                        for (int i = 0; i < paises.size(); i++) {
                            
                            Country pais = paises.get(i);

                            JSONObject objetoJSON = new JSONObject();
                            objetoJSON.put("identificador",pais.getidentificador());
                            objetoJSON.put("nombre",pais.getNombre());

                            arrayJSON.put(objetoJSON);

                        } 
                    }

                    if (segmentacion[0].equals("autores")){
                        
                        ArrayList<Author> autores = DataModel.getAutoresSinArgumento();

                        for (int i = 0; i < autores.size(); i++) {

                            Author autor = autores.get(i);

                            JSONObject objetoJSON = new JSONObject();

                            objetoJSON.put("identificador",autor.getidentificador());
                            objetoJSON.put("nacimiento",autor.getNacimiento());
                            objetoJSON.put("pais",autor.getPais());
                            objetoJSON.put("nombre",autor.getNombre());

                            arrayJSON.put(objetoJSON);

                        }
                    }
                }
                else if (numeroAPI == 2){

                    if (segmentacion[0].equals("libro")){

                        String identificador = segmentacion[1];
                        Book libro = DataModel.getBook(identificador);
                        String comprobar = libro.toString();


                        if(comprobar.length() == 0){

                            out.println(identificador);
                            out.println(arrayJSON.toString());
                            response.getStatus(404);

                        }else {

                            JSONObject objetoJSON = new JSONObject();

                            objetoJSON.put("identificador",libro.getidentificador());
                            objetoJSON.put("ISBN",libro.getISBN());
                            objetoJSON.put("autor",libro.getAutor());
                            objetoJSON.put("disponible",libro.getdisponible());
                            objetoJSON.put("titulo",libro.getNombre());

                            arrayJSON.put(objetoJSON);
                            out.println(arrayJSON.toString());

                        }
                    }

                    if (segmentacion[0].equals("pais")){

                        String identificador = segmentacion[1];
                        Country pais = DataModel.getCountry(identificador);
                        String comprobar = pais.toString();


                        if(comprobar.length() == 0){

                            out.println(identificador);
                            out.println(arrayJSON.toString());
                            response.getStatus(404);

                        }else {

                            JSONObject objetoJSON = new JSONObject();

                            objetoJSON.put("identificador",pais.getidentificador());
                            objetoJSON.put("nombre",pais.getNombre());

                            arrayJSON.put(objetoJSON);
                            out.println(arrayJSON.toString());

                        }
                    }

                    if (segmentacion[0].equals("autor")){

                        String identificador = segmentacion[1];
                        Author autor = DataModel.getAuthor(identificador);
                        String comprobar = autor.toString();


                        if(comprobar.length() == 0){

                            out.println(identificador);
                            out.println(arrayJSON.toString());
                            response.getStatus(404);

                        }else {

                            JSONObject objetoJSON = new JSONObject();

                            objetoJSON.put("identificador",autor.getidentificador());
                            objetoJSON.put("nacimiento",autor.getNacimiento());
                            objetoJSON.put("pais",autor.getPais());
                            objetoJSON.put("nombre",autor.getNombre());

                            arrayJSON.put(objetoJSON);
                            out.println(arrayJSON.toString());

                        }
                    }
                }

                else if (numeroAPI == 3){
                
                    if (segmentacion[0].equals("libros")&& segmentacion[1].equals("autor")){
                       
                        ArrayList<Book> libros = DataModel.getBooks(segmentacion[3]);

                        if (libros.isEmpty()){

                            out.println(arrayJSON.toString());
                            response.getStatus(404);
                        }

                        else {

                            for (int i = 0; i < libros.size(); i++) {

                            Book libro = libros.get(i);

                            JSONObject objetoJSON = new JSONObject();

                            objetoJSON.put("identificador",libro.getidentificador());
                            objetoJSON.put("ISBN",libro.getISBN());
                            objetoJSON.put("autor",libro.getAutor());
                            objetoJSON.put("disponible",libro.getdisponible());
                            objetoJSON.put("titulo",libro.getNombre());

                            arrayJSON.put(objetoJSON);
                            out.println(arrayJSON.toString());
                            }
                        }
                    }


                    if (segmentacion[0].equals("autores")&& segmentacion[1].equals("pais")){
                       
                        ArrayList<AUthor> autores = DataModel.getAuthors(segmentacion[3]);

                        if (autores.isEmpty()){

                            out.println(arrayJSON.toString());
                            response.getStatus(404);
                        }

                        else {

                            for (int i = 0; i < autores.size(); i++) {

                            Book autor = autores.get(i);

                            JSONObject objetoJSON = new JSONObject();

                            objetoJSON.put("identificador",autor.getidentificador());
                            objetoJSON.put("ISBN",autor.getISBN());
                            objetoJSON.put("autor",autor.getAutor());
                            objetoJSON.put("disponible",autor.getdisponible());
                            objetoJSON.put("titulo",autor.getNombre());

                            arrayJSON.put(objetoJSON);
                            out.println(arrayJSON.toString());
                            }
                        }
                    }
                }
            }

        }catch (Exception errorservlet){
            System.out.println("Fallo algo en el servlet: "+ errorservlet);
        }

    }





}