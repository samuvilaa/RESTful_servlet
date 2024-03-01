package p2;


public class Author implements Comparable<Author>{

        
        private String nombre;
        private String identificador;
        private String nacimiento;
        private String pais;


        public Author(String nombre, String identificador, String nacimiento, String pais){
           
            this.nombre=nombre;
            this.identificador=identificador;
            this.nacimiento=nacimiento;
            this.pais=pais;
    
        }

        /* Getters */
       public String getNombre(){
           return nombre;
        }   

        public String getidentificador(){
            return identificador;
        }

        public String getNacimiento(){
            return nacimiento;
        }

        public String getPais(){
            return pais;
        }
   
    // COMPARE

    public int compareTo(Author autor){
        int comparacion=this.getidentificador().compareTo(autor.getidentificador());
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


    
