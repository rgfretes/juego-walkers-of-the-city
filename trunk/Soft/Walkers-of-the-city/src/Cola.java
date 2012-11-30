


public class Cola {

    private final int MAXIMO = 100;
    private auto[] V;
    private int inicio;
    private int fin;
    private int prioridad;

    public int getPrioridad() {
		return prioridad;
	}

	public Cola(int prior) {
        this.V = new auto[MAXIMO + 1];
        this.inicio = 0;
        this.fin = 0;
        this.prioridad=prior;
    }

    public boolean esVacia() {
        return inicio == fin;
    }

    public boolean esLlena() {
        return tamanio() == MAXIMO - 1;
    }

    public void adicionar(auto a) {
        if (esLlena()) {
            System.out.println("Cola Llena! No se pudo adicionar.");
        } else {
            fin = (fin + 1) % MAXIMO;
            V[fin] = a;
        }
    }
    public auto get_primer(){
    	auto a =null;
    	if (esVacia()) {
            System.out.println("Cola Vacia! No se pudo eliminar.");
        } else {
            
            a = V[(inicio + 1) % MAXIMO];
        }
    	
		return a;
    	
    }

    public auto eliminar() {
        auto a = null;
        if (esVacia()) {
            System.out.println("Cola Vacia! No se pudo eliminar.");
        } else {
            inicio = (inicio + 1) % MAXIMO;
            a = V[inicio];
        }
        return a;
    }

    public int tamanio() {
        return (fin - inicio + MAXIMO) % MAXIMO;
    }

  
   

 
}