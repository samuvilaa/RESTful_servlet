package p2;




public class Book implements Comparable<Book>{

        private String identificador;
        private String disponible;
        private String autor;
        private String nombre;
        private String ISBN;

        public Book(){ //Constructor vacío

        }
     
        //Constructor
        public Book(String identificador,/* String disponible,*/ String autor, String ISBN, String nombre){
            this.identificador = identificador;
            //this.disponible = disponible;
            this.autor = autor;
            this.ISBN = ISBN;
            this.nombre = nombre;
            
        }

        /* Getters */
        public String getNombre(){
            return nombre;
        }

        public String getAutor(){
            return autor;
        }

        public String getidentificador(){
            return identificador;
        }

        public String getISBN(){
            return ISBN;
        }

        public String getdisponible(){
            return disponible;
        }

        
        /* SETTERS */

        public void setdisponible(String newdisponible){
            this.disponible=newdisponible;
        }


        public int compareTo(Book libro){
            int comparacion=this.getidentificador().compareTo(libro.getidentificador());
            if(comparacion>0){
                comparacion=1;
            }
            else if (comparacion<0){
                comparacion=-1;
            }
            else comparacion=0;
            return comparacion;
        }

    }
