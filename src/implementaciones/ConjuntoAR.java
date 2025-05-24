package implementaciones;

import interfaces.ConjuntoTDA;

public class ConjuntoAR implements ConjuntoTDA {	
	int[] arr;
	int cant;
	
	@Override
	public void inicializarConjunto() { //O(1)
		arr = new int[20];				
		cant = 0;						
	}

	@Override
	public void agregar(int x) { //O(n)
		if (!this.pertenece(x))	{
			arr[cant] = x;
			cant++;
		}
	}

	@Override
	public void sacar(int x) { //O(n)
		int i = buscar(x);
		if (i != -1) {
			arr[i] = arr[cant-1];
			cant--;
		}
	}

	@Override
	public int elegir() { //O(1)
		double num = Math.random() * cant;
		int inx = (int)Math.floor(num);
		return arr[inx];
	}

	@Override
	public boolean pertenece(int x) { //O(n)
		int i = buscar(x);
		return (i != -1);
	}

	@Override
	public boolean conjuntoVacio() { //O(1)
		return (cant == 0);
	}
	
	private int buscar(int x) { //O(n)
		int i = cant-1;
		while (i >= 0 && arr[i] != x)
			i--;
		return i;
	}
}