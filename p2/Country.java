package p2;



public class Country implements Comparable<Country>{

        
        private String nombre;
        private String identificador;


        public Country(String identificador, String nombre){
            this.identificador=identificador;
            this.nombre=nombre;
        }


    //GETTERS
        public String getNombre(){
            return nombre;
        }

        public String getidentificador(){
            return identificador;
        }

    //COMPARE

       public int compareTo(Country pais){
        int comparacion=this.getidentificador().compareTo(pais.getidentificador());
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