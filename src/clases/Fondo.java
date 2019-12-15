package clases;

import implementacion.Juego;
import javafx.scene.canvas.GraphicsContext;

public class Fondo extends Coordenadas{
	private int velocidad;
	private String indiceImagen;

	public Fondo(int x, int y, int velocidad, String indiceImagen) {
		super(x, y);
		this.velocidad = velocidad;
		this.indiceImagen = indiceImagen;
	}

	public int getVelocidad() {
		return velocidad;
	}
	public void setVelocidad(int velocidad) {
		this.velocidad = velocidad;
	}
	public String getIndiceImagen() {
		return indiceImagen;
	}
	public void setIndiceImagen(String indiceImagen) {
		this.indiceImagen = indiceImagen;
	}

	public void pintar(GraphicsContext graficos) {
		graficos.drawImage(Juego.imagenes.get(indiceImagen), x, y);
		graficos.drawImage(Juego.imagenes.get(indiceImagen), x, y);
	}
	public void mover() {
		if (Juego.accion) {
			this.velocidad = 2;
		}else {
			this.velocidad = 1;
		}
		if (Juego.derecha)
			x-=velocidad;

		if (Juego.izquierda)
			x+=velocidad;
	}
}