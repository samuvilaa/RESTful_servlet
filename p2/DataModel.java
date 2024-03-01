package p2;

import javax.xml.parsers.*;
import org.w3c.dom.*;
import org.xml.sax.*;
import javax.xml.parsers.*;
import java.io.*;
import java.lang.reflect.Array;
import java.util.*;
import java.net.URL;

import p2.*;


public class DataModel {

    private final static String documento_inicial = "https://manolo.webs.uvigo.gal/SINT/libreria.xml";
    private static String file;
    public static NodeList libroN;
    public static NodeList paisN;
    public static NodeList autorN;


    public static void parser() throws Exception,SAXParseException{
       

        try{
            DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = builderFactory.newDocumentBuilder();

            Document document = builder.parse(documento_inicial);
            
           
            Element libreria = document.getDocumentElement(); 
            libroN = libreria.getElementsByTagName("libro");
            paisN = libreria.getElementsByTagName("pais");
            autorN = libreria.getElementsByTagName("autor");


        }catch(SAXException | IOException e){ e.printStackTrace();}
             
    
    }
    public static String sacarfichero(){
        String[] direcciontrozos = documento_inicial.split("/");
        String fichero = direcciontrozos[(direcciontrozos.length - 1)];
        return fichero;
    }

    public static String espacios (String cadena){
        return cadena.replaceAll("\\s+"," ");  //es importante en esta parte del mapa DOM quitar los espacios.
    }

    public static ArrayList<Book> getBooks(String IDautor){
        ArrayList <Book> libros = new ArrayList<Book>();
        Element libro;
        String nombre;
        String autor;
        String ISBN;
        String identificador;
        String disponible;

        for (int i=0; i<libroN.getLength(); i++){
            libro = (Element) libroN.item(i);

            if (libro.getAttribute("autor").compareTo(IDautor)==0){  //si coincide el id de autor con el del elemento:
                nombre=espacios(libro.getTextContent());
                autor=libro.getAttribute("autor");
                ISBN=libro.getAttribute("ISBN");
                identificador=libro.getAttribute("identificador");

                Book libronuevo = new Book (identificador, ISBN, autor, nombre);
                try{
                    disponible=libro.getAttribute("disponible");
                    libronuevo.setdisponible(disponible);
                }catch (Exception nohaydisponible){} 

                libros.add(libronuevo);
            }
        }
        Collections.sort(libros);
        return libros;
    }

    public static ArrayList<Author> getAuthors(String IDpais){
        ArrayList <Author> autores = new ArrayList<Author>();
        Element autor;
        String nombre;
        String pais;
        String nacimiento;
        String identificador;

        for (int i=0; i<autorN.getLength(); i++){
            autor = (Element) autorN.item(i);

            if (autor.getAttribute("pais").compareTo(IDpais)==0){  //si coincide el id de autor con el del elemento:
                nombre=espacios(autor.getTextContent());
                pais=autor.getAttribute("pais");
                nacimiento=autor.getAttribute("nacimiento");
                identificador=autor.getAttribute("identificador");

                Author autornuevo = new Author (nombre, identificador, nacimiento, pais);
                
                autores.add(autornuevo);
            }
        }
        Collections.sort(autores);
        return autores;
    }



    public static ArrayList<Country> getCountries(){
        ArrayList <Country> paises = new ArrayList<Country>();
        Element pais;
        String nombre;
        String identificador;

        for (int i=0; i<paisN.getLength(); i++){
            pais = (Element) paisN.item(i);
            nombre=espacios(pais.getTextContent());
            identificador=pais.getAttribute("identificador");

            Country autornuevo = new Country(nombre, identificador);
                
            paises.add(autornuevo);
            }
        
        Collections.sort(paises);
        return paises;
    }



    public static Author getAuthor(String IDautor){
        Element autor=null;
  
        for (int i=0; i< autorN.getLength(); i++){
  
           autor=(Element)autorN.item(i);
           if(autor.getAttribute("identificador").compareTo(IDautor)==0){
              break;
           }
        }
  
        String nombre=espacios(autor.getTextContent());
        String pais=autor.getAttribute("pais");
        String nacimiento=autor.getAttribute("nacimiento");
        String identificador=autor.getAttribute("identificador");
  
        Author autorenviar = new Author(nombre, identificador, nacimiento, pais);
        return autorenviar;
    }

     public static Country getCountry(String IDpais){
        Element pais=null;
  
        for (int i=0; i< paisN.getLength(); i++){
  
           pais=(Element)paisN.item(i);
           if(pais.getAttribute("identificador").compareTo(IDpais)==0){
              break;
           }
        }
  
        String nombre=espacios(pais.getTextContent());
        String identificador=pais.getAttribute("identificador");
  
        Country paisenviar = new Country(nombre, identificador);
        return paisenviar;
    }


    public static Book getBook(String IDlibro){
        Element libro=null;
  
        for (int i=0; i< libroN.getLength(); i++){
           libro=(Element)libroN.item(i);
  
           if(libro.getAttribute("identificador").compareTo(IDlibro)==0){
              break;
           }
        }
  
        String nombre=espacios(libro.getTextContent());
        String autor=libro.getAttribute("autor");
        String ISBN=libro.getAttribute("ISBN");
        String identificador=libro.getAttribute("identificador");
        Book libro2=new Book(identificador, autor, ISBN, nombre);
  
        try{
           String disponible=libro.getAttribute("disponible");
           libro2.setdisponible(disponible);      
        }
        catch(Exception e){};
  
        return libro2;
     }

     public static ArrayList<Book> getLibrosSinArgumento(){
        ArrayList<Book> libros=new ArrayList<Book>();
        Element libro;
        String nombre;
        String autor;
        String ISBN;
        String identificador;
        String disponible;
  
        for (int i=0; i< libroN.getLength(); i++){
           libro= (Element)libroN.item(i);
  
           nombre=espacios(libro.getTextContent());
           autor=libro.getAttribute("autor");;
           ISBN=libro.getAttribute("ISBN");;
           identificador=libro.getAttribute("identificador");
           Book libro2=new Book(identificador, autor, ISBN, nombre);
  
           try{
              disponible=libro.getAttribute("disponible");
              libro2.setdisponible(disponible);
           }
           catch(Exception e){}
  
           libros.add(libro2);
        
        }
        Collections.sort(libros);
        return libros;
    }
  
    public static ArrayList<Author> getAuthorsEmpty(){
  
        ArrayList<Author> authorsList=new ArrayList<Author>();
        Element author;
        String name, country, birth, id;
  
        for (int i=0; i< autor.getLength(); i++){
  
              author= (Element)autor.item(i);
  
              name=quitarEspacios(author.getTextContent());
              country=author.getAttribute("pais");
              birth=author.getAttribute("nacimiento");
              id=author.getAttribute("identificador");
              authorsList.add(new Author(id, birth, country, name));
        }
  
        Collections.sort(authorsList);
  
        return authorsList;
     } 
































































































}




































